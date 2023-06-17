package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest2 {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // runAsync();
        // int i = supplyAsync();
        // System.out.println(i);

        // int handle = handle();
        // System.out.println(handle);
        // thenRunAsync();
        //thenAcceptAsync();
        //Integer integer = thenApplyAsync();
        //System.out.println(integer);
        //runAfterBothAsync();
        thenAcceptBothAsync();

    }

    // CompletableFuture 无返回值
    private static void runAsync() {
        System.out.println("main......start.....");
        CompletableFuture.runAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }, executorService);
        System.out.println("main......end.....");
    }

    // CompletableFuture 含返回值
    private static int supplyAsync() throws ExecutionException, InterruptedException {
        System.out.println("main......start.....");
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 18 / 3;
            return i;
        }, executorService).whenComplete((result, exception) -> {
            System.out.println("异步任务完成了，结果是:" + result + "; 异常是：" + exception);
        }).exceptionally(throwable -> 888);
        return completableFuture.get();
    }

    // CompletableFuture handle  方法执行完成后的处理
    private static int handle() throws ExecutionException, InterruptedException {
        System.out.println("main......start.....");
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 18 / 0;
            return i;
        }, executorService).handle((result, exception) -> {
            if (result != null) {
                return result * 2;
            }
            if (exception != null) {
                return -1;
            }
            return 0;
        });
        return completableFuture.get();
    }

    // CompletableFuture thenRunAsync
    private static void thenRunAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 18 / 3;
            return i;
        }, executorService).thenRunAsync(() -> {
            System.out.println("任务2启动了。。。。。");
        }, executorService);
    }

    // CompletableFuture thenAcceptAsync
    private static void thenAcceptAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 18 / 2;
            return i;
        }, executorService).thenAcceptAsync((result) -> {
            System.out.println("任务2启动了。。。。。" + result);
        }, executorService);
    }


    // CompletableFuture thenAcceptAsync
    private static Integer thenApplyAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 28 / 4;
            return i;
        }, executorService).thenApplyAsync((result) -> {
            System.out.println("任务2启动了。。。。。" + result);
            return result;
        }, executorService);

        return completableFuture.get();
    }

    // 两个都完成-- 不能感知结果
    private static void runAfterBothAsync() {
        CompletableFuture<Integer> feature01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程开始:" + Thread.currentThread().getId());
            int i = 28 / 4;
            System.out.println("任务1线程结束:" + Thread.currentThread().getId());
            return i;
        }, executorService);

        CompletableFuture<String> feature02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程开始:" + Thread.currentThread().getId());
            int i = 28 / 4;
            System.out.println("任务2线程结束:" + Thread.currentThread().getId());
            return "任务2执行返回结果";
        }, executorService);

        feature01.runAfterBothAsync(feature02,()->{
            System.out.println("任务3线程开始。。。。。。。。");
        },executorService);
    }

    // 两个都完成-- 感知结果
    private static void thenAcceptBothAsync() {
        CompletableFuture<Integer> feature01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程开始:" + Thread.currentThread().getId());
            int i = 28 / 4;
            System.out.println("任务1线程结束:" + Thread.currentThread().getId());
            return i;
        }, executorService);

        CompletableFuture<String> feature02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程开始:" + Thread.currentThread().getId());
            int i = 28 / 4;
            System.out.println("任务2线程结束:" + Thread.currentThread().getId());
            return "任务2执行返回结果";
        }, executorService);

        feature01.thenAcceptBothAsync(feature02,(f1,f2)->{
            System.out.println("任务3线程开始。。。。。。。。");
            System.out.println("f1的结果是: " + f1);
            System.out.println("f2的结果是: " + f2);
        },executorService);
    }
}
