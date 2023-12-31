package com.elaina;

public class HelloServiceImp implements HelloService{
    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }

}
