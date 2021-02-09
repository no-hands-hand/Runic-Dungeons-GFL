package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.enums.EnumPillar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSurvivalRunicPillar extends Block {

    public static final IProperty<EnumPillar> STATUS = PropertyEnum.create("pillar", EnumPillar.class);

    public BlockSurvivalRunicPillar() {
        super(Material.ROCK);
        this.setUnlocalizedName("survivalRunicPillar");
        this.setRegistryName(RunicDungeons.MODID, "survival_runic_pillar");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setHarvestLevel("pickaxe", 1);
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