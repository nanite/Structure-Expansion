package com.sunekaer.mods.structureexpansion;

import com.sunekaer.mods.structureexpansion.commands.CommandClean;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(StructureExpansion.MOD_ID)
public class StructureExpansion {
    public static final String MOD_ID = "structureexpansion";
    public static final int NEW_LIMIT = 256;
    public static final int NEW_LIMIT_POS = NEW_LIMIT - 1;
    public static final int NEW_LIMIT_NEG = -NEW_LIMIT_POS;

    public StructureExpansion(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("structure").then(CommandClean.register()));
    }
}
