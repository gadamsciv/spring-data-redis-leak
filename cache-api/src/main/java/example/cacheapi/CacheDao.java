package example.cacheapi;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheDao {
	
	private final Logger log = LoggerFactory.getLogger(CacheDao.class);

	@Cacheable(cacheNames = "test")
	public String getValue() {
		log.debug("cache entry missing, getting value");
		return "Hello, it's " + LocalDateTime.now();
	}
}
