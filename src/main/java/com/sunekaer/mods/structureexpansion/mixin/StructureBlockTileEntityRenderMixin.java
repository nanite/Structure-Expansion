package com.sunekaer.mods.structureexpansion.mixin;

import net.minecraft.tileentity.StructureBlockTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StructureBlockTileEntity.class)
public abstract class StructureBlockTileEntityRenderMixin extends TileEntity {
    public StructureBlockTileEntityRenderMixin(TileEntityType<?> tileEntityType_1) {
        super(tileEntityType_1);
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return 288.0D;
    }
}
