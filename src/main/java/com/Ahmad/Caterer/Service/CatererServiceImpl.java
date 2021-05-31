package com.Ahmad.Caterer.Service;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Ahmad.Caterer.Exception.GlobalExceptionHandler;
import com.Ahmad.Caterer.Pojo.Caterer;
import com.Ahmad.Caterer.Repository.RepositoryCaterer;

@Service("catereservice")
public class CatererServiceImpl implements CatererService {

	@Autowired
	RepositoryCaterer repo;

	@Autowired
	CacheManager cacheManager;

	public Caterer addCaterer(Caterer caterer) throws ConstraintViolationException, GlobalExceptionHandler {
		Caterer cater = repo.findByEmail(caterer.getEmail());
		if (cater != null) {
			throw new GlobalExceptionHandler(GlobalExceptionHandler.CatererAlreadyExists());
		}
		cater = repo.save(caterer);
		if (cater != null) {
			for (String name : cacheManager.getCacheNames()) {
				cacheManager.getCache(name).clear();
			}
			return cater;
		}
		return null;
	}

	@Cacheable(cacheNames = "keywordCaterers")
	public Page<Caterer> getKeywordCaterer(Pageable pageable, String keyword) {
		return repo.findCaterer(keyword, pageable);

	}

	@Cacheable(cacheNames = "caterers")
	public Page<Caterer> findAllCaterer(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Cacheable(cacheNames = "allCaterers", key = "#iD")
	public Optional<Caterer> findCaterer(String iD) {
		return repo.findById(iD);
	}
}
