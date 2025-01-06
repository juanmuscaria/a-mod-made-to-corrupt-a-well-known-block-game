package com.juanmuscaria.nuke.ui;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.juanmuscaria.nuke.logging.LoggerAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.util.Locale;
import java.util.Objects;

public class UITheme {
    public static FlatLaf forName(String themeName) {
        return switch (themeName.toLowerCase(Locale.ROOT)) {
            case "material-dark", "dark" -> new FlatDarkLaf();
            case "intellij-light", "intellij" -> new FlatIntelliJLaf();
            case "intellij-dark", "dracula" -> new FlatDarculaLaf();
            case "mac-light" -> new FlatMacLightLaf();
            case "mac-dark" -> new FlatMacDarkLaf();
            default -> new FlatLightLaf();
        };
    }

    public static void apply(String themeName, LoggerAdapter logger) {
        try {
            UIManager.put("ClassLoader", FlatLaf.class.getClassLoader());
            FlatLightLaf.setup();
            UIManager.setLookAndFeel(forName(themeName));
        } catch (Throwable e) {
            logger.warn("Unable to set UI look and feel", e);
        }
    }

    public static Image getDefaultIcon(LoggerAdapter logger) {
        try {
            var lookAndFeel = UIManager.getLookAndFeel();
            if (lookAndFeel instanceof FlatLaf materialLookAndFeel) {
                if (!materialLookAndFeel.isDark()) {
                    return ImageIO.read(Objects.requireNonNull(UITheme.class.getResourceAsStream("/modpackdirector/ModpackDirectorAlt.png")));
                }
            }
            return ImageIO.read(Objects.requireNonNull(UITheme.class.getResourceAsStream("/modpackdirector/ModpackDirector.png")));
        } catch (Exception e) {
            logger.warn("Unable to load built-in app icon", e);
            return new BufferedImage(64, 64, ColorSpace.TYPE_RGB);
        }
    }
}
