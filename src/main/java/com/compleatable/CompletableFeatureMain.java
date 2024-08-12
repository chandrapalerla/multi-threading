package com.compleatable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CompletableFeatureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Executor executor = Executors.newFixedThreadPool(10);

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("run AS Async");
        }, executor);

        Void unused = completableFuture.get();
        completableFuture.complete(unused);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        }, executor);
        String result = future.get();
        System.out.println(result);
    }
}
