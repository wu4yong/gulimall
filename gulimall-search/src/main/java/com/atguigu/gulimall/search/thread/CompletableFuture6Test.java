package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试CompletableFuture 两个任务组合都要完成
 * 当两个任务中， 任意一个 future 任务完成的时候， 执行任务。
 * applyToEither： 两个任务有一个执行完成， 获取它的返回值， 处理任务并有新的返回值。
 * acceptEither： 两个任务有一个执行完成， 获取它的返回值， 处理任务， 没有新的返回值。
 * runAfterEither： 两个任务有一个执行完成， 不需要获取 future 的结果， 处理任务， 也没有返
 * 回值。
 *
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
public class CompletableFuture6Test {


    // 当前系统中池只有一两个，每个异步任务，提交给线程池
    public static ExecutorService executor = Executors.newFixedThreadPool(10);


    /**
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 任务一
//        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("completableFuture1线程启动：" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("completableFuture1线程结束：");
//            return i;
//        }, executor);
//
//        // 任务二
//        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("completableFuture2线程启动：" + Thread.currentThread().getId());
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("completableFuture2线程结束：");
//            return "hello";
//        }, executor);

//        // 任务三 runAfterEitherAsync
//        completableFuture1.runAfterEitherAsync(completableFuture2, () -> {
//            System.out.println("任务三启动：" + Thread.currentThread().getId());
//        }, executor);


        /**-----------------------------------------------------------*/
        CompletableFuture<Object> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("completableFuture1线程启动：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("completableFuture1线程结束：");
            return i;
        }, executor);

        // 任务二
        CompletableFuture<Object> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("completableFuture2线程启动：" + Thread.currentThread().getId());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("completableFuture2线程结束：");
            return "hello";
        }, executor);

        // 任务三  acceptEitherAsync
//        completableFuture1.acceptEitherAsync(completableFuture2, (res) -> {
//            System.out.println("任务三启动：" + Thread.currentThread().getId());
//        }, executor);



        // 任务三  applyToEitherAsync
        CompletableFuture<String> applyToEitherAsync = completableFuture1.applyToEitherAsync(completableFuture2, (res) -> {
            System.out.println("任务三启动：" + Thread.currentThread().getId() + "之前的结果是" + res);
            return "task3处理结果" + res;
        }, executor);

        System.out.println("applyToEitherAsync:"+applyToEitherAsync.get());


    }

}
