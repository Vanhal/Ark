package tv.vanhal.ark.blocks;

import tv.vanhal.ark.tileentity.TileEntityFormer;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BlockFormer extends BlockContainerBase {

	public BlockFormer() {
		super("former", Material.rock);
		this.setBlockTextureName("ark:former");
		this.setHardness(2.0F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFormer();
	}
	
	protected void setRecipe() {
		ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
			"sss", "s s", "sss", 's', Blocks.stone});
		GameRegistry.addRecipe(recipe);
	}
	
}
