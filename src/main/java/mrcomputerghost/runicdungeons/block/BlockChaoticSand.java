package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

public class BlockChaoticSand extends BlockFalling {

    public BlockChaoticSand() {
        super(Material.SAND);
        this.setUnlocalizedName("chaoticSand");
        this.setRegistryName(RunicDungeons.MODID, "chaotic_sand");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(0.7816F);
        this.setResistance(12.0F);
        this.setHarvestLevel("shovel", 1);
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
        Random random = new Random();
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        if (world.isAirBlock(pos.up(2))) {
            world.setBlockToAir(pos);
            world.setBlockState(pos.up(2), this.getDefaultState());
            world.spawnParticle(EnumParticleTypes.PORTAL, x, y, z, 0.0D + random.nextFloat(), 1.3D, 0.0D + random.nextFloat());
        } else if (world.isAirBlock(pos.up(1))) {
            world.setBlockToAir(pos);
            world.setBlockState(pos.up(1), this.getDefaultState());
            world.spawnParticle(EnumParticleTypes.PORTAL, x, y, z, 0.0D + random.nextFloat(), 1.3D, 0.0D + random.nextFloat());
        }
    }

}