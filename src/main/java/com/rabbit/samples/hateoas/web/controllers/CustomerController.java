package com.rabbit.samples.hateoas.web.controllers;

import com.rabbit.samples.hateoas.persistence.data.Customer;
import com.rabbit.samples.hateoas.services.CustomerService;
import com.rabbit.samples.hateoas.web.resources.CustomerResource;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
		value = "/customers",
		produces = "application/hal+json"
)
public class CustomerController {

	@Autowired
	CustomerService customerService;

	/*
		PLEASE NOTE:
		The controller MUST return a ResourceSupport class (or one of its subtypes: Resource, Resources, PagedResources) for HAL serialization to work.
	 */
	@GetMapping
	public Resources<CustomerResource> getAll() {

		log.debug("Get all customers");

		return new Resources<>(
				getCustomerService().getAll()
						.stream()
						.map(CustomerResource::new)
						.collect(Collectors.toList()),
				getHateoasLinks()
		);
	}

	@GetMapping(value = "/{id}")
	public Resource<CustomerResource> get(@PathVariable final Long id) {

		log.debug("Get customer by id {}", id);

		final CustomerResource customerResource =
				new CustomerResource(
						getCustomerService().get(id)
				);

		return new Resource<>(
				customerResource,
				getHateoasLinks()
		);
	}

	@GetMapping(value = "/{id}/v2")
	public CustomerResource getWithoutGeneralLinks(@PathVariable final Long id) {

		log.debug("Get customer by id {}", id);

		return new CustomerResource(
				getCustomerService().get(id)
		);
	}

	@PostMapping
	public Resource<CustomerResource> insert(@RequestBody final Customer newCustomerResource) {

		log.debug("Insert new customer {}", newCustomerResource);

		return new Resource<>(
				new CustomerResource(
						getCustomerService().insert(newCustomerResource)
				),
				getHateoasLinks()
		);
	}

	@PutMapping
	public Resource<CustomerResource> update(@RequestBody final Customer updCustomerResource) {

		log.debug("Update customer {}", updCustomerResource);

		return new Resource<>(
				new CustomerResource(
						getCustomerService().update(updCustomerResource)
				),
				getHateoasLinks()
		);
	}

	@DeleteMapping(value = "/{id}")
	public Resource<?> delete(@PathVariable final Long id) {

		log.debug("Delete customer by id {}", id);

		getCustomerService().delete(id);
		return new Resource<>(
				getHateoasLinks()
		);
	}

	public static List<Link> getHateoasLinks() {

		final List<Link> links = new ArrayList<>();
		links.add(
				ControllerLinkBuilder
						.linkTo(CustomerController.class)
						.withRel("customers")
						.withType("GET")
		);
		links.add(
				ControllerLinkBuilder
						.linkTo(CustomerController.class)
						.withRel("insert-customer")
						.withType("POST")
		);
		links.add(
				ControllerLinkBuilder
						.linkTo(CustomerController.class)
						.withRel("update-customer")
						.withType("PUT")
		);
		return links;
	}

}
