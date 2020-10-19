package com.faker.android;

import brut.androlib.BaseTest;
import org.junit.Test;

public class Main extends BaseTest {

    @Test
    public void test1(){
        MyClass.fakerMethod("123");
    }
}
