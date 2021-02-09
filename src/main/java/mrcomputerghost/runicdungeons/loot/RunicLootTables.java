package mrcomputerghost.runicdungeons.loot;

import com.google.common.collect.Lists;
import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * @author ji_GGO
 * @date 11/1/2021
 */
@Mod.EventBusSubscriber(modid = RunicDungeons.MODID)
public class RunicLootTables {

    private static final Logger LOGGER = RunicDungeons.LOGGER;

    public static final ResourceLocation GUARDIAN = register("entities/dungeon_guardian");

    public static final ResourceLocation IRON = register("chests/iron_dungeon");

    public static final ResourceLocation OBSIDIAN = register("chests/obsidian_dungeon");

    public static final ResourceLocation COBBLE = register("chests/cobble_dungeon");

    public static final ResourceLocation BOOK = register("chests/dungeon_library");

    public static LootEntry magic_chalk = new LootEntryItem(
            RunicItems.MAGIC_CHALK, 1, 50, new LootFunction[0], new LootCondition[0],
            "runicdungeons:loot_magic_chalk");

    private static ResourceLocation register(String path) {
        return LootTableList.register(new ResourceLocation(RunicDungeons.MODID, path));
    }

    @SubscribeEvent
    public void onLootTablesLoad(final LootTableLoadEvent event) {
        List<ResourceLocation> resources = Lists.newArrayList(LootTableList.CHESTS_JUNGLE_TEMPLE,
                LootTableList.CHESTS_DESERT_PYRAMID);

        resources.forEach(resource -> {
            if (event.getName().equals(resource)) {
                event.getTable().getPool("main").addEntry(magic_chalk);
            }
        });

        List<ResourceLocation> dungeons = Lists.newArrayList(IRON, OBSIDIAN, COBBLE, BOOK);

        dungeons.forEach(dungeon -> {
            if (event.getName().equals(dungeon)) {
                List<LootItem> loot = genLootTables();
                LootCondition[] condition = new LootCondition[0];
                LootPool pool = new LootPool(new LootEntry[]{}, condition, new RandomValueRange(3, 6),
                        new RandomValueRange(0.0F, 0.0F), dungeon.getResourceDomain());
                loot.forEach(l -> {
                    List<LootFunction> functions = Lists.newArrayList();
                    functions.add(new SetCount(condition, new RandomValueRange(l.getMin(), l.getMax())));
                    if (l.getMeta() != 0) {
                        functions.add(new SetMetadata(condition, new RandomValueRange(l.getMeta())));
                    }
                    LootFunction[] _function = functions.toArray(new LootFunction[functions.size()]);
                    LootCondition[] _condition = new LootCondition[0];
                    if (new ItemStack(l.getItem()).isItemDamaged()) {
                        _condition = new LootCondition[]{new RandomChance(1f)};
                    }
                    LootEntryItem item = new LootEntryItem(l.getItem(), l.getWeight(), 50, _function, _condition,
                            l.item.getRegistryName().toString() + l.getMeta());
                    pool.addEntry(item);
                });
                event.getTable().addPool(pool);
            }
        });
    }

    public static String[] genDefaults() {
        List<String> list = Lists.newArrayList();
        String[] vanilla = {
                "minecraft:iron_ingot:0:1:5:10", "minecraft:gold_ingot:0:1:3:5", "minecraft:redstone:0:4:9:5",
                "minecraft:dye:4:4:9:5", "minecraft:diamond:0:1:2:3", "minecraft:coal:0:3:8:10",
                "minecraft:bread:0:1:3:15", "minecraft:iron_pickaxe:0:1:1:1", "minecraft:rail:0:4:8:1",
                "minecraft:melon_seeds:0:2:4:10", "minecraft:pumpkin_seeds:0:2:4:10", "minecraft:iron_horse_armor:0:1:1:1",
                "minecraft:ender_pearl:0:1:1:10", "minecraft:apple:0:1:3:15", "minecraft:iron_sword:0:1:1:5",
                "minecraft:iron_chestplate:0:1:1:5", "minecraft:iron_helmet:0:1:1:5", "minecraft:iron_leggings:0:1:1:5",
                "minecraft:iron_boots:0:1:1:5", "minecraft:golden_apple:0:1:1:1", "minecraft:book:0:1:3:20",
                "minecraft:paper:0:2:7:20", "minecraft:obsidian:0:3:7:5", "minecraft:gunpowder:0:1:4:10",
                "minecraft:string:0:1:4:10", "minecraft:name_tag:0:1:1:10", "minecraft:golden_apple:1:1:1:1"};
        Collections.addAll(list, vanilla);

        String[] dungeon = {"runicdungeons:runic_steel:0:1:2:2"};
        Collections.addAll(list, dungeon);

        if (Loader.isModLoaded("thaumcraft")) {
            String[] thaumcraft = {"thaumcraft:loot_bag:0:1:3:8", "thaumcraft:ingot:0:1:3:5", "thaumcraft:amber:0:1:3:5",
                    "thaumcraft:loot_bag:1:1:3:4"};
            Collections.addAll(list, thaumcraft);
        }

        if (Loader.isModLoaded("botania")) {
            String[] botania = {"botania:blacklotus:0:1:1:7", "botania:overgrowthseed:0:1:1:3", "botania:manabottle:0:1:1:5",
                    "botania:manaresource:1:1:3:8", "Botania:manaresource:0:1:5:9"};
            Collections.addAll(list, botania);
        }

        if (Loader.isModLoaded("enderio")) {
            String[] enderio = {"enderio:item_alloy_ingot:6:1:3:15", "enderio:item_alloy_ingot:0:2:6:7", "enderio:item_alloy_ingot:3:3:6:10"};
            Collections.addAll(list, enderio);
        }

        if (Loader.isModLoaded("rftools")) {
            //String[] rftools = { "rftools:unknownDimlet:0:0:7:15" };
            //Collections.addAll(list, rftools);
        }

        if (Loader.isModLoaded("artifacts")) {
            String[] artifacts = {
                    "Artifacts:artifact:0:1:1:2", "Artifacts:artifact_chainmail_helmet:0:1:1:2", "Artifacts:artifact_chainmail_chestplate:0:1:1:2",
                    "Artifacts:artifact_chainmail_leggings:0:1:1:2", "Artifacts:artifact_chainmail_boots:0:1:1:2", "Artifacts:artifact_golden_helmet:0:1:1:2",
                    "Artifacts:artifact_golden_chestplate:0:1:1:2", "Artifacts:artifact_golden_leggings:0:1:1:2", "Artifacts:artifact_golden_boots:0:1:1:2",
                    "Artifacts:artifact_iron_helmet:0:1:1:2", "Artifacts:artifact_iron_chestplate:0:1:1:2", "Artifacts:artifact_iron_leggings:0:1:1:2",
                    "Artifacts:artifact_iron_boots:0:1:1:2", "Artifacts:artifact_leather_helmet:0:1:1:2", "Artifacts:artifact_leather_chestplate:0:1:1:2",
                    "Artifacts:artifact_leather_leggings:0:1:1:2", "Artifacts:artifact_leather_boots:0:1:1:2"};
            Collections.addAll(list, artifacts);
        }

        if (Loader.isModLoaded("ic2")) {
            String[] ic2 = {"ic2:ingot:2:2:5:0", "ic2:ingot:6:2:5:50", "ic2:misc_resource:1:1:2:15",
                    "ic2:bronze_pickaxe:0:1:1:3", "ic2:bronze_sword:0:1:1:3", "ic2:bronze_helmet:0:1:1:3",
                    "ic2:bronze_chestplate:0:1:1:3", "ic2:bronze_leggings:0:1:1:3", "ic2:bronze_boots:0:1:1:3"};
            String[] classic = {"ic2:itemmisc:50:2:5:0", "ic2:itemmisc:51:2:5:50", "ic2:itemmisc:181:1:2:15",
                    "ic2:itemtoolbronzepickaxe:0:1:1:3", "ic2:itemtoolbronzesword:0:1:1:3", "ic2:itemarmorbronzehelmet:0:1:1:3",
                    "ic2:itemarmorbronzechestplate:0:1:1:3", "ic2:itemarmorbronzelegs:0:1:1:3", "ic2:itemarmorbronzeboots:0:1:1:3"};
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("ic2:ingot"));
            if (item != null) {
                Collections.addAll(list, ic2);
            } else {
                Collections.addAll(list, classic);
            }
        }

        if (Loader.isModLoaded("betterstorage")) {
            /*String[] betterstorage = { "betterstorage:drinkingHelmet:0:1:1:1" };
            Collections.addAll(list, betterstorage);*/
        }

        if (Loader.isModLoaded("HardcoreEnderExpansion")) {
            /*String[] ender = {"HardcoreEnderExpansion:knowledge_note:0:1:1:6",
                    "HardcoreEnderExpansion:adventurers_diary:0:1:1:3",
                    "HardcoreEnderExpansion:temple_caller:0:0:1:1"};
            Collections.addAll(list, ender);*/
        }

        if (Loader.isModLoaded("portalgun")) {
            //portalgun = {"PortalGun:PortalGunBlue:0:1:1:2", "PortalGun:PortalGunBlue:1:1:1:2", "PortalGun:PortalGunBlue:2:1:1:2", "PortalGun:PortalGunBlue:3:1:1:2", "PortalGun:PortalGunBlue:4:1:1:2", "PortalGun:PortalGunSpawner:0:1:4:2", "PortalGun:EnderPearlDust:0:2:9:10", "PortalGun:PortalMulti:14:1:1:2"}
            String[] portalgun = { "portalgun:item_portalgun:0:1:1:2", "portalgun:item_dust_ender_pearl:0:2:9:10"};
            Collections.addAll(list, portalgun);
        }

        if (Loader.isModLoaded("railcraft")) {
            String[] railcraft = {"railcraft:tool_sword_steel:0:1:1:5", "railcraft:armor_helmet_steel:0:1:1:5", "railcraft:armor_chestplate_steel:0:1:1:5", "railcraft:armor_leggings_steel:0:1:1:5", "railcraft:armor_boots_steel:0:1:1:5"};
            Collections.addAll(list, railcraft);
        }

        return list.toArray(new String[list.size()]);
    }

    public static List<LootItem> genLootTables() {
        List<LootItem> list = Lists.newArrayList();
        String[] array = genDefaults();
        for (int i = 0; i < array.length; i++) {
            String[] loot = array[i].split(":");
            ResourceLocation resource = new ResourceLocation(loot[0], loot[1]);
            Item item = ForgeRegistries.ITEMS.getValue(resource);
            if (item == null) {
                LOGGER.error("Unknown Item:" + resource);
                continue;
            } else {
                int meta = Integer.valueOf(loot[2].trim());
                int weight = Integer.valueOf(loot[5].trim());
                int min = Integer.valueOf(loot[3].trim());
                int max = Integer.valueOf(loot[4].trim());
                list.add(new LootItem(item, meta, weight, min, max));
            }
        }
        return list;
    }

    public static class LootItem{

        public LootItem() {}

        public LootItem(Item item, int meta, int min, int max, int weight) {
            this.item = item;
            this.meta = meta;
            this.min = min;
            this.max = max;
            this.weight = weight;
        }

        private Item item;

        private int meta;

        private int weight;

        private int min;

        private int max;

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public Item getItem() {
            return item;
        }

        public int getMeta() {
            return meta;
        }

        public int getWeight() {
            return weight;
        }

    }

}