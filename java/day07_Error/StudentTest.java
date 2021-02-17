public class StudentTest{
    
}

class Student{
    private int id;
    public void register(int id) throws Exception{
        if(id > 0){
            this.id=id;
        }else{
            throw new Exception("您输入的数据非法");
        }
    }
}