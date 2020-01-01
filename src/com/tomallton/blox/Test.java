package com.tomallton.blox;

import java.io.ByteArrayInputStream;

import com.tomallton.blox.jsonparser.JsonParser;

public class Test {

    public static void main(String[] args) throws Exception {
        print("");
    }
    
    static void print(String s) throws Exception{
        System.out.println(new JsonParser(new ByteArrayInputStream((s).getBytes())).parse());
    }

}