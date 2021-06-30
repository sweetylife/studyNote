# IO 流

## 1.File类的使用

* File类的一个对象，代表一个文件或一个文件目录（俗称：文件夹）
* File类声明在java.io包下

### 实例化

```java
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
    }
}
```

### 常用方法

* public String getAbsolutePath():获取绝对路径
* public String getPath():获取路径
* public String getName():获取名称
* public String getParent():获取上层文件目录路径。若无，返回null
* public long length():获取文件长度