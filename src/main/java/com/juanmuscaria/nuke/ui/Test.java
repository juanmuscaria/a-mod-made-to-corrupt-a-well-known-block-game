package com.juanmuscaria.nuke.ui;

import com.juanmuscaria.nuke.logging.OutStreamLogger;

import javax.swing.*;
import java.util.logging.Level;

public class Test {
    public static void main(String... args) {
        UITheme.apply("material-dark", new OutStreamLogger(System.out, "LOG", Level.ALL));
        JOptionPane.showConfirmDialog(null, "test", "aaa", JOptionPane.YES_NO_OPTION);
    }
}
