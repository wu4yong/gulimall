package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试CompletableFuture
 *  1、 runXxxx 都是没有返回结果的， supplyXxx 都是可以获取返回结果的
 *  2、 可以传入自定义的线程池， 否则就用默认的线程池；
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
public class CompletableFuture1Test {
    // 当前系统中池只有一两个，每个异步任务，提交给线程池
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        // runAsync 无返回值
//        System.out.println("main......start.....");
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果：" + i);
//        }, executor);
//        System.out.println("main......end.....");

        // supplyAsync 有返回值
        System.out.println("main......start.....");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor);
        System.out.println("main......end....."+future.get());


    }

}
