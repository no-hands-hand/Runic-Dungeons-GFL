package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.capabilities.CapabilityHandler;
import mrcomputerghost.runicdungeons.capabilities.IRuneDungeon;
import mrcomputerghost.runicdungeons.dimension.TeleporterDungeon;
import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * @author ji_GGO
 * @date 27/12/2020
 */
public class BlockRunicPortal extends Block {

    public BlockRunicPortal() {
        super(Material.ROCK);
        this.setUnlocalizedName("runicPortal");
        this.setRegistryName(RunicDungeons.MODID,"portal");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(-1.0F);
        this.setResistance(-1.0F);
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (player.dimension != RunicDungeons.ID) {
                if(player.hasCapability(CapabilityHandler.capability, null)) {
                    IRuneDungeon dungeon = player.getCapability(CapabilityHandler.capability, null);
                    dungeon.setPosValue(pos.up());
                    dungeon.setDimension(player.dimension);
                    if (dungeon.getScore() == 0) {
                        player.addItemStackToInventory(new ItemStack(RunicItems.KEY));
                    }
                }
                player.changeDimension(RunicDungeons.ID, new TeleporterDungeon());
                player.setPositionAndUpdate(7.0D, 3, 7.0D);
                player.setSpawnChunk(new BlockPos(7.0D, 3, 7.0D), true, RunicDungeons.ID);
            } else {
                boolean flag = false;
                if(player.hasCapability(CapabilityHandler.capability, null)) {
                    IRuneDungeon dungeon = player.getCapability(CapabilityHandler.capability, null);
                    int dimension = dungeon.getDimension();
                    BlockPos p = dungeon.getPosValue();
                    if (p != BlockPos.ORIGIN) {
                        player.changeDimension(dimension, new TeleporterDungeon());
                        player.setPositionAndUpdate(p.getX(), p.getY(), p.getZ());
                    } else {
                        flag = true;
                    }
                } else {
                    flag = true;
                }
                if (flag) {
                    player.changeDimension(0, new TeleporterDungeon());
                    WorldServer server = player.getServer().getWorld(0);
                    BlockPos pos1 = server.getTopSolidOrLiquidBlock(server.getSpawnPoint());
                    player.moveToBlockPosAndAngles(pos1, player.rotationYaw, player.rotationPitch);
                }
            }
        }

        return true;
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
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x, y, z, 0.0D + rand.nextFloat(), 1.3D, 0.0D + rand.nextFloat());
        world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x, y, z, 0.0D + rand.nextFloat(), 1.3D, 0.0D + rand.nextFloat());
    }

}