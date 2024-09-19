import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class DownloadStatus {
    private int totalByte;
    private int totalFiles;
    private Object totalBytesLock = new Object();
    private Object totalFilesLock = new Object();
    private volatile boolean isDone;
    private AtomicInteger atomicTotalByte = new AtomicInteger(0);

    public int getAdderTotalByte() {
        return adderTotalByte.intValue();
    }

    public void incrementAdderTotalByte() {
        this.adderTotalByte.increment();
    }

    private LongAdder adderTotalByte = new LongAdder();

    private int unsync;


    public void incrementNotSynchronized() {
        this.unsync++;
    }



    public void incrementAtomicTotalByte() {
        this.atomicTotalByte.incrementAndGet();
    }

    public boolean isDone() {
        return isDone;
    }

    public void done() {
        isDone = true;
    }
    public void startDownload() {
        isDone = false;
    }


//    private Lock lock = new ReentrantLock();


    public void incrementTotalFiles() {
        synchronized (totalFilesLock) {
            this.totalFiles++;
        }
    }

    public int incrementTotalBytes() {
        synchronized (totalBytesLock) {
            this.totalByte++;
        }
        // use synchronized in method structure act like this:
//        synchronized (this) {
//            this.totalByte++;
//        }
        // use lock
//        lock.lock();
//        try {
//            // do something
//            this.totalByte++;
//        }
//        finally {
//            lock.unlock();
//        }
        return this.totalByte;
    }

    public int getAtomicTotalByte() {
        return atomicTotalByte.get();
    }

    public int getUnsync() {
        return unsync;
    }

    public int getTotalByte() {
        return totalByte;
    }


    public int getTotalFiles() {
        return totalFiles;
    }
}
