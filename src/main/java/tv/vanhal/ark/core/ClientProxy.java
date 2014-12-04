package tv.vanhal.ark.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import tv.vanhal.ark.client.renderers.TileEntityTrapRenderer;

public class ClientProxy extends Proxy {
	
	@Override
	public void registerEntities() {
		ClientRegistry.bindTileEntitySpecialRenderer(tv.vanhal.ark.tileentity.TileEntityTrap.class, new TileEntityTrapRenderer());
		super.registerEntities();
	}

	@Override
	public boolean isClient() {
		return true;
	}
	
	@Override
	public boolean isServer() {
		return false;
	}
}
