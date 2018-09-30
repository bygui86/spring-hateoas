package com.rabbit.samples.hateoas.services.impl;

import com.rabbit.samples.hateoas.persistence.data.Customer;
import com.rabbit.samples.hateoas.persistence.repos.CustomerRepo;
import com.rabbit.samples.hateoas.services.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepo;

	@Override
	public List<Customer> getAll() {

		log.debug("Get all customers");

		return getCustomerRepo().findAll();
	}

	@Override
	public Customer get(final Long id) {

		log.debug("Get customer by id {}", id);

		return getCustomerRepo()
				.findById(id)
				.orElseGet(() -> createErrorCustomer(id, "Customer not found"))
				;
	}

	@Override
	public Customer insert(final Customer customerResource) {

		log.debug("Insert new customer {}", customerResource);

		return getCustomerRepo().save(customerResource);
	}

	@Override
	public Customer update(final Customer customerResource) {

		log.debug("Update customer {}", customerResource);

		return getCustomerRepo().save(customerResource);
	}

	@Override
	public void delete(final Long id) {

		log.debug("Delete customer by id {}", id);

		getCustomerRepo().deleteById(id);
	}

	protected Customer createErrorCustomer(final Long id, final String errorMsg) {

		log.error("Create error-customer with id {} and errorMsg {}", id, errorMsg);

		return Customer.builder()
				.id(id)
				.errorMsg(errorMsg)
				.build()
				;
	}

}
