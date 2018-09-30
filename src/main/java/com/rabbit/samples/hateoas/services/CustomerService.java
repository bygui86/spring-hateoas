package com.rabbit.samples.hateoas.services;

import com.rabbit.samples.hateoas.persistence.data.Customer;

import java.util.List;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 30 Sep 2018
 */
public interface CustomerService {

	List<Customer> getAll();

	Customer get(final Long id);

	Customer insert(final Customer customer);

	Customer update(final Customer customer);

	void delete(final Long id);

}
