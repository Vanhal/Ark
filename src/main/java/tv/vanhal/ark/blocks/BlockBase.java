package tv.vanhal.ark.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockBase extends Block {
	public String blockName;
	
	protected BlockBase(String name, Material material) {
		super(material);
		setName(name);
	}
	
	protected void setName(String name) {
		this.setBlockName(name);
		this.blockName = name;
	}
	
	public void preInit() {
		GameRegistry.registerBlock(this, blockName);
	}
	
	public void init() {
		setRecipe();
	}
	
	public void postInit() {
		
	}
	
	protected void setRecipe() {
		
	}

}
