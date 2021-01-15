
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

		String d="是";
		String e="是";
		System.out.println(d==e);


		/*
		----*----
		---***---
		--*****--
		-*******-
		*********
		-*******-    
		--*****--    
		---***---    
		----*----    
		*/
		// for(int i=1;i<=9;i++){
		// 	for(int j=0;j<Math.abs(5-i);j++){
		// 		System.out.print('-');
		// 	}
		// 	if(i<6){
		// 		for(int j=0;j<i*2-1;j++){
		// 			System.out.print('*');
		// 		}
		// 	}else{
		// 		for(int j=0;j<9-(i-5)*2;j++){
		// 			System.out.print('*');
		// 		}
		// 	}
		// 	for(int j=0;j<Math.abs(5-i);j++){
		// 		System.out.print('-');
		// 	}
		// 	System.out.println();
		// }

		// 9*9乘法表
		// for(int i=1;i<10;i++){
		// 	for(int j=1;j<=i;j++){
		// 		System.out.print(i+"*"+j+"="+i*j+" ");
		// 	}
		// 	System.out.println();
		// }

		long start = System.currentTimeMillis();
		label:for(int i=2;i<100;i++){
		    // 优化2：对所有数有效：（比如36  2,18   3,12   4,9   6,6--->最大值到开根号就可以）
			for(int j=2;j<=Math.sqrt(i);j++){    
				if(i%j==0){
					continue label;//优化1: 对非质数有效，已经判断为质数的跳出循环
				}
			}
			System.out.println(i);
		}
		long end =System.currentTimeMillis();
		System.out.println("time:"+(end-start));

		// label:for(int i=0;i<3;i++){
		// 	for(int j=0;j<3;j++){
		// 		if(j==1){
		// 			break label;
		// 			//continue label;
		// 		}
		// 		System.out.println(i+" "+j);
		// 	}
		// }
	}
}