package mrcomputerghost.runicdungeons.world.gen;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Random;

/**
 * @author ji_GGO
 * @date 6/1/2021
 */
public class GenOre extends GenStone {

    public static String[] oreNames = new String[] { "minecraft:gold_ore:27", "minecraft:iron_ore:80", "minecraft:coal_ore:45", "minecraft:lapis_ore:30", "minecraft:diamond_ore:5", "minecraft:redstone_ore:25", "minecraft:emerald_ore:2", "minecraft:quartz_ore:15" };

    public static Block getRandomOre() {
        double total = 0.0D;
        for (String s : oreNames)
            total += Integer.parseInt(s.split(":")[2]);
        int randomIndex;
        double random;
        int i;
        for (randomIndex = -1, random = Math.random() * total, i = 0; i < oreNames.length; i++) {
            random -= Integer.parseInt(oreNames[i].split(":")[2]);
            if (random <= 0.0D) {
                randomIndex = i;
                break;
            }
        }
        ResourceLocation resource = new ResourceLocation(oreNames[randomIndex].split(":")[0],
                oreNames[randomIndex].split(":")[1]);
        return ForgeRegistries.BLOCKS.getValue(resource);
    }

    @Override
    public void gen(World world, int x, int y, int z) {
        GenOre.genOre(world, x, y, z);
    }

    public static void genOre(World world, int x, int y, int z) {
        Random random = new Random();
        genStone(world, x, y, z);
        for (int f = random.nextInt(10) + 5; f > 0;) {
            BlockPos pos = new BlockPos(x, y, z);
            pos = pos.add(6 + random.nextInt(6), 0, 6 + random.nextInt(6));
            safeSetBlockState(world, pos.up(2), getRandomOre().getDefaultState());
            if (random.nextInt(3) == 1) {
                safeSetBlockState(world, pos.up(3), getRandomOre().getDefaultState());
            }
            f--;
        }
        safeSetBlockState(world, new BlockPos(x + 7, y + 2, z + 7), getRandomOre().getDefaultState());
        generateDropper(world, x + 7, y + 3, z + 7);
    }

}