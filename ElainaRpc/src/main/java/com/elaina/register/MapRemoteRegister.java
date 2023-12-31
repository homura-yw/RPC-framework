package com.elaina.register;

import com.elaina.common.URL;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class MapRemoteRegister {
    private static Jedis jedis;
    public MapRemoteRegister() {
        jedis = JedisLinkPool.getJedis();
        jedis.select(10);
    }
    public void regist(String interfaceName, URL url){
        jedis.lpush(interfaceName, url.toString());
    }
    public List<URL> get(String interfaceName) {
        long len = jedis.llen(interfaceName);
        List<String> list = jedis.lrange(interfaceName, 0, len - 1);
        List<URL> res = new ArrayList<>();
        for(String str : list) {
            String[] strs = str.split(":");
            res.add(new URL(strs[0], Integer.parseInt(strs[1])));
        }
        return res;
    }
    @Override
    protected void finalize(){
        //jedis.flushDB();
        if(jedis != null) jedis.close();
    }
}
