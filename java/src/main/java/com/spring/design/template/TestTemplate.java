package com.spring.design.template;

/**
 * @author Spring Zhang
 * @date 2020/3/5 17:55
 */
public class TestTemplate {
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        coffee.create();

        Tea tea = new Tea();
        tea.create();
    }

    // Spring JDBC就是一个模版模式
    // 是Java规范，各个数据库厂商自己去实现
    // 1.加载驱动类DriverManager
    // 建立连接
    // 创建语句集（标准语句集，预处理语句集）
    // 执行语句集
    // 结果集ResultSet 游标
}
