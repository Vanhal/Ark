package tv.vanhal.ark.items.tools;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemArkSword extends ItemSword {
	protected String itemName;
	protected ToolMaterial swordMat;

	public ItemArkSword(String toolname, ToolMaterial material, Item materialItem) {
		super(material);
		material.customCraftingMaterial = materialItem;
		swordMat = material;
		itemName = toolname;
		this.setUnlocalizedName(toolname);
		this.setTextureName("ark:tools/"+toolname);
	}
	
	public void preInit() {
		GameRegistry.registerItem(this, itemName);
	}
	
	public void init() {
		ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
			" m ", " m ", " s ", 's', Items.stick, 'm', this.swordMat.func_150995_f()});
		GameRegistry.addRecipe(recipe);
	}

}
