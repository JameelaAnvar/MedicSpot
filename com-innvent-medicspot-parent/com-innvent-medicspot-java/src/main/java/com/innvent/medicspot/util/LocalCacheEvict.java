package com.innvent.medicspot.util;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component

public class LocalCacheEvict {

	@CacheEvict(cacheNames = "branddrugs", allEntries = true)
	public void evictBrandDrugs() {
	}

	@CacheEvict(cacheNames = "medicinesalt", allEntries = true)
	public void evictMedicineSalt() {
	}
}
