/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package nl.derf.HelloRedis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class App {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String KEY = "name";
    private static final String VALUE = "fred";

    private static void putSomething(Jedis jedis) {
        if (jedis.get(KEY) == null) {
            jedis.set(KEY, VALUE);
            String value = jedis.get(KEY);
            System.out.println("Round tripped name: " + value);
        }
    }

    public static void main(String[] args) throws IOException  {
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        Jedis jedis = new Jedis("localhost");

        try {
            putSomething(jedis);

            // Dump
            byte[] dumpedVal = jedis.dump(KEY);
            if (dumpedVal == null) {
                throw new RuntimeException("No data received - does the key exist?");
            }

            RedisDumpedKeyValue redisKeyValue = new RedisDumpedKeyValue(KEY,dumpedVal);
            RedisDumpedKeyValue[] redisKeyValues = {redisKeyValue};

            MAPPER.writeValue(new File("/tmp/test.json"), redisKeyValues);
            System.out.println("Wrote JSON file to /tmp/test.json");

            // Restore

            RedisDumpedKeyValue[] deserialised = MAPPER.readValue(new File("/tmp/test.json"), RedisDumpedKeyValue[].class);
            assert(Arrays.equals(redisKeyValues[0].getValue(), deserialised[0].getValue()));


        } catch (JedisException je) {
            System.out.println("Could not connect to Redis: " + je);
        }
    }
}
