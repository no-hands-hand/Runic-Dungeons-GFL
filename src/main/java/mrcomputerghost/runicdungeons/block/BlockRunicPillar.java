package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.enums.EnumBricks;
import mrcomputerghost.runicdungeons.enums.EnumPillar;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockRunicPillar extends Block {

    public static final IProperty<EnumPillar> STATUS = PropertyEnum.create("pillar", EnumPillar.class);

    public BlockRunicPillar() {
        super(Material.ROCK);
        this.setUnlocalizedName("runicPillar");
        this.setRegistryName(RunicDungeons.MODID, "runic_pillar");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(-1.0F);
        this.setResistance(-1.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STATUS, EnumPillar.NORMAL));
        RunicBlocks.BLOCKS.add(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        boolean up = worldIn.getBlockState(pos.up()).getBlock().equals(this);
        boolean down = worldIn.getBlockState(pos.down()).getBlock().equals(this);

        if (up && down) {
            return state.withProperty(STATUS, EnumPillar.CENTER);
        } else if (down) {
            return state.withProperty(STATUS, EnumPillar.UP);
        } else if (up) {
            return state.withProperty(STATUS, EnumPillar.DOWN);
        }

        return state.withProperty(STATUS, EnumPillar.NORMAL);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STATUS);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {}

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
        return !(entity instanceof EntityPlayer);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return super.withRotation(state, rot);
    }

}