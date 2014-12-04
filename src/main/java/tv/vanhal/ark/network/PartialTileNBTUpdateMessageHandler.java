package tv.vanhal.ark.network;

import tv.vanhal.ark.tileentity.TileEntityBase;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PartialTileNBTUpdateMessageHandler implements IMessageHandler<PartialTileNBTUpdateMessage, IMessage> {
	@Override
	public IMessage onMessage(PartialTileNBTUpdateMessage message, MessageContext ctx) {
		TileEntity entity =  Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(message.x, message.y, message.z);
		
		if (entity != null && entity instanceof TileEntityBase) {
			TileEntityBase paEntity = (TileEntityBase) entity;
			paEntity.readCommonNBT(message.nbtTag);
			paEntity.readSyncOnlyNBT(message.nbtTag);
		}
		return null;
	}
}
