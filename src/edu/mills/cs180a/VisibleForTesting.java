package edu.mills.cs180a;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
/**
 * Indicates that the class, method or field has greater
 * visibility than otherwise needed to enhance testability.
 */
@Retention(SOURCE)
public @interface VisibleForTesting {
	
}
