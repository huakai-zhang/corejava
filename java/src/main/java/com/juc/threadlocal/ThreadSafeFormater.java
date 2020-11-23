package com.juc.threadlocal;

import java.text.SimpleDateFormat;

public class ThreadSafeFormater {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));
}
