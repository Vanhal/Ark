package tv.vanhal.ark.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockReinforcedWood extends BlockBase {

	protected BlockReinforcedWood() {
		super("reinforcedWood", Material.wood);
		this.setHardness(8.0f);
        this.setStepSound(soundTypeWood);
		this.setBlockTextureName("ark:reinforcedWood");
	}

	@Override
    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }
}
