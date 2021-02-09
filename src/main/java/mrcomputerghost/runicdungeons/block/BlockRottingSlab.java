package mrcomputerghost.runicdungeons.block;

import java.util.Random;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRottingSlab extends BlockSlab {

    public BlockRottingSlab() {
        super(Material.WOOD);
        this.setHardness(1.0F);
        this.setResistance(1.0F);
        IBlockState state = this.blockState.getBaseState();
        if (!this.isDouble()) {
            state = state.withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM);
        }
        this.setDefaultState(state);
        this.useNeighborBrightness = !this.isDouble();
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return 0;
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HALF);
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return this.getUnlocalizedName();
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return HALF;
    }

    @Override
    public Comparable<?> getTypeForItem(final ItemStack stack) {
        return EnumBlockHalf.BOTTOM;
    }

    @Override
    public int getMetaFromState(final IBlockState state) {
        if (this.isDouble()) {
            return 0;
        } else {
            return state.getValue(HALF).ordinal();
        }
    }

    @Override
    public IBlockState getStateFromMeta(final int meta) {
        return this.getDefaultState().withProperty(HALF, BlockSlab.EnumBlockHalf.values()[meta]);
    }

    public static class Half extends BlockRottingSlab {

        public Half() {
            this.setUnlocalizedName("rottingSlab");
            this.setRegistryName(RunicDungeons.MODID, "bookshelf_slab");
            this.setCreativeTab(RunicDungeons.TAB);
            RunicBlocks.BLOCKS.add(this);
        }

        @Override
        public boolean isDouble() {
            return false;
        }

    }

}