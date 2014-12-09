package tv.vanhal.ark.blocks;

import tv.vanhal.ark.blocks.crops.BlockCropPotato;
import tv.vanhal.ark.blocks.tempcrops.BlockTempCropPotato;

public class ArkBlocks {
	public static BlockTempCropPotato potato = new BlockTempCropPotato();
	public static BlockTrap trap = new BlockTrap();
	public static BlockFormer former = new BlockFormer();
	
	//materials
	public static BlockReinforcedWood reinforcedWood = new BlockReinforcedWood();

	public static void preInit() {
		potato.preInit();
		trap.preInit();
		former.preInit();
		reinforcedWood.preInit();
	}
	
	public static void init() {
		trap.init();
		former.init();
	}
	
	public static void postInit() {
		
	}
	
	
}
