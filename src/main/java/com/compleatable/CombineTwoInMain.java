package com.compleatable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CombineTwoInMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });
        System.out.println(weightInKgFuture.get());

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });
        System.out.println(heightInCmFuture.get());

        CompletableFuture<Double> doubleCompletableFuture = weightInKgFuture.thenCombine(heightInCmFuture, (weight, height) -> {
            Double heightInMeter = height / 100;
            return weight / (heightInMeter * heightInMeter);
        });
        System.out.println(doubleCompletableFuture.get());
    }
}
