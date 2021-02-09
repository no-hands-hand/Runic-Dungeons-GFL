package mrcomputerghost.runicdungeons.block;

import com.google.common.collect.Lists;
import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author ji_GGO
 * @date 27/12/2020
 */
@Mod.EventBusSubscriber(modid = RunicDungeons.MODID)
public class RunicBlocks {

    public final static ArrayList<Block> BLOCKS = Lists.newArrayList();

    public final static Block SMALL_CIRCLE = new BlockSmallCircle();

    public final static Block RUNE_CIRCLE = new BlockRunicCircle();

    public final static Block LARGE_CIRCLE = new BlockLargeCircle();

    public final static Block BRICKS = new BlockBricks();

    public final static Block DEMONIC_BRICKS = new BlockDemonicBricks();

    public final static Block SURVIVAL_BRICKS = new BlockSurvivalBricks();

    public final static Block SURVIVAL_DEMONIC_BRICKS = new BlockSurvivalDemonicBricks();

    public final static Block OBSIDIAN_BRICKS = new BlockObsidianBricks();

    public final static Block WITHER_BRICKS = new BlockWitherBricks();

    public final static Block RUNIC_LAMP = new BlockLamp();

    public final static Block RUNIC_PILLAR = new BlockRunicPillar();

    public final static Block SURVIVAL_RUNIC_LAMP = new BlockSurvivalLamp();

    public final static Block SURVIVAL_RUNIC_PILLAR = new BlockSurvivalRunicPillar();

    public final static Block RUNIC_GLASS = new BlockRunicGlass();

    public final static Block OBSIDIAN = new BlockCompressedObsidian();

    public final static Block OBSIDIAN_RUNIC_GLASS = new BlockObsidianGlass();

    public final static Block RUNIC_PORTAL = new BlockRunicPortal();

    public final static Block KEY_STONE = new BlockKeyStone();

    public final static Block SPIKE_TRAP = new BlockSpikeTrap();

    public final static Block WITHER_RUNIC_GLASS = new BlockWitherGlass();

    public final static Block CHAOTIC_SAND = new BlockChaoticSand();

    public final static Block BOOKSHELF  = new BlockAncientBookshelf();

    public final static Block SLAB = new BlockRottingSlab.Half();

    public final static Block LADDER = new BlockRottingLadder();

    public final static Block RUNIC_STEEL_BLOCK = new BlockCompressed();

    public final static Block RUNIC_SPAWNER = new BlockRunicSpawner();

    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
        BLOCKS.forEach(b -> event.getRegistry().register(b));
    }

    public static void registerItem(IForgeRegistry<Item> registry) {
        BLOCKS.forEach(block -> {
            ResourceLocation resource = Objects.requireNonNull(block.getRegistryName());
            if (block instanceof BlockBrickStatus) {
                registry.register(new ItemMultiTexture(block, block, new String[0]){
                    @Override
                    public String getUnlocalizedName(ItemStack stack) {
                        return "tile." + ((BlockBrickStatus)block).getUnlocalizedNames()[stack.getMetadata()];
                    }
                }.setRegistryName(resource));
            } else {
                registry.register(new ItemBlock(block).setRegistryName(resource));
            }
        });
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry(final ModelRegistryEvent event) {
        BLOCKS.forEach(block -> {
            if (block instanceof BlockBrickStatus) {
                String[] registry = ((BlockBrickStatus)block).getRegistryNames();
                for (int i = 0; registry.length > i; i++){
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i,
                            new ModelResourceLocation(new ResourceLocation(RunicDungeons.MODID, registry[i]), "inventory"));
                }
            } else {
                registerModel(block);
            }
        });
    }

    @SideOnly(Side.CLIENT)
    private static void registerModel(Block block) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(block.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, modelResourceLocation);
    }

}