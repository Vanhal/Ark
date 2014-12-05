package tv.vanhal.ark.items.tools;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tv.vanhal.ark.items.ItemBase;

public class ItemKnife extends ItemBase {
	private float itemDamage = 1.0f;
	
	public ItemKnife() {
		super("knife");
		this.setTextureName("ark:knife");
		this.setMaxDamage(59);
		this.maxStackSize = 1;
	}

	protected void setRecipe() {
		ShapedOreRecipe recipe = new ShapedOreRecipe(new ItemStack(this), new Object[]{
			" f ", "s  ", "   ", 'f', Items.flint, 's', Items.stick});
		GameRegistry.addRecipe(recipe);
	}
	
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase player) {
        itemStack.damageItem(1, player);
        return true;
    }
	
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
        if ((double)block.getBlockHardness(world, x, y, z) != 0.0D) {
            itemStack.damageItem(2, player);
        }
        return true;
    }
	
	public int getMaxItemUseDuration(ItemStack itemStack) {
        return 72000;
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }
    
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
    
    
    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.itemDamage, 0));
        return multimap;
    }
}
