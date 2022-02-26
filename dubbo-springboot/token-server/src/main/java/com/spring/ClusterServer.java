package com.spring;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

import java.util.Collections;

/**
 * @author 春阳
 * @date 2021-03-04 18:55
 */
public class ClusterServer {
    public static void main(String[] args) throws Exception {
        ClusterTokenServer tokenServer=new SentinelDefaultTokenServer();
        ClusterServerConfigManager.loadGlobalTransportConfig(
                new ServerTransportConfig().setIdleSeconds(600).setPort(9999));
        //设置成动态
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("server-boot-provider"));
        tokenServer.start();
    }
}
