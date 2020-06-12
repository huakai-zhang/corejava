package com.spring.dubbo.main;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.container.Main;
import com.alibaba.dubbo.rpc.Protocol;

/**
 * @author Spring 花开不合阳春暮127
 * @since 2020/5/8
 */
public class Bootstrap {
    public static void main(String[] args) {
        Main.main(args);
        //Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myprotocol");
        //System.out.println(protocol.getDefaultPort());
    }
}
