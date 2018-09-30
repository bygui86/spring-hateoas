package com.rabbit.samples.hateoas.persistence.repos;

import com.rabbit.samples.hateoas.persistence.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 30 Sep 2018
 */
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	// no-op

}
