import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
    public Utils() {
    }

    static String read(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder string = new StringBuilder();

        String tmp;
        while((tmp = reader.readLine()) != null) {
            string.append(tmp).append("\n");
        }

        return string.toString();
    }

    static void readAndPrint(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        String tmp;
        while((tmp = reader.readLine()) != null) {
            println(tmp);
        }

    }

    static void print(String msg) {
        System.out.print(msg);
    }

    static void println(String msg) {
        System.out.println(msg);
    }
}
