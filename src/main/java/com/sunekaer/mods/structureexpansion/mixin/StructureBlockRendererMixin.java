package com.sunekaer.mods.structureexpansion.mixin;

import com.sunekaer.mods.structureexpansion.StructureExpansion;
import net.minecraft.client.renderer.blockentity.StructureBlockRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(StructureBlockRenderer.class)
public abstract class StructureBlockRendererMixin {
	@Overwrite
	public int getViewDistance() {
		return StructureExpansion.NEW_LIMIT;
	}
}
