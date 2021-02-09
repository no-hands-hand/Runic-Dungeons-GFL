package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.enums.EnumCircle;
import mrcomputerghost.runicdungeons.enums.EnumLargeCircle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLargeCircle extends Block {

    public static final IProperty<EnumLargeCircle> CIRCLE = PropertyEnum.create("circle", EnumLargeCircle.class);

    public BlockLargeCircle() {
        super(Material.ROCK);
        this.setRegistryName(RunicDungeons.MODID, "circle_large");
        this.setUnlocalizedName("runicCircleLarge");
        this.setResistance(-1.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CIRCLE, EnumLargeCircle.BOTTOM_RIGHT));
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CIRCLE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        if (isPortal(worldIn, pos.add(-2, -1, -2))) {
            return state.withProperty(CIRCLE, EnumLargeCircle.TOP_LEFT);
        } else if (isPortal(worldIn, pos.add(0, -1, -2))) {
            return state.withProperty(CIRCLE, EnumLargeCircle.TOP);
        } else if (isPortal(worldIn, pos.add(2, -1, -2))) {
            return state.withProperty(CIRCLE, EnumLargeCircle.TOP_RIGHT);
        } else if (isPortal(worldIn, pos.add(-2, -1, 0))) {
            return state.withProperty(CIRCLE, EnumLargeCircle.LEFT);
        } else if (isPortal(worldIn, pos.add(2, -1, 0))) {
            return state.withProperty(CIRCLE, EnumLargeCircle.RIGHT);
        } else if (isPortal(worldIn, pos.add(-2, -1, 2))) {
            return state.withProperty(CIRCLE, EnumLargeCircle.BOTTOM_LEFT);
        } else if (isPortal(worldIn, pos.add(0, -1, 2))) {
            return state.withProperty(CIRCLE, EnumLargeCircle.BOTTOM);
        }

        return state.withProperty(CIRCLE, EnumLargeCircle.BOTTOM_RIGHT);
    }

    public boolean isPortal(IBlockAccess world, BlockPos pos) {
        IBlockState portal = RunicBlocks.RUNIC_PORTAL.getDefaultState();
        return world.getBlockState(pos) == portal &&
                world.getBlockState(pos.add(-1, 0, 0)) == portal &&
                world.getBlockState(pos.add(0, 0, -1)) == portal &&
                world.getBlockState(pos.add(-1, 0, -1)) == portal &&
                world.getBlockState(pos.add(1, 0, 0)) == portal &&
                world.getBlockState(pos.add(0, 0, 1)) == portal &&
                world.getBlockState(pos.add(1, 0, 1)) == portal &&
                world.getBlockState(pos.add(-1, 0, 1)) == portal &&
                world.getBlockState(pos.add(1, 0, -1)) == portal;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (this.getActualState(state, source, pos).getValue(CIRCLE) == EnumLargeCircle.BOTTOM_RIGHT) {
            return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 5.0F, 0.0625F, 5.0F);
        } else {
            return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        }
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {}

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
        return false;
    }

}