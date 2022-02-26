package com.spring.design.delegate;

/**
 * @author Spring Zhang
 * @date 2020/3/5 10:30
 */
public class Dispatcher implements IDelegate {

    IDelegate delegate;

    Dispatcher(IDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * 项目经理虽然也有执行方法
     * 但是工作职责不一样
     */
    @Override
    public void doing() {
        this.delegate.doing();
    }
}
