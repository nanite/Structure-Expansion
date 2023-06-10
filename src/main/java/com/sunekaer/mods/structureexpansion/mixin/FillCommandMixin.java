package com.sunekaer.mods.structureexpansion.mixin;

import net.minecraft.server.commands.FillCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FillCommand.class)
public class FillCommandMixin {
    @ModifyConstant(method = "fillBlocks", constant = {@Constant(intValue = 32768)})
    private static int fillBlocks(int value) {
        return Integer.MAX_VALUE;
    }
}
