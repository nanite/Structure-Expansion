package com.sunekaer.mods.structureexpansion.mixin;

import net.minecraft.network.play.client.CUpdateStructureBlockPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CUpdateStructureBlockPacket.class)
public abstract class CUpdateStructureBlockPacketMixin {
    @ModifyConstant(method = "readPacketData", constant = {@Constant(intValue = 48), @Constant(intValue = -48)})
    private int getMaxSizePos(int value){
        return value > 0 ? 255 : -256;
    }
}
