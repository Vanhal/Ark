package tv.vanhal.ark.core;

import tv.vanhal.ark.tileentity.TileEntityFormer;
import tv.vanhal.ark.tileentity.TileEntityTrap;
import cpw.mods.fml.common.registry.GameRegistry;

public class Proxy {

	public void registerEntities() {
		GameRegistry.registerTileEntity(TileEntityTrap.class, "TileEntityTrap");
		GameRegistry.registerTileEntity(TileEntityFormer.class, "TileEntityFormer");
	}

	public boolean isClient() {
		return false;
	}
	
	public boolean isServer() {
		return true;
	}
}
