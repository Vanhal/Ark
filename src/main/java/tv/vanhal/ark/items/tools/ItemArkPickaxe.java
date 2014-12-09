package tv.vanhal.ark.items.tools;

import cpw.mods.fml.common.registry.GameRegistry;
import tv.vanhal.ark.blocks.ArkBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;


public class ItemArkPickaxe extends ItemPickaxe {
	protected String itemName;

	public ItemArkPickaxe(String toolname, ToolMaterial material, Item materialItem) {
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
			"mmm", " s ", " s ", 's', Items.stick, 'm', this.toolMaterial.func_150995_f()});
		GameRegistry.addRecipe(recipe);
	}
}
