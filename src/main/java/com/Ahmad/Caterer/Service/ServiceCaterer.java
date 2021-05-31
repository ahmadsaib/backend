package com.Ahmad.Caterer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Ahmad.Caterer.Pojo.Caterer;
import com.Ahmad.Caterer.Repository.RepositoryCaterer;

@Service
public class ServiceCaterer {
	
	@Autowired
	RepositoryCaterer repo;
	
	public Caterer addCaterer(Caterer caterer) {
		return repo.save(caterer);
	}

	public Page<Caterer> getKeywordCaterer(Pageable pageable, String keyword) {
		return repo.findCaterer(keyword, pageable);
		
	}

	public Page<Caterer> findAllCaterer(Pageable pageable) {
		return repo.findAll(pageable);
	}

}
