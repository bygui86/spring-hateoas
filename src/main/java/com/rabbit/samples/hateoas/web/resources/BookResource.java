package com.rabbit.samples.hateoas.web.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rabbit.samples.hateoas.persistence.data.Book;
import com.rabbit.samples.hateoas.web.controllers.BookController;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.ResourceSupport;


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
public class BookResource extends ResourceSupport {

	Book book;

	public BookResource(final Book book) {

		this.book = book;

		addHateoasLinks();
	}

	protected void addHateoasLinks() {

		addHateoasSelfLinks();
		addHateoasBookControllerLinks();
	}

	protected void addHateoasSelfLinks() {

		// no-op
	}

	protected void addHateoasBookControllerLinks() {

		add(
				BookController.getHateoasLinks()
		);
	}

}
