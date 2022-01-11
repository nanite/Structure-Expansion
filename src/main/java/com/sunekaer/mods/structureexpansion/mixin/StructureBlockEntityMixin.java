package com.sunekaer.mods.structureexpansion.mixin;

import com.sunekaer.mods.structureexpansion.StructureExpansion;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StructureBlockEntity.class)
public abstract class StructureBlockEntityMixin {
	@ModifyConstant(method = "load", constant = {@Constant(intValue = 48), @Constant(intValue = -48)})
	private int getMaxSizePos(int value) {
		return value > 0 ? StructureExpansion.NEW_LIMIT_POS : StructureExpansion.NEW_LIMIT_NEG;
	}
}
