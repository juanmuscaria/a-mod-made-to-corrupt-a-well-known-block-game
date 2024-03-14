package com.juanmuscaria.nuke.ui;

import com.juanmuscaria.nuke.logging.LoggerDelegate;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;
import mdlaf.themes.MaterialTheme;

import javax.swing.*;
import java.util.Locale;

public class UITheme {
    public static MaterialTheme forName(String themeName) {
        switch (themeName.toLowerCase(Locale.ROOT)) {
            default:
                return new MaterialLiteTheme();
            case "material-dark":
            case "jmars-dark":
                return new JMarsDarkTheme();
            case "material-oceanic":
                return new MaterialOceanicTheme();
        }
    }

    public static void apply(String themeName, LoggerDelegate logger) {
        try {
            UIManager.put("ClassLoader", MaterialLookAndFeel.class.getClassLoader());
            UIManager.setLookAndFeel(new MaterialLookAndFeel(forName(themeName)));
        } catch (Throwable e) {
            logger.warn("Unable to set UI look and feel", e);
        }
    }
}
