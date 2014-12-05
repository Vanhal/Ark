package tv.vanhal.ark.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemSaltedFlesh extends ItemBase {

	public ItemSaltedFlesh() {
		super("saltedFlesh");
		this.setTextureName("ark:saltedFlesh");
	}
	
	protected void setRecipe() {
		ShapelessOreRecipe recipe = new ShapelessOreRecipe(new ItemStack(this), Items.rotten_flesh, ArkItems.salt);
		GameRegistry.addRecipe(recipe);
		GameRegistry.addSmelting(this, new ItemStack(Items.leather), 0);
	}
}
