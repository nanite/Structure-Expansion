package com.sunekaer.mods.structureexpansion.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

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
		StructureManager templateManager = worldServer.getStructureManager();
		ResourceLocation name = new ResourceLocation(file);
		StructureTemplate template = templateManager.getOrCreate(name);

        /*
		template.blocks.forEach(e -> {
			int preSize = e.func_237157_a_().size();
			e.func_237157_a_().removeIf(a -> a.state.getBlock() == Blocks.AIR);
			int removed = preSize - e.func_237157_a_().size();
			source.sendSuccess(new TranslatableComponent("Removed " + removed + " air blocks"), true);
		});
         */

		templateManager.save(name);
		return 1;
	}
}
