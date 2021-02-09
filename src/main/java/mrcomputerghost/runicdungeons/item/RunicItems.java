package mrcomputerghost.runicdungeons.item;

import com.google.common.collect.Lists;
import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.block.RunicBlocks;
import mrcomputerghost.runicdungeons.item.baubles.ItemArrowBelt;
import mrcomputerghost.runicdungeons.item.baubles.ItemCraftingBelt;
import mrcomputerghost.runicdungeons.item.baubles.ItemFlyingBelt;
import mrcomputerghost.runicdungeons.item.baubles.ItemRunicAmulet;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

@EventBusSubscriber(modid = RunicDungeons.MODID)
public class RunicItems {

    public final static ArrayList<Item> ITEMS = Lists.newArrayList();

    public static ItemArmor.ToolMaterial RUNIC_STEEL_TOOL = EnumHelper.addToolMaterial("runic_steel", 3, 1561, 8.0F, 3.0F, 23);

    public static Item KEY = new ItemKey();

    public static Item MAGIC_CHALK  = new ItemMagicChalk();

    public static Item MAGICAL_STAFF = new ItemMagicalStaff();

    public static Item TRAP_DISABLER = new ItemTrapDisabler();

    public static Item RUNIC_STEEL = new ItemCraftingItem("runic_steel");

    public static Item RUNIC_STEEL_SWORD = new ItemRunicSteelSword();

    public static Item RUNIC_STEEL_PICKAXE = new ItemRunicSteelPickaxe();

    public static Item RUNIC_STEEL_AXE = new ItemRunicSteelAxe();

    public static Item RUNIC_STEEL_SHOVEL = new ItemRunicSteelShovel();

    public static Item RUNIC_STEEL_HOE = new ItemRunicSteelHoe();

    public static Item AIR_CRYSTAL = new ItemCraftingItem("air_crystal");

    public static Item EARTH_CRYSTAL = new ItemCraftingItem("earth_crystal");

    public static Item FIRE_CRYSTAL = new ItemCraftingItem("fire_crystal");

    public static Item WATER_CRYSTAL = new ItemCraftingItem("water_crystal");

    //public static Item RIDER = new ItemRider();

    @ObjectHolder(RunicDungeons.MODID + ":amulet_speed")
    public static Item AMULET_SPEED;

    @ObjectHolder(RunicDungeons.MODID + ":amulet_strong")
    public static Item AMULET_STRONG;

    @ObjectHolder(RunicDungeons.MODID + ":amulet_regen")
    public static Item AMULET_REGEN;

    @ObjectHolder(RunicDungeons.MODID + ":amulet_fire_resist")
    public static Item AMULET_FIRE;

    @ObjectHolder(RunicDungeons.MODID + ":amulet_breath")
    public static Item AMULET_BREATH;

    @ObjectHolder(RunicDungeons.MODID + ":amulet_night_vision")
    public static Item AMULET_NIGHT;

    @ObjectHolder(RunicDungeons.MODID + ":fly_belt")
    public static Item BELT_FLY;

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        RunicBlocks.registerItem(event.getRegistry());
        if (Loader.isModLoaded("baubles")) {
            new ItemRunicAmulet(Potion.getPotionById(1), "amulet_speed");
            new ItemRunicAmulet(Potion.getPotionById(3), "amulet_haste");
            new ItemRunicAmulet(Potion.getPotionById(5), "amulet_strong");
            new ItemRunicAmulet(Potion.getPotionById(8), "amulet_jump");
            new ItemRunicAmulet(Potion.getPotionById(10), "amulet_regen");
            new ItemRunicAmulet(Potion.getPotionById(11), "amulet_resist");
            new ItemRunicAmulet(Potion.getPotionById(12), "amulet_fire_resist");
            new ItemRunicAmulet(Potion.getPotionById(13), "amulet_breath");
            new ItemRunicAmulet(Potion.getPotionById(16), "amulet_night_vision");
            new ItemArrowBelt();
            new ItemFlyingBelt();
            new ItemCraftingBelt("basic_belt");
            new ItemCraftingBelt("advanced_belt");
        }
        ITEMS.forEach(i -> event.getRegistry().register(i));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry(final ModelRegistryEvent event) {
        ITEMS.forEach(RunicItems::registerModel);
    }

    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
    }

}