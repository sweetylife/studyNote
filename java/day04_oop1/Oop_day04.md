# object-oriented Programming

## 1. class & object

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

### memory structure

==**vm stack**==

* local variable

==**pile**==

* the structure which be newed（like：Array，Object）
* the attribute of object（non-static）

==**methods area**==

* Loading information for the class
* constant pool
* static field

### anonymous object

```java
new Phone().sendEmail();
```

## 2. one of the structure of class：attribute

### differences between  atrributes and variables

* atrributes can be decorated by modifier like public,private and protected,but variables can not be.

* atrributes have initial values,but variables do not.

* Non-static properties are loaded into the heap space and variables into the stack space.

## 3.one of the structure of class ：methods

### use of methods

* can call properties and methods in current class

### classification of variables

==**by type**==

![图 2](../../images/e29e2c674283d8da08a0e77e959b54e0125992978e2ff5c3b3d5c2e3398004ac.png)  

==**by location**==

![图 1](../../images/e02347bf2e9348abb310ce3a8f2c6d7f2a20291f8a19bb44bba6cbdcfa24aed0.png)  

### method overloading

* in same class
* methods with the same name
* different parameters
* not related to permission,return type,name of parameter variable and method content.
* preference is given to methods with a smaller range,regardless of the order of the methods.

 ==**Varargs（variable number of argument）**==

* The number of arguments could be 0~n.
* Varargs need to come last.
* a method most have one vararg.

```java
public void show(String ... strs){
}
```

* They can't coexist.

```java
public void show(String ... strs){
}
public void show(String[] strs){
}
```

## 4. one of the structure of class：constructors

## 5. one of the characteristics of object orientation：encapsulation

## 6. key words