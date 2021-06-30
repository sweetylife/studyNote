package com.file;

import org.junit.jupiter.api.Test;

import java.io.File;

public class FileTest {
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
}
