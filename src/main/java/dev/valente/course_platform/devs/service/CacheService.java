package dev.valente.course_platform.devs.service;

import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CacheService {

    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void evictCache(String cacheName, UUID key){
        cacheManager.getCache(cacheName).evict(key);

    }

    public void putCache(String cacheName, UUID key, DevsResponseDTO devsResponseDTO){
        cacheManager.getCache(cacheName).put(key, devsResponseDTO);
    }

    @Scheduled(fixedRateString = "${caching.spring.devsTTL}")
    public void evictAllCaches(){
        for (String cacheName : cacheManager.getCacheNames()) {
            try{
                cacheManager.getCache(cacheName).clear();
            } catch (NullPointerException error){
                error.getMessage();
            }
        }

    }

}
