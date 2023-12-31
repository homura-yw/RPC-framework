import com.elaina.register.JedisLinkPool;
import redis.clients.jedis.Jedis;

public class test {
    private static Jedis jedis = new JedisLinkPool().getJedis();
    public static void main(String[] args) {
        jedis.lpush("com.elaina.HelloService", "localhost:8080");
    }
}
