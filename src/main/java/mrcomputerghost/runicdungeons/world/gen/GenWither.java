package mrcomputerghost.runicdungeons.world.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author ji_GGO
 * @date 6/1/2021
 */
public class GenWither extends GenStone {

    @Override
    public void gen(World world, int x, int y, int z) {
        GenWither.genWither(world, x, y, z);
    }

    public static void genWither(World world, int x, int y, int z) {
        genStone(world, x, y, z);
        BlockPos pos = new BlockPos(x + 7, y + 2, z + 7);
        IBlockState SAND = Blocks.SOUL_SAND.getDefaultState();
        world.setBlockState(pos, SAND);
        world.setBlockState(pos.up(), SAND);
        world.setBlockState(pos.add(1, 1, 0), SAND);
        world.setBlockState(pos.add(-1, 1, 0), SAND);
        generateSkull(world, pos.up(2));
        generateSkull(world, pos.add(1, 2, 0));
        generateSkull(world, pos.add(-1, 2, 0));
    }

    public static BlockPos generateSkull(World world, BlockPos pos){
        world.setBlockState(pos, Blocks.SKULL.getStateFromMeta(1), 2);
        TileEntitySkull skull = (TileEntitySkull)world.getTileEntity(pos);
        if (skull != null) {
            skull.setType(1);
        }
        return pos;
    }

}
