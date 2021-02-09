package mrcomputerghost.runicdungeons.item;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.block.BlockLargeCircle;
import mrcomputerghost.runicdungeons.block.BlockRunicCircle;
import mrcomputerghost.runicdungeons.block.RunicBlocks;
import mrcomputerghost.runicdungeons.enums.EnumCircle;
import mrcomputerghost.runicdungeons.enums.EnumLargeCircle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Logger;

public class ItemMagicalStaff extends Item {

    private static final Logger LOGGER = RunicDungeons.LOGGER;

    protected static final IBlockState STONE_BRICK = Blocks.STONEBRICK.getDefaultState();

    protected static final IBlockState CHISELED = Blocks.STONEBRICK.getStateFromMeta(3);

    protected static final IBlockState PORTAL = RunicBlocks.RUNIC_PORTAL.getDefaultState();

    protected static final IBlockState BRICKS = RunicBlocks.BRICKS.getDefaultState();

    public ItemMagicalStaff() {
        this.setUnlocalizedName("magic_staff");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setRegistryName(RunicDungeons.MODID, "magic_staff");
        this.setMaxStackSize(1);
        RunicItems.ITEMS.add(this);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState brick = Blocks.STONEBRICK.getStateFromMeta(3);
        if (world.getBlockState(pos) == brick && player.dimension != RunicDungeons.ID) {
            int x = pos.getX(), y = pos.getY(), z = pos.getZ();
            LOGGER.info(player.getName() + " created a Runic Portal at: " + x + ", " + y + ", " + z);
            genPortal(world, x, y, z, player, hand);
            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }

    public static void genPortal(World world, int x, int y, int z, EntityPlayer player, EnumHand hand) {
        boolean flag = false;

        if (is3x3(world, x, y, z)) {
            generate3x3(world, x, y, z);
            flag = true;
        } else if (is4x4(world, x - 1, y, z - 1)) {
            generate4x4(world, x, y, z);
            flag = true;
        } else if (is4x4(world, x - 1, y, z - 2)) {
            generate4x4(world, x, y, z - 1);
            flag = true;
        } else if (is4x4(world, x - 2, y, z - 2)) {
            generate4x4(world, x - 1, y, z - 1);
            flag = true;
        } else if (is4x4(world, x - 2, y, z - 1)) {
            generate4x4(world, x - 1, y, z);
            flag = true;
        } else if (is5x5(world, x - 1, y, z - 1)) {
            generate5x5(world, x - 1, y, z - 1);
            flag = true;
        } else if (is5x5(world, x - 2, y, z - 1)) {
            generate5x5(world, x - 2, y, z - 1);
            flag = true;
        } else if (is5x5(world, x - 3, y, z - 1)) {
            generate5x5(world, x - 3, y, z - 1);
            flag = true;
        } else if (is5x5(world, x - 1, y, z - 2)) {
            generate5x5(world, x - 1, y, z - 2);
            flag = true;
        } else if (is5x5(world, x - 1, y, z - 3)) {
            generate5x5(world, x - 1, y, z - 3);
            flag = true;
        } else if (is5x5(world, x - 2, y, z - 2)) {
            generate5x5(world, x - 2, y, z - 2);
            flag = true;
        } else if (is5x5(world, x - 3, y, z - 3)) {
            generate5x5(world, x - 3, y, z - 3);
            flag = true;
        } else if (is5x5(world, x - 3, y, z - 2)) {
            generate5x5(world, x - 3, y, z - 2);
            flag = true;
        } else if (is5x5(world, x - 2, y, z - 3)) {
            generate5x5(world, x - 2, y, z - 3);
            flag = true;
        }

        if (flag && !player.capabilities.isCreativeMode) {
            ItemStack itemStack = player.getHeldItem(hand);
            if (itemStack.getItem() == RunicItems.MAGICAL_STAFF) {
                itemStack.shrink(1);
            }
        }

    }

    private static boolean is3x3(World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return world.getBlockState(pos.west()) == STONE_BRICK &&
                world.getBlockState(pos.south()) == STONE_BRICK &&
                world.getBlockState(pos.north()) == STONE_BRICK &&
                world.getBlockState(pos.east()) == STONE_BRICK &&
                world.getBlockState(pos.add(1, 0, 1)) == STONE_BRICK &&
                world.getBlockState(pos.add(1, 0, -1)) == STONE_BRICK &&
                world.getBlockState(pos.add(-1, 0, -1)) == STONE_BRICK &&
                world.getBlockState(pos.add(-1, 0, 1)) == STONE_BRICK;
    }

    private static boolean is4x4(World world, int x, int y, int z) {
        return (world.getBlockState(new BlockPos(x, y, z)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x, y, z + 1)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x, y, z + 2)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x, y, z + 3)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 1, y, z)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 1, y, z + 1)) == CHISELED &&
                world.getBlockState(new BlockPos(x + 1, y, z + 2)) == CHISELED &&
                world.getBlockState(new BlockPos(x + 1, y, z + 3)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 2, y, z)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 2, y, z + 1)) == CHISELED &&
                world.getBlockState(new BlockPos(x + 2, y, z + 2)) == CHISELED &&
                world.getBlockState(new BlockPos(x + 2, y, z + 3)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 3, y, z)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 3, y, z + 1)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 3, y, z + 2)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 3, y, z + 3)) == STONE_BRICK);
    }

    private static boolean is5x5(World world, int x, int y, int z) {
        return (world.getBlockState(new BlockPos(x, y, z)) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x, y, z + 1) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x, y, z + 2) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x, y, z + 3) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x, y, z + 4) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 1, y, z) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 1, y, z + 1) ) == CHISELED &&
                world.getBlockState(new BlockPos(x + 1, y, z + 2) ) == CHISELED &&
                world.getBlockState(new BlockPos(x + 1, y, z + 3) ) == CHISELED &&
                world.getBlockState(new BlockPos(x + 1, y, z + 4) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 2, y, z) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 2, y, z + 1) ) == CHISELED &&
                world.getBlockState(new BlockPos(x + 2, y, z + 2) ) == CHISELED &&
                world.getBlockState(new BlockPos(x + 2, y, z + 3) ) == CHISELED &&
                world.getBlockState(new BlockPos(x + 2, y, z + 4) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 3, y, z) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 3, y, z + 1) ) == CHISELED &&
                world.getBlockState(new BlockPos(x + 3, y, z + 2) ) == CHISELED &&
                world.getBlockState(new BlockPos(x + 3, y, z + 3) ) == CHISELED &&
                world.getBlockState(new BlockPos(x + 3, y, z + 4) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 4, y, z) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 4, y, z + 1) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 4, y, z + 2) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 4, y, z + 3) ) == STONE_BRICK &&
                world.getBlockState(new BlockPos(x + 4, y, z + 4) ) == STONE_BRICK);
    }

    private static void generate3x3(World world, int x, int y, int z) {
        world.setBlockState(new BlockPos(x, y, z), PORTAL);
        world.setBlockState(new BlockPos(x + 1, y, z), BRICKS);
        world.setBlockState(new BlockPos(x + 1, y, z + 1), BRICKS);
        world.setBlockState(new BlockPos(x + 1, y, z - 1), BRICKS);
        world.setBlockState(new BlockPos(x - 1, y, z), BRICKS);
        world.setBlockState(new BlockPos(x - 1, y, z + 1), BRICKS);
        world.setBlockState(new BlockPos(x - 1, y, z - 1), BRICKS);
        world.setBlockState(new BlockPos(x, y, z + 1), BRICKS);
        world.setBlockState(new BlockPos(x, y, z - 1), BRICKS);
        world.setBlockState(new BlockPos(x - 1, y + 1, z - 1), RunicBlocks.SMALL_CIRCLE.getDefaultState());
    }

    private static void generate4x4(World world, int x, int y, int z) {
        world.setBlockState(new BlockPos(x, y, z), PORTAL, 2);
        world.setBlockState(new BlockPos(x + 1, y, z), PORTAL, 2);
        world.setBlockState(new BlockPos(x + 1, y, z + 1), PORTAL, 2);
        world.setBlockState(new BlockPos(x, y, z + 1), PORTAL, 2);
        world.setBlockState(new BlockPos(x + 2, y, z), BRICKS, 2);
        world.setBlockState(new BlockPos(x + 2, y, z + 1), BRICKS, 2);
        world.setBlockState(new BlockPos(x + 2, y, z - 1), BRICKS, 2);
        world.setBlockState(new BlockPos(x + 2, y, z + 2), BRICKS, 2);
        world.setBlockState(new BlockPos(x + 1, y, z - 1), BRICKS, 2);
        world.setBlockState(new BlockPos(x + 1, y, z + 2), BRICKS, 2);
        world.setBlockState(new BlockPos(x, y, z - 1), BRICKS, 2);
        world.setBlockState(new BlockPos(x, y, z + 2), BRICKS, 2);
        world.setBlockState(new BlockPos(x - 1, y, z), BRICKS, 2);
        world.setBlockState(new BlockPos(x - 1, y, z + 1), BRICKS, 2);
        world.setBlockState(new BlockPos(x - 1, y, z - 1), BRICKS, 2);
        world.setBlockState(new BlockPos(x - 1, y, z + 2), BRICKS, 2);
        IBlockState state = RunicBlocks.RUNE_CIRCLE.getDefaultState();
        world.setBlockState(new BlockPos(x - 1, y + 1, z - 1), state, 2);
        world.setBlockState(new BlockPos(x + 2, y + 1, z - 1),
                state.withProperty(BlockRunicCircle.CIRCLE, EnumCircle.BOTTOM_LEFT), 2);
        world.setBlockState(new BlockPos(x + 2, y + 1, z + 2),
                state.withProperty(BlockRunicCircle.CIRCLE, EnumCircle.TOP_LEFT), 2);
        world.setBlockState(new BlockPos(x - 1, y + 1, z + 2),
                state.withProperty(BlockRunicCircle.CIRCLE, EnumCircle.TOP_RIGHT), 2);
    }

    private static void generate5x5(World world, int x, int y, int z) {
        world.setBlockState(new BlockPos(x, y, z), BRICKS);
        world.setBlockState(new BlockPos(x, y, z + 1), BRICKS);
        world.setBlockState(new BlockPos(x, y, z + 2), BRICKS);
        world.setBlockState(new BlockPos(x, y, z + 3), BRICKS);
        world.setBlockState(new BlockPos(x, y, z + 4), BRICKS);
        world.setBlockState(new BlockPos(x + 1, y, z), BRICKS);
        world.setBlockState(new BlockPos(x + 1, y, z + 1), PORTAL);
        world.setBlockState(new BlockPos(x + 1, y, z + 2), PORTAL);
        world.setBlockState(new BlockPos(x + 1, y, z + 3), PORTAL);
        world.setBlockState(new BlockPos(x + 1, y, z + 4), BRICKS);
        world.setBlockState(new BlockPos(x + 2, y, z), BRICKS);
        world.setBlockState(new BlockPos(x + 2, y, z + 1), PORTAL);
        world.setBlockState(new BlockPos(x + 2, y, z + 2), PORTAL);
        world.setBlockState(new BlockPos(x + 2, y, z + 3), PORTAL);
        world.setBlockState(new BlockPos(x + 2, y, z + 4), BRICKS);
        world.setBlockState(new BlockPos(x + 3, y, z), BRICKS);
        world.setBlockState(new BlockPos(x + 3, y, z + 1), PORTAL);
        world.setBlockState(new BlockPos(x + 3, y, z + 2), PORTAL);
        world.setBlockState(new BlockPos(x + 3, y, z + 3), PORTAL);
        world.setBlockState(new BlockPos(x + 3, y, z + 4), BRICKS);
        world.setBlockState(new BlockPos(x + 4, y, z), BRICKS);
        world.setBlockState(new BlockPos(x + 4, y, z + 1), BRICKS);
        world.setBlockState(new BlockPos(x + 4, y, z + 2), BRICKS);
        world.setBlockState(new BlockPos(x + 4, y, z + 3), BRICKS);
        world.setBlockState(new BlockPos(x + 4, y, z + 4), BRICKS);
        IBlockState state = RunicBlocks.LARGE_CIRCLE.getDefaultState();
        world.setBlockState(new BlockPos(x, y + 1, z), state);
        world.setBlockState(new BlockPos(x, y + 1, z + 2),
                state.withProperty(BlockLargeCircle.CIRCLE, EnumLargeCircle.RIGHT));
        world.setBlockState(new BlockPos(x, y + 1, z + 4),
                state.withProperty(BlockLargeCircle.CIRCLE, EnumLargeCircle.TOP_RIGHT));
        world.setBlockState(new BlockPos(x + 2, y + 1, z + 4),
                state.withProperty(BlockLargeCircle.CIRCLE, EnumLargeCircle.TOP));
        world.setBlockState(new BlockPos(x + 2, y + 1, z),
                state.withProperty(BlockLargeCircle.CIRCLE, EnumLargeCircle.BOTTOM));
        world.setBlockState(new BlockPos(x + 4, y + 1, z),
                state.withProperty(BlockLargeCircle.CIRCLE, EnumLargeCircle.BOTTOM_LEFT));
        world.setBlockState(new BlockPos(x + 4, y + 1, z + 2),
                state.withProperty(BlockLargeCircle.CIRCLE, EnumLargeCircle.LEFT));
        world.setBlockState(new BlockPos(x + 4, y + 1, z + 4),
                state.withProperty(BlockLargeCircle.CIRCLE, EnumLargeCircle.TOP_LEFT));
    }

}