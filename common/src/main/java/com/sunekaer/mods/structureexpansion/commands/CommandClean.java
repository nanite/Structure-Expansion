package com.sunekaer.mods.structureexpansion.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class CommandClean {
	public static ArgumentBuilder<CommandSourceStack, ?> register() {
		return Commands.literal("clean")
				.requires(cs -> cs.hasPermission(2))
				.then(Commands.argument("structure_file", StringArgumentType.string())
						.executes(ctx -> cleanStruc(ctx.getSource(), StringArgumentType.getString(ctx, "structure_file")))
				);
	}

	private static int cleanStruc(CommandSourceStack source, String file) {
		ServerLevel worldServer = source.getLevel();
		StructureTemplateManager templateManager = worldServer.getStructureManager();
		ResourceLocation name = new ResourceLocation(file);
		StructureTemplate template = templateManager.getOrCreate(name);

		template.palettes.forEach(e ->{
			int preSize = e.blocks().size();
			e.blocks().removeIf(a -> a.state().getBlock() == Blocks.AIR);
			int removed = preSize - e.blocks().size();
			source.sendSuccess(() -> Component.literal("Removed " + removed + " air blocks"), true);
		});

		templateManager.save(name);
		return 1;
	}
}
