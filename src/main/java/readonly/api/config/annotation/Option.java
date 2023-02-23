package readonly.api.config.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Option {
	Type type();
	
	String defaultString() default "EMPTY";
	
	double defaultDouble() default -1.0D;
	
	int defaultInt() default -9999;
}
