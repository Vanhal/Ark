package tv.vanhal.ark.items.tools;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemArkHoe extends ItemHoe {
	protected String itemName;

	public ItemArkHoe(String toolname, ToolMaterial material, Item materialItem) {
		super(material);
		material.customCraftingMaterial = materialItem;
		itemName = toolname;
		this.setUnlocalizedName(toolname);
		this.setTextureName("ark:tools/"+toolname);
	}
	
	public void preInit() {
		GameRegistry.registerItem(this, itemName);
	}
	
	public void init() {
		ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
			"mm ", " s ", " s ", 's', Items.stick, 'm', this.theToolMaterial.func_150995_f()});
		GameRegistry.addRecipe(recipe);
	}

}
