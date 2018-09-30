package com.rabbit.samples.hateoas.persistence.data;

/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 30 Sep 2018
 * <p>
 * This object gives an overview of a more complex target object (in this case {@link Book}).
 * <p>
 * To include a field in the view, simply annotate the field of the target object with @JsonView(BookView.Summary.class)
 * <p>
 * In a REST API, to return this kind of object instead of the target one, simply annotate the controller method with @JsonView(BookView.Summary.class)
 */
public class BookView {

	public interface Summary {

		// no-op
	}

}
