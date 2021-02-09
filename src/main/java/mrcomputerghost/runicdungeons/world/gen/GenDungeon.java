package mrcomputerghost.runicdungeons.world.gen;

import mrcomputerghost.runicdungeons.block.RunicBlocks;
import mrcomputerghost.runicdungeons.item.RunicItems;
import mrcomputerghost.runicdungeons.loot.RunicLootTables;
import mrcomputerghost.runicdungeons.world.mob.DungeonMobs;
import net.minecraft.block.BlockDropper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.ForgeModContainer;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author ji_GGO
 * @date 27/12/2020
 */
public abstract class GenDungeon {

    protected static final IBlockState PLAIN = RunicBlocks.BRICKS.getStateFromMeta(0);

    protected static final IBlockState MOSSY = RunicBlocks.BRICKS.getStateFromMeta(1);

    protected static final IBlockState CRACKED = RunicBlocks.BRICKS.getStateFromMeta(2);

    protected static final IBlockState RUNE_PLAIN = RunicBlocks.BRICKS.getStateFromMeta(3);

    protected static final IBlockState RUNE_MOSSY = RunicBlocks.BRICKS.getStateFromMeta(4);

    protected static final IBlockState RUNE_CRACKED = RunicBlocks.BRICKS.getStateFromMeta(5);

    protected static final IBlockState KEY_STONE = RunicBlocks.KEY_STONE.getDefaultState();

    protected static final IBlockState RUNIC_LAMP = RunicBlocks.RUNIC_LAMP.getDefaultState();

    protected static final IBlockState RUNIC_PILLAR = RunicBlocks.RUNIC_PILLAR.getDefaultState();

    protected static final IBlockState SPIKE_TRAP = RunicBlocks.SPIKE_TRAP.getDefaultState();

    protected static final IBlockState OBSIDIAN = Blocks.OBSIDIAN.getDefaultState();

    protected static final IBlockState CHEST = Blocks.CHEST.getDefaultState();

    protected static final IBlockState MOB_SPAWNER = Blocks.MOB_SPAWNER.getDefaultState();

    protected static final IBlockState DROPPER = Blocks.DROPPER.getDefaultState();

    public abstract void gen(World world, int x, int y, int z);

    public static BlockPos generateDropper(World world, int x, int y, int z) {
        return generateDropper(world, new BlockPos(x, y, z));
    }

    public static BlockPos generateDropper(World world, BlockPos pos) {
        Random random = new Random();
        IBlockState dropper = DROPPER.getBlock()
                .getStateFromMeta(random.nextInt(4) + 2);
        world.setBlockState(pos, dropper, 2);
        TileEntityDropper drop = (TileEntityDropper)world.getTileEntity(pos);
        if (drop != null) {
            drop.setInventorySlotContents(random.nextInt(8), new ItemStack(RunicItems.KEY));
        }
        return pos;
    }

    public static BlockPos generateChest(World world, int x, int y, int z) {
        return generateChest(world, new BlockPos(x, y, z));
    }

    public static BlockPos generateChest(World world, int x, int y, int z, @Nullable ResourceLocation loot) {
        return generateChest(world, new BlockPos(x, y, z), loot);
    }

    public static BlockPos generateChest(World world, BlockPos pos) {
        Random random = new Random();
        return generateChest(world, pos, random.nextInt(4));
    }

    public static BlockPos generateChest(World world, BlockPos pos, @Nullable ResourceLocation loot) {
        Random random = new Random();
        return generateChest(world, pos, random.nextInt(4), loot);
    }

    public static BlockPos generateChest(World world, BlockPos pos, int id) {
        return generateChest(world, pos, id, null);
    }

    public static BlockPos generateChest(World world, BlockPos pos, int id, @Nullable ResourceLocation loot) {
        IBlockState chest = CHEST.getBlock().getStateFromMeta(id);
        world.setBlockState(pos, chest, 2);
        TileEntityChest box = (TileEntityChest) world.getTileEntity(pos);
        if (box != null && loot != null) {
            box.setLootTable(loot, new Random().nextLong());
        }
        return pos;
    }

    public static BlockPos generateMobSpawner(World world, int x, int y, int z){
        return generateMobSpawner(world, new BlockPos(x, y, z));
    }

    public static BlockPos generateMobSpawner(World world, BlockPos pos){
        world.setBlockState(pos, MOB_SPAWNER, 2);
        TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(pos);
        if (spawner != null) {
            spawner.getSpawnerBaseLogic().setEntityId(DungeonMobs.getRandEntity());
        }
        return pos;
    }

    public static BlockPos safeSetBlockState(World world, BlockPos pos, IBlockState state){
        if (world.getBlockState(pos).getBlock() == Blocks.AIR &&
                world.getBlockState(pos.down()).getBlock() != Blocks.AIR) {
            world.setBlockState(pos, state, 2);
        }
        return pos;
    }

}