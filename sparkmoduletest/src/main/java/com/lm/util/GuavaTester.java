package com.lm.util;

import com.google.common.base.CaseFormat;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Classname GuavaTester
 * @Description TODO
 * @Date 2020/1/9 13:57
 * @Created by limeng
 */
public class GuavaTester {


    @Test
    public void test() {
//        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));//testData
//        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));//testData
//        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));//TestData

       // System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testdata"));//testdata
//        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));//test_data
//        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));//test-data


        Object object = null;
        System.out.println(object.toString());
    }
    @Test
    public void testString(){
        String str="--dbSQL=select id,name,desc1,age from test";
        int index = str.indexOf('=');
        Assert.assertNotNull(index);
    }

}
