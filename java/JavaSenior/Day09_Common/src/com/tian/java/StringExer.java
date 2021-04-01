package com.tian.java;

import org.junit.jupiter.api.Test;

public class StringExer {
    // 判断str2在str1中出现的次数
    public int getCount(String mainStr, String subStr) {
        if (mainStr.length() >= subStr.length()) {
            int count = 0;
            int index = 0;
            // while((index = mainStr.indexOf(subStr)) != -1){
            // count++;
            // mainStr = mainStr.substring(index + subStr.length());
            // }
            // 改进：
            while ((index = mainStr.indexOf(subStr, index)) != -1) {
                index ++;
                count++;
            }

            return count;
        } else {
            return 0;
        }

    }

    @Test
    public void testGetCount() {
        String str1 = "cdabkkcadkabkebfkabkskab";
        String str2 = "ab";
        int count = getCount(str1, str2);
        System.out.println(count);
    }

}
