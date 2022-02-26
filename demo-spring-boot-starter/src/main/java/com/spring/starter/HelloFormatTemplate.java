package com.spring.starter;

import com.spring.starter.autoconfiguration.HelloProperties;
import com.spring.starter.format.FormatProcessor;

/**
 * @author 春阳
 * @date 2020-12-21 17:31
 */
public class HelloFormatTemplate {
    private FormatProcessor formatProcessor;

    private HelloProperties helloProperties;

    public HelloFormatTemplate(HelloProperties helloProperties,FormatProcessor formatProcessor) {
        this.helloProperties=helloProperties;
        this.formatProcessor = formatProcessor;
    }

    public <T> String doFormat(T obj){

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("begin:Execute format").append("<br/>");
        stringBuilder.append("HelloProperties:").append(formatProcessor.format(helloProperties.getInfo())).append("<br/>");
        stringBuilder.append("Obj format result:").append(formatProcessor.format(obj)).append("<br/>");
        return stringBuilder.toString();

    }
}
