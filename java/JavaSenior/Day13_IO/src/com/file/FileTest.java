package com.file;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Locale;

public class FileTest {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true){
            System.out.println("请输入字符串");
            String s = bufferedReader.readLine();
            if ("e".equalsIgnoreCase(s)||"exis".equalsIgnoreCase(s)){
                System.out.println("程序结束");
                break;
            }
            String s1 = s.toUpperCase();
            System.out.println(s1);

        }
    }
    @Test
    public void test1(){

         //构造器1
        File file = new File("hello.txt");//相对于当前module
        File file1 =new File("D:\\Desktop\\studyNote\\studyNote\\java\\JavaSenior\\Day13_IO\\hello.txt");
        System.out.println(file);
        System.out.println(file1);

        //构造器2
        File hello = new File("D:\\Desktop\\studyNote\\studyNote\\java\\JavaSenior\\Day13_IO\\", "hello");

        //构造器3
        File file2 = new File(hello, "hello.txt");
        System.out.println(file.getAbsoluteFile());
    }

    @Test
    public void test2() throws IOException {
        FileReader fileReader = null;
        try {
            //1.实例化File类的对象，指明要操作的文件
            File file = new File("src\\com\\file\\hello.txt");
            //2.提供具体的流
            fileReader = new FileReader(file);
            //3.数据的读入
//        int data = fileReader.read();
//        while (data!=-1){
//            System.out.print((char) data);
//            data = fileReader.read();
//        }

            int data;
            while ((data=fileReader.read())!=-1){
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭
            try {
                if(fileReader!=null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test3() throws IOException {
        //1.File的实例化
        File file = new File("hello.txt");
        //2.FileReader流的实例化
        FileReader fileReader = new FileReader(file);
        //3.读入的操作
        char[] cbuf = new char[5];
        int len;
        while ((len=fileReader.read(cbuf))!=-1){
//            方式1
//            for (int i = 0; i < len; i++) {
//                System.out.println(cbuf[i]);
//            }
//            方式2
            String s = new String(cbuf,0,len);
            System.out.println(s);
        }
        //4.流的关闭
        fileReader.close();
    }

    @Test
    public void test4() throws IOException {
        //1、提供File类的对象，指明写出到的文件
        File file = new File("hello1.txt");
        //2.提供FileWriter的对象，用于数据的写出
        FileWriter fileWriter = new FileWriter(file);
        //3.写出操作
        fileWriter.write("Hello ya");
        //4.流资源的关闭
        fileWriter.close();
    }
    @Test
    public void test5(){
        FileReader fileReader=null;
        FileWriter fileWriter=null;
        try {
            //1.创建File类的对象，指明读入和写出的文件
            File srcfile = new File("src\\com\\file\\hello.txt");
            File destFile = new File("hello1.txt");
            //2.提供FIleWriter的对象，用于数据的写出
            fileReader = new FileReader(srcfile);
            fileWriter = new FileWriter(destFile);
            //3.写出操作
            char[] cbuf = new char[5];
            int len;
            while ((len=fileReader.read(cbuf))!=-1){
                fileWriter.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭
            try {
                if(fileReader!=null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fileWriter!=null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test6(){
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            //1.造文件
            File srcFile = new File("src\\img.png");
            File destFile = new File("src\\img2.png");
            //2.造流
            //2.1 造节点流（直接连接文件的流）
            FileInputStream fileInputStream = new FileInputStream(srcFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
            //2.2 造缓冲流
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            //3.复制细节
            byte[] buffer = new byte[10];
            int len ;
            while ((len=bufferedInputStream.read(buffer))!=-1){
                bufferedOutputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流
            //要求：先关闭外层的流，再关闭内层的流
            //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭可以省略
            if (bufferedOutputStream!=null){
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedInputStream!=null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Test
    public void test7() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("hello1.txt"));
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");

        OutputStreamWriter gbk = new OutputStreamWriter(new FileOutputStream(new File("hello2.txt")), "gbk");
        char[] chars = new char[20];
        int len;
        while ((len=inputStreamReader.read(chars))!=-1){
            gbk.write(chars,0,len);
        }
        inputStreamReader.close();
    }

    @Test
    public void test8(){
        PrintStream printStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("io.txt"));
            printStream = new PrintStream(fileOutputStream, true);
            if (printStream!=null){
                System.setOut(printStream);
            }
            for (int i = 0; i < 255; i++) {
                System.out.print((char) i);
                if (i%50==0){
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printStream!=null){
                try {
                    printStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Test
    public void test9() throws IOException {
        //写入
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("data.txt"));
        dataOutputStream.writeUTF("tian");
        dataOutputStream.flush();
        dataOutputStream.writeInt(18);
        dataOutputStream.flush();
        dataOutputStream.writeBoolean(true);
        dataOutputStream.flush();
        dataOutputStream.close();

        //读取（读取的数据要与写入的数据一致）
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("data.txt"));
        String s = dataInputStream.readUTF();
        int i = dataInputStream.readInt();
        boolean b = dataInputStream.readBoolean();
        System.out.println(s);
        System.out.println(i);
        System.out.println(b);
    }

    @Test
    public void test10(){
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("data.txt")));
            objectOutputStream.writeObject(new String("天安门"));
            objectOutputStream.flush();

            objectOutputStream.writeObject(new Person("甜",18));
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Test
    public void test11(){
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(new File("data.txt")));
            Object o = objectInputStream.readObject();
            String str = (String) o;
            Object person = objectInputStream.readObject();
            Person person1 = (Person) person;
            System.out.println(person1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
