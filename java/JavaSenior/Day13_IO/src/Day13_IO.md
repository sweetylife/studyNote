# IO 流

## 1.File类的使用

* File类的一个对象，代表一个文件或一个文件目录（俗称：文件夹）
* File类声明在java.io包下

### 路径的注意事项
* 如果使用的是JUnit中的单元测试方法测试，相对路径即为当前Module下。
* 如果是在main方法中，相对路径即为当前的Project中。

### 路径分隔符

* windows和DOS用'\'
* Unix和URL使用'/'
* public static final String separator 可以获取当前系统的分隔符

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
* public long lastModified():最后一次修改的时间，毫秒值
* public String[] list():获取指定目录下的所有文件或者文件目录的名称数组
* public File[] listFiles():获取指定目录下的所有文件或者文件目录的File数组
* public boolean renameTo(File dest):把文件重命名为指定的文件路径（file1.renameTo(file2):要保证file1存在，file2不存在,执行成功后，file不存在了，file2存在）

* public boolean isDirectory():判断是否是文件目录
* public boolean isFile():判断是否是文件
* public boolean exists():判断是否存在
* public boolean canRead():判断是否可读
* public boolean canWrite():判断是否可写
* public boolean isHidden():判断是否隐藏

创建硬盘中对应的文件或文件目录
* public boolean createNewFile():创建文件，若文件存在，则不创建，返回false
* public boolean mkdir():创建文件目录，如文件目录存在，就不创建，如果此文件目录上层目录不存在，也不创建
* public boolean mkdirs():创建文件目录，如果上层文件不存在，一并创建
删除磁盘中的文件或文件目录
* public boolean delete():删除文件或者文件夹（注意：删除不走回收站，且文件夹中不能有文件或文件目录，必须是空文件夹）

## 2.IO流体系
![img.png](img.png)