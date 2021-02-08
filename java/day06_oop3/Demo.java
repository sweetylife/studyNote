public class  Demo{
    public static void main(String[] args) {
        Demo demo=new Demo();

        // 方法1
        Printer printer = new Printer();
        demo.func(printer);

        // 方法2
        demo.func(new Printer());

        // 方法3
        USB usb=new USB(){
			@Override
			public void start() {
				System.out.println("匿名类 start");
			}
			@Override
			public void stop() {
				System.out.println("匿名类 stop");
			}
        };
        demo.func(usb);

        // 方法4
        demo.func(new USB(){
			@Override
			public void start() {
				System.out.println("匿名类 start");
			}
			@Override
			public void stop() {
				System.out.println("匿名类 stop");
			}
        });

    }
    public void func(USB usb){
        usb.start();
        usb.stop();
    }
}

interface USB{
    public void start();
    public void stop();
}

class Printer implements USB{

    @Override
    public void start() {
        System.out.println("printer start");
    }

    @Override
    public void stop() {
        System.out.println("printer stop");
    }

}