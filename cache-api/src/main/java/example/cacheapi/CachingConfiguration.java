package example.cacheapi;


import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableCaching
public class CachingConfiguration {
	
	private ObjectMapper _redisObjectMapper;
	
	private synchronized ObjectMapper getRedisObjectMapper() {
		if (_redisObjectMapper == null) {
			_redisObjectMapper = new ObjectMapper();
			_redisObjectMapper.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
			_redisObjectMapper.registerModule(new JavaTimeModule());
		}
		return _redisObjectMapper;
	}
	
	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {
		
		return RedisCacheConfiguration.defaultCacheConfig()
			.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
			.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(getRedisObjectMapper())))
			.entryTtl(Duration.ofMinutes(5));
	}
}
