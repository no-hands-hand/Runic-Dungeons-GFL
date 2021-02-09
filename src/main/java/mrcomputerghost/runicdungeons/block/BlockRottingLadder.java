package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockRottingLadder extends BlockLadder {

    public BlockRottingLadder() {
        super();
        this.setUnlocalizedName("rottingLadder");
        this.setRegistryName(RunicDungeons.MODID, "rotting_ladder");
        this.setHardness(1.0F);
        this.setResistance(1.0F);
        this.setCreativeTab(RunicDungeons.TAB);
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return 0;
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        return true;
    }

}