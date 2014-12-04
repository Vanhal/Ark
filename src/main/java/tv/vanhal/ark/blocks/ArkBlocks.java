package tv.vanhal.ark.blocks;

import tv.vanhal.ark.blocks.crops.BlockCropPotato;
import tv.vanhal.ark.blocks.tempcrops.BlockTempCropPotato;

public class ArkBlocks {
	public static BlockTempCropPotato potato = new BlockTempCropPotato();
	public static BlockTrap trap = new BlockTrap();

	public static void preInit() {
		potato.preInit();
		trap.preInit();
	}
	
	public static void init() {
		trap.init();
	}
	
	public static void postInit() {
		
	}
	
	
}
