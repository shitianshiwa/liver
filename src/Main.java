import java.io.IOException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        try {
            ConfigReader config;
            if (args.length == 2) {
                config = new ConfigReader(args);
            } else {
                config = new ConfigReader();
            }

            StreamUrl url = new StreamUrl(config.getSource());
            if (!url.hasUrl()) {
                System.out.println(url.getErrorMessage());
                System.exit(2);
            }

            LivePusher pusher = new LivePusher(url.getUrl(), config.getTarget());
            Thread thread = new Thread(pusher);
            thread.start();
        } catch (IOException var5) {
            var5.printStackTrace();
            System.exit(1);
        }

    }
}
