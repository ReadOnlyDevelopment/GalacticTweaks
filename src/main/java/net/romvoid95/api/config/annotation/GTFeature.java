package net.romvoid95.api.config.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import net.romvoid95.galactic.feature.*;

@Retention(RUNTIME)
@Target(FIELD)
public @interface GTFeature {
	
	Class<? extends Feature> featureClass();
}
