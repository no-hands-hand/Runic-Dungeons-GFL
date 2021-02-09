package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.entity.EntityDungeonGuardian;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class BlockRunicSpawner extends Block {

    public BlockRunicSpawner() {
        super(Material.ROCK);
        this.setUnlocalizedName("runeSpawner");
        this.setRegistryName(RunicDungeons.MODID, "rune_spawner");
        setCreativeTab(RunicDungeons.TAB);
        setHardness(-1.0F);
        setResistance(-1.0F);
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (player.dimension == RunicDungeons.ID && !world.isRemote && !player.isSneaking()) {
            EntityDungeonGuardian guardian = new EntityDungeonGuardian(world);
            guardian.setPosition(pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(guardian);
            ForgeEventFactory.canEntitySpawn(guardian, world, pos.getX(), pos.getY(), pos.getZ(), false);
            world.setBlockToAir(pos);
            return true;
        }
        return false;
    }

}