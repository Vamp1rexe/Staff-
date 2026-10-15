package be.garagepoort.staffplusplus.craftbukkit.api;

import org.bukkit.Bukkit;

final class VersionUtil {
    private static final String SERVER_VERSION = parseServerVersion();

    public static String getMcVersion() {
        return SERVER_VERSION;
    }

    private static String parseServerVersion() {
        String version;
        try {
            Class<?> clazz = Class.forName("io.papermc.paper.ServerBuildInfo");
            Object info = clazz.getMethod("buildInfo").invoke(null);
            version = clazz.getMethod("minecraftVersionId").invoke(info) + "-R0.1";
        } catch (ReflectiveOperationException e) {
            version = Bukkit.getBukkitVersion().replace("-SNAPSHOT", "");
        }
        return applyCommunityMapping(version);
    }

    /** Community update: Minecraft 26.3.x reuses the 26.2 compatibility layer. */
    private static String applyCommunityMapping(String version) {
        if (version.equals("26.3-R0.1") || version.startsWith("26.3.")) {
            return "26.2-R0.1";
        }
        return version;
    }
}
