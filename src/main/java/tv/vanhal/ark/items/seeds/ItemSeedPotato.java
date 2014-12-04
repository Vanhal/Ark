package tv.vanhal.ark.items.seeds;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tv.vanhal.ark.blocks.ArkBlocks;
import tv.vanhal.ark.blocks.crops.BlockCropBase;
import tv.vanhal.ark.items.ArkItems;

public class ItemSeedPotato extends ItemSeedBase {

	public ItemSeedPotato() {
		super("Potato", ArkBlocks.potato);
		this.setTextureName("minecraft:potato");
		this.genermiateTime = 48000;
	}
	
	protected void setRecipe() {
		ItemStack itemStack = new ItemStack(Items.baked_potato, 1);
		itemStack.setStackDisplayName("Ego's Baked Spud");
		GameRegistry.addSmelting(new ItemStack(ArkItems.potato, 1, 1), itemStack, 1);
	}

}
