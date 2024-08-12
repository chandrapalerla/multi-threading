package com.compleatable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HandlingExceptions {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "komali");
            try {
                return future3.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }
}
