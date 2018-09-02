//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigReader {
    private String source;
    private String target;
    private boolean broken = false;
    private File configPath = new File("config.yml");

    public ConfigReader(String[] args) {
        this.source = args[0];
        this.target = args[1];
    }

    public ConfigReader() throws IOException {
        Utils.print("loading config...");
        if (!this.configPath.exists()) {
            this.reformatConfig();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.configPath)));
        String line_1 = reader.readLine();
        String line_2 = reader.readLine();
        reader.close();
        if (line_1.startsWith("source: ")) {
            this.source = line_1.substring(8);
        } else {
            this.broken = true;
        }

        if (line_2.startsWith("target: ")) {
            this.target = line_2.substring(8);
        } else {
            this.broken = true;
        }

        if (this.broken) {
            this.reformatConfig();
            Utils.println("failed");
            throw new IOException("system can't get correct url");
        } else if (this.source.length() != 0 && this.target.length() != 0) {
            Utils.println("succeed");
        } else {
            Utils.println("failed");
            throw new IOException("system can't get correct url");
        }
    }

    private void reformatConfig() throws IOException {
        if (this.configPath.exists()) {
            this.configPath.delete();
        }

        this.configPath.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.configPath));
        writer.write("source: \ntarget: ");
        writer.flush();
        writer.close();
    }

    public String getSource() {
        return this.source;
    }

    public String getTarget() {
        return this.target;
    }
}
