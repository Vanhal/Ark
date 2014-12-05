package tv.vanhal.ark.items;

import tv.vanhal.ark.items.seeds.ItemSeedPotato;
import tv.vanhal.ark.items.tools.ItemKnife;

public class ArkItems {

	public static void preInit() {
		potato.preInit();
		creeperMeat.preInit();
		creeperMeatCooked.preInit();
		knife.preInit();
		salt.preInit();
		saltedFlesh.preInit();
	}
	
	public static void init() {
		
	}
	
	public static void postInit() {
		
	}
	
	public static ItemSeedPotato potato = new ItemSeedPotato();
	public static ItemCreeperMeat creeperMeat = new ItemCreeperMeat();
	public static ItemCreeperMeatCooked creeperMeatCooked = new ItemCreeperMeatCooked();
	public static ItemKnife knife = new ItemKnife();
	public static ItemSalt salt = new ItemSalt();
	public static ItemSaltedFlesh saltedFlesh = new ItemSaltedFlesh();
}
