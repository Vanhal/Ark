package tv.vanhal.ark.items;

import cpw.mods.fml.common.registry.GameRegistry;
import tv.vanhal.ark.Ark;
import net.minecraft.item.Item;

public class ItemBase extends Item {
	public String itemName;
	
	public ItemBase() {
		
	}
	
	public ItemBase(String name) {
		setName(name);
		setCreativeTab(Ark.ArkTab);
	}
	
	
	public void setName(String newName) {
		itemName = newName;
		setUnlocalizedName(itemName);
	}
	
	public void preInit() {
		GameRegistry.registerItem(this, itemName);
		setRecipe();
	}

	protected void setRecipe() {
		
	}
}
