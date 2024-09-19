import java.sql.Time;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExecutiveFramework {
    public static void show(){

        System.out.println("\n\nRun 15 tasks with 2 threads available");
        System.out.println(Thread.currentThread().getName());

        var executor = Executors.newFixedThreadPool(2);
        try {
            for(int i = 0; i < 15; i++)
                executor.submit(
                        () -> {
                            System.out.println(Thread.currentThread().getName());
                        }
                );
        }
        finally {
            executor.shutdown();
        }

        // callable
        System.out.println("------------------------");
        var executor2 = Executors.newSingleThreadExecutor();
        try {
            var future = executor2.submit(() -> {
                longTask();
                return "long task done";
            });
            System.out.println("do something after submit callable");
            try {
                var result = future.get();  // block: wait for task complete
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        finally {
            executor2.shutdown();
        }

        System.out.println("--------------------------");
        // asynchronous = non-blocking
        // Completable Future in used <- Future, CompletionStage
        Runnable runnable = () -> System.out.println("ran completableFuture runnable");
        Supplier<Integer> supplier = () -> (int)(Math.random() * 100);
        var completableFuture1 =
                CompletableFuture.runAsync(runnable);
        var completableFuture2 =
                CompletableFuture.supplyAsync(supplier);
        try {
            // get method has blocking and is not async
            var result2 = completableFuture2.get();
            System.out.println("completableFuture supplier result: " + result2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Non async send mail-------------");
        MailService.send();
        System.out.println("Async send mail---------------");
        MailService.asyncSend();
        try {
            // this block make thread sleep for completing async send
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------------------------------");
        System.out.println("Running code on completion------");
        var completionfuture =
                CompletableFuture.supplyAsync(() -> 1);
        completionfuture.thenAccept(result -> {
            System.out.println("thenAccept, second stage:" +
                    Thread.currentThread().getName());
            System.out.println("consumer input: " + result);
        });
        completionfuture.thenAcceptAsync(result -> {
            System.out.println("thenAcceptAsync, second stage:" +
                    Thread.currentThread().getName());
            System.out.println("consumer input: " + result);
        });
        completionfuture.thenRun(
                () -> System.out.println("Run in thenRun - " +
                            Thread.currentThread().getName())
        );
        completionfuture.thenRunAsync(
                () -> System.out.println("Run in thenRunAsync - " +
                        Thread.currentThread().getName())
        );
        System.out.println("--------------------------");
        System.out.println("Exception handling");
        var completableFuture3 = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("compltable supply that throws exception");
                    throw new IllegalStateException();
                }
        );
        try {
            var res = completableFuture3.exceptionally(ex -> 0).get();
            System.out.println("exceptionally handled get: " + res);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("--------------------------------");
        System.out.println("transforming a completable Future");
        var future =
                CompletableFuture.supplyAsync(() -> 20);
        future
                .thenApply(ExecutiveFramework::toFarenheit)  // transform value to far
                .thenAccept(far -> System.out.println("transformed value: " + far)); // instead of get value that need try catch

        //  compose operations
        // get user email by id: id -> email
        System.out.println("-------------------");
        System.out.println("Compose Operations");
        getUserEmailAsync(2)
                .thenCompose(ExecutiveFramework::getPlaylistAsync)
                .thenAccept(System.out::println);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // combine 2 operation
        System.out.println("-----------------------");
        System.out.println("Combine 2 operations");
        var getProductPrice =
                CompletableFuture.supplyAsync(() -> "20USD")
                        .thenApply(price -> {
                            var p = price.trim().replace("USD", "");
                            return Integer.parseInt(p);
                        });
        var getExchangeRate =
                CompletableFuture.supplyAsync(() -> 0.9);
        getProductPrice
                .thenCombine(getExchangeRate, (price, rate) -> price * rate)  // exec after 2 async ops end
                .thenAccept(System.out::println);

        System.out.println("-------------------------");
        System.out.println("Combine many ops");
        var first = CompletableFuture.supplyAsync(() -> 1);
        var second = CompletableFuture.supplyAsync(() -> 2);
        var third = CompletableFuture.supplyAsync(() -> 3);
        var allOps =
                CompletableFuture.allOf(first, second, third);
        allOps.thenRun(
                () -> {
                    System.out.println("All operations done");
                    try {
                        var firstResult = first.get();
                        System.out.println("First: " + firstResult);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        // waiting for first task
        System.out.println("---------------------");
        System.out.println("waiting for first task");
        var slower =
                CompletableFuture.supplyAsync(() -> {
                    longTask();
                    return "slower";
                });
        var faster =
                CompletableFuture.supplyAsync(() -> "faster");

        CompletableFuture.anyOf(slower, faster)
                .thenAccept(result -> System.out.println("first done: " + result));

        System.out.println("------------------");
        System.out.println("Handling Timeout");
        var comfuture =
                CompletableFuture.supplyAsync(() -> {
                    longTask();
                    return 1;
        });
//        comfuture.completeOnTimeout(-1, 1, TimeUnit.SECOND)


    }
    public static CompletableFuture<String> getUserEmailAsync(int id) {
        return CompletableFuture.supplyAsync(() -> id + ": email");
    }
    public static CompletableFuture<String> getPlaylistAsync(String email) {
        return CompletableFuture.supplyAsync(() -> email + " Playlist");
    }

    public static int toFarenheit(int degrees) {
        return (int) (degrees * 1.8) + 32;
    }

    private static void longTask() {
        System.out.println("running long task...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
