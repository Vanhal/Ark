package tv.vanhal.ark.blocks;

import tv.vanhal.ark.tileentity.TileEntityBase;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockContainerBase extends BlockContainer {
	public String blockName;
	
	protected BlockContainerBase(String name, Material material) {
		super(material);
		setName(name);
	}
	
	protected void setName(String name) {
		this.setBlockName(name);
		this.blockName = name;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityBase) {
			return ((TileEntityBase)tile).onBlockActivated(player, side, hitX, hitY, hitZ);
		}
		return false;
	}
	
	public void preInit() {
		GameRegistry.registerBlock(this, blockName);
	}
	
	public void init() {
		setRecipe();
	}
	
	public void postInit() {
		
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return null;
	}
	
	protected void setRecipe() {
		
	}

}
