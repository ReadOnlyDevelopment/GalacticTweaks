package net.romvoid95.galactic.modules.galacticraft.features.serverhub.assets;
//package net.romvoid95.galactic.feature.common.serverhub.assets;
//
//import java.awt.image.*;
//import java.io.*;
//import java.util.*;
//
//import org.apache.commons.io.filefilter.*;
//
//import net.minecraft.client.*;
//import net.minecraft.client.resources.*;
//import net.minecraft.client.resources.data.*;
//import net.minecraft.util.*;
//import net.romvoid95.galactic.*;
//import net.romvoid95.galactic.core.config.*;
//
//public class HubAssets implements IResourcePack {
//
//	private boolean debug = CoreBooleanValues.OUTPUT_DEBUG.currentValue;
//
//	private static String loc = "config/GalacticTweaks/assets";
//
//	@Override
//	public InputStream getInputStream(ResourceLocation rl) throws IOException {
//		if (!resourceExists(rl)) {
//			return null;
//		} else {
//
//			File file = new File(new File(Minecraft.getMinecraft().mcDataDir, loc + "/" + rl.getResourceDomain()),
//					rl.getResourcePath());
//			String realFileName = file.getCanonicalFile().getName();
//			if (!realFileName.equals(file.getName())) {
//				GCTLogger.debug("Resource Location " + rl.toString() + " only matches the file " + realFileName
//						+ " : We are running a Non-Case Sensitive OS Environment(regarding file name cases");
//			}
//			return new FileInputStream(file);
//		}
//	}
//
//	@Override
//	public boolean resourceExists(ResourceLocation rl) {
//		File fileRequested = new File(new File(Minecraft.getMinecraft().mcDataDir, loc + "/" + rl.getResourceDomain()),
//				rl.getResourcePath());
//		if (debug && !fileRequested.isFile()) {
//			GCTLogger
//					.debug("Looking For: " + rl.toString() + " But Cannot Find At: " + fileRequested.getAbsolutePath());
//		}
//
//		return fileRequested.isFile();
//	}
//
//	@Override
//	public Set<String> getResourceDomains() {
//		File folder = new File(Minecraft.getMinecraft().mcDataDir, loc);
//		if (!folder.exists()) {
//			folder.mkdir();
//		}
//		HashSet<String> folders = new HashSet<String>();
//		GCTLogger.debug("[GalacticTweaks] Domains: ");
//		File[] resourceDomains = folder.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
//		for (File resourceFolder : resourceDomains) {
//			if (resourceFolder.getName().equals("debug")) {
//				debug = true;
//			}
//		}
//		for (File resourceFolder : resourceDomains) {
//			GCTLogger
//					.debug("GalacticTweaks  - " + resourceFolder.getName() + " | " + resourceFolder.getAbsolutePath());
//			folders.add(resourceFolder.getName());
//		}
//
//		return folders;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public IMetadataSection getPackMetadata(MetadataSerializer p_135058_1_, String p_135058_2_) throws IOException {
//		return null;
//	}
//
//	@Override
//	public BufferedImage getPackImage() throws IOException {
//		return null;
//	}
//
//	@Override
//	public String getPackName() {
//		return "GalacticTweaksAssets";
//	}
//}
