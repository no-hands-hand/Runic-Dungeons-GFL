package mrcomputerghost.runicdungeons.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFlyingBelt extends Item implements IBauble {

    public ItemFlyingBelt() {
        this.setUnlocalizedName("fly_belt");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setRegistryName(RunicDungeons.MODID, "fly_belt");
        this.setMaxStackSize(1);
        RunicItems.ITEMS.add(this);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BELT;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        EntityPlayer e = (EntityPlayer) player;
        e.capabilities.allowFlying = true;
        e.setAir(0);
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        EntityPlayer e = (EntityPlayer) player;
        e.capabilities.allowFlying = true;
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        EntityPlayer e = (EntityPlayer) player;
        e.capabilities.allowFlying = false;
        e.capabilities.isFlying = false;
    }

}
