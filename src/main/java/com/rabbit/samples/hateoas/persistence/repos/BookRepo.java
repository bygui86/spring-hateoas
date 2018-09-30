package com.rabbit.samples.hateoas.persistence.repos;

import com.rabbit.samples.hateoas.persistence.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 30 Sep 2018
 */
public interface BookRepo extends JpaRepository<Book, Long> {

	// no-op

}
