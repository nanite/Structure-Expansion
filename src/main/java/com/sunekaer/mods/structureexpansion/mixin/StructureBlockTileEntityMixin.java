package com.sunekaer.mods.structureexpansion.mixin;

import net.minecraft.tileentity.StructureBlockTileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StructureBlockTileEntity.class)
public abstract class StructureBlockTileEntityMixin {
    @ModifyConstant(method = "read", constant = {@Constant(intValue = 48), @Constant(intValue = -48)})
    private int getMaxSizePos(int value){
        return value > 0 ? 127 : -128;
    }
}
