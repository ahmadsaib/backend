package com.Ahmad.Caterer.Controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.Ahmad.Caterer.Pojo.Caterer;

public class CatererResource extends ResourceSupport {

	private final Caterer caterer;

	public CatererResource(final Caterer caterer) {
		this.caterer = caterer;
		final String id = caterer.getID();
		add(linkTo(ControllerCaterer.class).withRel("Caterer"));
		add(linkTo(methodOn(ControllerCaterer.class).get(id)).withSelfRel());
	}

	public Caterer getCaterer() {
		return caterer;
	}

}
