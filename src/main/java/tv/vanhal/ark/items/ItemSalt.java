package tv.vanhal.ark.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemSalt extends ItemBase {

	public ItemSalt() {
		super("salt");
		this.setTextureName("ark:salt");
	}
	
	protected void setRecipe() {
		GameRegistry.addSmelting(new ItemStack(Items.potionitem, 1, 0), new ItemStack(this), 0);
	}
	
}
