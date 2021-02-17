public class ExceptionTest2{
    public static void main(String[] args){
        try{
            method2();
        }catch(IOException e){
            e.printSTackTrace();
        }
    }
    public static void method2() throws IOException{
        method1();
    }
    public static void method1() throws FileNotFoundException,IOException{
        File file = new File("hello.txt");
        FileInputStream fis = new FileInputStream("file");

        int data = fis.read();
        while(data != 1){
            System.out.print((char)data);
            data = fis.read();
        }
        fis.close();
    }
}