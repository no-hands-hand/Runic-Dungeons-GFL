package mrcomputerghost.runicdungeons.recipes;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.block.RunicBlocks;
import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;

/**
 * @author jiGGO
 * @date 2020/8/25
 */
public class RunicDungeonRecipe {

    public static void init() {
        String name = RunicDungeons.MODID;

        addShapedRecipe(new ResourceLocation(name, "runic_brick"),
                new ItemStack(RunicBlocks.SURVIVAL_BRICKS, 8),
                "SSS", "SKS", "SSS",
                'K', RunicItems.KEY,
                'S', Blocks.STONEBRICK);

        addShapedRecipe(new ResourceLocation(name, "runic_brick_magic"),
                new ItemStack(RunicBlocks.SURVIVAL_BRICKS, 8),
                "SSS", "SMS", "SSS",
                'M', RunicItems.MAGIC_CHALK,
                'S', Blocks.STONEBRICK);

        addShapedRecipe(new ResourceLocation(name, "obsidian_brick"),
                new ItemStack(RunicBlocks.OBSIDIAN_BRICKS, 9),
                "BOB", "OBO", "BOB",
                'O', Blocks.OBSIDIAN,
                'B', RunicBlocks.SURVIVAL_BRICKS);

        addShapedRecipe(new ResourceLocation(name, "wither_brick"),
                new ItemStack(RunicBlocks.WITHER_BRICKS),
                "OOO", "OBO", "OOO",
                'O', RunicBlocks.OBSIDIAN,
                'B', RunicBlocks.OBSIDIAN_BRICKS);

        if (Loader.isModLoaded("baubles")) {
            addShapedRecipe(new ResourceLocation(name, "amulet_speed"),
                    new ItemStack(RunicItems.AMULET_SPEED, 1),
                    "SSS", "G G", "DPD",
                    'S', Items.STRING,
                    'G', Items.GOLD_INGOT,
                    'D', Items.DIAMOND,
                    'P', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
                            PotionType.getPotionTypeForName("strong_swiftness")));

            addShapedRecipe(new ResourceLocation(name, "amulet_strong"),
                    new ItemStack(RunicItems.AMULET_STRONG, 1),
                    "SSS", "G G", "DPD",
                    'S', Items.STRING,
                    'G', Items.GOLD_INGOT,
                    'D', Items.DIAMOND,
                    'P', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
                            PotionType.getPotionTypeForName("strong_strength")));

            addShapedRecipe(new ResourceLocation(name, "amulet_regen"),
                    new ItemStack(RunicItems.AMULET_REGEN, 1),
                    "SSS", "G G", "DPD",
                    'S', Items.STRING,
                    'G', Items.GOLD_INGOT,
                    'D', Items.DIAMOND,
                    'P', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
                            PotionType.getPotionTypeForName("strong_regeneration")));

            addShapedRecipe(new ResourceLocation(name, "amulet_fire_resist"),
                    new ItemStack(RunicItems.AMULET_FIRE, 1),
                    "SSS", "G G", "DPD",
                    'S', Items.STRING,
                    'G', Items.GOLD_INGOT,
                    'D', Items.DIAMOND,
                    'P', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
                            PotionType.getPotionTypeForName("long_fire_resistance")));

            addShapedRecipe(new ResourceLocation(name, "amulet_breath"),
                    new ItemStack(RunicItems.AMULET_BREATH, 1),
                    "SSS", "G G", "DPD",
                    'S', Items.STRING,
                    'G', Items.GOLD_INGOT,
                    'D', Items.DIAMOND,
                    'P', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
                            PotionType.getPotionTypeForName("long_water_breathing")));


            addShapedRecipe(new ResourceLocation(name, "amulet_breath"),
                    new ItemStack(RunicItems.AMULET_BREATH, 1),
                    "SSS", "G G", "DPD",
                    'S', Items.STRING,
                    'G', Items.GOLD_INGOT,
                    'D', Items.DIAMOND,
                    'P', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
                            PotionType.getPotionTypeForName("long_water_breathing")));

            addShapedRecipe(new ResourceLocation(name, "amulet_night_vision"),
                    new ItemStack(RunicItems.AMULET_NIGHT, 1),
                    "SSS", "G G", "DPD",
                    'S', Items.STRING,
                    'G', Items.GOLD_INGOT,
                    'D', Items.DIAMOND,
                    'P', PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
                            PotionType.getPotionTypeForName("long_night_vision")));
        }

    }

    public static void addShapedRecipe(ResourceLocation name, ResourceLocation group, @Nonnull ItemStack output, Object... params) {
        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(params);
        ForgeRegistries.RECIPES.register(new BaubleRecipe(group == null ? "" : group.toString(),
                primer.width, primer.height, primer.input, output).setRegistryName(name));
    }

    public static void addShapedRecipe(ResourceLocation name, @Nonnull ItemStack output, Object... params) {
        addShapedRecipe(name, null, output, params);
    }

}