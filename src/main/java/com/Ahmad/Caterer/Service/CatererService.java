package com.Ahmad.Caterer.Service;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Ahmad.Caterer.Exception.GlobalExceptionHandler;
import com.Ahmad.Caterer.Pojo.Caterer;

public interface CatererService {

	public Caterer addCaterer(Caterer caterer) throws ConstraintViolationException, GlobalExceptionHandler;

	public Page<Caterer> getKeywordCaterer(Pageable pageable, String keyword);

	public Page<Caterer> findAllCaterer(Pageable pageable);

	public Optional<Caterer> findCaterer(String iD);

}
