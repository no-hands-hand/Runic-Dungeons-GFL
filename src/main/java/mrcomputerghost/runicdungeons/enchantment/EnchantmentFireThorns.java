package mrcomputerghost.runicdungeons.enchantment;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class EnchantmentFireThorns extends Enchantment {

    public EnchantmentFireThorns() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_HEAD, new EntityEquipmentSlot[] {
                EntityEquipmentSlot.HEAD
        });
        this.setName("fireThorns");
        this.setRegistryName(RunicDungeons.MODID, "fire_thorns");
        RunicEnchantments.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 + 20 * (enchantmentLevel - 1);
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 30;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof ItemArmor || super.canApply(stack);
    }

    @Override
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
        Random random = user.getRNG();
        ItemStack itemstack = EnchantmentHelper.getEnchantedItem(RunicEnchantments.FIRE_THORNS, user);
        if (level > 0 && random.nextFloat() < 0.15F * level) {
            if (attacker instanceof EntityLivingBase) {
                attacker.setFire(15 * level);
                if (itemstack != null) {
                    itemstack.damageItem(3, user);
                }
            }
        } else if (itemstack != null) {
            itemstack.damageItem(1, user);
        }
    }

}