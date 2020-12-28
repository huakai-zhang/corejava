package com.spring.design.processor;

/**
 * @author 春阳
 * @date 2020-12-28 13:22
 */
public class ProcessorDemo {

    PrintProcessor printProcessor;

    protected ProcessorDemo() {
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
    }

    private void doTest(Request request) {
        printProcessor.processRequest(request);
    }

    public static void main(String[] args) throws InterruptedException {
        Request request = new Request();
        ProcessorDemo processorDemo = new ProcessorDemo();
        for (int i = 0; i < 3; i++) {
            request.setName("Xiaoxiao:" + i);
            processorDemo.doTest(request);
            Thread.sleep(2000);
        }
    }
}