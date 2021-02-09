package mrcomputerghost.runicdungeons.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRunicAmulet extends Item implements IBauble {

    private Potion potion;

    public ItemRunicAmulet(Potion potion, String name) {
        this.potion = null;
        this.setCreativeTab(RunicDungeons.TAB);
        this.setRegistryName(RunicDungeons.MODID, name);
        this.setUnlocalizedName(name);
        this.setMaxStackSize(1);
        this.potion = potion;
        RunicItems.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.getActivePotionEffect(this.potion) != null) {
            player.removePotionEffect(this.potion);
        }
        player.addPotionEffect(new PotionEffect(this.potion, 416, itemstack.getCount() - 1, true, true));
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        PotionEffect effect = player.getActivePotionEffect(this.potion);
        if (effect != null) {
            player.removePotionEffect(this.potion);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        switch (Potion.getIdFromPotion(this.potion)) {
            case 1:
                tooltip.add(I18n.translateToLocal("amulet.speed.describe"));
                break;
            case 3:
                tooltip.add(I18n.translateToLocal("amulet.haste.describe"));
                break;
            case 5:
                tooltip.add(I18n.translateToLocal("amulet.strong.describe"));
                break;
            case 8:
                tooltip.add(I18n.translateToLocal("amulet.jump.describe"));
                break;
            case 10:
                tooltip.add(I18n.translateToLocal("amulet.regen.describe"));
                break;
            case 11:
                tooltip.add(I18n.translateToLocal("amulet.resist.describe"));
                break;
            case 12:
                tooltip.add(I18n.translateToLocal("amulet.fire_resist.describe"));
                break;
            case 13:
                tooltip.add(I18n.translateToLocal("amulet.breath.describe"));
                break;
            case 16:
                tooltip.add(I18n.translateToLocal("amulet.night_vision.describe"));
                break;
        }
    }

}