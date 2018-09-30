package com.rabbit.samples.hateoas.web.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rabbit.samples.hateoas.persistence.data.Customer;
import com.rabbit.samples.hateoas.web.controllers.BookController;
import com.rabbit.samples.hateoas.web.controllers.CustomerController;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 30 Sep 2018
 * <p>
 * ResourceSupport adds Hypermedia Support to this Resource
 * <p>
 * CustomerResource extends ResourceSupport class to inherit the add() method.
 * So once we create a link, we can easily set that value to the resource representation without adding any new fields to it.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResource extends ResourceSupport {

	Customer customer;

	public CustomerResource(final Customer customer) {

		this.customer = customer;

		addHateoasLinks();
	}

	protected void addHateoasLinks() {

		addHateoasSelfLinks();
		addHateoasCustomerControllerLinks();
		addHateoasBookControllerLinks();
	}

	protected void addHateoasSelfLinks() {

		final long id = customer.getId();

		add(
				ControllerLinkBuilder
						.linkTo(
								ControllerLinkBuilder
										.methodOn(CustomerController.class)
										.get(id)
						)
						.withSelfRel()
						.withType("GET")
		);
		add(
				ControllerLinkBuilder
						.linkTo(
								ControllerLinkBuilder
										.methodOn(CustomerController.class)
										.delete(id)
						)
						.withRel("self-delete")
						.withType("DELETE")
		);
	}

	protected void addHateoasCustomerControllerLinks() {

		add(
				CustomerController.getHateoasLinks()
		);
	}

	protected void addHateoasBookControllerLinks() {

		add(
				ControllerLinkBuilder
						.linkTo(
								ControllerLinkBuilder
										.methodOn(BookController.class)
										.getAllByCustomerId(customer.getId())
						)
						.withRel("books")
						.withType("GET")
		);
	}

}
