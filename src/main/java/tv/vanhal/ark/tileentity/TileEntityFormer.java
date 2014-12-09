package tv.vanhal.ark.tileentity;

import java.util.ArrayList;
import java.util.Arrays;

import tv.vanhal.ark.Ark;
import tv.vanhal.ark.blocks.ArkBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityFormer extends TileEntityBase {
	public final int RUN_TIME = 1200;
	
	protected int running = 0;
	protected boolean validSetup = false;

	public TileEntityFormer() {
		super(0);
		running = 0;
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			validateSetup();
			if (running>0) {
				
				if (!validSetup) {
					running = 0;
				} else {
					running--;
					if (running<=0) {
						//finished
						worldObj.setBlock(xCoord, yCoord + 1, zCoord, checkRecipe(true));
					}
				}
			} else if ( (validSetup) && (running==0) ) {
				running = RUN_TIME;
			}
		}
	}
	@Override
	public boolean onBlockActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!worldObj.isRemote) {
			validateSetup();
			if ( (validSetup) && (running==0) ) {
				running = RUN_TIME;
			}
		}
		return true;
	}
	
	@Override
	protected void writeCommonNBT(NBTTagCompound nbt) {
		nbt.setInteger("running", this.running);
		nbt.setBoolean("validSetup", this.validSetup);
	}
	
	@Override
	public void readCommonNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("running")) this.running = nbt.getInteger("running");
		if (nbt.hasKey("validSetup")) this.validSetup = nbt.getBoolean("validSetup");
	}
	
	public void validateSetup() {
		boolean newBool = false;
		if (worldObj.getBlock(xCoord, yCoord+1, zCoord) == Blocks.air) {
			if (checkWater()) {
				if (checkRecipe()!=null) {
					newBool = true;
				}
			}
		}
		
		if (newBool!=validSetup) {
			validSetup = newBool;
			this.addPartialUpdate("validSetup", validSetup);
		}
		//Ark.logger.info("Setup is "+((validSetup)?"Valid":"Invalid"));
	}
	
	protected boolean checkWater() {
		int testY = this.yCoord + 1;
		for (int i = -2; i<=2; i++) {
			if (worldObj.getBlock(xCoord+i, testY, zCoord-2)!=Blocks.water) return false;
			if (worldObj.getBlock(xCoord+i, testY, zCoord+2)!=Blocks.water) return false;
			if (worldObj.getBlock(xCoord+2, testY, zCoord+i)!=Blocks.water) return false;
			if (worldObj.getBlock(xCoord-2, testY, zCoord+i)!=Blocks.water) return false;
		}
		return true;
	}
	
	protected Block checkRecipe() {
		return checkRecipe(false);
	}
	
	protected Block checkRecipe(boolean remove) {
		ArrayList<Block> recipe = new ArrayList<Block>();
		for (int i = -1; i<=1; i++) {
			for (int j=-1; j<=1; j++) {
				if (!( (i==0) && (j==0) )) {
					recipe.add(worldObj.getBlock(xCoord+i, yCoord+1, zCoord+j));
					if (remove) {
						worldObj.setBlockToAir(xCoord+i, yCoord+1, zCoord+j);
					}
				}
			}
		}
		if (recipe.size()==8) {
			//test the various recipes
			boolean wood = true;
			for(int i = 0; i<8; i++) {
				if (!recipe.get(i).equals(reinforcedWood.get(i))) {
					wood = false;
				}
			}
			
			if (wood) {
				return ArkBlocks.reinforcedWood;
			}
		}
		
		
		
		return null;
	}
	
	protected final ArrayList<Block> reinforcedWood = new ArrayList<Block>(Arrays.asList(Blocks.cobblestone, Blocks.log, Blocks.cobblestone, Blocks.log, Blocks.log, Blocks.cobblestone, Blocks.log, Blocks.cobblestone));
}
