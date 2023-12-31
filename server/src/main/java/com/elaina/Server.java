package com.elaina;

import com.elaina.common.URL;
import com.elaina.protocol.HttpServer;
import com.elaina.register.LocalRegister;
import com.elaina.register.MapRemoteRegister;
public class Server {
    public static void main(String[] args) {

        //本地注册
        LocalRegister.regist(HelloService.class.getName(), "1.0", HelloServiceImp.class);

        //注册中心注册
        URL url = new URL("localhost", 8080);
        MapRemoteRegister mapRemoteRegister = new MapRemoteRegister();
        mapRemoteRegister.regist(HelloService.class.getName(), url);

        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
