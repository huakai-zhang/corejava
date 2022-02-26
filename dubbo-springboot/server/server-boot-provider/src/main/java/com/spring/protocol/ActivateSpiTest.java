package com.spring.protocol;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Filter;

import java.util.List;

/**
 * @author 春阳
 * @date 2021-01-26 12:12
 */
public class ActivateSpiTest {
    public static void main(String[] args) {
        ExtensionLoader<Filter> extensionLoader = ExtensionLoader.getExtensionLoader(Filter.class);
        URL url = new URL("", "", 0);
        url= url.addParameter("cache", "cache");
        List<Filter> cache = extensionLoader.getActivateExtension(url, "cache");
        System.out.println(cache.size());
    }
}
