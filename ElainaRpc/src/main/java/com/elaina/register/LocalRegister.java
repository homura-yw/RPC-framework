package com.elaina.register;

import java.util.HashMap;
import java.util.Map;

public class LocalRegister {
    private static Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceName, String version, Class impClass){
        map.put(interfaceName + version, impClass);
    }
    public static Class get(String interfaceName, String version) {
        return map.get(interfaceName + version);
    }
}
