package com.ali.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author 春阳
 * @date 2021-02-26 16:06
 */
public class NacosSdkDemo {
    private static final String SERVER_ADDR = "localhost:8848";
    private static final String DATA_ID = "spring";
    private static final String GROUP_ID = "DEFAULT_GROUP";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("serverAddr", SERVER_ADDR);

        try {
            ConfigService configService = NacosFactory.createConfigService(properties);

            configService.addListener(DATA_ID, GROUP_ID, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("配置改变==》" + configInfo);
                }
            });

            configService.addListener("xiaoxiao", GROUP_ID, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("配置改变==》" + configInfo);
                }
            });

            String info = configService.getConfig(DATA_ID, GROUP_ID, 3000);
            System.out.println(info);

            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
