package tv.vanhal.ark;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tv.vanhal.ark.core.Proxy;
import tv.vanhal.ark.events.EventDrops;
import tv.vanhal.ark.events.EventSpawning;
import tv.vanhal.ark.events.EventWorld;
import tv.vanhal.ark.ref.Ref;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Ref.MODID, name = Ref.MODNAME, version = Ref.Version)
public class Ark {
	@Instance(Ref.MODID)
	public static Ark instance;

	@SidedProxy(clientSide = "tv.vanhal."+Ref.MODID+".core.ClientProxy", serverSide = "tv.vanhal."+Ref.MODID+".core.Proxy")
	public static Proxy proxy;

	//logger
	public static final Logger logger = LogManager.getLogger(Ref.MODID);
	
	//events
	public static final EventSpawning spawnEvents = new EventSpawning();
	public static final EventDrops dropEvents = new EventDrops();
	public static final EventWorld worldEvents = new EventWorld();


	//Creative Tab
	public static CreativeTabs ArkTab = new CreativeTabs("ArkTab") {
		@Override
		public Item getTabIconItem() {
			return Items.diamond;
		}
	};


	public Ark() {
		logger.info("Welcome to Planet Ark");
	}


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(spawnEvents);
		MinecraftForge.EVENT_BUS.register(dropEvents);
		MinecraftForge.TERRAIN_GEN_BUS.register(worldEvents);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.registerEntities();
	}

}
