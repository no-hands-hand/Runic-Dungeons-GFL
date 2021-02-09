package mrcomputerghost.runicdungeons.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;

import java.util.Random;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemArrowBelt extends Item implements IBauble {

    public ItemArrowBelt() {
        this.setUnlocalizedName("belt_arrow");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setRegistryName(RunicDungeons.MODID, "belt_arrow");
        this.setMaxStackSize(1);
        RunicItems.ITEMS.add(this);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BELT;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.getArrowCountInEntity() >= 1) {
            Random rand = new Random();
            EntityItem i = new EntityItem(player.world, player.posX, player.posY, player.posZ);
            i.setItem(new ItemStack(Items.ARROW));
            int foo = rand.nextInt(5);
        }
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        EntityPlayer e = (EntityPlayer) player;
        e.getAttributeMap().getAttributeInstanceByName("generic.knockbackResistance").setBaseValue(1.0D);
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        EntityPlayer e = (EntityPlayer) player;
        e.getAttributeMap().getAttributeInstanceByName("generic.knockbackResistance").setBaseValue(0.0D);
    }

}