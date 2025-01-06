package com.juanmuscaria.nuke.launch;

import com.juanmuscaria.nuke.ChaosEngine;
import com.juanmuscaria.nuke.logging.Log4jLogger;
import com.juanmuscaria.nuke.logging.LoggerAdapter;
import cpw.mods.modlauncher.api.*;
import org.apache.logging.log4j.LogManager;
import org.objectweb.asm.tree.ClassNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.*;

public class ModLauncherEntrypoint implements ITransformationService, ITransformer<ClassNode>, Platform {
    private final LoggerAdapter logger = new Log4jLogger(LogManager.getLogger("ChaosEngine"));
    private Path gameDir;
    private ChaosEngine engine;

    @Override
    public String name() {
        return "CorruptMyGame";
    }

    @Override
    public void initialize(IEnvironment env) {
        register();
        gameDir = env.getProperty(IEnvironment.Keys.GAMEDIR.get()).get();
        engine = new ChaosEngine(this);
        engine.primeTheExplosives();
    }

    @Override
    public void beginScanning(IEnvironment env) {

    }

    @Override
    public void onLoad(IEnvironment iEnvironment, Set<String> set) {
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<ITransformer> transformers() {
        return Collections.singletonList(this);
    }


    @Override
    public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
        engine.corruptClass(input);
        return input;
    }

    @Override
    public TransformerVoteResult castVote(ITransformerVotingContext ctx) {
        return TransformerVoteResult.YES;
    }

    @Override
    public Set<Target> targets() {
        //PLEASE tell me if there's another way around for patching EVERY class in the game
        Set<Target> targets = new HashSet<>();
        try (BufferedReader classes = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream("/classlist"))))) {
            String line;
            while ((line = classes.readLine()) != null) {
                targets.add(Target.targetClass(line));
            }
        } catch (Throwable e) {
            logger.error("No classes will be corrupted :(", e);
        }
        return targets;
    }

    @Override
    public LoggerAdapter logger() {
        return logger;
    }

    @Override
    public Path gameDir() {
        return gameDir;
    }

    @Override
    public ChaosEngine engine() {
        return engine;
    }
}
