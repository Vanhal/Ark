package tv.vanhal.ark.events;

import tv.vanhal.ark.Ark;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class EventWorld {

	@SubscribeEvent
	public void stripWildlife(DecorateBiomeEvent.Decorate event) {
		if ( 	/*(event.type == DecorateBiomeEvent.Decorate.EventType.TREE) || */
				(event.type == DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM) || 
				(event.type == DecorateBiomeEvent.Decorate.EventType.PUMPKIN)|| 
				(event.type == DecorateBiomeEvent.Decorate.EventType.REED) || 
				(event.type == DecorateBiomeEvent.Decorate.EventType.CACTUS) || 
				(event.type == DecorateBiomeEvent.Decorate.EventType.LILYPAD) || 
				(event.type == DecorateBiomeEvent.Decorate.EventType.CUSTOM) ) {
			event.setResult(Event.Result.DENY);
		}
	}
}
