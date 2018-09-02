import java.io.IOException;

public class LivePusher implements Runnable {
    private InputListener listener;
    private String u;
    private String t;
    private boolean flag = false;
    private Process p;

    public LivePusher(String url, String target) {
        this.u = url;
        this.t = target;
        this.listener = new InputListener(this);
    }

    public void run() {
        Utils.print("pushing stream...");

        try {
            this.p = Runtime.getRuntime().exec("tools/ffmpeg -i " + this.u + " -c:v copy -c:a aac -f flv " + this.t);
            Utils.println("succeed");
            (new Thread(this.listener)).start();

            while(!this.flag) {
                Thread.sleep(1000L);
                Utils.readAndPrint(this.p.getErrorStream());
                Utils.readAndPrint(this.p.getInputStream());
                if (!this.p.isAlive()) {
                    break;
                }
            }
        } catch (InterruptedException | IOException var5) {
            Utils.println("failed");
            var5.printStackTrace();
        } finally {
            this.listener.exit();
        }

    }

    public void stopLive() {
        this.flag = true;

        try {
            this.p.getOutputStream().write(113);
            this.p.getOutputStream().flush();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void writeInProcess(int c) {
        try {
            this.p.getOutputStream().write(c);
            this.p.getOutputStream().flush();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
