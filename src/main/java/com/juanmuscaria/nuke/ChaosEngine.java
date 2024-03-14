package com.juanmuscaria.nuke;

import com.juanmuscaria.nuke.launch.Platform;
import com.juanmuscaria.nuke.logging.LoggerDelegate;
import com.juanmuscaria.nuke.ui.Detonate;
import com.juanmuscaria.nuke.ui.DiceSettings;
import com.juanmuscaria.nuke.ui.UITheme;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import sun.misc.Unsafe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//TODO: STOP USING SWING STUFF OUTSIDE THE SWING THREAD
//TODO: Stop using JOptionPane and make a custom dialog window, JOptionPane is ugly and buggy
//TODO: Cleanup this code
//TODO: Current way Platform is implemented is ugly, refactor it
//TODO: Stop wasting time on silly projects
public class ChaosEngine {
    private static final boolean userIsAwareOfTheDangers = Boolean.getBoolean("chaos-engine.ImAwareThisModWillDESTROYMyGame");
    private static final Unsafe theUnsafe;
    private static final MethodHandles.Lookup theLookup;
    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(r -> new Thread(r, "ChaosEngine"));
    private static final Boolean realFalse = Boolean.FALSE;
    private static final Boolean realTrue = Boolean.TRUE;
    private static final List<String> mixUp = new ArrayList<>();
    private final Platform platform;
    private final LoggerDelegate logger;
    private Dice dice = new Dice();

    public ChaosEngine(Platform platform) {
        this.platform = platform;
        this.logger = platform.logger();
    }

    public void primeTheExplosives() {
        String[] scareAwayTheUser = {
            "You are about to start the game with a potentially dangerous mod, are you sure you want to proceed?",
            "This mod WILL cause save corruption and make the game UTTERLY BROKEN, you may and WILL lose data! Are you really sure?",
            "THIS IS YOUR LAST WARNING! PROCEEDING WITH THIS MOD WILL CORRUPT YOUR GAME! Are you REALLY sure?"
        };
        UITheme.apply("material-dark", logger);

        if (!GraphicsEnvironment.isHeadless() && !userIsAwareOfTheDangers) {

            for (String message : scareAwayTheUser) {
                if (JOptionPane.showConfirmDialog(null, message, "WARNING WARNING WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    logger.error("User asked to exit before any damage can be done");
                    JOptionPane.showMessageDialog(null, "Backing out and crashing the game before any damage can be done...");
                    $$__PANIC__$$();
                }
            }
        } else if (!userIsAwareOfTheDangers) {
            // It looks like we are on a server
            logger.info("Looks like this is a server and we can't display a GUI, all ChaosEngine settings will be present inside chaos-engine.xml (created once you enable the mod)");
            logger.info("Since this is probably your fist time using this mod, it won't do anything and just exit the game, here the same warnings you would see if you had a graphics environment:");
            for (String message : scareAwayTheUser) {
                logger.warn(message);
            }
            logger.info("If you want to proceed, start the server with -Dchaos-engine.ImAwareThisModWillDESTROYMyGame=true");
        }

        logger.error(" ");
        logger.warn("###############################");
        logger.warn("# WARNING # WARNING # WARNING #");
        logger.warn("# Corrupt My Game is present  #");
        logger.warn("# It WILL mess up everything! #");
        logger.warn("###############################");
        logger.error(" ");

        if (theUnsafe == null) {
            logger.error("# It was not possible to get theUnsafe, some features will not be available #");
        }

        Path diceFile = platform.mcHome().resolve("chaos-engine.xml");
        if (Files.exists(diceFile)) {
            try {
                this.dice = Dice.fromStream(Files.newInputStream(diceFile));
            } catch (Exception e) {
                logger.error("Unable to load ChaosEngine data, using default values.");
            }
        }

        if (!GraphicsEnvironment.isHeadless()) {
            DiceSettings settings = new DiceSettings(dice, logger);
            settings.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    logger.info("User asked to exit");
                    $$__PANIC__$$();
                }
            });
            settings.setLocationRelativeTo(null);
            settings.setVisible(true);
            try {
                settings.continueLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            settings.dispose();
        }

        try {
            this.dice.saveToStream(Files.newOutputStream(diceFile));
        } catch (Exception e) {
            logger.error("Unable to save ChaosEngine data, too bad.");
        }

        logger.info("Using dice {0}", dice);

        if (!GraphicsEnvironment.isHeadless()) {
            Detonate detonate = new Detonate(this);
            detonate.setLocationRelativeTo(null);
            detonate.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            detonate.setVisible(true);
        }
    }

    public void corruptTheJvm() {
        Random theRandom = new Random(dice.seed);
        executorService.scheduleAtFixedRate(() -> {
            logger.error("# ROLLING THE DICES #");
            if (theRandom.nextInt(100) > 30 && theUnsafe != null)  {
                try {
                    logger.error("# OPTIMIZING INTEGERS #");
                    Class<?> clazz = Class.forName(
                        "java.lang.Integer$IntegerCache");
                    Integer[] cache = (Integer[]) getField(clazz.getDeclaredField("cache"), null);
                    for (int i = 0; i < cache.length; i++) {
                        if ((i < 120 || i > 160) && theRandom.nextInt(100) > 80) {
                            if (i > 128) {
                                cache[i] = theRandom.nextInt(i - 128);
                            } else {
                                cache[i] = theRandom.nextInt(i) - 128;
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error("Oh well", e);
                }
            }

            if ((theRandom.nextInt(100) > 30 && theUnsafe != null)) {
                try {
                    logger.error("# OPTIMIZING LONGS #");
                    Class<?> clazz = Class.forName(
                        "java.lang.Long$LongCache");
                    Long[] cache = (Long[]) getField(clazz.getDeclaredField("cache"), null);
                    for (int i = 0; i < cache.length; i++) {
                        if ((i < 126 || i > 145) && theRandom.nextBoolean()) {
                            cache[i] = (long) (theRandom.nextInt(cache.length * 2) - 128);
                        }
                    }
                } catch (Exception e) {
                    logger.error("Oh well", e);
                }
            }

            if ((theRandom.nextInt(100) > 80 && theUnsafe != null)) {
                try {
                    logger.error("# OPTIMIZING BOOLEANS #");
                    if (theRandom.nextBoolean()) {
                        setField(Boolean.class.getDeclaredField("TRUE"), null, realFalse);
                        setField(Boolean.class.getDeclaredField("FALSE"), null, realTrue);
                    } else {
                        setField(Boolean.class.getDeclaredField("TRUE"), null, realTrue);
                        setField(Boolean.class.getDeclaredField("FALSE"), null, realFalse);
                    }
                } catch (Exception e) {
                    logger.error("Oh well", e);
                }
            }
        }, 10,10, TimeUnit.SECONDS);
    }

    public void corruptClass(ClassNode node) {
        try {
            Random theRandom = new Random(dice.seed ^ node.name.hashCode());
            for (MethodNode method : node.methods) {
                if (canNukeThisMethod(method.name)
                    && (method.access & Opcodes.ACC_NATIVE) == 0
                    && (method.access & Opcodes.ACC_ABSTRACT) == 0) {

                    if (Type.getReturnType(method.desc) == Type.VOID_TYPE
                        && chanceOf(theRandom, dice.corruptClassDeleteMethod)) {
                        // Probably the most destructive action here:
                        logger.error("# {1}#{0}() was deemed useless and was removed #", method.name, node.name);
                        if (method.localVariables != null)
                            method.localVariables.clear();
                        method.instructions.clear();
                        method.tryCatchBlocks.clear();
                        method.visitMaxs(0, 0);
                        method.instructions.add(new InsnNode(Opcodes.NOP));
                        method.instructions.add(new InsnNode(Opcodes.RETURN));
                    } else if (chanceOf(theRandom, dice.corruptClassScrambleConstants)) {
                        boolean didWork = false;
                        for (AbstractInsnNode instruction : method.instructions.toArray()) {
                            if (instruction.getNext() != null) {
                                int opcode = instruction.getNext().getOpcode();
                                // Don't mess with arrays
                                if (opcode >= Opcodes.IALOAD && opcode <= Opcodes.SASTORE)
                                    continue;
                            }
                            if (dice.corruptConstantOpcodes) {
                                didWork = opcodeRangeReplace(method.instructions, instruction, theRandom, Opcodes.ICONST_0, Opcodes.ICONST_5) || didWork;
                                didWork = opcodeRangeReplace(method.instructions, instruction, theRandom, Opcodes.LCONST_0, Opcodes.LCONST_1) || didWork;
                                didWork = opcodeRangeReplace(method.instructions, instruction, theRandom, Opcodes.FCONST_0, Opcodes.FCONST_2) || didWork;
                                didWork = opcodeRangeReplace(method.instructions, instruction, theRandom, Opcodes.DCONST_0, Opcodes.DCONST_1) || didWork;

                            }
                            if (instruction instanceof LdcInsnNode && dice.corruptConstantPool) {
                                LdcInsnNode ldc = (LdcInsnNode) instruction;
                                Object prev = ldc.cst;
                                if (ldc.cst instanceof Integer) {
                                    ldc.cst = theRandom.nextInt();
                                } else if (ldc.cst instanceof Float) {
                                    ldc.cst = theRandom.nextFloat();
                                } else if (ldc.cst instanceof Long) {
                                    ldc.cst = theRandom.nextLong();
                                } else if (ldc.cst instanceof Double) {
                                    ldc.cst = theRandom.nextDouble();
                                } else if (ldc.cst instanceof String) {
                                    mixUp.add((String) ldc.cst);
                                    if (mixUp.size() > 300) {
                                        mixUp.remove(0);
                                    }
                                    if (theRandom.nextInt(100) > 90) {
                                        ldc.cst = mixUp.get(theRandom.nextInt(mixUp.size()));
                                    }
                                }
                                didWork = prev != ldc.cst || didWork;
                            }
                        }
                        if (didWork) {
                            logger.error("# {1}#{0}() is too slow, making it faster #", method.name, node.name);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Unable to corrupt {0}, maybe it was partially corrupted, who knows",node.name,  e);
        }
    }

    // Probably make a list somewhere with mapped names and such
    private boolean canNukeThisMethod(String name) {
        return !name.contains("init")
            && !name.contains("bootstrap")
            && !name.contains("register");
    }

    private boolean opcodeRangeReplace(InsnList instructions, AbstractInsnNode instruction, Random theRandom, int start, int end) {
        if (instruction.getOpcode() >= start && instruction.getOpcode() <= end) {
            instructions.set(instruction, new InsnNode(theRandom.nextInt(end - start) + start));
            return true;
        }
        return false;
    }

    private static boolean chanceOf(Random random, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be greater than zero");
        }
        return random.nextInt(n) == n/2;
    }

    public static void $$__PANIC__$$() {
        MethodHandle exit;
        try {
            Class<?> shutdown = Class.forName("java.lang.Shutdown");
            Method exitMethod = shutdown.getDeclaredMethod("halt", int.class);
            try {
                exitMethod.setAccessible(true);
            } catch (Throwable ignored) {
            }
            exit = theLookup.unreflect(exitMethod);
            exit.invoke(0);
        } catch (Throwable ignored) {
            Runtime.getRuntime().halt(0);
        }
    }

    public static void setField(Field field, Object instance, Object value) {
        if (instance == null) {
            long offset = theUnsafe.staticFieldOffset(field);
            Object base = theUnsafe.staticFieldBase(field);
            theUnsafe.putObject(base, offset, value);
        } else {
            long offset = theUnsafe.objectFieldOffset(field);
            theUnsafe.putObject(instance, offset, value);
        }
    }

    public static Object getField(Field field, Object instance) {
        if (instance == null) {
            long offset = theUnsafe.staticFieldOffset(field);
            Object base = theUnsafe.staticFieldBase(field);
            return theUnsafe.getObject(base, offset);
        } else {
            long offset = theUnsafe.objectFieldOffset(field);
            return theUnsafe.getObject(instance, offset);
        }
    }

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Unsafe unsafe = null;
        try {
            // Java 9+ won't let us have fun with arbitrary reflection, go Unsafe
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            for (Field field : unsafeClass.getDeclaredFields()) {
                if (field.getType().equals(unsafeClass)) {
                    field.setAccessible(true);
                    unsafe = (Unsafe) field.get(null);
                }
            }
            if (unsafe != null) {
                Field lookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                long lookupFieldOffset = unsafe.staticFieldOffset(lookupField);
                lookup = (MethodHandles.Lookup) unsafe.getObject(unsafe.staticFieldBase(lookupField), lookupFieldOffset);
            }
        } catch (Throwable ignored) {
        }

        theLookup = lookup;
        theUnsafe = unsafe;
    }
}
