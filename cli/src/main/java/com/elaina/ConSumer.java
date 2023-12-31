package com.elaina;

import com.elaina.proxy.ProxyFactory;

public class ConSumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String res = helloService.sayHello(" Elaina1v");
        System.out.println(res);
    }
}
