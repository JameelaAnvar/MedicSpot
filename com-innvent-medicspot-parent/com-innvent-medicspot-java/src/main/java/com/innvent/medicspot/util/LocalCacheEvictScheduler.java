package com.innvent.medicspot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.innvent.medicspot.util.LocalCacheEvict;

@Component
public class LocalCacheEvictScheduler {

	@Autowired
	private LocalCacheEvict localCacheEvict;

	@Scheduled(fixedDelay = 600000)
	public void clearCaches() {

		localCacheEvict.evictBrandDrugs();
		localCacheEvict.evictMedicineSalt();
	}

}
