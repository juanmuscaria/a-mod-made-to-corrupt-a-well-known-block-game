package com.juanmuscaria.nuke.ui;

import lombok.SneakyThrows;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class Events {
    @SneakyThrows
    public static void invokeAndAwait(Runnable r) {
        EventQueue.invokeAndWait(r);
    }
    @SneakyThrows
    public static <T> T invokeAndAwait(Supplier<T> r) {
        var ret = new AtomicReference<T>();
        EventQueue.invokeAndWait(() -> ret.set(r.get()));
        return ret.get();
    }
}
