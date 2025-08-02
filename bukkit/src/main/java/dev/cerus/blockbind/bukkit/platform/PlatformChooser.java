package dev.cerus.blockbind.bukkit.platform;

import dev.cerus.blockbind.api.platform.PlatformAdapter;
import dev.cerus.blockbind.platform.PlatformAdapter16R3;
import dev.cerus.blockbind.platform.PlatformAdapter18R1;
import org.bukkit.Bukkit;

/**
 * Simple utility for choosing the right adapter
 */
public class PlatformChooser {

    private PlatformChooser() {
    }

    /**
     * Attempts to find a matching platform adapter
     *
     * @return A matching adapter or null
     */
    public static PlatformAdapter choose() {
        String version = Bukkit.getVersion();

        try {
            // Extract "MC: x.y.z" or "MC: x.y"
            version = version.substring(version.indexOf("MC: ") + 4, version.lastIndexOf(')'));
        } catch (Exception e) {
            // Fallback
            version = "unknown";
        }

        // Handle common cases with minor version tolerance
        if (version.startsWith("1.16")) {
            return new PlatformAdapter16R3();
        } else if (version.startsWith("1.18")) {
            return new PlatformAdapter18R1();
        }

        // Unsupported
        return null;
    }

}
