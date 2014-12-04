package tv.vanhal.ark.blocks.tempcrops;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tv.vanhal.ark.items.ArkItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockPotato;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTempCropPotato extends BlockCrops {
	
	public BlockTempCropPotato() {
		super();
		setBlockName("potatoe");
		setBlockTextureName("minecraft:potatoes");
	}
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta < 7) {
            if (meta == 6) {
                meta = 5;
            }
            return this.icons[meta >> 1];
        } else {
            return this.icons[3];
        }
    }

	protected Item func_149866_i() {
        return ArkItems.potato;
    }

    protected Item func_149865_P() {
        return ArkItems.potato;
    }
	
	public void preInit() {
		GameRegistry.registerBlock(this, "Potatos");
	}
	
	public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList();
        if (metadata>=7) { //ripe
        	int chance = 16;
        	for (int i =1; i<=3; i++) {
        		if (world.rand.nextInt(15) < ((int)chance/i)) {
        			ItemStack itemStack = new ItemStack(ArkItems.potato, 1);
        			itemStack = ArkItems.potato.harvestCrop(itemStack, world);
        			ret.add(itemStack);
        		}
        	}
        } else { //not yet grown, 50-50 chance of returning nothing
        	 if (world.rand.nextInt(15) < 8) {
        		 ret.add(new ItemStack(ArkItems.potato));
        	 }
        }
        return ret;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.icons = new IIcon[4];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
}
