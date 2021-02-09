package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRunicGlass extends Block {

    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");

    public BlockRunicGlass() {
        super(Material.GLASS);
        this.setHardness(0.5F);
        this.setUnlocalizedName("runicGlass");
        this.setRegistryName(RunicDungeons.MODID, "runic_glass");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.valueOf(false))
                        .withProperty(DOWN, Boolean.valueOf(false))
                        .withProperty(NORTH, Boolean.valueOf(false))
                        .withProperty(EAST, Boolean.valueOf(false))
                        .withProperty(SOUTH, Boolean.valueOf(false))
                        .withProperty(WEST, Boolean.valueOf(false)));
        RunicBlocks.BLOCKS.add(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(UP, canFenceConnectTo(worldIn, pos, EnumFacing.UP))
                .withProperty(DOWN, canFenceConnectTo(worldIn, pos, EnumFacing.DOWN))
                .withProperty(NORTH, canFenceConnectTo(worldIn, pos, EnumFacing.NORTH))
                .withProperty(SOUTH, canFenceConnectTo(worldIn, pos, EnumFacing.SOUTH))
                .withProperty(EAST, canFenceConnectTo(worldIn, pos, EnumFacing.EAST))
                .withProperty(WEST, canFenceConnectTo(worldIn, pos, EnumFacing.WEST));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, UP, DOWN, NORTH, SOUTH, EAST, WEST);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    private boolean canFenceConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        BlockPos other = pos.offset(facing);
        Block block = world.getBlockState(other).getBlock();
        return block == this;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @SuppressWarnings("deprecation")
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        if (block == this) {
            return false;
        }
        if (blockState != iblockstate) {
            return true;
        }
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

}