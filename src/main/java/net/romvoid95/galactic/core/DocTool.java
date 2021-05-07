//package net.romvoid95.galactic.core;
//
//import static java.nio.charset.StandardCharsets.*;
//
//import java.io.*;
//import java.util.*;
//import java.util.function.*;
//
//import org.reflections.*;
//
//import com.google.common.io.*;
//
//import net.romvoid95.api.docs.*;
//import net.romvoid95.galactic.*;
//
//public class DocTool {
//
//	static Consumer<String> printer;
//
//	public static void runTool() {
//
//		File file = new File("C:/Users/AJ/Documents/GitHub/GalacticTweaks/ReadOnlyDocs/source/global.conf-rst.generated");
//
//		try {
//			GalacticTweaks.LOG.info(file.getAbsolutePath());
//			//file.createNewFile();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		printer = newFilePrinter(file);
//
//		Reflections reflections = new Reflections("net.romvoid95.galactic");
//		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Doc.class);
//
//		for(Class<?> clazz : annotated) {
//
//			Doc doc = clazz.getAnnotation(Doc.class);
//
//			//printer.accept(doc.title());
//		}
//	}
//
//    private static Consumer<String> newFilePrinter(final String path) {
//        return newFilePrinter(new File(path));
//    }
//
//    private static Consumer<String> newFilePrinter(final File target) {
//        System.err.println("Writing to " + target.getAbsolutePath());
//        return text -> {
//            try (BufferedWriter writer = Files.newWriter(target, UTF_8)) {
//                writer.write(text);
//            } catch (final IOException e) {
//                throw new UncheckedIOException("Failed to write outout file", e);
//            }
//        };
//    }
//
//    /**
//     * Extracts the name of the field.
//     *
//     * @param field The field to extract the name from.
//     * @return The defined name or the derived name of the field.
//     */
//    public static <T> T extractValue(T t, String valueName) {
//    	if(t instanceof Feature) {
//    		Object obj =
//    		Class<T> clazz = (Class<T>) t.getClass();
//    		Doc annotation = clazz.getAnnotation(Doc.class);
//
//    	}
//
//
//        final Setting setting = field.getAnnotation(Setting.class);
//        if (setting.value().isEmpty()) {
//            return field.getName();
//        }
//        return setting.value();
//    }
//}
