package tv.vanhal.ark.events;

import java.util.ArrayList;

import tv.vanhal.ark.Ark;
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
			event.setCanceled(true);
		} 
	}
}
