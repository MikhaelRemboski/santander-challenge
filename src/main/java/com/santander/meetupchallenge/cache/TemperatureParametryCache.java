package com.santander.meetupchallenge.cache;

import com.santander.meetupchallenge.domain.Weather;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class TemperatureParametryCache {
	
	@PersistenceContext
	private EntityManager em;
	
	@Cacheable("valuesMap")
	public List<Weather> getParametry() {
		return em.createQuery("from Weather", Weather.class).getResultList();
	}
	
	@CachePut(value = "valuesMap")
	public List<Weather> refresh() {
		return getParametry();
	}

}
