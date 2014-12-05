package tv.vanhal.ark.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemCreeperMeatCooked extends ItemBase {
	
	public ItemCreeperMeatCooked() {
		super("creeperMeatCooked");
		this.setTextureName("ark:creeperMeatCooked");
	}
	
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
        --itemStack.stackSize;
        //player.getFoodStats().func_151686_a(this, itemStack);
        player.getFoodStats().addStats(4, 1.2F);
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        //this.onFoodEaten(itemStack, world, player); <--this is for random actions when eating the food.
        return itemStack;
    }
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (player.canEat(false)) {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }
        return itemStack;
    }
	
	public int getMaxItemUseDuration(ItemStack itemStack) {
        return 32;
    }

    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.eat;
    }

	protected void setRecipe() {
		GameRegistry.addSmelting(ArkItems.creeperMeat, new ItemStack(this), 0);
	}
}
