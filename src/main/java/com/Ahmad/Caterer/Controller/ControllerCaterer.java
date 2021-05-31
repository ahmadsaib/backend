package com.Ahmad.Caterer.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Ahmad.Caterer.Exception.CatererNotFoundException;
import com.Ahmad.Caterer.Exception.GlobalExceptionHandler;
import com.Ahmad.Caterer.Pojo.Caterer;
import com.Ahmad.Caterer.Service.CatererService;
import com.Ahmad.Caterer.Service.ProducerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/caterer", produces = "application/hal+json")
public class ControllerCaterer {

	@Autowired
	@Qualifier("catereservice")
	CatererService service;

	@Autowired
	ProducerService producer;

	private final Logger logger = LoggerFactory.getLogger(ControllerCaterer.class);

	@PostMapping(value = "/addCaterer")
	public ResponseEntity<?> AddCaterer(@RequestBody Caterer caterer) {
		try {
			logger.info("Request Caterer data " + caterer.toString());
			Caterer cater = service.addCaterer(caterer);
			if (cater != null) {
				this.producer.sendMessage(cater.toString());
				logger.info("Document saved successfully ....." + cater.toString());
				final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
						.buildAndExpand(cater.getID()).toUri();
				return ResponseEntity.created(uri).body(new CatererResource(cater));
			}
		} catch (ConstraintViolationException e) {
			logger.error("Getting error as ViolationException while saving ....." + caterer.getName() + ": "
					+ e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (GlobalExceptionHandler e) {
			logger.error("Getting error as ExceptionHandler ....." + caterer.getName() + ": " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return null;
	}

	@GetMapping(value = "/getViewKeywordCaterer")
	public ResponseEntity<Map<String, Object>> getViewKeywordCaterer(Pageable pageable, @RequestParam String keyword) {
		try {
			logger.info("Request Pageable data , page size , page number values" + keyword + ", "
					+ pageable.getPageNumber() + ", " + pageable.getPageSize());
			Page<Caterer> pagable = service.getKeywordCaterer(pageable, keyword);
			List<CatererResource> pageabl = pagable.stream().map(CatererResource::new).collect(Collectors.toList());
			final Resources<CatererResource> resources = new Resources<>(pageabl);
			final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
			resources.add(new Link(uriString, "self"));

			Map<String, Object> mapdata = new HashMap<String, Object>();
			mapdata.put("pagabledata", resources);
			mapdata.put("number", pagable.getNumber());
			mapdata.put("totalElements", pagable.getTotalElements());
			mapdata.put("totalPages", pagable.getTotalPages());

			logger.info("Get Keyword Caterer ....." + pageabl);
			return new ResponseEntity<>(mapdata, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Getting error as Exception ....." + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getAllViewCaterer")
	public ResponseEntity<Map<String, Object>> vieAllCaterer(Pageable pageable) {
		try {
			logger.info("Request Pageable page size , page number values" + pageable.getPageNumber() + ", "
					+ pageable.getPageSize());
			Page<Caterer> pageablecaterer = service.findAllCaterer(pageable);
			List<CatererResource> pageabl = pageablecaterer.stream().map(CatererResource::new)
					.collect(Collectors.toList());
			final Resources<CatererResource> resources = new Resources<>(pageabl);
			final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
			resources.add(new Link(uriString, "self"));

			Map<String, Object> mapdata = new HashMap<String, Object>();
			mapdata.put("pagabledata", resources);
			mapdata.put("number", pageablecaterer.getNumber());
			mapdata.put("totalElements", pageablecaterer.getTotalElements());
			mapdata.put("totalPages", pageablecaterer.getTotalPages());

			logger.info("Get Keyword Caterer ....." + pageabl);
			return new ResponseEntity<>(mapdata, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@GetMapping(value = "/getAllCaterer")
//	public ResponseEntity<Page<Caterer>> getAllCaterer(Pageable pageable) {
//		try {
//			return new ResponseEntity<>(service.findAllCaterer(pageable), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@GetMapping("/{id}")
	public ResponseEntity<CatererResource> get(@PathVariable final String id) {
		logger.info("Get Caterer detail of....." + ": " + id);
		return service.findCaterer(id).map(c -> ResponseEntity.ok(new CatererResource(c)))
				.orElseThrow(() -> new CatererNotFoundException(id));
	}
}
