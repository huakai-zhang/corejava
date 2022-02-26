package com.ali.slot;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.node.DefaultNode;
import com.alibaba.csp.sentinel.slotchain.AbstractLinkedProcessorSlot;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import com.alibaba.csp.sentinel.spi.Spi;

/**
 * @author 春阳
 * @date 2021-03-02 17:37
 */
@Spi(isSingleton = false, order = -10001)
public class MySlot extends AbstractLinkedProcessorSlot<DefaultNode> {

    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode param, int count, boolean prioritized, Object... args) throws Throwable {
        System.out.println("======MySlot======");
        fireEntry(context, resourceWrapper, param, count, prioritized, args);
    }

    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {

    }
}
