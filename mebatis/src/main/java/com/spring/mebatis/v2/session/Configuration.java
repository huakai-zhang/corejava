package com.spring.mebatis.v2.session;

import com.mysql.jdbc.StringUtils;
import com.spring.mebatis.v2.TestMybatis;
import com.spring.mebatis.v2.annotation.Entity;
import com.spring.mebatis.v2.annotation.Select;
import com.spring.mebatis.v2.binding.MapperRegistry;
import com.spring.mebatis.v2.executor.CachingExecutor;
import com.spring.mebatis.v2.executor.Executor;
import com.spring.mebatis.v2.executor.SimpleExecutor;
import com.spring.mebatis.v2.plugin.Interceptor;
import com.spring.mebatis.v2.plugin.InterceptorChain;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author 春阳
 * @date 2020-12-11 14:33
 */
public class Configuration {
    public static final ResourceBundle sqlMappings; // SQL映射关系配置，使用注解时不用重复配置
    public static final ResourceBundle properties; // 全局配置
    public static final MapperRegistry mapperRegistry = new MapperRegistry(); // 维护接口与工厂类关系
    public static final Map<String, String> mappedStatements = new HashMap<>(); // 维护接口方法与SQL关系

    private InterceptorChain interceptorChain = new InterceptorChain(); // 插件
    private List<Class<?>> mapperList = new ArrayList<>(); // 所有Mapper接口
    private List<String> classPaths = new ArrayList<>(); // 类所有文件

    static{
        sqlMappings = ResourceBundle.getBundle("sql");
        properties = ResourceBundle.getBundle("mybatis");
    }

    /**
     * 初始化时解析全局配置文件
     */
    public Configuration()  {
        // Note：在properties和注解中重复配置SQL会覆盖
        // 1.解析sql.properties
        for(String key : sqlMappings.keySet()){
            Class mapper = null;
            String statement = null;
            String pojoStr = null;
            Class pojo = null;
            // properties中的value用--隔开，第一个是SQL语句
            statement = sqlMappings.getString(key).split("--")[0];
            // properties中的value用--隔开，第二个是需要转换的POJO类型
            pojoStr = sqlMappings.getString(key).split("--")[1];
            try {
                // properties中的key是接口类型+方法
                // 从接口类型+方法中截取接口类型
                mapper = Class.forName(key.substring(0,key.lastIndexOf(".")));
                pojo = Class.forName(pojoStr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            mapperRegistry.addMapper(mapper, pojo); // 接口与返回的实体类关系
            mappedStatements.put(key, statement); // 接口方法与SQL关系
        }

        // 2.解析Mapper接口配置，扫描注册
        String mapperPath = properties.getString("mapper.path");
        scanPackage(mapperPath);
        for (Class<?> mapper : mapperList) {
            parsingClass(mapper);
        }
        // 3.解析插件，可配置多个插件
        String pluginPathValue = properties.getString("plugin.path");
        if (!StringUtils.isNullOrEmpty(pluginPathValue)) {
            String[] pluginPaths = pluginPathValue.split(",");
            // 将插件添加到interceptorChain中
            for (String plugin : pluginPaths) {
                Interceptor interceptor = null;
                try {
                    interceptor = (Interceptor) Class.forName(plugin).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                interceptorChain.addInterceptor(interceptor);
            }
        }
    }

    /**
     * 根据statement判断是否存在映射的SQL
     * @param statementName
     * @return
     */
    public boolean hasStatement(String statementName) {
        return mappedStatements.containsKey(statementName);
    }

    /**
     * 根据statement ID获取SQL
     * @param id
     * @return
     */
    public String getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public <T> T getMapper(Class<T> clazz, DefaultSqlSession sqlSession) {
        return mapperRegistry.getMapper(clazz, sqlSession);
    }

    /**
     * 创建执行器，当开启缓存时使用缓存装饰
     * 当配置插件时，使用插件代理
     * @return
     */
    public Executor newExecutor() {
        Executor executor = null;
        if (properties.getString("cache.enabled").equals("true")) {
            executor = new CachingExecutor(new SimpleExecutor());
        }else{
            executor = new SimpleExecutor();
        }

        // 目前只拦截了Executor，所有的插件都对Executor进行代理，没有对拦截类和方法签名进行判断
        if (interceptorChain.hasPlugin()) {
            return (Executor)interceptorChain.pluginAll(executor);
        }
        return executor;
    }

    /**
     * 解析Mapper接口上配置的注解（SQL语句）
     */
    private void parsingClass(Class<?> mapper) {
        // 1.解析类上的注解
        // 如果有@Entity注解，说明是查询数据库的接口
        if (mapper.isAnnotationPresent(Entity.class)) {
            for (Annotation annotation : mapper.getAnnotations()) {
                if (annotation.annotationType().equals(Entity.class)) {
                    // 注册接口与实体类的映射关系
                    mapperRegistry.addMapper(mapper, ((Entity)annotation).value());
                }
            }
        }

        // 2.解析方法上的注解
        Method[] methods = mapper.getMethods();
        for (Method method : methods) {
            // 解析@Select注解的SQL语句
            if (method.isAnnotationPresent(Select.class)) {
                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    if (annotation.annotationType().equals(Select.class)) {
                        // 注册接口类型+方法名和SQL语句的映射关系
                        String statement = method.getDeclaringClass().getName() + "." +method.getName();
                        mappedStatements.put(statement, ((Select) annotation).value());
                    }
                }
            }
        }
    }

    /**
     * 根据全局配置文件的Mapper接口路径，扫描所有接口
     */
    private void scanPackage(String mapperPath) {
        String classPath = TestMybatis.class.getResource("/").getPath();
        mapperPath = mapperPath.replace(".", File.separator);
        String mainPath = classPath + mapperPath;
        doPath(new File(mainPath));
        for (String className : classPaths) {
            className = className.replace(classPath.replace("/","\\").replaceFirst("\\\\",""),"").replace("\\",".").replace(".class","");
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(clazz.isInterface()){
                mapperList.add(clazz);
            }

        }
    }

    /**
     * 获取文件或文件夹下所有的类.class
     */
    private void doPath(File file) {
        // 文件夹，遍历
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f1 : files) {
                doPath(f1);
            }
        } else {
            // 文件，直接添加
            if (file.getName().endsWith(".class")) {
                classPaths.add(file.getPath());
            }
        }
    }
}
