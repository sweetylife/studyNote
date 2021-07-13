import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://dywl-abdiuy-dev.oss-cn-zhangjiakou.aliyuncs…/driver/63fbfb5f-10dd-4ee8-adb1-b75498a2a7ab.jpeg");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();

        InputStream is = urlConnection.getInputStream();
        FileOutputStream fos = new FileOutputStream("down.jpeg");

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        //关闭资源
        is.close();
        fos.close();
        urlConnection.disconnect();
    }
}
