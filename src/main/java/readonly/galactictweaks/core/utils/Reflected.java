package readonly.galactictweaks.core.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import readonly.galactictweaks.core.GCTLog;

@UtilityClass
public class Reflected {
	
    /**
     * Creates a new instance with the provided constructor and arguments.
     * @param constructor The constructor of the class for which a new instance should be created.
     * @param arguments Array of objects to be passed as arguments to the constructor call.
     * @return A new object created by calling the constructor.
     */
    public static <T> T getInstance(Constructor<T> constructor, Object... arguments) {
        try {
            return constructor.newInstance(arguments);
        } catch (Exception e) { GCTLog.error("Exception creating instance of " + constructor.getClass().getName(), e); }

        return null;
    }
    
    /**
     * Obtains the constructor for the named class identified by the parameter types.
     * @param className The fully qualified name of the class.
     * @param argumentTypes Parameter types to identify the constructor.
     * @return Constructor object representing the declared constructor for the parameter types.
     */
    public static Constructor<?> getConstructor(final String className, final Class<?>... argumentTypes) {
        if(className == null || argumentTypes == null) {
            GCTLog.error("The provided class name or arguments can't be null.");
            return null;
        }
        
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getDeclaredConstructor(argumentTypes);
            constructor.setAccessible(true);
            return constructor;
        } catch (Exception ex) { GCTLog.error("Exception getting constructor of " + className, ex); }

        return null;
    }
    
    /**
     * Obtains the constructor for the class identified by the parameter types.
     * @param clazz The Class for which the constructor should be obtained.
     * @param types Parameter types to identify the constructor.
     * @return Constructor object representing the declared constructor for the parameter types.
     */
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... types) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(types);
            constructor.setAccessible(true);
            return constructor;
        } catch (Exception ex) { GCTLog.error("Exception getting constructor of " + clazz.getName(), ex); }

        return null;
    }

    /**
     * Returns the value of a private field for an object instance.
     * @param object An object instance from which the field value is to be extracted.
     * @param fieldNames A list of field names for which the value should be extracted.
     * The functions returns value of the first field found.
     * @return The value of the provided field name.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObject(Object object, String... fieldNames) {
        Class<?> cls = object.getClass();
        for (String field : fieldNames) {
            try {
                Field result = cls.getDeclaredField(field);
                result.setAccessible(true);
                return (T) result.get(object);
            } catch (Exception ex) { GCTLog.error("Exception in getObject()", ex); }
        }

        GCTLog.error("Could not retrieve any object for the provided field names.");
        
        return null;
    }

    /**
     * Returns the value of a private final field for an object instance and removes the final modifier.
     * @param object An object instance from which the field value is to be extracted.
     * @param fieldNames A list of field names for which the value should be extracted.
     * The functions returns value of the first field found.
     * @return The value of the provided field name.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFinalObject(Object object, String... fieldNames) {
        Class<?> cls = object.getClass();
        for (String field : fieldNames) {
            try {
                Field result = cls.getDeclaredField(field);
                result.setAccessible(true);
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(result, result.getModifiers() & ~Modifier.FINAL);
                return (T) result.get(object);
            } catch (Exception ex) { GCTLog.error("Exception in getFinalObject()", ex); }
        }
        
        GCTLog.error("Could not retrieve any final object for the provided field names.");
        
        return null;
    }

    /**
     * Returns the value of a private static field for a class.
     * @param clazz The class for which the field value is to be extracted.
     * @param fieldNames A list of field names for which the value should be extracted.
     * @return The value of the provided field name.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getStaticObject(Class<?> clazz, String... fieldNames) {
        for (String field : fieldNames) {
            try {
                Field result = clazz.getDeclaredField(field);
                result.setAccessible(true);
                return (T) result.get(null);
            } catch (Exception e) { GCTLog.error("Exception in getStaticObject()", e); }
        }
        
        GCTLog.error("Could not retrieve any static object for the provided field names.");

        return null;
    }
    
    /**
     * Returns the value of a private static field for a class.
     * @param className The fully qualified name of the class.
     * @param fieldNames A list of field names for which the value should be extracted.
     * @return The value of the provided field name.
     */
    public static <T> T getStaticObject(String className, String... fieldNames) {
        try {
            Class<?> clazz = Class.forName(className);
            return getStaticObject(clazz, fieldNames);
        } catch (ClassNotFoundException e) { GCTLog.error("Exception in getStaticObject()", e); }
        
        GCTLog.error("Could not retrieve any static object for the provided field names.");
        
        return null;
    }
 
	@SuppressWarnings("unchecked")
	public static <T, E> T getPrivateValue(Class<? super E> classToAccess, @Nullable E instance, String fieldName) {
		try {
			return (T) findField(classToAccess, fieldName, null).get(instance);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T, E> T getPrivateValue(Class<? super E> classToAccess, @Nullable E instance, String fieldName,
			@Nullable String fieldObfName) {
		try {
			return (T) findField(classToAccess, fieldName, fieldObfName).get(instance);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	public static <T, E> void setPrivateValue(Class<? super T> classToAccess, @Nullable T instance, @Nullable E value,
			String fieldName) {
		try {
			findField(classToAccess, fieldName, null).set(instance, value);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static <T, E> void setPrivateValue(Class<? super T> classToAccess, @Nullable T instance, @Nullable E value,
			String fieldName, @Nullable String fieldObfName) {
		try {
			findField(classToAccess, fieldName, fieldObfName).set(instance, value);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static Field findField(@Nonnull Class<?> clazz, @Nonnull String fieldName, @Nullable String fieldObfName) {
		Preconditions.checkNotNull(clazz);
		Preconditions.checkArgument(StringUtils.isNotEmpty(fieldName), "Field name cannot be empty");

		String nameToFind = FMLLaunchHandler.isDeobfuscatedEnvironment() ? fieldName
				: MoreObjects.firstNonNull(fieldObfName, fieldName);

		try {
			Field f = clazz.getDeclaredField(nameToFind);
			f.setAccessible(true);
			return f;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
