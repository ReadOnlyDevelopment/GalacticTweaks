package net.romvoid95.api.docs;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Doc {

	String value();
	
	String comment();
	
	Stability stability() default Stability.NA;
}
