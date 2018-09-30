package com.rabbit.samples.hateoas.services;

import com.rabbit.samples.hateoas.persistence.data.Book;

import java.util.List;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 30 Sep 2018
 */
public interface BookService {

	List<Book> getAll();

	Book getById(final Long id);

}
