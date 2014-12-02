package tv.vanhal.ark.items;

import tv.vanhal.ark.items.seeds.ItemSeedPotato;

public class ArkItems {

	public static void preInit() {
		potato.preInit();
	}
	
	public static void init() {
		
	}
	
	public static void postInit() {
		
	}
	
	public static ItemSeedPotato potato = new ItemSeedPotato();
}
