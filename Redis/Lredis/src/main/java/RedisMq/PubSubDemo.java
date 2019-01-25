package RedisMq;

import Util.ReadProperties;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

public class PubSubDemo {

    public static void main( String[] args )
    {
        Properties pps = ReadProperties.loadProperties("redis.properties");
        String redisIp = pps.getProperty("redis.Ip","127.0.0.1");

        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), redisIp, 6379);

        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", redisIp, 6379));

        //订阅者
        SubThread subThread = new SubThread(jedisPool);
        subThread.start();

        //发布者
        Publisher publisher = new Publisher(jedisPool);
        publisher.start();
    }
}
