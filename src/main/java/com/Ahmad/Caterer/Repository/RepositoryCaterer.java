package com.Ahmad.Caterer.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.Ahmad.Caterer.Pojo.Caterer;

@Repository
public interface RepositoryCaterer extends MongoRepository<Caterer, String> {
	
	@Query("{$or :[{name:{ $regex:?0}},{'location.city':{ $regex:?0}}, {email:{ $regex:?0}}]}")
	public Page<Caterer> findCaterer(String searchText, Pageable pageable);
	
	public Caterer findByEmail(String email);

}
