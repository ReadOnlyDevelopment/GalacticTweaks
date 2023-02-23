package readonly.api.config.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

import readonly.api.feature.*;

@Retention(RUNTIME)
@Target(FIELD)
public @interface GCTFeature {

	Class<? extends Feature> featureClass();
	
	String comment() default "";
}
