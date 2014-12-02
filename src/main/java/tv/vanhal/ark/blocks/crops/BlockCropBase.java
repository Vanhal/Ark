package tv.vanhal.ark.blocks.crops;

import java.util.Random;

import tv.vanhal.ark.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCropBase extends BlockBase implements IGrowable, IPlantable {

	public BlockCropBase(String name) {
		super(name, Material.plants);
		this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs)null);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.disableStats();
	}
	
	@Override
	public boolean isOpaqueCube() {
        return false;
    }
	
	@Override
	public boolean renderAsNormalBlock() {
        return false;
    }

	@Override
    public int getRenderType() {
        return 6;
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        this.checkAndDropBlock(world, x, y, z);
    }

	@Override
    public void updateTick(World world, int x, int y, int z, Random rnd) {
        this.checkAndDropBlock(world, x, y, z);
        
        if (world.getBlockLightValue(x, y + 1, z) >= 9) {
            int l = world.getBlockMetadata(x, y, z);
            if (l < 7) {
                float f = 5.0F; //chance of growing every rnd tick
                if (rnd.nextInt((int)(25.0F / f) + 1) == 0) {
                    ++l;
                    world.setBlockMetadataWithNotify(x, y, z, l, 2);
                }
            }
        }
    }

    protected void checkAndDropBlock(World world, int x, int y, int z) {
        if (!this.canBlockStay(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, getBlockById(0), 0, 2);
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return  world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
    }
	
	//IGrowable
	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean bool) {
		return world.getBlockMetadata(x, y, z) != 7;
	}

	@Override
	public boolean func_149852_a(World world, Random rnd, int x, int y, int z) {
		return true;
	}

	@Override
	public void func_149853_b(World world, Random rnd, int x, int y, int z) {
		
	}

	//IPlantable
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

}
