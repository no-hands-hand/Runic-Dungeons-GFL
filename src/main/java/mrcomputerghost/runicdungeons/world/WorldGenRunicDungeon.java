package mrcomputerghost.runicdungeons.world;

import mrcomputerghost.runicdungeons.block.BlockRunicCircle;
import mrcomputerghost.runicdungeons.block.RunicBlocks;
import mrcomputerghost.runicdungeons.enums.EnumCircle;
import mrcomputerghost.runicdungeons.world.gen.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author ji_GGO
 * @date 27/12/2020
 */
public class WorldGenRunicDungeon {

    protected final static IBlockState PORTAL = RunicBlocks.RUNIC_PORTAL.getDefaultState();

    protected final static IBlockState RUNIC = RunicBlocks.RUNE_CIRCLE.getDefaultState();

    public static void generate(World world, Random random, int x, int y, int z) {
        int foo =  random.nextInt(4);

        if (x == 0 && z == 0) {
            GenStone.genStone(world, x, y, z);
            BlockPos pos = new BlockPos(x, y, z).add(7, 1, 7);
            world.setBlockState(pos, PORTAL);
            world.setBlockState(pos.add(1, 0, 0), PORTAL);
            world.setBlockState(pos.add(1, 0, 1), PORTAL);
            world.setBlockState(pos.add(0, 0, 1), PORTAL);
            world.setBlockState(pos.add(-1, 1, -1), RUNIC);
            world.setBlockState(pos.add(2, 1, -1), RUNIC);
            world.setBlockState(pos.add(2, 1, 2), RUNIC);
            world.setBlockState(pos.add(-1, 1, 2), RUNIC);
        } else if (foo == 0){
            int bar = random.nextInt(100);
            if (bar < 15) {
                GenGuardian.genGuardian(world, x, y, z);
            } else {
                GenCobble.genCobble(world, x, y, z);
            }
        } else if (foo == 1) {
            GenObsidian.genObsidian(world, x, y, z);
        } else if (foo == 2) {
            int bar = random.nextInt(100);
            if (bar < 15) {
                GenWither.genWither(world, x, y, z);
            } else {
                GenIron.genIron(world, x, y, z);
            }
        } else if (foo == 3) {
            int bar = random.nextInt(100);
            if (bar < 45) {
                GenOre.genOre(world, x, y, z);
            } else {
                GenBookshelf.genBookshelf(world, x, y, z);
            }
        }

    }

}