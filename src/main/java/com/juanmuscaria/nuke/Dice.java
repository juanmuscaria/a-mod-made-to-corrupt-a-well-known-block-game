package com.juanmuscaria.nuke;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Holds all ChaosEngine data to nuke the jvm
 */
public class Dice {
    public volatile long seed = (long) (Math.random() * System.currentTimeMillis());
    public volatile int corruptClassDeleteMethod = 400; // Represents '1 in N' chance to roll corruption.
    public volatile int corruptClassScrambleConstants = 100;
    public volatile boolean corruptConstantPool = true;
    public volatile boolean corruptConstantOpcodes = true;

    @SuppressWarnings("NonAtomicOperationOnVolatileField")
    public static Dice fromStream(InputStream in) throws IOException {
        Dice dice = new Dice();
        Properties properties = new Properties();
        properties.loadFromXML(in);
        dice.seed = Long.parseLong(properties.getProperty("seed",
            String.valueOf(dice.seed)));
        dice.corruptClassDeleteMethod = Integer.parseInt(properties.getProperty("corruptClassDeleteMethod",
            String.valueOf(dice.corruptClassDeleteMethod)));
        dice.corruptClassScrambleConstants = Integer.parseInt(properties.getProperty("corruptClassScrambleConstants",
            String.valueOf(dice.corruptClassScrambleConstants)));
        dice.corruptConstantPool = Boolean.parseBoolean(properties.getProperty("corruptConstantPool",
            String.valueOf(dice.corruptConstantPool)));
        dice.corruptConstantOpcodes = Boolean.parseBoolean(properties.getProperty("corruptConstantOpcodes",
            String.valueOf(dice.corruptConstantOpcodes)));
        return dice;
    }

    public void saveToStream(OutputStream out) throws IOException {
        Properties properties = new Properties();
        properties.put("seed", Long.toString(seed));
        properties.put("corruptClassDeleteMethod", Integer.toString(corruptClassDeleteMethod));
        properties.put("corruptClassScrambleConstants", Integer.toString(corruptClassScrambleConstants));
        properties.put("corruptConstantPool", Boolean.toString(corruptConstantPool));
        properties.put("corruptConstantOpcodes", Boolean.toString(corruptConstantOpcodes));
        properties.storeToXML(out, "This basically stores all ChaosEngine settings, same settings should always yield same results.");
    }

    @Override
    public String toString() {
        return "Dice{" +
            "seed=" + seed +
            ", corruptClassDeleteMethod=" + corruptClassDeleteMethod +
            ", corruptClassScrambleConstants=" + corruptClassScrambleConstants +
            ", corruptConstantPool=" + corruptConstantPool +
            ", corruptConstantOpcodes=" + corruptConstantOpcodes +
            '}';
    }

    public void replaceWith(Dice newDice) {
        this.seed = newDice.seed;
        this.corruptClassDeleteMethod = newDice.corruptClassDeleteMethod;
        this.corruptClassScrambleConstants = newDice.corruptClassScrambleConstants;
        this.corruptConstantPool = newDice.corruptConstantPool;
        this.corruptConstantOpcodes = newDice.corruptConstantOpcodes;
    }
}
