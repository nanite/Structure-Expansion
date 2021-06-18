package com.sunekaer.mods.structureexpansion.mixin;

import com.sunekaer.mods.structureexpansion.StructureExpansion;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CUpdateStructureBlockPacket;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.tileentity.StructureBlockTileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

@Mixin(CUpdateStructureBlockPacket.class)
public abstract class CUpdateStructureBlockPacketMixin {
	@Shadow
	private BlockPos pos;

	@Shadow
	private StructureBlockTileEntity.UpdateCommand updateCommand;

	@Shadow
	private StructureMode mode;

	@Shadow
	private String name;

	@Shadow
	private BlockPos position;

	@Shadow
	private BlockPos size;

	@Shadow
	private Mirror mirror;

	@Shadow
	private Rotation rotation;

	@Shadow
	private String metaData;

	@Shadow
	private boolean ignoreEntities;

	@Shadow
	private boolean showAir;

	@Shadow
	private boolean showBoundingBox;

	@Shadow
	private float integrity;

	@Shadow
	private long seed;

	/**
	 * @author LatvianModder
	 * @reason to fix 48 block limit
	 */
	@Overwrite
	public void readPacketData(PacketBuffer buf) throws IOException {
		pos = buf.readBlockPos();
		updateCommand = buf.readEnumValue(StructureBlockTileEntity.UpdateCommand.class);
		mode = buf.readEnumValue(StructureMode.class);
		name = buf.readString(32767);

		position = new BlockPos(
				MathHelper.clamp(buf.readVarInt(), StructureExpansion.NEW_LIMIT_NEG, StructureExpansion.NEW_LIMIT_POS),
				MathHelper.clamp(buf.readVarInt(), StructureExpansion.NEW_LIMIT_NEG, StructureExpansion.NEW_LIMIT_POS),
				MathHelper.clamp(buf.readVarInt(), StructureExpansion.NEW_LIMIT_NEG, StructureExpansion.NEW_LIMIT_POS)
		);

		size = new BlockPos(
				MathHelper.clamp(buf.readVarInt(), 0, StructureExpansion.NEW_LIMIT_POS),
				MathHelper.clamp(buf.readVarInt(), 0, StructureExpansion.NEW_LIMIT_POS),
				MathHelper.clamp(buf.readVarInt(), 0, StructureExpansion.NEW_LIMIT_POS)
		);

		mirror = buf.readEnumValue(Mirror.class);
		rotation = buf.readEnumValue(Rotation.class);
		metaData = buf.readString(12);
		integrity = MathHelper.clamp(buf.readFloat(), 0.0F, 1.0F);
		seed = buf.readVarLong();
		int k = buf.readVarInt();
		ignoreEntities = (k & 1) != 0;
		showAir = (k & 2) != 0;
		showBoundingBox = (k & 4) != 0;
	}

	/**
	 * @author LatvianModder
	 * @reason to fix 48 block limit
	 */
	@Overwrite
	public void writePacketData(PacketBuffer buf) {
		buf.writeBlockPos(pos);
		buf.writeEnumValue(updateCommand);
		buf.writeEnumValue(mode);
		buf.writeString(name);
		buf.writeVarInt(position.getX());
		buf.writeVarInt(position.getY());
		buf.writeVarInt(position.getZ());
		buf.writeVarInt(size.getX());
		buf.writeVarInt(size.getY());
		buf.writeVarInt(size.getZ());
		buf.writeEnumValue(mirror);
		buf.writeEnumValue(rotation);
		buf.writeString(metaData);
		buf.writeFloat(integrity);
		buf.writeVarLong(seed);

		int i = 0;
		if (ignoreEntities) {
			i |= 1;
		}

		if (showAir) {
			i |= 2;
		}

		if (showBoundingBox) {
			i |= 4;
		}

		buf.writeVarInt(i);
	}
}
