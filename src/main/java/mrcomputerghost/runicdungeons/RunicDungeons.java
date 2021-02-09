package mrcomputerghost.runicdungeons;

import mrcomputerghost.runicdungeons.advancements.RunicAdvancements;
import mrcomputerghost.runicdungeons.block.RunicBlocks;
import mrcomputerghost.runicdungeons.capabilities.AnotherHandler;
import mrcomputerghost.runicdungeons.capabilities.CapabilityHandler;
import mrcomputerghost.runicdungeons.commands.CommandGetDungeonScore;
import mrcomputerghost.runicdungeons.commands.CommandSetDungeonScore;
import mrcomputerghost.runicdungeons.creativetab.CreativeTab;
import mrcomputerghost.runicdungeons.dimension.WorldProviderDungeon;
import mrcomputerghost.runicdungeons.handler.RunicEventHandler;
import mrcomputerghost.runicdungeons.item.RunicItems;
import mrcomputerghost.runicdungeons.loot.RunicLootTables;
import mrcomputerghost.runicdungeons.proxy.CommonProxy;
import mrcomputerghost.runicdungeons.recipes.RunicDungeonRecipe;
import mrcomputerghost.runicdungeons.util.Thaumcraft;
import mrcomputerghost.runicdungeons.world.BiomeDungeon;
import mrcomputerghost.runicdungeons.world.TerribleDungeon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = RunicDungeons.MODID, name = RunicDungeons.NAME, version = RunicDungeons.VERSION,
        dependencies = "after:thaumcraft@[6.1.BETA21,)")
public class RunicDungeons {

    public static final String MODID = "runicdungeons";
    public static final String NAME = "Runic Dungeons";
    public static final String VERSION = "1.1.8b";

    public final static CreativeTabs TAB = new CreativeTab();

    public final static Biome DUNGEON = new BiomeDungeon();

    public final static Logger LOGGER = LogManager.getLogger(MODID);

    public static int ID = 93;

    public static DimensionType dimension;

    @SidedProxy(clientSide = "mrcomputerghost.runicdungeons.proxy.ClientProxy",
            serverSide = "mrcomputerghost.runicdungeons.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Instance(MODID)
    public static RunicDungeons instance;

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        ForgeRegistries.BIOMES.register(DUNGEON);
        dimension = DimensionType.register("dungeon", "_dungeon", ID, WorldProviderDungeon.class, false);
        DimensionManager.registerDimension(ID, dimension);
        //MinecraftForge.EVENT_BUS.register(new EventLoader());
        MinecraftForge.EVENT_BUS.register(new RunicEventHandler());
        MinecraftForge.EVENT_BUS.register(new RunicLootTables());
        MinecraftForge.EVENT_BUS.register(new AnotherHandler());
        CapabilityHandler.setupCapabilities();
        if (Loader.isModLoaded("thaumcraft")) {
            MinecraftForge.EVENT_BUS.register(Thaumcraft.class);
        }
        proxy.preInit(event);
    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new TerribleDungeon(), 1);
        OreDictionary.registerOre("ingotRunicSteel", RunicItems.RUNIC_STEEL);
        for (int i = 0; i < 3; i++) OreDictionary.registerOre("stoneBrick", new ItemStack(RunicBlocks.SURVIVAL_BRICKS, 1, i));
        OreDictionary.registerOre("glass", RunicBlocks.RUNIC_GLASS);
        OreDictionary.registerOre("compressedObsidian", RunicBlocks.OBSIDIAN);
        OreDictionary.registerOre("blockRunicSteel", RunicBlocks.RUNIC_STEEL_BLOCK);
        //MapGenStructureIO.registerStructureComponent(null, "main_room");
        RunicAdvancements.init();
        RunicDungeonRecipe.init();
        proxy.init(event);
    }

    @EventHandler
    public void serverStart(final FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandGetDungeonScore());
        event.registerServerCommand(new CommandSetDungeonScore());
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}