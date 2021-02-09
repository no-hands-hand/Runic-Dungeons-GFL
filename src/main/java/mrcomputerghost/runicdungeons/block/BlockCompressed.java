package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * @author ji_GGO
 * @date 4/1/2021
 */
public class BlockCompressed extends Block {

    public BlockCompressed() {
        super(Material.IRON);
        this.setUnlocalizedName("runicSteelBlock");
        this.setRegistryName(RunicDungeons.MODID, "runic_steel_block");
        this.setResistance(2.5F);
        this.setHardness(2.0F);
        this.setCreativeTab(RunicDungeons.TAB);
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return true;
    }

}