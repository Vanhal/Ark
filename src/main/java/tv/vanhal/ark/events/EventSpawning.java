package tv.vanhal.ark.events;

import tv.vanhal.ark.Ark;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.EntityRegistry.EntityRegistration;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;

public class EventSpawning {

	@SubscribeEvent
	public void onEnityAdded(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityLiving) {
			if (!(
					(event.entity instanceof EntityZombie) ||
					(event.entity instanceof EntityCreeper)
				)) {
				event.setCanceled(true);
			} else if (event.entity instanceof EntityZombie) {
				EntityZombie zombie = (EntityZombie)event.entity;
				if ( (zombie.isChild()) || (zombie.isVillager()) || (zombie.isRiding()) ) {
					event.setCanceled(true);
				}
				if (
					(zombie.getEquipmentInSlot(1)!=null) || (zombie.getEquipmentInSlot(2)!=null) || 
					(zombie.getEquipmentInSlot(3)!=null) || (zombie.getEquipmentInSlot(4)!=null)
				) {
					event.setCanceled(true);
				}
			}
		} else if (event.entity instanceof EntityItem) {
			EntityItem itemDrop = (EntityItem)event.entity;
			if (itemDrop.getEntityItem().getItem() == Item.getItemFromBlock(Blocks.sapling)) {
				event.setCanceled(true);
			}
		}
	}
}
