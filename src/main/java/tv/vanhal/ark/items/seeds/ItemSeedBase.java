package tv.vanhal.ark.items.seeds;

import java.util.List;

import cpw.mods.fml.common.MinecraftDummyContainer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tv.vanhal.ark.Ark;
import tv.vanhal.ark.blocks.crops.BlockCropBase;
import tv.vanhal.ark.items.ArkItems;
import tv.vanhal.ark.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemSeedBase extends ItemBase implements IPlantable {
	protected Block plant;
	//24,000 ticks in a MC day == 20 minutes game time
	protected long genermiateTime = 48000;
	
	public ItemSeedBase(String name, Block crop) {
		this.plant = crop;
		this.itemName = name;
		this.setUnlocalizedName(name);
	}
	
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    	itemStack.getItemDamage();
    	if (side!=1) {
    		return false;
    	} else if (itemStack.getItemDamage()!=0) {
    		return false;
    	} else if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack)) {
    		if (world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
                world.setBlock(x, y + 1, z, this.plant);
                --itemStack.stackSize;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
    	if (itemStack.getItemDamage()!=0) {
    		list.add(EnumChatFormatting.GRAY + "Not germinated");
    	} else {
    		list.add(EnumChatFormatting.GRAY + "Germinated");
    	}
    }
    
    //use this to update the seed to germinated
    @Override
    public int getDamage(ItemStack stack) {
    	if ( (stack.hasTagCompound()) && (stack.getTagCompound().hasKey("HarvestTime")) ) {
    		long worldTime = 0;
    		if (Ark.proxy.isServer()) {
    			worldTime = MinecraftServer.getServer().worldServerForDimension(0).getWorldTime();
    		} else {
    			worldTime = Minecraft.getMinecraft().theWorld.getWorldTime();
    		}
    		long harvestTime = stack.getTagCompound().getLong("HarvestTime");
    		if ((worldTime - harvestTime) > genermiateTime) {
    			stack.setTagCompound(null);
    			stack.setItemDamage(0);
    		}
    	}
    	return super.getDamage(stack);
    }
    
    public ItemStack harvestCrop(ItemStack itemStack, World world) {
    	if (!world.isRemote) {
	    	itemStack.setItemDamage(1);
	    	itemStack.setTagCompound(new NBTTagCompound());
	    	long worldTime = world.getWorldTime();
	    	worldTime = (worldTime + 50)/100 * 100;
	    	//Ark.logger.info("Setting harvest time to: "+worldTime);
	    	itemStack.getTagCompound().setLong("HarvestTime", worldTime);
    	}
    	return itemStack;
    }

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return plant;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}
	
	@Override
	protected void setRecipe() {
		
	}
	

}
