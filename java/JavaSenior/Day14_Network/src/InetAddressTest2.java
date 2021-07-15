import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class InetAddressTest2 {
    //客户端发送文件给服务端，服务端保存到本地，并返回“发送成功”给客户端，并关闭相应的连接
    //客户端（后启动）
    @Test
    public void client() throws IOException {
        //1.创建Socket对象，指明服务器端的ip和端口号
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inet, 8899);
        //2.获取一个输出流，用于输出数据
        OutputStream outputStream = socket.getOutputStream();
        //3.写出数据的操作
        FileInputStream fileInputStream = new FileInputStream(new File("cat.png"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fileInputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        //4.结束outputStream
        socket.shutdownOutput();
        //5.接受服务端反馈
        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[5];
        int len2;
        while ((len2=inputStream.read(bytes)) != -1){
            byteArrayOutputStream.write(bytes,0,len2);
        }
        System.out.println(byteArrayOutputStream.toString());
        //4.关闭资源（关闭输出流之后，socket才会出发去服务端）
        byteArrayOutputStream.close();
        inputStream.close();
        fileInputStream.close();
        outputStream.close();
        socket.close();
    }
    //服务端（先启动）
    @Test
    public void server() throws IOException {
        //1.创建服务器端的ServerSocket，指明自己的端口号
        ServerSocket serverSocket = new ServerSocket(8899);
        //2.调用accept（）表示接受来自于客户端的socket
        Socket socket = serverSocket.accept();
        //3.获取输入流
        InputStream inputStream = socket.getInputStream();
        //4.读取输入流中的数据
        FileOutputStream fileOutputStream = new FileOutputStream(new File("cat2.png"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
        System.out.println("收到来自于：" + socket.getInetAddress().getHostAddress());
        System.out.println(System.currentTimeMillis());
        //5.给客户端传输消息
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("收到啦".getBytes());

        //5.关闭资源
        fileOutputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        outputStream.close();
    }

}
