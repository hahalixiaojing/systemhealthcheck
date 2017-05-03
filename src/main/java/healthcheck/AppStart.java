package healthcheck;

import okhttp3.*;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AppStart {

    public static void main(String[] args) throws IOException {

        String homeDir = System.getProperty("user.dir");
        Path path = Paths.get(homeDir, args[0] + ".properties");
        String s = FileUtils.readFileToString(path.toFile(), "utf-8");

        Properties properties = new Properties();
        properties.load(new StringReader(s));

        final String url = properties.get("url").toString();
        final String[] ipList = properties.get("ip").toString().split(" ");


        IpListContainer ipListContainer = new IpListContainer();
        ipListContainer.ipList = ipList;


        OkHttpClient build = new OkHttpClient.Builder().dns(new DNS(ipListContainer)).build();


        System.out.println("request url is :" + url);

        int i = 0;
        for (String ip : ipList) {
            i++;
            System.out.println();
            System.out.print(i + ". request ip :" + ip);
            Request request = new Request.Builder().url(url).get().build();
            try {
                Response execute = build.newCall(request).execute();
                System.out.print(" > " + execute.networkResponse().code());
                System.out.println();

            } catch (IOException e) {
                System.out.print(" > " + e.getMessage());
                System.out.println();
            } finally {
                ipListContainer.index++;
            }
        }
    }
}
