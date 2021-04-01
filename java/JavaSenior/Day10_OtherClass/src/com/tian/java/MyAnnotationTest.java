package com.tian.java;
import java.lang.annotation.Repeatable;

public class MyAnnotationTest {
}



@MyAnnotation("hi")
@MyAnnotation("abc")
class Person {

}

@Repeatable(MyAnnotations.class)
@interface MyAnnotation {
    String value() default "hello";
}
@interface MyAnnotations{
    MyAnnotation[] value();
}