import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InputListener implements Runnable {
    private LivePusher pusher;
    BufferedReader reader;
    Scanner scanner;
    private boolean flag = true;

    public InputListener(LivePusher p) {
        this.pusher = p;
    }

    public void run() {
        Utils.println("input [q] to exit");
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while(this.flag) {
            try {
                Thread.sleep(100L);
                input = this.reader.readLine();
            } catch (IOException | InterruptedException var3) {
                var3.printStackTrace();
            }

            if (input.equals("q")) {
                this.pusher.stopLive();
                break;
            }
        }

    }

    public void exit() {
        this.flag = false;
        Runtime.getRuntime().exit(0);
    }
}
