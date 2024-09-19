import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Concurrency {
    public static void show() throws InterruptedException {
        System.out.println("Active threads: " + Thread.activeCount());
        System.out.println("available processor: " +
                Runtime.getRuntime().availableProcessors());

        System.out.println("Current: " + Thread.currentThread().getName());
        Thread thread = new Thread(() -> {
            System.out.println("Wait 2 second in " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.join();  // wait for thread end -> block program

        System.out.println("--------Volatile Field--------");
        var status1 = new DownloadStatus();
        var thread1 = new Thread(new DownloadingTask(status1));
        var thread2 = new Thread( () -> {
            while (!status1.isDone()) {
                synchronized (status1) {
                    try {
                        status1.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };  // wait for done, by watching volatile field of status
            System.out.println("Download is done");
            System.out.println("TotalBytes: " + status1.getTotalByte());
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------------------");

        var status = new DownloadStatus();
        List<Thread> threads = new ArrayList<>();
        for(int i = 10; i > 0; i--) {
            thread = new Thread(new DownloadingTask(status));
            thread.start();
           /* if ((i % 2) == 0)
                thread.interrupt();*/
            threads.add(thread);
        }
        try {
            for (var t : threads)
                t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("None sync total bytes: " + status.getUnsync());
        System.out.println("sync total bytes: " + status.getTotalByte());
        System.out.println("sync total files: " + status.getTotalFiles());
        System.out.println("Is done(volatile): " + status.isDone());
        System.out.println("Atomic totalBytes: " + status.getAtomicTotalByte());
        System.out.println("LongAdder totalBytes: " + status.getAdderTotalByte());
        System.out.println("----------------");
        System.out.println("------------------");

        Collection<Integer> nonSyncList =
                new ArrayList<>();
        Collection<Integer> syncList =
                Collections.synchronizedList(new ArrayList<>());
        Map<Integer, String> concurrentHashMap =
                new ConcurrentHashMap<>();
        Thread thread3 = new Thread(() -> {
            nonSyncList.add(1);
            nonSyncList.add(2);
            nonSyncList.add(3);
            syncList.add(1);
            syncList.add(2);
            syncList.add(3);
            concurrentHashMap.put(1, "1");
            concurrentHashMap.put(2, "2");
            concurrentHashMap.put(3, "3");
        });
        Thread thread4 = new Thread(() -> {
            nonSyncList.add(4);
            nonSyncList.add(5);
            nonSyncList.add(6);
            syncList.add(4);
            syncList.add(5);
            syncList.add(6);
            concurrentHashMap.put(4, "4");
            concurrentHashMap.put(5, "5");
            concurrentHashMap.put(6, "6");
        });
        Thread thread5 = new Thread(() -> {
            nonSyncList.add(4);
            nonSyncList.add(5);
            nonSyncList.add(6);
            syncList.add(4);
            syncList.add(5);
            syncList.add(6);
            concurrentHashMap.put(4, "1");
            concurrentHashMap.put(5, "1");
            concurrentHashMap.put(6, "1");
        });
        Thread thread6 = new Thread(() -> {
            nonSyncList.add(4);
            nonSyncList.add(5);
            nonSyncList.add(6);
            syncList.add(4);
            syncList.add(5);
            syncList.add(6);
            concurrentHashMap.put(4, "0");
            concurrentHashMap.put(5, "0");
            concurrentHashMap.put(6, "0");
        });
        thread3.start();
        thread4.start();
        thread6.start();
        thread5.start();
        try {
            thread6.join();
            thread5.join();
            thread4.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Non synchronized ArrayList: \n\t" + nonSyncList);
        System.out.println("Collections.synchronize... ArrayList: \n\t" + syncList);
        System.out.println("Concurrent ArrayList: \n\t" + concurrentHashMap);

    }
}
