package org.jdesktop.swingx.plaf.basic;

import org.jdesktop.swingx.JXTaskPane;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

//HACK: placeholder class to avoid pulling the entirety of swingx as dependency for things we are not even using
public class BasicTaskPaneUI {
    protected JXTaskPane group;

    public void installUI(JComponent c) {
    }

    public void uninstallUI(JComponent c) {
    }

    public void update(Graphics g, JComponent c) {
    }
    protected void uninstallListeners() {
    }

    protected Border createPaneBorder() {
        return BorderFactory.createLoweredBevelBorder();
    }

    public void paint(Graphics g, JComponent c) {
    }
}
