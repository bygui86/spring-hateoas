package com.rabbit.samples.hateoas.services.impl;

import com.rabbit.samples.hateoas.persistence.data.Book;
import com.rabbit.samples.hateoas.persistence.repos.BookRepo;
import com.rabbit.samples.hateoas.services.BookService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 30 Sep 2018
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(value = AccessLevel.PROTECTED)
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepo bookRepo;

	@Override
	public List<Book> getAll() {

		log.debug("Get all books");

		return getBookRepo().findAll();
	}

	@Override
	public Book getById(final Long id) {

		log.debug("Get book by id {}", id);

		return getBookRepo()
				.findById(id)
				.orElseGet(() -> createErrorBook(id, "Book not found"))
				;
	}

	protected Book createErrorBook(final Long id, final String errorMsg) {

		log.error("Create error-book with id {} and errorMsg {}", id, errorMsg);

		return Book.builder()
				.id(id)
				.errorMsg(errorMsg)
				.build()
				;
	}

}
