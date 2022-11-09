package co.ke.coffee_sacco.api;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The goal of Spring Data repository abstraction is to significantly reduce the amount of boilerplate code required to
 * implement data access layers for various persistence stores.
 *
 * The central interface in Spring Data repository abstraction is Repository. It takes the domain class to manage
 * as well as the id type of the domain class as type arguments. This interface acts primarily as a marker interface to
 * capture the types to work with and to help you to discover interfaces that extend this one.
 *
 * The CrudRepository provides sophisticated CRUD functionality for the entity class that is being managed.
 */
public interface BranchRepository  extends JpaRepository<Branch, Long> {
}
