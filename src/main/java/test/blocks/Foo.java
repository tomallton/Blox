package test.blocks;

import java.util.List;

public class Foo {

    public Foo(List<String> cur) {
        System.out.println(cur);
//        System.out.println(String.join(",", cur.stream().map(Object::toString).collect(Collectors.toList())));
    }

}