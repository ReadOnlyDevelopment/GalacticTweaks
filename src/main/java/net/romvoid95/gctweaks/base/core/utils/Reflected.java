package net.romvoid95.gctweaks.base.core.utils;

import java.lang.reflect.Field;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import net.minecraftforge.fml.relauncher.FMLLaunchHandler;

public class Reflected {

	public static <T, E> T getPrivateValue(Class<? super E> classToAccess, @Nullable E instance, int fieldIndex) {
		try {
			Field f = classToAccess.getDeclaredFields()[fieldIndex];
			f.setAccessible(true);
			return (T) f.get(instance);
		} catch (Exception e) {
			e.getStackTrace();
		}
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
