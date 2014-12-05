package tv.vanhal.ark.events;

import java.util.ArrayList;

import tv.vanhal.ark.Ark;
import tv.vanhal.ark.items.ArkItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventDrops {

	@SubscribeEvent
	public void changeDrops(LivingDropsEvent event) {
		if ( (event.entityLiving instanceof EntityZombie) || (event.entityLiving instanceof EntityCreeper) ) {
			event.drops.clear();
			if (event.source!=null) {
				if (event.source.damageType=="player") {
					if (event.source.getSourceOfDamage()!=null) {
						if (event.source.getSourceOfDamage() instanceof EntityPlayer) {
							EntityPlayer player = (EntityPlayer)event.source.getSourceOfDamage();
							if (player.getHeldItem().getItem().equals(ArkItems.knife)) {
								//we can actually give drops now
								int chance = 16;
					        	for (int i =1; i<=3; i++) {
					        		if (event.entity.worldObj.rand.nextInt(15) < ((int)chance/i)) {
					        			EntityItem newDrop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ);
					        			if (event.entityLiving instanceof EntityZombie) {
					        				newDrop.setEntityItemStack(new ItemStack(Items.rotten_flesh));
					        			} else if (event.entityLiving instanceof EntityCreeper) {
					        				newDrop.setEntityItemStack(new ItemStack(ArkItems.creeperMeat));
					        			}
					        			event.drops.add(newDrop);
					        		}
					        	}
							}
						}
						
					}
				}
			}
			//event.setCanceled(true);
			
		} 
	}
}
