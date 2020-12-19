package com.kevin.auto.fill;

import com.kevin.auto.fill.functions.Helper;

public class TestClass {
    public static void main(String[] args) {
        testMethod(1, 2.2, "test", "method", "");
    }

    public static void testMethod(int arg1, double arg2, String... args) {
        System.out.printf("arg1: %s%n", arg1);
        System.out.printf("arg2: %s%n", arg2);
        System.out.printf("args length: %s%n", args.length);
    }
}
