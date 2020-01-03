package com.tomallton.blox;

import java.io.ByteArrayInputStream;

import com.tomallton.blox.parser.Parser;

public class Test {

    public static void main(String[] args) throws Exception {
        print("{\"Tom\": 21, \"Jacob\": 16, \"Tom\": 18}");
    }

    static void print(String s) throws Exception {
        System.out.println(new Parser(new ByteArrayInputStream(s.getBytes())).parse());
    }

}