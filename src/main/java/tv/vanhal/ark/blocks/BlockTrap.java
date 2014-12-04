package tv.vanhal.ark.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import tv.vanhal.ark.tileentity.TileEntityTrap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BlockTrap extends BlockContainerBase {

	protected BlockTrap() {
		super("trap", Material.rock);
		this.setBlockTextureName("ark:trap");
		this.setHardness(1.0f);
	}
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		TileEntityTrap te = (TileEntityTrap)world.getTileEntity(x, y, z);
		if (te != null) te.onEntityCollided(entity);
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
	public boolean isBlockNormalCube() {
		return false;
	}
	
	@Override
	public int getRenderType() {
        return -1;
    }

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0, y + 0.1, z + 1.0);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBounds(0.1f, 0, 0.1f, 0.9f, 0.4f, 0.9f);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTrap();
	}
	
	protected void setRecipe() {
		ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
			"f f", "sps", "f f", 's', Items.stick, 'p', Blocks.stone_pressure_plate, 'f', Items.flint});
		GameRegistry.addRecipe(recipe);
	}
}
