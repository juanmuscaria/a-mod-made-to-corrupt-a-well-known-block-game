# Corrupt My Game

Feeling lucky today? See how long your game can last with this mod, that is if it even starts to begin with...

This mod will scramble and destroy your game, leading to all sorts of unexpected behaviors, be warned this mod
is CORRUPTING your game and may and WILL lead to save corruption and data loss!
DO NOT use this mod alongside your normal minecraft save.


## Supported platforms

* LaunchWrapper: Can run on *any* minecraft version being bootstrapped by LaunchWrapper, used by forge 1.6.4 up to 1.12.2
* ModLauncher: Same as above, any minecraft version, used by (neo)forge from 1.13 up to latest.

This mod is not really a minecraft mod, so it has no version restrictions and should work on any loader as long 
as it can capture and process minecraft classes.


## What's the deal?

This is a project I made in a few days
because I thought it would be funny to tear apart and break Minecraft and see how far it goes,
this mod can work in two ways:

* Class Corruptor: Patch, scramble and remove parts of the game code, most of the time it won't even start the game, but when it does some fun things can happen.
* JVM Corruptor: Destroy the java runtime by removing and swapping important things with reflection, true will become false, false will become true, anything can happen until the game gives up.

Class corruption is deterministic when targeting specific Minecraft versions under the same loader,
which means you can create, save and share your configuration with friends and experience the same game corruptions

JVM corruption will cause all values used by java to be scrambled,
meaning you cannot reproduce the same corruption effects sometimes,
it tries to be deterministic, but at that point the JVM cannot be trusted with coherent logic.

