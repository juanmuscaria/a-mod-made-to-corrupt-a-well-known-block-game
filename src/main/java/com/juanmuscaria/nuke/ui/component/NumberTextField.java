package com.juanmuscaria.nuke.ui.component;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NumberTextField<T extends Number & Comparable<T>> extends JTextField {
    private final Supplier<T> defaultValue;
    private T previousValidValue;

    public NumberTextField(T minValue, T maxValue, T current, Supplier<T> defaultValueSupplier, Consumer<T> changeConsumer) {
        this.defaultValue = defaultValueSupplier;
        this.previousValidValue = current;
        setText(current.toString());

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    T value;
                    if (getText().isEmpty()) {
                        value = defaultValue.get();
                        setText(value.toString());
                    } else {
                        value = parseValue(getText());
                    }
                    if (value.compareTo(minValue) < 0 || value.compareTo(maxValue) > 0) {
                        setText(previousValidValue.toString());
                    } else {
                        previousValidValue = value;
                        changeConsumer.accept(value);
                    }
                } catch (NumberFormatException ex) {
                    setText(previousValidValue.toString());
                }
            }
        });

        this. getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateValue();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateValue();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateValue();
            }

            private void updateValue() {
                try {
                    T value = parseValue(getText());
                    if (value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0) {
                        previousValidValue = value;
                        changeConsumer.accept(value);
                    }
                } catch (NumberFormatException ex) {
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private T parseValue(String text) {
        if (text.isEmpty()) {
            return defaultValue.get();
        }
        if (previousValidValue instanceof Integer) {
            return (T) Integer.valueOf(text);
        } else if (previousValidValue instanceof Long) {
            return (T) Long.valueOf(text);
        } else if (previousValidValue instanceof Double) {
            return (T) Double.valueOf(text);
        } else if (previousValidValue instanceof Float) {
            return (T) Float.valueOf(text);
        }
        throw new IllegalArgumentException("Unsupported number type");
    }
}
