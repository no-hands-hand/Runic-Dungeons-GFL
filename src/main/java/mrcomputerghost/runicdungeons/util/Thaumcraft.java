package mrcomputerghost.runicdungeons.util;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.block.RunicBlocks;
import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

public class Thaumcraft {

    // Use the thaumcraft API to register our things with aspects and biomes with values
    @Optional.Method(modid = "thaumcraft")
    @SubscribeEvent
    public static void registerAspects(AspectRegistryEvent event) {
        RunicDungeons.LOGGER.debug("Attempting to register Thaumcraft Aspects for Runic Dungeons items!");

        try {
            TFAspectRegisterHelper helper = new TFAspectRegisterHelper(event);

            helper.registerTCObjectTag(RunicBlocks.BRICKS, new int[] {0, 1, 2, 3, 4, 5}, new AspectList()
                    .add(Aspect.ELDRITCH, 2)
                    .add(Aspect.MAGIC, 4)
                    .add(Aspect.EARTH, 4));

            helper.registerTCObjectTag(RunicBlocks.SURVIVAL_BRICKS, new int[] {0, 1, 2, 3, 4, 5}, new AspectList()
                    .add(Aspect.MAGIC, 2)
                    .add(Aspect.EARTH, 2));

            helper.registerTCObjectTag(RunicBlocks.OBSIDIAN_BRICKS, new int[] {0, 1, 2, 3, 4, 5}, new AspectList()
                    .add(Aspect.MAGIC, 2)
                    .add(Aspect.EARTH, 2)
                    .add(Aspect.DARKNESS, 3)
                    .add(Aspect.PROTECT, 3));

            helper.registerTCObjectTag(RunicBlocks.BOOKSHELF, new AspectList()
                    .add(Aspect.MIND, 3));

            helper.registerTCObjectTag(RunicBlocks.CHAOTIC_SAND, new AspectList()
                    .add(Aspect.VOID, 1));

            helper.registerTCObjectTag(RunicBlocks.KEY_STONE, new AspectList()
                    .add(Aspect.ELDRITCH, 2)
                    .add(Aspect.EARTH, 4)
                    .add(Aspect.MAGIC, 4));

            helper.registerTCObjectTag(RunicBlocks.SPIKE_TRAP, new AspectList()
                    .add(Aspect.EARTH, 2)
                    .add(Aspect.MAGIC, 2)
                    .add(Aspect.TRAP, 8));

            helper.registerTCObjectTag(RunicBlocks.RUNIC_LAMP, new AspectList()
                    .add(Aspect.LIGHT, 5)
                    .add(Aspect.EARTH, 2)
                    .add(Aspect.MAGIC, 4)
                    .add(Aspect.ELDRITCH, 2));

            helper.registerTCObjectTag(RunicBlocks.RUNIC_PILLAR, new AspectList()
                    .add(Aspect.EARTH, 4)
                    .add(Aspect.MAGIC, 2)
                    .add(Aspect.ELDRITCH, 1)
                    .add(Aspect.ORDER, 5));

            helper.registerTCObjectTag(RunicBlocks.RUNIC_SPAWNER, new AspectList()
                    .add(Aspect.EARTH, 4)
                    .add(Aspect.MAGIC, 2)
                    .add(Aspect.ELDRITCH, 1)
                    .add(Aspect.LIFE, 2));

            helper.registerTCObjectTag(RunicBlocks.SURVIVAL_RUNIC_LAMP, new AspectList()
                    .add(Aspect.LIGHT, 5)
                    .add(Aspect.EARTH, 4)
                    .add(Aspect.MAGIC, 2));

            helper.registerTCObjectTag(RunicBlocks.SURVIVAL_RUNIC_PILLAR, new AspectList()
                    .add(Aspect.EARTH, 4)
                    .add(Aspect.MAGIC, 2)
                    .add(Aspect.ORDER, 2));

            helper.registerTCObjectTag(RunicBlocks.RUNIC_GLASS, new AspectList()
                    .add(Aspect.CRYSTAL, 1)
                    .add(Aspect.MAGIC, 2)
                    .add(Aspect.ORDER, 2));

            helper.registerTCObjectTag(RunicBlocks.OBSIDIAN_RUNIC_GLASS, new AspectList()
                    .add(Aspect.CRYSTAL, 1)
                    .add(Aspect.MAGIC, 2)
                    .add(Aspect.ORDER, 2)
                    .add(Aspect.PROTECT, 4));

            helper.registerTCObjectTag(RunicBlocks.OBSIDIAN, new AspectList()
                    .add(Aspect.DARKNESS, 2)
                    .add(Aspect.PROTECT, 4));

            helper.registerTCObjectTag(RunicBlocks.WITHER_RUNIC_GLASS, new AspectList()
                    .add(Aspect.CRYSTAL, 2)
                    .add(Aspect.MAGIC, 4)
                    .add(Aspect.ORDER, 4)
                    .add(Aspect.PROTECT, 4));

            helper.registerTCObjectTag(RunicBlocks.WITHER_BRICKS, new int[] {0, 1, 2, 3, 4, 5}, new AspectList()
                    .add(Aspect.MAGIC, 5)
                    .add(Aspect.EARTH, 4)
                    .add(Aspect.PROTECT, 8));

            helper.registerTCObjectTag(RunicBlocks.DEMONIC_BRICKS, new int[] {0, 1}, new AspectList()
                    .add(Aspect.ELDRITCH, 2)
                    .add(Aspect.MAGIC, 4)
                    .add(Aspect.FIRE, 5));

            helper.registerTCObjectTag(RunicBlocks.SURVIVAL_DEMONIC_BRICKS, new int[] {0, 1, 2, 3, 4, 5}, new AspectList()
                    .add(Aspect.MAGIC, 2)
                    .add(Aspect.FIRE, 5));

            helper.registerTCObjectTag(RunicBlocks.RUNIC_PORTAL, new AspectList()
                    .add(Aspect.MAGIC, 4)
                    .add(Aspect.ELDRITCH, 4)
                    .add(Aspect.ORDER, 2)
                    .add(Aspect.VOID, 5));

            helper.registerTCObjectTag(RunicItems.MAGIC_CHALK, new AspectList()
                    .add(Aspect.MAGIC, 8));

            helper.registerTCObjectTag(RunicItems.MAGICAL_STAFF, new AspectList()
                    .add(Aspect.EXCHANGE, 4)
                    .add(Aspect.AURA, 3)
                    .add(Aspect.CRYSTAL, 2));

            helper.registerTCObjectTag(RunicItems.TRAP_DISABLER, new AspectList()
                    .add(Aspect.EXCHANGE, 2)
                    .add(Aspect.ORDER, 3));

            helper.registerTCObjectTag(RunicItems.RUNIC_STEEL, new AspectList()
                    .add(Aspect.ORDER, 6)
                    .add(Aspect.METAL, 15));

            ThaumcraftApi.addLootBagItem(new ItemStack(RunicItems.RUNIC_STEEL), 25, 2);
            //ThaumcraftApi.registerObjectTag(new ItemStack(RunicItems.rider), (new AspectList()).add(Aspect.TRAP, 1).add(Aspect.MOTION, 1));
            //ThaumcraftApi.registerObjectTag(new ItemStack(RunicItems.stacker), (new AspectList()).add(Aspect.TRAP, 1).add(Aspect.MOTION, 1));
            //TwilightForestMod.LOGGER.info("Loaded ThaumcraftApi integration.");
        } catch (Exception e) {
            //TwilightForestMod.LOGGER.warn("Had an %s error while trying to register with ThaumcraftApi.", e.getLocalizedMessage());
            // whatever.
        }
    }

    private static class TFAspectRegisterHelper {

        private final AspectRegistryEvent event;

        private TFAspectRegisterHelper(AspectRegistryEvent event) {
            this.event = event;
        }

        private void registerTCObjectTag(Block block, AspectList list) {
            registerTCObjectTag(new ItemStack(block), list);
        }

        // Register a block with Thaumcraft aspects
        private void registerTCObjectTag(Block block, int meta, AspectList list) {
            if (meta == -1) meta = OreDictionary.WILDCARD_VALUE;
            registerTCObjectTag(new ItemStack(block, 1, meta), list);
        }

        // Register blocks with Thaumcraft aspects
        private void registerTCObjectTag(Block block, int[] metas, AspectList list) {
            for (int meta : metas)
                this.registerTCObjectTag(block, meta, list);
        }

        private void registerTCObjectTag(Item item, AspectList list) {
            registerTCObjectTag(new ItemStack(item), list);
        }

        // Register an item with Thaumcraft aspects
        private void registerTCObjectTag(Item item, int meta, AspectList list) {
            if (meta == -1) meta = OreDictionary.WILDCARD_VALUE;
            registerTCObjectTag(new ItemStack(item, 1, meta), list);
        }

        // Register item swith Thaumcraft aspects
        private void registerTCObjectTag(Item item, int[] metas, AspectList list) {
            for (int meta : metas)
                this.registerTCObjectTag(item, meta, list);
        }

        private void registerTCObjectTag(ItemStack stack, AspectList list) {
            event.register.registerObjectTag(stack, list);
        }

    }

}