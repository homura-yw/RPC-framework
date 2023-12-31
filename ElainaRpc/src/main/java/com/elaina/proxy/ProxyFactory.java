package com.elaina.proxy;

import com.elaina.HelloService;
import com.elaina.common.Invocation;
import com.elaina.common.URL;
import com.elaina.loadbalance.LoadBalance;
import com.elaina.protocol.HttpClient;
import com.elaina.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {
    public static <T> T getProxy(final Class interfaceClass){
        //用户配置

        Object proxyInstance = Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Invocation invocation = new Invocation(
                                HelloService.class.getName(),
                                method.getName(),
                                method.getParameterTypes(),
                                args
                        );
                        HttpClient httpClient = new HttpClient();
                        //服务发现
                        MapRemoteRegister mapRemoteRegister = new MapRemoteRegister();
                        List<URL> list = mapRemoteRegister.get(interfaceClass.getName());

                        //负载均衡
                        URL url = LoadBalance.random(list);

                        String res = httpClient.send(url.getHostname(), url.getPort(), invocation);
                        return res;
                    }
                }
        );
        return (T) proxyInstance;
    }
}
