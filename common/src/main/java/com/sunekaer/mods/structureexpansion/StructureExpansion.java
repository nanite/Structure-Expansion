package com.sunekaer.mods.structureexpansion;

import com.mojang.brigadier.CommandDispatcher;
import com.sunekaer.mods.structureexpansion.commands.CommandClean;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class StructureExpansion {
    public static final int NEW_LIMIT = 256;
    public static final int NEW_LIMIT_POS = NEW_LIMIT - 1;
    public static final int NEW_LIMIT_NEG = -NEW_LIMIT_POS;


    public static void init() {
        CommandRegistrationEvent.EVENT.register(StructureExpansion::registerCommands);
    }

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context, Commands.CommandSelection selection) {
        dispatcher.register(Commands.literal("structure").then(CommandClean.register()));
    }
}
