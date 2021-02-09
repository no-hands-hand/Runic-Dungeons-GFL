package mrcomputerghost.runicdungeons.world.gen;

import mrcomputerghost.runicdungeons.block.RunicBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author ji_GGO
 * @date 6/1/2021
 */
public class GenGuardian extends GenStone {

    @Override
    public void gen(World world, int x, int y, int z) {
        GenGuardian.genGuardian(world, x, y, z);
    }

    public static void genGuardian(World world, int x, int y, int z) {
        genStone(world, x, y, z);
        world.setBlockState(new BlockPos(x + 7, y + 2, z + 7), RunicBlocks.RUNIC_SPAWNER.getDefaultState());
    }

}