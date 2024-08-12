package com.compleatable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFeatureAnyOf {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "chandu";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Shekar";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "komali");

        System.out.println(CompletableFuture.anyOf(future1, future2, future3).get());

    }
}
