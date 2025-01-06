package com.juanmuscaria.nuke.ui;

import com.juanmuscaria.nuke.Dice;
import com.juanmuscaria.nuke.logging.LoggerAdapter;
import com.juanmuscaria.nuke.ui.component.NumberTextField;
import com.juanmuscaria.nuke.ui.component.ScrollablePane;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DiceSettings extends JFrame {
    private static final Dice DEFAULTS = new Dice();
    private final Dice dice;
    private static final SecureRandom random = new SecureRandom();
    private JPanel root;
    private JButton load;
    private JButton saveAndStartButton;
    private JButton exportButton;
    private JPanel diceSettings;
    public CountDownLatch continueLatch = new CountDownLatch(1);

    public DiceSettings(Dice dice, LoggerAdapter logger) {
        this.dice = dice;
        $$$setupUI$$$();
        setTitle("ChaosEngine Configuration");
        add(root);
        addDiceOptions();
        pack();
        setSize(650, 400);
        setMinimumSize(new Dimension(400, 300));
        saveAndStartButton.addActionListener((action) -> continueLatch.countDown());
        exportButton.addActionListener((action) -> {
            try {
                ByteArrayOutputStream data = new ByteArrayOutputStream();
                dice.saveToStream(data);
                Toolkit.getDefaultToolkit().getSystemClipboard()
                    .setContents(new StringSelection(Base64.getEncoder().encodeToString(data.toByteArray())), null);
                JOptionPane.showMessageDialog(this, "Engine data saved to clipboard");
            } catch (Exception e) {
                logger.warn("Unable to save settings to clipboard", e);
                JOptionPane.showMessageDialog(this, "Unable to save to clipboard, check the logs.");
            }
        });
        load.addActionListener((action) -> {
            try {
                String data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
                Dice newDice = Dice.fromStream(new ByteArrayInputStream(Base64.getDecoder().decode(data)));
                dice.replaceWith(newDice);
                diceSettings.removeAll();
                addDiceOptions();
                diceSettings.revalidate();
                diceSettings.repaint();
                JOptionPane.showMessageDialog(this, "Imported new engine data!");
            } catch (Exception e) {
                logger.warn("Unable to load settings from clipboard", e);
                JOptionPane.showMessageDialog(this, "No valid data found in the clipboard");
            }
        });
    }

    public void addDiceOptions() {
        addOptions("Seed", "Randomization seed used to corrupt the game, leave it blank to generate a new seed.",
            () -> makeNumberField(Long.MIN_VALUE, Long.MAX_VALUE, dice.seed,
                random::nextLong,
                seed -> dice.seed = seed));
        addOptions("Chance to delete a method", "1 in N chance that a method will be chosen to be deleted! Currently only void methods are affected.",
            () -> makeNumberField(2, 5000, dice.corruptClassDeleteMethod, () -> DEFAULTS.corruptClassDeleteMethod, value -> dice.corruptClassDeleteMethod = value));
        addOptions("Chance to scramble a method", "1 in N chance that a method will be chosen to have all the constants mixed around.",
            () -> makeNumberField(2, 5000, dice.corruptClassScrambleConstants, () -> DEFAULTS.corruptClassScrambleConstants, value -> dice.corruptClassScrambleConstants = value));
        addOptions("Corrupt constant pool", "If enabled, the constant pool will be mixed around when scrambling a method.",
            () -> {
                JCheckBox enable = new JCheckBox("Enabled");
                enable.setSelected(dice.corruptConstantPool);
                enable.addItemListener((value) -> dice.corruptConstantPool = enable.isSelected());
                return enable;
            });
        addOptions("Corrupt constant opcodes", "If enabled, constant value opcodes will be mixed around when scrambling a method.",
            () -> {
                JCheckBox enable = new JCheckBox("Enabled");
                enable.setSelected(dice.corruptConstantOpcodes);
                enable.addItemListener((value) -> dice.corruptConstantOpcodes = enable.isSelected());
                return enable;
            });
    }

    private void addOptions(String name, String description, Supplier<Component> createComponent) {
        JPanel inputPane = new JPanel();
        inputPane.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(3, 3, 3, 3);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx = 1;
        cons.gridx = 0;
        Component input = createComponent.get();
        inputPane.add(input, cons);
        inputPane.add(new JLabel("<html>" + description + "</html>"), cons);
        inputPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), name, TitledBorder.LEFT, TitledBorder.TOP));
        diceSettings.add(inputPane);
    }

    private void createUIComponents() {
        diceSettings = new ScrollablePane(true, false);
        diceSettings.setLayout(new BoxLayout(diceSettings, BoxLayout.Y_AXIS));
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        root = new JPanel();
        root.setLayout(new BorderLayout(0, 0));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        root.add(panel1, BorderLayout.SOUTH);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, BorderLayout.WEST);
        load = new JButton();
        load.setText("Import");
        load.setToolTipText("Imports engine settings from Clipboard");
        panel2.add(load);
        exportButton = new JButton();
        exportButton.setText("Export");
        exportButton.setToolTipText("Exports engine settings to Clipboard");
        panel2.add(exportButton);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel3, BorderLayout.EAST);
        saveAndStartButton = new JButton();
        saveAndStartButton.setText("Save and Start");
        panel3.add(saveAndStartButton);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        root.add(panel4, BorderLayout.CENTER);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(31);
        scrollPane1.setVerticalScrollBarPolicy(20);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel4.add(scrollPane1, gbc);
        scrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Dice Settings", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        scrollPane1.setViewportView(diceSettings);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    private <T extends Number & Comparable<T>> Component makeNumberField(T minValue, T maxValue, T current, Supplier<T> defaultValueSupplier, Consumer<T> changeConsumer) {
        NumberTextField<T> field = new NumberTextField<>(minValue, maxValue, current, defaultValueSupplier, changeConsumer);
        field.setMaximumSize(new Dimension(250, 26));
        return field;
    }
}
