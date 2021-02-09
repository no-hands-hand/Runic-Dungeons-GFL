package mrcomputerghost.runicdungeons.enchantment;

import java.util.Random;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;

public class EnchantmentKnockbackThorns extends Enchantment {

    public EnchantmentKnockbackThorns() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[] {
                EntityEquipmentSlot.FEET
        });
        this.setName("knockbackThorns");
        this.setRegistryName(RunicDungeons.MODID, "knockback_thorns");
        RunicEnchantments.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 + 20 * (enchantmentLevel - 2);
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof ItemArmor || super.canApply(stack);
    }

    @Override
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
        Random random = user.getRNG();
        ItemStack itemstack = EnchantmentHelper.getEnchantedItem(RunicEnchantments.KNOCKBACK_THORNS, user);
        if (level > 0 && random.nextFloat() < 0.15F * level) {
            if (attacker instanceof EntityLivingBase) {
                ((EntityLivingBase) attacker).knockBack(user, 0.5F, (5.0F * level), -5.0D * level);
                if (itemstack != null) {
                    itemstack.damageItem(3, user);
                }
            }
        } else if (itemstack != null) {
            itemstack.damageItem(1, user);
        }
    }

}