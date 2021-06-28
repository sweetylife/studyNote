package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.*;

public class MapTest {

    @Test
    public void test(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put("BB",456);
        map.put("CC",789);

        //遍历所有的key集：keySet()
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println();

        //遍历所有的value集：values()
        Collection values=map.values();
        for (Object obj : values){
            System.out.println(obj);
        }

        //遍历所有的key-value
        //方法1：entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object next = iterator1.next();
            Map.Entry entry = (Map.Entry) next;
            System.out.println(entry.getKey()+"----->"+entry.getValue());
        }
        //方式二
        Set set2 = map.keySet();
        Iterator iterator2 = set2.iterator();
        while (iterator2.hasNext()){
            Object key = iterator2.next();
            Object value = map.get(key);
            System.out.println(key+"===========>"+value);
        }
    }

    @Test
    public void test2(){
        HashMap hashMap = new HashMap();
        hashMap = new LinkedHashMap();
        hashMap.put(123,"AA");
        hashMap.put(789,"cc");
        hashMap.put(456,"bb");
        System.out.println(hashMap);
    }


}
