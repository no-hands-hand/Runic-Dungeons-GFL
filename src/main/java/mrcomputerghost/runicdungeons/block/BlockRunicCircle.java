package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.enums.EnumCircle;
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

public class BlockRunicCircle extends Block {

    public static final IProperty<EnumCircle> CIRCLE = PropertyEnum.create("circle", EnumCircle.class);

    public BlockRunicCircle() {
        super(Material.ROCK);
        this.setUnlocalizedName("runicCircle");
        this.setRegistryName(RunicDungeons.MODID, "circle");
        this.setResistance(-1.0F);
        //this.setCreativeTab(RunicDungeons.TAB);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CIRCLE, EnumCircle.BOTTOM_RIGHT));
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

        if (isPortal(worldIn, pos.add(-1, -1, -1))//){
                || (isCircle(worldIn, pos.add(-3, 0, 0)) && isCircle(worldIn, pos.add(0,0,-3)))) {
            return state.withProperty(CIRCLE, EnumCircle.TOP_LEFT);
        } else if (isPortal(worldIn, pos.add(2, -1, -1))//) {
                || (isCircle(worldIn, pos.north(3)) && isCircle(worldIn, pos.east(3)))) {
            return state.withProperty(CIRCLE, EnumCircle.TOP_RIGHT);
        } else if (isPortal(worldIn, pos.add(-1, -1, 2))//) {
                || (isCircle(worldIn, pos.south(3)) && isCircle(worldIn, pos.west(3)))) {
            return state.withProperty(CIRCLE, EnumCircle.BOTTOM_LEFT);
        } else if (isPortal(worldIn, pos.add(2, -1, 2))//)  {
                || (isCircle(worldIn, pos.north(3)) && isCircle(worldIn, pos.west(3)))) {
            return state.withProperty(CIRCLE, EnumCircle.BOTTOM_RIGHT);
        }

        return state.withProperty(CIRCLE, EnumCircle.BOTTOM_RIGHT);
    }

    public boolean isCircle(IBlockAccess world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() == this;
    }

    public boolean isPortal(IBlockAccess world, BlockPos pos) {
        IBlockState portal = RunicBlocks.RUNIC_PORTAL.getDefaultState();
        return world.getBlockState(pos) == portal &&
                world.getBlockState(pos.add(-1, 0, 0)) == portal &&
                world.getBlockState(pos.add(0, 0, -1)) == portal &&
                world.getBlockState(pos.add(-1, 0, -1)) == portal;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (this.getActualState(state, source, pos).getValue(CIRCLE) == EnumCircle.BOTTOM_RIGHT) {
            return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 4.0F, 0.0625F, 4.0F);
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