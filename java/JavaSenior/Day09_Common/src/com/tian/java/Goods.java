package com.tian.java;

public class Goods implements Comparable {
    private String name;
    private double price;

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Object o){
        if(o instanceof Goods){
            Goods o1 = (Goods) o;
//            方法1
//            return Double.compare(this.price,o1.getPrice());
//            方法2
            if(this.price>o1.getPrice()){
                return 1;
            }else if(this.price<o1.getPrice()){
                return -1;
            }else {
                return this.name.compareTo(o1.name);
            }
        }
        throw new RuntimeException("比较类型不一致");
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
