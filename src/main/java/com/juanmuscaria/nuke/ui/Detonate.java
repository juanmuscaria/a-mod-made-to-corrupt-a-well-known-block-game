package com.juanmuscaria.nuke.ui;

import com.juanmuscaria.nuke.ChaosEngine;

import javax.swing.*;
import java.awt.*;

public class Detonate extends JFrame {
    public Detonate(ChaosEngine engine) {
        setTitle("Detonate the JVM");
        JPanel inputPane = new JPanel();
        inputPane.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(10, 10, 10, 10);
        cons.weightx = 1;
        cons.gridx = 0;
        JButton detonate = new JButton("Detonate the JVM");
        inputPane.add(detonate, cons);
        add(inputPane);
        pack();
        setMinimumSize(getPreferredSize());

        detonate.addActionListener((action) -> {
            if (JOptionPane.showConfirmDialog(null, "You are about to ABSOLUTELY DESTROY your game, proceed?", "WARNING WARNING WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                engine.corruptTheJvm();
                dispose();
            }
        });
    }
}
