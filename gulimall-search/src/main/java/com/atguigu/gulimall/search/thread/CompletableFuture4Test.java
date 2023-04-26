package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试CompletableFuture 串行化
 *  thenApply 方法： 当一个线程依赖另一个线程时， 获取上一个任务返回的结果， 并返回当前任务的返回值。
 *  thenAccept 方法： 消费处理结果。 接收任务的处理结果， 并消费处理， 无返回结果。
 *  thenRun 方法： 只要上面的任务执行完成， 就开始执行 thenRun， 只是处理完任务后， 执行
 *  thenRun 的后续操作
 * 带有 Async 默认是异步执行的。 同之前。
 * 以上都要前置任务成功完成。
 *      Function<? super T,? extends U>
 *              T： 上一个任务返回结果的类型U： 当前任务的返回值类型
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
public class CompletableFuture4Test {


    // 当前系统中池只有一两个，每个异步任务，提交给线程池
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * 线程串行化 thenRunAsync 不能感知上一步的结果，但无返回值
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, executor).thenRunAsync(()->{
//            System.out.println("任务2启动了。。。。。");
//        }, executor);
//
//    }

    /**
     *  线程串行化 thenAcceptAsync 接收上一步的结果，但无返回值
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, executor).thenAcceptAsync((result)->{
//            System.out.println("任务2启动了。。。。。"+result);
//        }, executor);
//
//    }

    /**
     *  线程串行化 thenApplyAsync 既能接收上一步的结果，也能返回值
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程1：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("当前线程1运行结果：" + i);
            return i;
        }, executor).thenApplyAsync((result) -> {
            System.out.println("当前线程2：" + Thread.currentThread().getId());
            System.out.println("当前线程2运行结果。。。。。" + result);
            return "运行了" + result + "次";
        }, executor);
        System.out.println(completableFuture.get());
    }

}
