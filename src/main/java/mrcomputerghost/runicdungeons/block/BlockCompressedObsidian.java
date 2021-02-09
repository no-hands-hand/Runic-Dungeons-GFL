package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockCompressedObsidian extends BlockObsidian {

    public BlockCompressedObsidian() {
        super();
        this.setHardness(16.0F);
        this.setResistance(32.0F);
        this.setRegistryName(RunicDungeons.MODID, "compressed_obsidian");
        this.setUnlocalizedName("compressedObsidian");
        this.setCreativeTab(RunicDungeons.TAB);
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

}