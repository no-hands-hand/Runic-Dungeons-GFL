package mrcomputerghost.runicdungeons.enchantment;

import com.google.common.collect.Lists;
import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

/**
 * @author ji_GGO
 * @date 5/1/2021
 */
@Mod.EventBusSubscriber(modid = RunicDungeons.MODID)
public class RunicEnchantments {

    public static final ArrayList<Enchantment> ENCHANTMENTS = Lists.newArrayList();

    public static final Enchantment POISON_THORNS = new EnchantmentPoisonThorns();

    public static final Enchantment KNOCKBACK_THORNS = new EnchantmentKnockbackThorns();

    public static final Enchantment FIRE_THORNS = new EnchantmentFireThorns();

    public static final Enchantment MAGIC_RESIST = new EnchantmentMagicResist();

    @SubscribeEvent
    public static void onRegisterEnchantment(final RegistryEvent.Register<Enchantment> event) {
        ENCHANTMENTS.forEach(e -> event.getRegistry().register(e));
    }

}