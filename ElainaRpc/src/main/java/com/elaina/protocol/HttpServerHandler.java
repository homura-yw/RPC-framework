package com.elaina.protocol;

import com.elaina.common.Invocation;
import com.elaina.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp)  {
        //处理请求-->方法
        try {
            Invocation invocation = (Invocation)new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();

            Class classImp = LocalRegister.get(interfaceName, "1.0");
            Method method = classImp.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            String res = (String) method.invoke(classImp.newInstance(), invocation.getParameters());

            IOUtils.write(res, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
