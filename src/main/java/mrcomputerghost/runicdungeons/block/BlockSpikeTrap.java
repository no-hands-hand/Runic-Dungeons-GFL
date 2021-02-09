package mrcomputerghost.runicdungeons.block;

import com.google.common.collect.Lists;
import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockSpikeTrap extends Block {

    public static List<Item> boots = Lists.newArrayList();

    public BlockSpikeTrap() {
        super(Material.ROCK);
        this.setUnlocalizedName("spikeTrap");
        this.setRegistryName(RunicDungeons.MODID,"spike_trap");
        this.setResistance(-1.0F);
        this.setHardness(-1.0F);
        this.setCreativeTab(RunicDungeons.TAB);
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            ItemStack itemStack = player.getArmorInventoryList().iterator().next();
            if (itemStack != null) {
                if (!boots.contains(itemStack.getItem())){
                    entity.attackEntityFrom(DamageSource.CACTUS, 1.2F);
                }
            } else {
                entity.attackEntityFrom(DamageSource.CACTUS, 1.2F);
            }
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {}

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return false;
    }

}