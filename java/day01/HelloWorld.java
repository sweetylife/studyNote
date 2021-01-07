/**
 * 文档注释
 * 这是我的第一个程序
 * @author "tian" true
 * @version v1.0 true
 *
 * 这是我的第一个程序
 */
public class HelloWorld {
	/**
	 * main(),作用：程序的入口
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println("hello world");

		byte a=3; //声明的时候会自动强转，且不能包含变量
		byte b=4;
		byte c=(byte)(4+b);
		System.out.println(c);

		// byte b = 5;
		// short s = 3;
		// short t = s + b;
		System.out.println(a<b?"aa":"b");

		boolean d=false;
		if(d=false){
		System.out.println(123);
		}
	}
}