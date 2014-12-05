package tv.vanhal.ark.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCreeperMeat extends ItemBase implements IFuelHandler {
	protected final int burnTime = 400;
	
	public ItemCreeperMeat() {
		super("creeperMeat");
		this.setTextureName("ark:creeperMeat");
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
    	list.add(EnumChatFormatting.GREEN + "Looks Flamable");
    }

	public int getBurnTime(ItemStack fuel) {
		if (fuel.isItemEqual(new ItemStack(this))) {
			return burnTime;
		} else {
			return 0;
		}
	}
	protected void setRecipe() {
		GameRegistry.registerFuelHandler(this);
		ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(Blocks.torch, 2), new Object[]{
			"m  ", "s  ", "   ", 'm', this, 's', Items.stick});
		GameRegistry.addRecipe(recipe);
		
	}
}
