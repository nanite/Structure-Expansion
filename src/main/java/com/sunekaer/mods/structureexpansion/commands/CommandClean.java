package com.sunekaer.mods.structureexpansion.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;


public class CommandClean {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return literal("clean")
                .requires(cs -> cs.hasPermissionLevel(2))
                .then(argument("structure_file", StringArgumentType.string())
                        .executes(ctx -> cleanStruc(
                                ctx.getSource(),
                                StringArgumentType.getString(ctx, "structure_file")
                                )
                        )
                );
    }

    private static int cleanStruc(CommandSource source, String file) {

        ServerWorld worldServer = source.getWorld();
        TemplateManager templateManager = worldServer.getStructureTemplateManager();
        ResourceLocation name = new ResourceLocation(file);
        Template template = templateManager.getTemplate(name);

        template.blocks.forEach(e -> {
            int preSize = e.func_237157_a_().size();
            e.func_237157_a_().removeIf(a -> a.state.getBlock() == Blocks.AIR);
            int removed = preSize - e.func_237157_a_().size();
            source.sendFeedback(new TranslationTextComponent("Removed " + removed + " air blocks"), true);
        });
        templateManager.writeToFile(name);

        return 1;
    }
}
