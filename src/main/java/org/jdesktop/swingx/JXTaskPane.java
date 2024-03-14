package org.jdesktop.swingx;

import javax.swing.*;
import java.awt.*;

//HACK: placeholder class to avoid pulling the entirety of swingx as dependency for things we are not even using
public class JXTaskPane extends JPanel {
    public Container getContentPane() {
        return this;
    }
}
