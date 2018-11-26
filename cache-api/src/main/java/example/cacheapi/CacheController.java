package example.cacheapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
	
	private final CacheDao cacheDao;
	
	public CacheController(CacheDao cacheDao) {
		this.cacheDao = cacheDao;
	}

	@GetMapping(path = "/cache/get")
	public String getValue() {
		return cacheDao.getValue();
	}
}
