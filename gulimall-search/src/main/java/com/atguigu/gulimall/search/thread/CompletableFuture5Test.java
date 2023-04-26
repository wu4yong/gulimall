package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试CompletableFuture 两个任务组合都要完成
 * 两个任务必须都完成， 触发该任务。
 * thenCombine： 组合两个 future， 获取两个 future 的返回结果， 并返回当前任务的返回值
 * thenAcceptBoth： 组合两个 future， 获取两个 future 任务的返回结果， 然后处理任务， 没有返回值。
 * runAfterBoth： 组合两个 future， 不需要获取 future 的结果， 只需两个 future 处理完任务后，处理该任务。
 *
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
public class CompletableFuture5Test {


    // 当前系统中池只有一两个，每个异步任务，提交给线程池
    public static ExecutorService executor = Executors.newFixedThreadPool(10);


    /**
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 任务一
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("completableFuture1线程启动：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("completableFuture1线程结束：");
            return i;
        }, executor);

        // 任务二
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("completableFuture2线程启动：" + Thread.currentThread().getId());
            System.out.println("completableFuture2线程结束：");
            return "hello";
        }, executor);

        // 任务三 runAfterBothAsync
//        completableFuture1.runAfterBothAsync(completableFuture2, () -> {
//            System.out.println("任务三启动：" + Thread.currentThread().getId());
//        }, executor);


        // 任务三  thenAcceptBothAsync
        completableFuture1.thenAcceptBothAsync(completableFuture2, (f1, f2) -> {
            System.out.println("任务三启动：" + Thread.currentThread().getId());
            System.out.println("completableFuture1的结果是:" + f1);
            System.out.println("completableFuture2的结果是:" + f2);
        }, executor);

        // 任务三  thenCombineAsync
        CompletableFuture<String> task3 = completableFuture1.thenCombineAsync(completableFuture2, (f1, f2) -> {
            System.out.println("任务三启动：" + Thread.currentThread().getId());
            System.out.println("completableFuture1的结果是:" + f1);
            System.out.println("completableFuture2的结果是:" + f2);
            return f2 + ":" + f1;
        }, executor);

        System.out.println("task3的结果是:"+task3.get());

    }

}
