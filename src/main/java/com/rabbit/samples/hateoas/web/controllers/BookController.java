package com.rabbit.samples.hateoas.web.controllers;

import com.rabbit.samples.hateoas.persistence.data.Book;
import com.rabbit.samples.hateoas.services.BookService;
import com.rabbit.samples.hateoas.services.CustomerService;
import com.rabbit.samples.hateoas.web.resources.BookResource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 30 Sep 2018
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(value = AccessLevel.PROTECTED)
@RestController
@RequestMapping(
		value = "/books",
		produces = "application/hal+json"
)
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	CustomerService customerService;

	@GetMapping
	public Resources<BookResource> getAll() {

		log.debug("Get all books");

		return new Resources<>(
				getBookService().getAll()
						.stream()
						.map(BookResource::new)
						.collect(Collectors.toList()),
				getHateoasLinks()
		);
	}

	/*
		PLEASE NOTE:
		@JsonView(BookView.Summary.class) does not work with Resources<BookResource> but with List<BookResource>.
		This means that it is not compliant to HATEOAS specifications.
	 */
	// @GetMapping("/summaries")
	// public Resources<BookResource> getAllSummaries() {
	//
	// 	log.debug("Get all books");
	//
	// 	return new Resources<>(
	// 			getBookService().getAll()
	// 					.stream()
	// 					.map(BookResource::new)
	// 					.collect(Collectors.toList()),
	// 			getHateoasLinks()
	// 	);
	// }

	@GetMapping("/{customerId}")
	public Resources<BookResource> getAllByCustomerId(
			@PathVariable final Long customerId) {

		log.debug("Get all books by customer id {}", customerId);

		List<Book> books = getCustomerService()
				.get(customerId)
				.getBooks();
		if (books == null) {
			books = Collections.emptyList();
		}

		return new Resources<>(
				books
						.stream()
						.map(BookResource::new)
						.collect(Collectors.toList()),
				getHateoasLinks()
		);
	}

	public static List<Link> getHateoasLinks() {

		final List<Link> links = new ArrayList<>();
		links.add(
				ControllerLinkBuilder
						.linkTo(BookController.class)
						.withRel("books")
						.withType("GET")
		);
		// links.add(
		// 		ControllerLinkBuilder
		// 				.linkTo(BookController.class)
		// 				.slash("summaries")
		// 				.withRel("summaries")
		// 				.withType("GET")
		// );
		return links;
	}

}
