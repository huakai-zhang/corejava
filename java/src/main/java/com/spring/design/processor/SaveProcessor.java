package com.spring.design.processor;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 春阳
 * @date 2020-12-28 13:21
 */
public class SaveProcessor extends Thread implements RequestProcessor {

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();

    @Override
    public void run() {
        while (true) {
            try {
                Request request=requests.take();
                System.out.println("begin save request info:"+request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }
}
