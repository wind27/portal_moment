
package com.wind.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP.Connection.Tune;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Pool;


@Component
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    //@Resource(name = "jedisPool")
    protected Pool<Jedis> jedisPool;//可做主从的JedisSentinelPool


    public Pool<Jedis> getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(Pool<Jedis> jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 根据pattern获取匹配的所有key <br/>
     * 例如：keys * 返回所有key
     *
     * @param pattern
     * @return
     * @author sumory.wu @date 2014年8月14日 下午5:38:28
     */
    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        Set<String> result = null;

        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.keys(pattern);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }

    //~===============set相关===============================

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略<br>
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合
     * 例如：
     * <p/>
     * <pre>
     * # 添加单个元素
     * redis> SADD bbs "discuz.net"
     * (integer) 1
     *
     * # 添加重复元素
     * redis> SADD bbs "discuz.net"
     * (integer) 0
     *
     * # 添加多个元素
     * redis> SADD bbs "tianya.cn" "groups.google.com"
     * (integer) 2
     *
     * redis> SMEMBERS bbs
     * 1) "discuz.net"
     * 2) "groups.google.com"
     * 3) "tianya.cn"
     * </pre>
     *
     * @param key
     * @param members
     * @return
     * @author sumory.wu @date 2014年8月14日 下午5:46:37
     */
    public long sadd(String key, String... members) {
        long result = Long.MIN_VALUE;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.sadd(key, members);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }

    /**
     * 从集合key中删除members，不存在的member会被忽略
     *
     * @param key
     * @param members
     * @return 被成功移除的元素的数量，不包括被忽略的元素
     * @author sumory.wu @date 2014年8月15日 上午11:19:22
     */
    public long srem(String key, String... members) {
        long result = Long.MIN_VALUE;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.srem(key, members);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }

    /**
     * 返回集合 key 中的所有成员 <br/>
     * 例如：
     * <p/>
     * <pre>
     * redis> SADD language Ruby Python Clojure
     * (integer) 3
     *
     * redis> SMEMBERS language
     * 1) "Python"
     * 2) "Ruby"
     * 3) "Clojure"
     * </pre>
     *
     * @param key
     * @return
     * @author sumory.wu @date 2014年8月14日 下午5:42:11
     */
    public Set<String> smembers(String key) {
        Set<String> result = null;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.smembers(key);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }

    //~===============string、对象相关===============================

    /**
     * 获取key值，值类型为string
     *
     * @param key
     * @return
     * @author sumory.wu @date 2014年8月14日 下午5:50:31
     */
    public String getString(String key) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.get(key);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }

    /**
     * 设置key值，值类型为string
     *
     * @param key
     * @param value
     * @return
     * @author sumory.wu @date 2014年8月14日 下午5:52:14
     */
    public String setString(String key, String value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.set(key, value);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }

    /**
     * 根据key获取一个对象
     *
     * @param key
     * @return
     * @author sumory.wu @date 2014年8月14日 下午5:59:09
     */
    public Object get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object result = null;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            byte[] bytes = jedis.get(getKey(key));
            if (bytes != null) {
                result = bytes2Object(bytes);
            }
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }



    /**
     * 根据key获取一个对象
     *
     * @param key
     * @return
     * @author sumory.wu @date 2014年8月14日 下午5:59:09
     */
    public Object getForString(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object result = null;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            Object value = jedis.get(key);
            if (value != null &&!("null").equals(value)) {
                result = value;
            }
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }




    /**
     * 根据key获取一个对象
     *
     * @param key
     * @return
     * @author sumory.wu @date 2014年8月14日 下午5:59:09
     */
    public long ttl(String key) {
        if (StringUtils.isBlank(key)) {
            return 0;
        }
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            if (jedis.exists(key)) {
                result = jedis.ttl(key);
            } else {
                return 0;
            }
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }

    /**
     * 保存一个对象
     *
     * @param key
     * @param value 需实现了Serializable接口
     * @author sumory.wu @date 2014年8月14日 下午6:01:19
     */
    public void set(String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            jedis.set(getKey(key), object2Bytes(value));


        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }


    //~===============map相关===============================

    /**
     * 获取Map,这个Map是是Java中的Map<String, Object>类型，最终序列化后被redis存储
     *
     * @param key
     * @return
     * @author sumory.wu @date 2014年8月14日 下午6:08:35
     */
    public Map<String, Object> getMap(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object obj = this.get(key);
        if (obj != null && obj instanceof Map) {
            return (Map<String, Object>) obj;
        } else {
            return null;
        }
    }

    /**
     * 存储Map,这个Map是是Java中的Map<String, Object>类型，最终序列化后被redis存储
     *
     * @param key
     * @param map
     * @author sumory.wu @date 2014年8月15日 上午9:28:10
     */
    public void setMap(String key, Map<String, Object> map) {
        this.set(key, map);
    }

    /**
     * 同时将多个field-value (域-值)对设置到哈希表 key 中，使用redis的hmset命令存储，<br/>
     * 使用场景：可用于初始化一个map，也可用于批量修改某个map里的某些值
     * <p/>
     * hmset示例：
     * <p/>
     * <pre>
     * redis> HMSET website google www.google.com yahoo www.yahoo.com
     * OK
     *
     * redis> HGET website google
     * "www.google.com"
     *
     * redis> HGET website yahoo
     * "www.yahoo.com"
     * </pre>
     *
     * @param key
     * @param map
     * @author sumory.wu @date 2014年8月15日 上午9:34:52
     */
    public void setRedisMap(String key, Map<String, String> map) {
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            if (map != null && !map.isEmpty()) {
                jedis.hmset(key, map);
            }
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 将哈希表 key 中的域 field 的值设为 value<br/>
     *
     * @param key
     * @param field
     * @param value
     * @author sumory.wu @date 2014年8月15日 下午12:04:52
     */
    public void setRedisMap(String key, String field, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
            return;
        }
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            jedis.hset(key, field, value);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 返回哈希表 key 中，所有的域和值，使用redis的hgetall命令，Jedis的自动将hgetall命令的返回结果包装成了Java Map
     * <p/>
     * hgetall示例：
     * <p/>
     * <pre>
     * redis> HSET people jack "Jack Sparrow"
     * (integer) 1
     *
     * redis> HSET people gump "Forrest Gump"
     * (integer) 1
     *
     * redis> HGETALL people
     * 1) "jack"          # 域
     * 2) "Jack Sparrow"  # 值
     * 3) "gump"
     * 4) "Forrest Gump"
     * </pre>
     *
     * @param key
     * @return
     * @author sumory.wu @date 2014年8月15日 上午9:46:00
     */
    public Map<String, String> getRedisMap(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Jedis jedis = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            jedis = (Jedis) jedisPool.getResource();
            map = jedis.hgetAll(key);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return map;
    }

    /**
     * 返回哈希表 key 中给定域 field 的值
     *
     * @param key
     * @param field
     * @return
     * @author sumory.wu @date 2014年8月15日 下午12:08:10
     */
    public String getRedisMap(String key, String field) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Jedis jedis = null;
        String value = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            value = jedis.hget(key, field);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return value;
    }

    /**
     * 从redis map中返回哈希表 key 中给定域 mapKey 的值
     *
     * @param key    map key
     * @param mapKey map里某个域的key
     */
    public String getFromRedisMap(String key, String mapKey) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(mapKey)) {
            return "";
        }
        Jedis jedis = null;
        String value = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            value = jedis.hget(key, mapKey);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return value;
    }

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     * @return
     * @author sumory.wu @date 2014年8月15日 上午11:27:21
     */
    public long deleteFromRedisMap(String key, String... fields) {
        if (StringUtils.isBlank(key) || fields == null || fields.length == 0) {
            return 0L;
        }
        Jedis jedis = null;
        Long count = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            count = jedis.hdel(key, fields);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return count;
    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 increment <br>
     * <p/>
     * 使用场景：
     * # increment可为负数
     * <p/>
     * <pre>
     * redis> HGET counter page_view
     * "200"
     *
     * redis> HINCRBY counter page_view -50
     * (integer) 150
     *
     * redis> HGET counter page_view
     * "150"
     * </pre>
     *
     * @param key
     * @param field
     * @param increment 须为整形
     * @return
     * @author sumory.wu @date 2014年8月15日 上午9:52:14
     */
    public long incrMapValue(String key, String field, long increment) {
        long result = Long.MIN_VALUE;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.hincrBy(key, field, increment);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }

    //~===============过期相关===============================

    /**
     * 设置过期时间点 <br/>
     * <p/>
     * 例如：
     * expireat abc 1408071100 即设置到2014/8/15 10:51:40过期
     *
     * @param key
     * @param timestamp 秒数，从“January 1, 1970 UTC”到设定的过期时间的秒数，即unix时间戳
     * @return 设置成功返回1
     * @author sumory.wu @date 2014年8月15日 上午10:37:30
     */
    public long expireAt(String key, long unixTime) {
        long result = Integer.MIN_VALUE;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.expireAt(key, unixTime);//第二个参数是秒数
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return (result == 0 ? Integer.MIN_VALUE : result);
    }

    /**
     * 设置在多少秒后过期
     *
     * @param key
     * @param seconds
     * @return 设置成功返回1
     * @author sumory.wu @date 2014年8月15日 上午10:49:15
     */
    public long expire(String key, int seconds) {
        long result = Integer.MIN_VALUE;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.expire(key, seconds);//第二个参数是秒数
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return (result == 0 ? Integer.MIN_VALUE : result);
    }

    //~===============判断是否存在===============================

    /**
     * 判断key是否存在
     *
     * @param key
     * @return 若 key 存在，返回 1 ，否则返回 0，key过期也会返回0
     * @author sumory.wu @date 2014年8月15日 上午10:57:50
     */
    public boolean exists(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        Jedis jedis = null;
        boolean rest = false;
        try {
            jedis = (Jedis) jedisPool.getResource();
            rest = jedis.exists(key);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    /**
     * 查看哈希表 key 中，给定域 mapKey 是否存在<br/>
     *
     * @param key
     * @param mapKey
     * @return 如果哈希表含有给定域，返回 1 。 如果哈希表不含有给定域，或 key 不存在，返回 0 。
     * @author sumory.wu @date 2014年8月15日 上午10:58:05
     */
    public boolean hexists(String key, String mapKey) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        Jedis jedis = null;
        boolean rest = false;
        try {
            jedis = (Jedis) jedisPool.getResource();
            rest = jedis.hexists(key, mapKey);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    //~===============删除===============================

    /**
     * 删除key
     *
     * @param key
     * @return 被删除key的数量
     * @author sumory.wu @date 2014年8月15日 上午11:00:58
     */
    public long del(String... key) {
        if (key == null || key.length == 0) {
            return 0L;
        }
        Jedis jedis = null;
        Long rest = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            rest = jedis.del(key);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    //~====================list列表相关====================

    /**
     * 返回列表的长度
     *
     * @param listKey
     * @return 不存在返回0
     * @author sumory.wu @date 2014年8月15日 下午12:34:33
     */
    public long lengthOfList(String listKey) {
        Jedis jedis = null;
        Long rest = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            rest = jedis.llen(listKey);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    /**
     * 存入列表尾，listkey不存在会被创建<br/>
     *
     * @param listKey
     * @param values
     * @return 返回插入后列表的长度
     */
    public long pushToListTail(String listKey, String... values) {
        Jedis jedis = null;
        long rest = Long.MIN_VALUE;
        try {
            jedis = (Jedis) jedisPool.getResource();
            if (listKey != null && values != null && values.length != 0) {
                rest = jedis.rpush(listKey, values);
            }
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    /**
     * 移除并返回列表 key 的尾元素<br/>
     *
     * @param listKey
     * @return 列表的尾元素，没有返回NULL
     */
    public String popFromListTail(String listKey) {
        Jedis jedis = null;
        String rest = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            rest = jedis.rpop(listKey);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    /**
     * 存入列表头，listkey不存在会被创建<br/>
     *
     * @param listKey
     * @param values
     * @return 返回插入后列表的长度
     */
    public long pushToListHead(String listKey, String... values) {
        Jedis jedis = null;
        long rest = Long.MIN_VALUE;
        try {
            jedis = (Jedis) jedisPool.getResource();
            if (listKey != null && values != null && values.length != 0) {
                rest = jedis.lpush(listKey, values);
            }
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    /**
     * 移除并返回列表 key 的头元素<br/>
     *
     * @param listKey
     * @return 列表的头元素，没有返回NULL
     */
    public String popFromListHead(String listKey) {
        Jedis jedis = null;
        String rest = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            rest = jedis.lpop(listKey);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    /**
     * 从列表中取出某个元素<br/>
     * <p/>
     * 以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br/>
     * 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * <p/>
     * 例如：
     * <p/>
     * <pre>
     * redis> LPUSH mylist "World"
     * (integer) 1
     *
     * redis> LPUSH mylist "Hello"
     * (integer) 2
     *
     * redis> LINDEX mylist 0
     * "Hello"
     *
     * redis> LINDEX mylist -1
     * "World"
     *
     * redis> LINDEX mylist 3 # index不在 mylist 的区间范围内
     * (nil)
     * </pre>
     *
     * @param key
     * @param index
     * @return 如果 index 参数的值不在列表的区间范围内(out of range)，返回 nil 。
     * @author sumory.wu @date 2014年8月18日 上午11:45:52
     */
    public String getFromList(String key, int index) {
        Jedis jedis = null;
        String rest = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            rest = jedis.lindex(key, index);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    /**
     * 从listKey列表中删除value元素
     *
     * @param listKey
     * @param value
     * @return 被移除元素的数量
     * @author sumory.wu @date 2014年8月15日 下午12:24:17
     */
    public Long removeFromList(String listKey, String value) {
        Jedis jedis = null;
        long rest = Long.MIN_VALUE;
        try {
            jedis = (Jedis) jedisPool.getResource();
            if (listKey != null && value != null) {
                rest = jedis.lrem(listKey, 0, value);//第二个参数为0表示全部删除匹配的元素
            }
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return rest;
    }

    //~====================Object list列表相关====================

    /**
     * 获取所有列表(默认从左边第一个开始)
     *
     * @param listKey
     * @return
     */
    public List<Object> getListAll(String listKey) {
        List<byte[]> data = getListAll(getKey(listKey));
        List<Object> result = new ArrayList<Object>();
        if (data != null && data.size() > 0) {
            for (byte[] item : data) {
                result.add(bytes2Object(item));
            }
            return result;
        }
        return null;
    }

    /**
     * 获取列表所有数据
     *
     * @param listKey
     * @return
     */
    public List<byte[]> getListAll(byte[] listKey) {
        Jedis jedis = (Jedis) jedisPool.getResource();
        if (jedis == null) {
            return null;
        }
        try {
            List<byte[]> data = jedis.lrange(listKey, 0, 1000000000); // 默认设置一个大数
            return data;
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return null;
    }

    /**
     * 从左边添加到list
     *
     * @param listKey
     * @param value
     */
    public void addToListLeft(String listKey, Object value) {
        addToListLeft(getKey(listKey), object2Bytes(value));
    }

    /**
     * 从左边添加到list
     *
     * @param listKey
     * @param value
     */
    public void addToListLeft(byte[] listKey, byte[] value) {
        Jedis jedis = (Jedis) jedisPool.getResource();
        if (jedis == null) {
            return;
        }
        try {
            jedis.lpush(listKey, value);

        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 从右边添加到list
     *
     * @param listKey
     * @param value
     */
    public void addToListRight(String listKey, Object value) {
        addToListRight(getKey(listKey), object2Bytes(value));
    }

    /**
     * 从右边添加到list
     *
     * @param listKey
     * @param value
     */
    public void addToListRight(byte[] listKey, byte[] value) {
        Jedis jedis = (Jedis) jedisPool.getResource();
        if (jedis == null) {
            return;
        }
        try {
            jedis.rpush(listKey, value);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 从左边移除一个对象，并返回该对象
     *
     * @param listKey
     * @return
     */
    public Object popFromListLeft(String listKey) {
        return bytes2Object(popFromListLeft(getKey(listKey)));
    }

    /**
     * 从左边移除一个对象，并返回该对象
     *
     * @param listKey
     * @return
     */
    public byte[] popFromListLeft(byte[] listKey) {
        Jedis jedis = (Jedis) jedisPool.getResource();
        if (jedis == null) {
            return null;
        }
        try {
            byte[] data = jedis.lpop(listKey);
            return data;
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return null;
    }

    /**
     * 从右边移除一个对象，并返回该对象
     *
     * @param listKey
     * @return
     */
    public Object popFromListRight(String listKey) {
        return bytes2Object(popFromListRight(getKey(listKey)));
    }

    /**
     * 从右边移除一个对象，并返回该对象
     *
     * @param listKey
     * @return
     */
    public byte[] popFromListRight(byte[] listKey) {
        Jedis jedis = (Jedis) jedisPool.getResource();
        if (jedis == null) {
            return null;
        }
        try {
            byte[] data = jedis.rpop(listKey);
            return data;
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return null;
    }

    //    /**  测试未通过  暂时不用
    //     * 获取列表长度
    //     * @param listKey
    //     * @return
    //     */
    //    public long getLengthOfListByKey(String listKey) {
    //        return getLengthOfList(getKey(listKey));
    //    }
    //     
    //    /**
    //     * 获取列表长度
    //     * @param listKey
    //     * @return
    //     */
    //    public long getLengthOfList(byte[] listKey) {
    //        Jedis jedis = (Jedis) jedisPool.getResource();
    //        if (jedis == null) {
    //            return 0;
    //        }
    //        try {
    //            long length = jedis.llen(listKey);
    //            return length;
    //        } catch (JedisConnectionException e) {
    //            logger.error("Jedis Connection error", e);
    //            if (jedis != null) {
    //                jedisPool.returnBrokenResource(jedis);
    //                jedis = null;
    //            }
    //            return 0;
    //        }
    //        catch (Exception e) {
    //            logger.error(e.getMessage(), e);
    //            return 0;
    //        }
    //        finally {
    //            if (jedis != null) {
    //                this.jedisPool.returnResource(jedis);
    //                return 0;
    //            }
    //        }
    //    }

    /**
     * 获取list某一范围的段
     *
     * @param listKey
     * @param start
     * @param size
     * @return
     */
    public List<Object> getListRange(String listKey, int start, int size) {
        List<byte[]> data = getListRange(getKey(listKey), start, start + size - 1);
        if (data != null && data.size() > 0) {
            List<Object> result = new ArrayList<Object>();
            for (byte[] item : data) {
                result.add(bytes2Object(item));
            }
            return result;
        }
        return null;
    }

    /**
     * 获取list某一范围的段
     *
     * @param listKey
     * @param start
     * @param size
     * @return
     */
    public List<byte[]> getListRange(byte[] listKey, int start, int size) {
        Jedis jedis = (Jedis) jedisPool.getResource();
        if (jedis == null) {
            return null;
        }
        try {
            List<byte[]> data = jedis.lrange(listKey, start, start + size - 1);
            return data;
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                this.jedisPool.returnResource(jedis);
            }
        }
        return null;
    }

    //~=============工具========================

    /**
     * 字节转化为对象
     *
     * @param bytes
     * @return
     */
    private Object bytes2Object(byte[] bytes) {
        if (bytes == null || bytes.length == 0)
            return null;
        try {
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            Object obj = inputStream.readObject();
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转化为字节
     *
     * @param value
     * @return
     */
    private byte[] object2Bytes(Object value) {
        if (value == null)
            return null;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(arrayOutputStream);
            outputStream.writeObject(value);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                arrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayOutputStream.toByteArray();
    }

    private byte[] getKey(String key) {
        return key.getBytes();
    }

    //-------------------------------------------------------------------------------------------

    public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
        Set<Tuple> result = null;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            result = jedis.zrevrangeWithScores(key, start, end);
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }

    /**
     * 根据key获取一个对象
     *
     * @param key
     * @return
     * @author sumory.wu @date 2014年8月14日 下午5:59:09
     */
    /**
     * 根据key和field获取一个对象
     * 
     * @author qianchun  @date 2016年1月12日 下午2:10:58
     * @param key
     * @param field
     * @return
     */
    public Object hget(String key, String field) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object result = null;
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            byte[] bytes = jedis.hget(getKey(key), getKey(field));
            if (bytes != null) {
                result = bytes2Object(bytes);
            }
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }

        return result;
    }
    
    /**
     * hset 
     * 
     * @author qianchun  @date 2016年1月12日 下午2:13:57
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        Jedis jedis = null;
        try {
            jedis = (Jedis) jedisPool.getResource();
            jedis.hset(getKey(key), getKey(field), object2Bytes(value));
        } catch (JedisConnectionException e) {
            logger.error("Jedis Connection error", e);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }
}
