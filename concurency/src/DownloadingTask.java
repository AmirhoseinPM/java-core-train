public class DownloadingTask implements Runnable{
    private DownloadStatus status;

    public DownloadingTask(DownloadStatus status) {
        this.status = status;
    }

    @Override
    public void run() {
        status.startDownload();
        System.out.println("Downloading in: " + Thread.currentThread().getName());

        for(int i = 0; i < 10_000; i++){
            this.status.incrementTotalBytes();
            this.status.incrementAtomicTotalByte();
            this.status.incrementNotSynchronized();
            this.status.incrementAdderTotalByte();
            System.out.println("total: " + this.status.getTotalByte());
        }
        if (Thread.currentThread().isInterrupted()) {
            System.out.println(
                    "********* Interrupted ********* "
                    + Thread.currentThread().getName());
            return;
        }
        this.status.incrementTotalFiles();

        this.status.done();
        synchronized (status) {
            status.notifyAll();
        }
        System.out.println("Downloading complete: " + Thread.currentThread().getName());
    }
}
 