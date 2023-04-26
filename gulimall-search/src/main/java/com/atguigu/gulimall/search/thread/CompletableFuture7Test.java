package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试CompletableFuture 多任务组合
 * allOf： 等待所有任务完成
 * anyOf： 只要有一个任务完成
 *
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
public class CompletableFuture7Test {


    // 当前系统中池只有一两个，每个异步任务，提交给线程池
    public static ExecutorService executor = Executors.newFixedThreadPool(10);


    /**
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("main......start.....");

        // 任务一
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的图片信息");
            return "xiaowang.JPG";
        }, executor);

        // 任务二
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的属性");
            return "黑色";
        }, executor);

        // 任务三
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的结束");
            return "Apple";
        }, executor);

        // allOf
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2, future3);
//        allOf.get();// 等待所有结果完成
//        System.out.println("main......end.....");


        // allOf
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2, future3);
        anyOf.get();// 等待所有结果完成
        System.out.println(anyOf.get());
        System.out.println("main......end.....");


    }

}
