package com.sunekaer.mods.structureexpansion.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "structureexpansion", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StructureCommand {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event)
    {
        new StructureCommand(event.getDispatcher());
    }

    public StructureCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("structure")
                        .then(CommandClean.register())
        );
    }
}
