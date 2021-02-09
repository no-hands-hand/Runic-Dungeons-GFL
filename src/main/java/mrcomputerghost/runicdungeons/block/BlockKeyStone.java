package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.advancements.RunicAdvancements;
import mrcomputerghost.runicdungeons.capabilities.CapabilityHandler;
import mrcomputerghost.runicdungeons.capabilities.IRuneDungeon;
import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockKeyStone extends Block {

    public BlockKeyStone() {
        super(Material.ROCK);
        this.setUnlocalizedName("runicKeyhole");
        this.setRegistryName(RunicDungeons.MODID, "key_stone");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(-1.0F);
        this.setResistance(-1.0F);
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        Random random = new Random();

        if (player.getHeldItem(hand).getItem() == RunicItems.KEY) {
            if (!world.isRemote) {
                IBlockState keystone = RunicBlocks.KEY_STONE.getDefaultState();
                IBlockState corridor = RunicBlocks.KEY_STONE.getDefaultState();
                Block bricks = RunicBlocks.BRICKS;
                boolean flag = false;

                if ((world.getBlockState(new BlockPos(x - 1, y, z)) == keystone ||
                        world.getBlockState(new BlockPos(x - 1, y, z)) == corridor) &&
                        facing == EnumFacing.SOUTH &&
                        world.getBlockState(new BlockPos(x + 1, y, z)).getBlock() == bricks &&
                        (world.getBlockState(new BlockPos(x, y, z - 1)) == keystone ||
                                world.getBlockState(new BlockPos(x, y, z - 1)) == corridor)) {

                    world.setBlockToAir(new BlockPos(x, y, z));
                    world.setBlockToAir(new BlockPos(x - 1, y, z));
                    world.setBlockToAir(new BlockPos(x - 2, y, z));
                    world.setBlockToAir(new BlockPos(x + 1, y, z));
                    world.setBlockToAir(new BlockPos(x, y + 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x - 2, y + 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x, y - 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x - 2, y - 1, z));
                    world.setBlockToAir(new BlockPos(x, y, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x - 2, y, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y, z - 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 2, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 2, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z - 1));
                    flag = true;
                } else if ((world.getBlockState(new BlockPos(x + 1, y, z)) == keystone ||
                        world.getBlockState(new BlockPos(x + 1, y, z)) == corridor) &&
                        facing == EnumFacing.SOUTH &&
                        world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == bricks &&
                        (world.getBlockState(new BlockPos(x, y, z - 1)) == keystone ||
                                world.getBlockState(new BlockPos(x, y, z - 1)) == corridor)) {

                    world.setBlockToAir(new BlockPos(x, y, z));
                    world.setBlockToAir(new BlockPos(x - 1, y, z));
                    world.setBlockToAir(new BlockPos(x + 1, y, z));
                    world.setBlockToAir(new BlockPos(x + 2, y, z));
                    world.setBlockToAir(new BlockPos(x, y + 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x + 2, y + 1, z));
                    world.setBlockToAir(new BlockPos(x, y - 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x + 2, y - 1, z));
                    world.setBlockToAir(new BlockPos(x, y, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y, z - 1));
                    world.setBlockToAir(new BlockPos(x + 2, y, z - 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 2, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 2, y - 1, z - 1));
                    flag = true;
                } else if ((world.getBlockState(new BlockPos(x, y, z - 1)) == keystone ||
                        world.getBlockState(new BlockPos(x, y, z - 1)) == corridor) &&
                        facing == EnumFacing.WEST &&
                        world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == bricks &&
                        (world.getBlockState(new BlockPos(x + 1, y, z)) == keystone ||
                                world.getBlockState(new BlockPos(x + 1, y, z)) == corridor)) {

                    world.setBlockToAir(new BlockPos(x, y, z));
                    world.setBlockToAir(new BlockPos(x, y, z + 1));
                    world.setBlockToAir(new BlockPos(x, y, z - 1));
                    world.setBlockToAir(new BlockPos(x, y, z - 2));
                    world.setBlockToAir(new BlockPos(x, y + 1, z));
                    world.setBlockToAir(new BlockPos(x, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z - 2));
                    world.setBlockToAir(new BlockPos(x, y - 1, z));
                    world.setBlockToAir(new BlockPos(x, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z - 2));
                    world.setBlockToAir(new BlockPos(x + 1, y, z));
                    world.setBlockToAir(new BlockPos(x + 1, y, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y, z - 2));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z - 2));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z - 2));
                    flag = true;
                } else if ((world.getBlockState(new BlockPos(x, y, z + 1)) == keystone ||
                        world.getBlockState(new BlockPos(x, y, z + 1)) == corridor) &&
                        facing == EnumFacing.WEST &&
                        world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == bricks &&
                        (world.getBlockState(new BlockPos(x + 1, y, z)) == keystone ||
                                world.getBlockState(new BlockPos(x + 1, y, z)) == corridor)) {

                    world.setBlockToAir(new BlockPos(x, y, z));
                    world.setBlockToAir(new BlockPos(x, y, z - 1));
                    world.setBlockToAir(new BlockPos(x, y, z + 1));
                    world.setBlockToAir(new BlockPos(x, y, z + 2));
                    world.setBlockToAir(new BlockPos(x, y + 1, z));
                    world.setBlockToAir(new BlockPos(x, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z + 2));
                    world.setBlockToAir(new BlockPos(x, y - 1, z));
                    world.setBlockToAir(new BlockPos(x, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z + 2));
                    world.setBlockToAir(new BlockPos(x + 1, y, z));
                    world.setBlockToAir(new BlockPos(x + 1, y, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y, z + 2));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z + 2));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z + 2));
                    flag = true;
                } else if ((world.getBlockState(new BlockPos(x + 1, y, z)) == keystone ||
                        world.getBlockState(new BlockPos(x + 1, y, z)) == corridor) &&
                        facing == EnumFacing.NORTH &&
                        world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == bricks &&
                        (world.getBlockState(new BlockPos(x, y, z + 1)) == keystone ||
                                world.getBlockState(new BlockPos(x, y, z + 1)) == corridor)) {

                    world.setBlockToAir(new BlockPos(x, y, z));
                    world.setBlockToAir(new BlockPos(x - 1, y, z));
                    world.setBlockToAir(new BlockPos(x + 1, y, z));
                    world.setBlockToAir(new BlockPos(x + 2, y, z));
                    world.setBlockToAir(new BlockPos(x, y + 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x + 2, y + 1, z));
                    world.setBlockToAir(new BlockPos(x, y - 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x + 2, y - 1, z));
                    world.setBlockToAir(new BlockPos(x, y, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y, z + 1));
                    world.setBlockToAir(new BlockPos(x + 2, y, z + 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 2, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 2, y - 1, z + 1));
                    flag = true;
                } else if ((world.getBlockState(new BlockPos(x - 1, y, z)) == keystone ||
                        world.getBlockState(new BlockPos(x - 1, y, z)) == corridor) &&
                        facing == EnumFacing.NORTH &&
                        world.getBlockState(new BlockPos(x + 1, y, z)).getBlock() == bricks &&
                        (world.getBlockState(new BlockPos(x, y, z + 1)) == keystone ||
                                world.getBlockState(new BlockPos(x, y, z + 1)) == corridor)) {

                    world.setBlockToAir(new BlockPos(x, y, z));
                    world.setBlockToAir(new BlockPos(x - 1, y, z));
                    world.setBlockToAir(new BlockPos(x - 2, y, z));
                    world.setBlockToAir(new BlockPos(x + 1, y, z));
                    world.setBlockToAir(new BlockPos(x, y + 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x - 2, y + 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x, y - 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x - 2, y - 1, z));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x, y, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y, z + 1));
                    world.setBlockToAir(new BlockPos(x - 2, y, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y, z + 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 2, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 2, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x + 1, y - 1, z + 1));
                    flag = true;
                } else if ((world.getBlockState(new BlockPos(x, y, z + 1)) == keystone ||
                        world.getBlockState(new BlockPos(x, y, z + 1)) == corridor) &&
                        facing == EnumFacing.EAST &&
                        world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == bricks &&
                        (world.getBlockState(new BlockPos(x - 1, y, z)) == keystone ||
                                world.getBlockState(new BlockPos(x - 1, y, z)) == corridor)) {

                    world.setBlockToAir(new BlockPos(x, y, z));
                    world.setBlockToAir(new BlockPos(x, y, z - 1));
                    world.setBlockToAir(new BlockPos(x, y, z + 1));
                    world.setBlockToAir(new BlockPos(x, y, z + 2));
                    world.setBlockToAir(new BlockPos(x, y + 1, z));
                    world.setBlockToAir(new BlockPos(x, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z + 2));
                    world.setBlockToAir(new BlockPos(x, y - 1, z));
                    world.setBlockToAir(new BlockPos(x, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z + 2));
                    world.setBlockToAir(new BlockPos(x - 1, y, z));
                    world.setBlockToAir(new BlockPos(x - 1, y, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y, z + 2));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z + 2));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z + 2));
                    flag = true;
                } else if ((world.getBlockState(new BlockPos(x, y, z - 1)) == keystone ||
                        world.getBlockState(new BlockPos(x, y, z - 1)) == corridor) &&
                        facing == EnumFacing.EAST &&
                        world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == bricks &&
                        (world.getBlockState(new BlockPos(x - 1, y, z)) == keystone ||
                                world.getBlockState(new BlockPos(x - 1, y, z)) == corridor)) {

                    world.setBlockToAir(new BlockPos(x, y, z));
                    world.setBlockToAir(new BlockPos(x, y, z + 1));
                    world.setBlockToAir(new BlockPos(x, y, z - 1));
                    world.setBlockToAir(new BlockPos(x, y, z - 2));
                    world.setBlockToAir(new BlockPos(x, y + 1, z));
                    world.setBlockToAir(new BlockPos(x, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y + 1, z - 2));
                    world.setBlockToAir(new BlockPos(x, y - 1, z));
                    world.setBlockToAir(new BlockPos(x, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x, y - 1, z - 2));
                    world.setBlockToAir(new BlockPos(x - 1, y, z));
                    world.setBlockToAir(new BlockPos(x - 1, y, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y, z - 2));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y + 1, z - 2));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z + 1));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z - 1));
                    world.setBlockToAir(new BlockPos(x - 1, y - 1, z - 2));
                    flag = true;
                } else {
                    player.sendMessage(new TextComponentTranslation("messages.key_stone.error"));
                }

                if (flag && !player.capabilities.isCreativeMode) {
                    ItemStack itemStack = player.getHeldItem(hand);
                    if (itemStack.getItem() == RunicItems.KEY) {
                        itemStack.shrink(1);
                    }
                }

                if (flag){
                    if(player.hasCapability(CapabilityHandler.capability, null)) {
                        IRuneDungeon dungeon = player.getCapability(CapabilityHandler.capability, null);
                        dungeon.setScore(dungeon.getScore() + 1);
                        RunicAdvancements.KEY_TRIGGER.trigger((EntityPlayerMP) player);
                    }
                }

                return flag;
            }
        } else if (!world.isRemote) {
            if (hand == EnumHand.MAIN_HAND) {
                player.sendMessage(new TextComponentTranslation("messages.key_stone.key"));
            }
        }

        return false;
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

}