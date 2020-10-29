package com.spring.design.strategy.op_code;

public enum ColorStrategyEnum {
    BLACK {
        @Override
        void doSomeThing() {
            System.out.println("我是黑色");
        }
    },
    RED {
        @Override
        void doSomeThing() {
            System.out.println("我是红色");
        }
    },
    BLUE {
        @Override
        void doSomeThing() {
            System.out.println("我是蓝色");
        }
    };
    abstract void doSomeThing();
}
