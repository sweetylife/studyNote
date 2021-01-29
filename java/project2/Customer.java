public class Customer {
    private String name;
    private char sex;
    private int age;
    private String tel;
    private String email;
    
    
    public Customer(String name,char sex,int age,String tel,String email){
        this.name=name;
        this.sex=sex;
        this.age=age;
        this.tel=tel;
        this.email=email;
    }
    public String getName() {
        return name;
    }
    public char getSex() {
        return sex;
    }
    public int getAge() {
        return age;
    }
    public String getTel() {
        return tel;
    }
    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
