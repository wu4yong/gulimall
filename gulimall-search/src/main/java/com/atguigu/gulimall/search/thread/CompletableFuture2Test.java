package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试CompletableFuture
 *  whenComplete 可以处理正常和异常的计算结果， exceptionally 处理异常情况。
 *  whenComplete 和 whenCompleteAsync 的区别：
 *  whenComplete： 是执行当前任务的线程执行继续执行 whenComplete 的任务。
 *  whenCompleteAsync： 是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
 *
 *  方法不以 Async 结尾， 意味着 Action 使用相同的线程执行， 而 Async 可能会使用其他线程
 *  执行（如果是使用相同的线程池， 也可能会被同一个线程选中执行）
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
public class CompletableFuture2Test {
    // 当前系统中池只有一两个，每个异步任务，提交给线程池
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // whenComplete 方法完成后的感知
        System.out.println("main......start.....");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).whenComplete((result,exception)->{
            // 虽然能得到异常信息，但是没法修改返回数据
            System.out.println("异步任务成功完成了......结果是"+result+"；异常是："+exception);
        }).exceptionally(throwable -> {
            // 可以感知异常，同时返回默认值
            return  10 ;
        });
        System.out.println("main......end....."+future.get());



    }

}
