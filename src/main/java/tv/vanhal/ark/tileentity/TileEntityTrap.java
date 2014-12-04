package tv.vanhal.ark.tileentity;

import tv.vanhal.ark.network.NetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

public class TileEntityTrap extends TileEntityBase {
	public static final int OPENING_ANIMATION_TIME = 15;
	
	private int tickSinceOpened = 0;
	private boolean isShut = false;
	private int trappedEntity = 0;

	public TileEntityTrap() {
		super(0);
		open();
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!isShut) {
			tickSinceOpened++;
			if (tickSinceOpened<=OPENING_ANIMATION_TIME) {
				addPartialUpdate("TickSinceOpened", tickSinceOpened);
			}
		} else {
			if (this.trappedEntity != 0) {
				Entity entity = worldObj.getEntityByID(this.trappedEntity);
				if (entity==null) {
					this.trappedEntity = 0;
					if (!worldObj.isRemote) {
						this.addPartialUpdate("trappedEntity", trappedEntity);
					}
				} else {
					immobilizeEntity(entity);
				}
			}
		}
		if (!worldObj.isRemote) {
			if (this.isDirty()) {
				NetworkHandler.sendToAllAroundNearby(this.getPartialUpdateMessage(), this);
			}
		}
	}
	
	public void onEntityCollided(Entity entity) {
		if (!worldObj.isRemote) {
			if (entity instanceof EntityLivingBase && !isShut && tickSinceOpened > OPENING_ANIMATION_TIME) {
				close((EntityLivingBase) entity);
			}
		} else {
			if (entity instanceof EntityLivingBase && !isShut && tickSinceOpened > OPENING_ANIMATION_TIME) {
				if (entity!=null)
					entity.performHurtAnimation();
			}
		}
		
	}
	
	public boolean isShut() {
		return isShut;
	}
	

	public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!worldObj.isRemote) open();
		return true;
	}
	
	@Override
	protected void writeCommonNBT(NBTTagCompound nbt) {
		nbt.setBoolean("isShut", this.isShut);
		nbt.setInteger("TickSinceOpened", this.tickSinceOpened);
		nbt.setInteger("trappedEntity", this.trappedEntity);
	}
	
	@Override
	public void readCommonNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("isShut")) this.isShut = nbt.getBoolean("isShut");
		if (nbt.hasKey("TickSinceOpened")) this.tickSinceOpened = nbt.getInteger("TickSinceOpened");
		if (nbt.hasKey("trappedEntity")) this.trappedEntity = nbt.getInteger("trappedEntity");
	}
	
	public void open() {
		if (isShut) {
			tickSinceOpened = 0;
			trappedEntity = 0;
			isShut = false;
			this.addPartialUpdate("isShut", isShut);
			this.addPartialUpdate("TickSinceOpened", tickSinceOpened);
			this.addPartialUpdate("trappedEntity", trappedEntity);
		}
	}
	
	public void close(EntityLivingBase entity) {
		if (!isShut) {
			isShut = true;
			trappedEntity = entity.getEntityId();
			entity.setHealth(1);
			this.addPartialUpdate("trappedEntity", trappedEntity);
			this.addPartialUpdate("isShut", isShut);
		}
	}

	public int ticksSinceOpened() {
		return (this.tickSinceOpened>15)? 15 : this.tickSinceOpened;
	}
	
	private void immobilizeEntity(Entity trappedEntity) {
		if (trappedEntity != null) {
			trappedEntity.fallDistance = 0.0f;
			trappedEntity.distanceWalkedOnStepModified = 0.0f;
			trappedEntity.distanceWalkedModified = 0.0f;
			trappedEntity.posX = 0.5 + xCoord;
			trappedEntity.posZ = 0.5 + zCoord;
			trappedEntity.posY = yCoord + trappedEntity.yOffset;
			trappedEntity.prevPosX = 0.5 + xCoord;
			trappedEntity.prevPosZ = 0.5 + zCoord;
			trappedEntity.prevPosY = yCoord + trappedEntity.yOffset;
			trappedEntity.lastTickPosX = 0.5 + xCoord;
			trappedEntity.lastTickPosZ = 0.5 + zCoord;
			trappedEntity.lastTickPosY = yCoord + trappedEntity.yOffset;
			trappedEntity.motionX = 0;
			trappedEntity.motionY = 0;
			trappedEntity.motionZ = 0;
		}
	}
	
	
}
