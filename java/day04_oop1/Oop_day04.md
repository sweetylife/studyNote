# object-oriented Programming

## class & object

### the point of object-oriented Programming is designing class

### How to use?

1. create class
2. create object
3. use property or methods

```java
public class OppTest{
    public static void main(String[] args) {
        Person p1=new Person();
        p1.name="Sweety";//property
        p1.run();//method
    }
}
class Person{
    String name;
    int age;
    Boolean isMale;

    public void eat(){
        System.out.println("eat");
    }
    public void run(){
        System.out.println("run");
    }
}
```

### tips

* if an object be created ,each object contains a complete set of atrribute(non-static).
