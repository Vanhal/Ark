package tv.vanhal.ark.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;
import tv.vanhal.ark.blocks.ArkBlocks;
import tv.vanhal.ark.items.seeds.ItemSeedPotato;
import tv.vanhal.ark.items.tools.ItemArkAxe;
import tv.vanhal.ark.items.tools.ItemArkHoe;
import tv.vanhal.ark.items.tools.ItemArkPickaxe;
import tv.vanhal.ark.items.tools.ItemArkShovel;
import tv.vanhal.ark.items.tools.ItemArkSword;
import tv.vanhal.ark.items.tools.ItemKnife;

public class ArkItems {

	public static void preInit() {
		potato.preInit();
		creeperMeat.preInit();
		creeperMeatCooked.preInit();
		knife.preInit();
		salt.preInit();
		saltedFlesh.preInit();
		
		//tools
		reinforcedPickaxe.preInit();
		reinforcedShovel.preInit();
		reinforcedAxe.preInit();
		reinforcedSword.preInit();
		reinforcedHoe.preInit();
		
	}
	
	public static void init() {
		reinforcedPickaxe.init();
		reinforcedShovel.init();
		reinforcedAxe.init();
		reinforcedSword.init();
		reinforcedHoe.init();
	}
	
	public static void postInit() {
		
	}
	
	public static ItemSeedPotato potato = new ItemSeedPotato();
	public static ItemCreeperMeat creeperMeat = new ItemCreeperMeat();
	public static ItemCreeperMeatCooked creeperMeatCooked = new ItemCreeperMeatCooked();
	public static ItemKnife knife = new ItemKnife();
	public static ItemSalt salt = new ItemSalt();
	public static ItemSaltedFlesh saltedFlesh = new ItemSaltedFlesh();
	
	//tools
	public static final ToolMaterial materialReinforcedWood = EnumHelper.addToolMaterial("ReinforcedWood", 0, 131, 4.0F, 1.0F, 15);
	public static ItemArkPickaxe reinforcedPickaxe  = new ItemArkPickaxe("reinforcedPickaxe", materialReinforcedWood, Item.getItemFromBlock(ArkBlocks.reinforcedWood));
	public static ItemArkShovel reinforcedShovel  = new ItemArkShovel("reinforcedShovel", materialReinforcedWood, Item.getItemFromBlock(ArkBlocks.reinforcedWood));
	public static ItemArkAxe reinforcedAxe  = new ItemArkAxe("reinforcedAxe", materialReinforcedWood, Item.getItemFromBlock(ArkBlocks.reinforcedWood));
	public static ItemArkSword reinforcedSword  = new ItemArkSword("reinforcedSword", materialReinforcedWood, Item.getItemFromBlock(ArkBlocks.reinforcedWood));
	public static ItemArkHoe reinforcedHoe  = new ItemArkHoe("reinforcedHoe", materialReinforcedWood, Item.getItemFromBlock(ArkBlocks.reinforcedWood));
}
