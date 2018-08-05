package com.mj.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.mj.util.EntityRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host; // Redis服务器地址
	@Value("${spring.redis.port}")
	private int port; // Redis服务器连接端口
	@Value("${spring.redis.password}")
	private String password; // Redis服务器连接密码（默认为空）
	@Value("${spring.redis.timeout}")
	private int timeout; // 连接超时时间（毫秒）
	@Value("${spring.redis.database}")
	private int database; // 连接超时时间（毫秒）
	@Value("${spring.redis.jedis.pool.max-wait}")
	private int maxWaitMillis; // 连接池最大阻塞等待时间（使用负值表示没有限制）
	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle; // 连接池中的最大空闲连接

	/**
	 * 配置JedisPoolConfig
	 * 
	 * @return JedisPoolConfig实体
	 */
	@Bean(name = "jedisPoolConfig")
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// jedisPoolConfig.setMaxTotal(this.maxTotal); // 连接池最大连接数（使用负值表示没有限制）
		jedisPoolConfig.setMaxWaitMillis(this.maxWaitMillis); // 连接池最大阻塞等待时间（使用负值表示没有限制）
		jedisPoolConfig.setMaxIdle(this.maxIdle); // 连接池中的最大空闲连接
		// jedisPoolConfig.setMinIdle(this.minIdle); // 连接池中的最小空闲连接
		// jedisPoolConfig.setTestOnBorrow(true);
		// jedisPoolConfig.setTestOnCreate(true);
		// jedisPoolConfig.setTestWhileIdle(true);
		return jedisPoolConfig;
	}

	/**
	 * 实例化 RedisConnectionFactory 对象
	 * 
	 * @param poolConfig
	 * @return
	 */
	@Bean(name = "jedisConnectionFactory")
	public RedisConnectionFactory jedisConnectionFactory(
			@Qualifier(value = "jedisPoolConfig") JedisPoolConfig poolConfig) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
		return jedisConnectionFactory;
	}

	/**
	 * 实例化 RedisTemplate 对象
	 * 
	 * @return
	 */
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, String> functionDomainRedisTemplate(
			@Qualifier(value = "jedisConnectionFactory") RedisConnectionFactory factory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new EntityRedisSerializer());
		redisTemplate.setValueSerializer(new EntityRedisSerializer());
		redisTemplate.afterPropertiesSet();
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

}
