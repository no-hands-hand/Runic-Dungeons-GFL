package mrcomputerghost.runicdungeons.handler;

import baubles.api.BaublesApi;
import com.google.common.collect.Maps;
import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.block.RunicBlocks;
import mrcomputerghost.runicdungeons.entity.EntityDungeonGuardian;
import mrcomputerghost.runicdungeons.item.RunicItems;
import mrcomputerghost.runicdungeons.world.gen.GenOre;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.EnumHand;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@Mod.EventBusSubscriber(modid = RunicDungeons.MODID)
public class RunicEventHandler {

    private static Map<BlockPos, WeightedSpawnerEntity> map = Maps.newHashMap();

    private static final Logger LOGGER = RunicDungeons.LOGGER;

    @SubscribeEvent
    public void livingSpawn(final LivingSpawnEvent event) {
        Random rand = new Random();
        int strong = rand.nextInt(100), speed = rand.nextInt(100);
        EntityLivingBase entity = event.getEntityLiving();
        if (strong < 25 && (entity instanceof EntityZombie || entity instanceof AbstractSkeleton) && entity.dimension == RunicDungeons.ID) {
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 999, 1));
        }
        if (speed < 25 && (entity instanceof EntityZombie || entity instanceof AbstractSkeleton) && entity.dimension == RunicDungeons.ID) {
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 999, 1));
        }
        if (entity instanceof EntityDungeonGuardian && entity.dimension == RunicDungeons.ID) {
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 1000000, 1));
        }
    }

    //@SubscribeEvent
    public void specialSpawn(final LivingSpawnEvent.SpecialSpawn event){
        Random rand = new Random();
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntitySkeleton && entity.dimension == RunicDungeons.ID && rand.nextInt(100) < 15) {
            MobSpawnerBaseLogic logic = event.getSpawner();
            BlockPos blockpos = logic.getSpawnerPosition();
            if (!map.containsKey(blockpos)) {
                Field field = ReflectionHelper.findField(MobSpawnerBaseLogic.class, "spawnData", "field_98282_f");
                field.setAccessible(true);
                WeightedSpawnerEntity weighted = null;
                try {
                    weighted = (WeightedSpawnerEntity) field.get(logic);
                } catch (IllegalAccessException e) {
                    LOGGER.info(e.getMessage());
                }
                if (weighted != null) {
                    map.put(blockpos, weighted);
                    NBTTagCompound nbttagcompound = weighted.getNbt();
                    NBTTagList nbttaglist = nbttagcompound.getTagList("Pos", 6);
                    World world = event.getWorld();
                    int j = nbttaglist.tagCount();
                    double x = j >= 1 ? nbttaglist.getDoubleAt(0) : (double)blockpos.getX() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double)4 + 0.5D;
                    double y = j >= 2 ? nbttaglist.getDoubleAt(1) : (double)(blockpos.getY() + world.rand.nextInt(3) - 1);
                    double z = j >= 3 ? nbttaglist.getDoubleAt(2) : (double)blockpos.getZ() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double)4 + 0.5D;
                    EntityWitherSkeleton wither = new EntityWitherSkeleton(world);
                    wither.setPositionAndUpdate(x, y, z);
                    wither.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, world.rand.nextFloat() * 360.0F, 0.0F);
                    wither.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
                    AnvilChunkLoader.spawnEntity(wither, world);
                    ForgeEventFactory.canEntitySpawn(wither, event.getWorld(), (float)x, (float)y, (float)z, false);
                    event.setCanceled(true);
                }
            } else {
                WeightedSpawnerEntity weighted = map.get(blockpos);
                NBTTagCompound nbttagcompound = weighted.getNbt();
                NBTTagList nbttaglist = nbttagcompound.getTagList("Pos", 6);
                World world = event.getWorld();
                int j = nbttaglist.tagCount();
                double x = j >= 1 ? nbttaglist.getDoubleAt(0) : (double)blockpos.getX() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double)4 + 0.5D;
                double y = j >= 2 ? nbttaglist.getDoubleAt(1) : (double)(blockpos.getY() + world.rand.nextInt(3) - 1);
                double z = j >= 3 ? nbttaglist.getDoubleAt(2) : (double)blockpos.getZ() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double)4 + 0.5D;
                EntityWitherSkeleton wither = new EntityWitherSkeleton(world);
                wither.setPositionAndUpdate(x, y, z);
                wither.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, world.rand.nextFloat() * 360.0F, 0.0F);
                wither.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
                AnvilChunkLoader.spawnEntity(wither, world);
                ForgeEventFactory.canEntitySpawn(wither, event.getWorld(), (float)x, (float)y, (float)z, false);
                event.setCanceled(true);
            }
        }
    }

    @Optional.Method(modid = "baubles")
    @SubscribeEvent
    public void fixFly(final EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (BaublesApi.getBaubles(player) != null &&
                    BaublesApi.getBaubles(player).getStackInSlot(3) != null &&
                    BaublesApi.getBaubles(player).getStackInSlot(3).equals(new ItemStack(RunicItems.BELT_FLY))){
                player.capabilities.allowFlying = true;
            }
        }
    }

    @SubscribeEvent
    public void checkSpawn(final LivingSpawnEvent.CheckSpawn event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityDungeonGuardian && entity.dimension != RunicDungeons.ID) {
            event.setResult(Event.Result.DENY);
        }
        if (entity.dimension == RunicDungeons.ID) {
            if (entity.posY > 4.0D) {
                event.setResult(Event.Result.DENY);
            }
        }
    }

    @SubscribeEvent
    public void bonemeal(final BonemealEvent event) {
        if (event.getEntityPlayer() != null && event.getEntityPlayer().dimension == RunicDungeons.ID) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void livingDrops(final LivingDropsEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntityWither &&
                entity.dimension == RunicDungeons.ID &&
                event.getSource().getImmediateSource() instanceof EntityPlayer) {
            event.getDrops().add(entity.dropItem(RunicItems.KEY, 1));
        }
    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (player.dimension == RunicDungeons.ID) {
            if ((player.posY > 9.0D + 0 || player.posY <= 0.0D) && !player.capabilities.isCreativeMode) {
                player.setPositionAndUpdate(7.0D, 4.0D, 7.0D);
            }
            if (!player.capabilities.isCreativeMode) {
                player.capabilities.isFlying = false;
            }
        }
    }

    @SubscribeEvent
    public void enderTeleport(final EnderTeleportEvent event) {
        if (event.getEntityLiving().dimension == RunicDungeons.ID) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void blockBreak(final BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        IBlockState outerBlock = Blocks.STONEBRICK.getDefaultState();
        IBlockState innerBlock = Blocks.STONEBRICK.getStateFromMeta(3);
        boolean dimension = player.dimension == RunicDungeons.ID;
        Block block = event.getState().getBlock();

        if (block == RunicBlocks.RUNE_CIRCLE) {
            BlockPos pos = event.getPos();
            int x = pos.getX(), y = pos.getY() - 1, z = pos.getZ();
            World world = event.getWorld();
            if (!dimension) {
                world.setBlockToAir(pos.add(3, 0, 3));
                world.setBlockToAir(pos.add(3, 0, 0));
                world.setBlockToAir(pos.add(0, 0, 3));
                world.setBlockState(new BlockPos(x, y, z), outerBlock, 2);
                world.setBlockState(new BlockPos(x, y, z + 1), outerBlock, 2);
                world.setBlockState(new BlockPos(x, y, z + 2), outerBlock, 2);
                world.setBlockState(new BlockPos(x, y, z + 3), outerBlock, 2);
                world.setBlockState(new BlockPos(x + 1, y, z), outerBlock, 2);
                world.setBlockState(new BlockPos(x + 1, y, z + 1), innerBlock, 2);
                world.setBlockState(new BlockPos(x + 1, y, z + 2), innerBlock, 2);
                world.setBlockState(new BlockPos(x + 1, y, z + 3), outerBlock, 2);
                world.setBlockState(new BlockPos(x + 2, y, z), outerBlock, 2);
                world.setBlockState(new BlockPos(x + 2, y, z + 1), innerBlock, 2);
                world.setBlockState(new BlockPos(x + 2, y, z + 2), innerBlock, 2);
                world.setBlockState(new BlockPos(x + 2, y, z + 3), outerBlock, 2);
                world.setBlockState(new BlockPos(x + 3, y, z), outerBlock, 2);
                world.setBlockState(new BlockPos(x + 3, y, z + 1), outerBlock, 2);
                world.setBlockState(new BlockPos(x + 3, y, z + 2), outerBlock, 2);
                world.setBlockState(new BlockPos(x + 3, y, z + 3), outerBlock, 2);
                EntityItem item = new EntityItem(world, x, y + 1, z);
                item.setItem(new ItemStack(RunicItems.MAGICAL_STAFF));
                world.spawnEntity(item);
            } else if (!player.capabilities.isCreativeMode) {
                event.setCanceled(true);
            }
        }

        if (block == RunicBlocks.SMALL_CIRCLE) {
            BlockPos pos = event.getPos();
            int x = pos.getX() + 1, y = pos.getY() - 1, z = pos.getZ() + 1;
            World world = event.getWorld();
            world.setBlockState(new BlockPos(x, y, z), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 1, y, z), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 1, y, z + 1), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 1, y, z - 1), outerBlock, 2);
            world.setBlockState(new BlockPos(x - 1, y, z), outerBlock, 2);
            world.setBlockState(new BlockPos(x - 1, y, z + 1), outerBlock, 2);
            world.setBlockState(new BlockPos(x - 1, y, z - 1), outerBlock, 2);
            world.setBlockState(new BlockPos(x, y, z + 1), outerBlock, 2);
            world.setBlockState(new BlockPos(x, y, z - 1), outerBlock, 2);
            EntityItem item = new EntityItem(world, x - 1, y + 1, z - 1);
            item.setItem(new ItemStack(RunicItems.MAGICAL_STAFF));
            world.spawnEntity(item);
        }

        if (block == RunicBlocks.LARGE_CIRCLE) {
            BlockPos pos = event.getPos();
            int x = pos.getX(), y = pos.getY() - 1, z = pos.getZ();
            World world = event.getWorld();
            world.setBlockToAir(pos.add(0, 0, 2));
            world.setBlockToAir(pos.add(0, 0, 4));
            world.setBlockToAir(pos.add(2, 0, 0));
            world.setBlockToAir(pos.add(2, 0, 4));
            world.setBlockToAir(pos.add(4, 0, 0));
            world.setBlockToAir(pos.add(4, 0, 2));
            world.setBlockToAir(pos.add(4, 0, 4));
            world.setBlockState(new BlockPos(x, y, z), outerBlock, 2);
            world.setBlockState(new BlockPos(x, y, z + 1), outerBlock, 2);
            world.setBlockState(new BlockPos(x, y, z + 2), outerBlock, 2);
            world.setBlockState(new BlockPos(x, y, z + 3), outerBlock, 2);
            world.setBlockState(new BlockPos(x, y, z + 4), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 1, y, z), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 1, y, z + 1), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 1, y, z + 2), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 1, y, z + 3), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 1, y, z + 4), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 2, y, z), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 2, y, z + 1), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 2, y, z + 2), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 2, y, z + 3), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 2, y, z + 4), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 3, y, z), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 3, y, z + 1), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 3, y, z + 2), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 3, y, z + 3), innerBlock, 2);
            world.setBlockState(new BlockPos(x + 3, y, z + 4), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 4, y, z), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 4, y, z + 1), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 4, y, z + 2), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 4, y, z + 3), outerBlock, 2);
            world.setBlockState(new BlockPos(x + 4, y, z + 4), outerBlock, 2);
            EntityItem item = new EntityItem(world, x, y + 1, z);
            item.setItem(new ItemStack(RunicItems.MAGICAL_STAFF));
            world.spawnEntity(item);
        }

        if (dimension && !player.capabilities.isCreativeMode) {
            String ores = Arrays.toString(GenOre.oreNames);
            if (ores.contains(block.getRegistryName().toString())) {
                Random rand = new Random();
                BlockPos pos = event.getPos();
                if (rand.nextInt(100) < 35) {
                    event.getPlayer().getEntityWorld().createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 2.0F, true);
                }
            }
        }

        if (dimension) {
            if (block instanceof BlockMobSpawner) {
                map.remove(event.getPos());
            }
        }
    }

}