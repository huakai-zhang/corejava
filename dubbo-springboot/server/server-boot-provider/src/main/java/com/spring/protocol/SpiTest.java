package com.spring.protocol;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

/**
 * @author 春阳
 * @date 2021-01-26 11:05
 */
public class SpiTest {
    public static void main(String[] args) {
        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myProtocol");
        System.out.println(protocol.getDefaultPort());
    }
}
