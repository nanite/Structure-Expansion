package com.sunekaer.mods.structureexpansion.mixin;

import com.sunekaer.mods.structureexpansion.StructureExpansion;
import net.minecraft.client.renderer.blockentity.BlockEntityWithBoundingBoxRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockEntityWithBoundingBoxRenderer.class)
public abstract class BlockEntityWithBoundingBoxRendererMixin {
	/**
	 * @author Sunekaer
	 * @reason Extend the view distance of Structure Block.
	 */
	@Overwrite
	public int getViewDistance() {
		return StructureExpansion.NEW_LIMIT;
	}
}
