package tv.vanhal.ark.tileentity;

import tv.vanhal.ark.network.PartialTileNBTUpdateMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBase extends TileEntity {
	protected ItemStack[] slots;

	/**
	 * Sygnalises whether the TileEntity needs to be synced with clients
	 */
	private boolean dirty;
	/**
	 * Tag holding partial updates that will be sent to players upon synchronisation
	 */
	private NBTTagCompound partialUpdateTag = new NBTTagCompound();
	
	public TileEntityBase(int numSlots) {
		slots = new ItemStack[numSlots];
	}

	/**
	 * Do not extend this method, use writeSyncOnlyNBT, writeCommonNBT or writeNonSyncableNBT as needed.
	 */
	@Override
	public final void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		writeCommonNBT(nbt);
		writeNonSyncableNBT(nbt);
	}

	/**
	 * Do not extend this method, use readSyncOnlyNBT, readCommonNBT or readNonSyncableNBT as needed.
	 */
	@Override
	public final void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		readCommonNBT(nbt);
		readNonSyncableNBT(nbt);
	}

	/**
	 * This overridable method is intended for writing to NBT all data that is used only at runtime
	 * and never saved to hdd.
	 * @param nbt
	 */
	protected void writeSyncOnlyNBT(NBTTagCompound nbt) {
	}

	/**
	 * This overridable method is intended for writing to NBT all data that is both saved to hdd
	 * and can be sent to the client TileEntity through S35PacketUpdateTileEntity.
	 * 
	 * WARNING: Do not include slot contents here. 
	 * They are automagically synced when a GUI is opened using S30PacketWindowItems 
	 * @param nbt
	 */
	protected void writeCommonNBT(NBTTagCompound nbt) {
	}

	/**
	 * This overridable method is intended for writing to NBT all data that will 
	 * NOT be synced using S35PacketUpdateTileEntity packets.
	 * 
	 * This includes, but is not limited to, slot contents. 
	 * See writeSyncableNBT for more info.
	 * @param nbt
	 */
	protected void writeNonSyncableNBT(NBTTagCompound nbt) {
		
		if (slots.length>0) {
			NBTTagList contents = new NBTTagList();
			for (int i = 0; i < slots.length; i++) {
				if (slots[i] != null) {
					ItemStack stack = slots[i];
					NBTTagCompound tag = new NBTTagCompound();
					tag.setByte("Slot", (byte)i);
					stack.writeToNBT(tag);
					contents.appendTag(tag);
				}
			}
			nbt.setTag("Contents", contents);
		}
	}

	/**
	 * This overridable method is intended for reading from NBT all data that is used only at runtime
	 * and never saved to hdd.
	 * @param nbt
	 */
	public void readSyncOnlyNBT(NBTTagCompound nbt) {
	}

	/**
	 * This overridable method is intended for reading from NBT all data that is both saved to hdd
	 * and can be sent to the client TileEntity through S35PacketUpdateTileEntity and PartialTileNBTUpdateMessage.
	 * 
	 * WARNING: Please check if the tag exists before reading it. Due to the nature of 
	 * PartialTileNBTUpdateMessage properties that didn't change since the last message
	 * will not be included.
	 * 
	 * WARNING: Do not include slot contents here. 
	 * They are automagically synced when a GUI is opened using S30PacketWindowItems 
	 * @param nbt
	 */
	public void readCommonNBT(NBTTagCompound nbt) {

	}

	/**
	 * This overridable method is intended for reading from NBT all data that is only saved to hdd 
	 * and never synced between client and server, or is synced using a different method (e.g. inventory 
	 * contents and S30PacketWindowItems)
	 * @param nbt
	 */
	protected void readNonSyncableNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("Contents")) {
			NBTTagList contents = nbt.getTagList("Contents", 10);
			for (int i = 0; i < contents.tagCount(); i++) {
				NBTTagCompound tag = (NBTTagCompound) contents.getCompoundTagAt(i);
				byte slot = tag.getByte("Slot");
				if (slot < slots.length) {
					slots[slot] = ItemStack.loadItemStackFromNBT(tag);
				}
			}
		}
		
	}

	/**
	 * This method is used to sync data when a GUI is opened. the packet will contain
	 * all syncable data.
	 */
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeCommonNBT(nbttagcompound);
		this.writeSyncOnlyNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -1, nbttagcompound);
	}

	/**
	 * This method is used to load syncable data when a GUI is opened.
	 */
	@Override    
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readCommonNBT(pkt.func_148857_g());
		this.readSyncOnlyNBT(pkt.func_148857_g());
	}

	/**
	 * This method will generate a message with partial updates for this TileEntity.
	 * 
	 * WARNING: Using this method resets the dirty flag and clears all pending updates up to this point.
	 * @return
	 */
	public PartialTileNBTUpdateMessage getPartialUpdateMessage() {

		PartialTileNBTUpdateMessage message = new PartialTileNBTUpdateMessage(this.xCoord, this.yCoord, this.zCoord, partialUpdateTag);
		dirty = false;
		partialUpdateTag = new NBTTagCompound();

		return message;
	}

	/**
	 * Utility method, so you don't have to remember to set dirty to true
	 */
	protected void addPartialUpdate(String fieldName, Integer value) {
		partialUpdateTag.setInteger(fieldName, value);
		dirty = true;
	}
	/**
	 * Utility method, so you don't have to remember to set dirty to true
	 */	
	protected void addPartialUpdate(String fieldName, String value) {
		partialUpdateTag.setString(fieldName, value);
		dirty = true;
	}
	/**
	 * Utility method, so you don't have to remember to set dirty to true
	 */	
	protected void addPartialUpdate(String fieldName, NBTBase value) {
		partialUpdateTag.setTag(fieldName, value);
		dirty = true;
	}
	/**
	 * Utility method, so you don't have to remember to set dirty to true
	 */	
	protected void addPartialUpdate(String fieldName, Boolean value) {
		partialUpdateTag.setBoolean(fieldName, value);
		dirty = true;
	}

	/**
	 * Whether the TileEntity needs syncing.
	 * @return
	 */
	public boolean isDirty() {
		return dirty;
	}
	
	public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return false;
	}

}
