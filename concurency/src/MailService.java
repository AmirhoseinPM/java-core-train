import java.util.concurrent.CompletableFuture;

public class MailService {
    public static void send() {
        try {
            System.out.println("sleep 2s in sending messages...");
            Thread.sleep(2000);
            System.out.println("message was sent");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static CompletableFuture<Void> asyncSend(){
        var completableFuture = CompletableFuture.runAsync(MailService::send);
        return completableFuture;
    }
}
