package tv.vanhal.ark.items.seeds;

import net.minecraft.init.Blocks;
import tv.vanhal.ark.blocks.ArkBlocks;
import tv.vanhal.ark.blocks.crops.BlockCropBase;

public class ItemSeedPotato extends ItemSeedBase {

	public ItemSeedPotato() {
		super("Potato", ArkBlocks.potato);
		this.setTextureName("minecraft:potato");
		this.genermiateTime = 48000;
	}

}
