public class ExceptionTest{
    public void main(String[] args){
        String str = "123";
        str = "abc";
        try{
            int num = Integer.parseInt(str);
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }catch(NullPointerException e){
            e.printStackTrace();
        }catch(Exception e){
            //如果上面的异常匹配，这里不会执行
            // 如果大的异常在前，小的异常在后，会编译出错，因为小异常永远执行不到
            System.out.println("出错啦");
        }
    }
}