package com.spring.protocol;

import org.apache.dubbo.common.compiler.Compiler;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.registry.RegistryFactory;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.ProxyFactory;

/**
 * @author 春阳
 * @date 2021-01-26 11:38
 */
public class AdaptiveSpiTest {
    public static void main(String[] args) {
        RegistryFactory compiler= ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        System.out.println(compiler.getClass());
        // class org.apache.dubbo.common.compiler.support.AdaptiveCompiler
    }
}
