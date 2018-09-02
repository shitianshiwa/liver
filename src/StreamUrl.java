public class StreamUrl {
    private boolean isSucceed = true;
    private String url = "";
    private String error = "";

    public StreamUrl(String source) {
        Utils.print("decoding config...");

        try {
            Process process = Runtime.getRuntime().exec("tools/youtube-dl.exe -g " + source);
            process.waitFor();
            this.url = Utils.read(process.getInputStream());
            this.error = Utils.read(process.getErrorStream());
        } catch (Exception var6) {
            this.isSucceed = false;
            this.error = var6.getMessage();
        } finally {
            if (this.url.equals("")) {
                Utils.println("failed");
                this.isSucceed = false;
            } else {
                Utils.println("succeed");
            }

        }

    }

    public boolean hasUrl() {
        return this.isSucceed;
    }

    public String getUrl() {
        return this.url;
    }

    public String getErrorMessage() {
        return this.error;
    }
}
