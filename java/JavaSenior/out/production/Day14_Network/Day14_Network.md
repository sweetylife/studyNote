# 14 网络编程

## 1.网络编程中有两个重要问题
* 1.如何准确定位网络上一台或多台主机：定位主机上的特定的应用
* 2.找到主机后如何可靠高效的进行数据传输

## 2.网络编程中的两个要素：
* 1.对应问题1：IP和端口号
* 2.对应问题2：通过网络通信协议：TCP/IP参考模型（应用层，传输层，网络层，物理+链路层）

## 3.通信要素一：IP和端口号

1. IP：唯一的表示 Internet 上的计算机（通信实体）
2. 在java中使用InetAddress类代表IP
3. IP分类：IP4和IP6；万维网和局域网
4. 域名
5. 本地回路地址 127.0.0.1 (localhost)
6. 如何实例化InetAddress：getByName(String host)、getLocalhost()
   两个常用方法：getHostName,getHostAddress
   
```java
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress byName = InetAddress.getByName("192.168.10.14");
            System.out.println(byName);

            //获取本地ip
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);

            //getHostName
            System.out.println(localHost.getHostName());
            //getHostAddress
            System.out.println(localHost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
```

7. 端口号：正在计算机上运行的进程 

要求：不同进程有不同的端口号
 
范围：被规定为一个16位的整数 0-65535
   
8. 端口号与Ip地址的组合得出一个网络套接字：Socket

## 4.例子
### 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
```java
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class InetAddressTest {

    //例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
    //客户端（后启动）
    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            //1.创建Socket对象，指明服务器端的ip和端口号
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);
            //2.获取一个输出流，用于输出数据
            outputStream = socket.getOutputStream();
            //3.写出数据的操作
            outputStream.write("你好呀，我是小甜甜".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           try {
              Thread.sleep(5 * 1000);
           } catch (InterruptedException e) {
              e.printStackTrace();
           }
           //4.关闭资源（关闭输出流之后，socket才会出发去服务端）
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //服务端（先启动）
    @Test
    public void server(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器端的ServerSocket，指明自己的端口号
            serverSocket = new ServerSocket(8899);
            //2.调用accept（）表示接受来自于客户端的socket
            socket = serverSocket.accept();
            //3.获取输入流
            inputStream = socket.getInputStream();
            //4.读取输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while ((len = inputStream.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
            System.out.println("收到来自于："+socket.getInetAddress().getHostAddress());
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 例子2：客户端发送文件给服务端，服务端保存到本地，并返回“发送成功”给客户端，并关闭相应的连接 。

```java
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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

```

## 5.UDP协议网络编程

与TCP的区别
* 只管发送和接受，不关心连接问题
* 先启动发送端，不会报错

```java
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

```

## 6.URL类的常用方法

* public String getProtocol（） 获取该url的协议名
* public String getHost（） 获取该URL的主机名
* public String getPort（） 获取该URL的端口号
* public String getPath（） 获取该URL的文件路径
* public String getFile（） 获取该URL的文件名
* public String getQuery（） 获取该URL的查询名

## 7.从服务器读取内容到本地

```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:5000/20210714094816.png");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();

        InputStream is = urlConnection.getInputStream();
        FileOutputStream fos = new FileOutputStream("down.png");

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

```

