package com.sunekaer.mods.structureexpansion;

import com.sunekaer.mods.structureexpansion.commands.CommandClean;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("structureexpansion")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StructureExpansion {
	public static final int NEW_LIMIT = 512;
	public static final int NEW_LIMIT_POS = NEW_LIMIT - 1;
	public static final int NEW_LIMIT_NEG = -NEW_LIMIT_POS;

	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("structure").then(CommandClean.register()));
	}
}
