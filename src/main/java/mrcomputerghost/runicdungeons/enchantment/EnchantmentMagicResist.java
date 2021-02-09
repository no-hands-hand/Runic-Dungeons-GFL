package mrcomputerghost.runicdungeons.enchantment;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class EnchantmentMagicResist extends Enchantment {

    public EnchantmentMagicResist() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[] {
                EntityEquipmentSlot.CHEST
        });
        this.setName("magicProtection");
        this.setRegistryName(RunicDungeons.MODID, "magic_protection");
        RunicEnchantments.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 + 20 * (enchantmentLevel - 1);
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 40;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return (stack.getItem() instanceof ItemArmor || super.canApply(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        if (enchantment instanceof EnchantmentProtection) {
            return false;
        }
        return super.canApplyTogether(enchantment);
    }

    @Override
    public int calcModifierDamage(int level, DamageSource source) {
        if (source.canHarmInCreative()) {
            return 0;
        }
        float f = (6 + level * level) / 3.0F;
        return source.isMagicDamage() ? floorFloat(f * 0.5F) : 1;
    }

    public static int floorFloat(float number) {
        int i = (int)number;
        return number < (float)i ? i - 1 : i;
    }

}