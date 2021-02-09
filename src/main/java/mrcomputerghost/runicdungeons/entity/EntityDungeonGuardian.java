package mrcomputerghost.runicdungeons.entity;

import com.google.common.collect.Lists;
import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.block.RunicBlocks;
import mrcomputerghost.runicdungeons.item.RunicItems;
import mrcomputerghost.runicdungeons.loot.RunicLootTables;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class EntityDungeonGuardian extends EntityGolem {

    private static final List<Item> DROPS = Lists.newArrayList(RunicItems.TRAP_DISABLER,
            RunicItems.MAGIC_CHALK, Item.getItemFromBlock(RunicBlocks.CHAOTIC_SAND),
            Item.getItemFromBlock(RunicBlocks.WITHER_BRICKS), Item.getItemFromBlock(RunicBlocks.SURVIVAL_RUNIC_LAMP),
            Item.getItemFromBlock(RunicBlocks.WITHER_RUNIC_GLASS));

    private int attackTimer;

    public EntityDungeonGuardian(World world) {
        super(world);
        this.setSize(1.4F, 2.9F);
        this.setHealth(200.0F);
        this.experienceValue = 50;
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.6D));
        //this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 1, false, true, null));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        //this.dataManager.register(PLAYER_CREATED, Byte.valueOf((byte)1));
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(420.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    @Override
    protected int decreaseAirSupply(int air) {
        return 0;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.attackTimer > 0) {
            this.attackTimer--;
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0) {
            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY - 0.20000000298023224D);
            int k = MathHelper.floor(this.posZ);
            IBlockState iblockstate = this.world.getBlockState(new BlockPos(i, j, k));

            if (iblockstate.getMaterial() != Material.AIR) {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D, Block.getStateId(iblockstate));
            }
        }
    }

    @Override
    public boolean canAttackClass(Class<? extends EntityLivingBase> cls) {
        return true;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        this.attackTimer = 20;
        this.world.setEntityState(this, (byte)4);
        float multiplier = 1.0F;
        if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            multiplier = 0.0F;
        }
        if (this.world.getDifficulty() == EnumDifficulty.EASY) {
            multiplier = 0.5F;
        }
        if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
            multiplier = 1.0F;
        }
        if (this.world.getDifficulty() == EnumDifficulty.HARD) {
            multiplier = 1.5F;
        }
        List players = this.world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(50.0D, 100.0D, 50.0D));
        multiplier = (float)(multiplier * players.size() * 0.75D);
        boolean flag;
        if (multiplier > 0) {
            flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3 + ((this.rand.nextInt(7) + 1) * multiplier));
        } else {
            flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 0);
        }
        if (flag) {
            entityIn.motionY += 0.4000000059604645D;
        }
        playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @SideOnly(Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

    @Override
    public int getTotalArmorValue() {
        return 4;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
        this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextFloat() - 0.5D) * this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D, (this.rand.nextFloat() - 0.5D) * 4.0D);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
    }

    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 16) {
            this.world.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.MASTER, 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @Nullable
    @Override
    protected Item getDropItem() {
        return this.dimension == RunicDungeons.ID ? RunicItems.KEY : super.getDropItem();
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        Item item = this.getDropItem();
        if (item != null) {
            this.dropItem(item, 1);
        }
        for (int j = this.rand.nextInt(4) + 1, k = 0; k < j; k++) {
            Item drop = DROPS.get(this.rand.nextInt(DROPS.size()));
            this.dropItem(drop, 1);
        }
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        super.dropLoot(wasRecentlyHit, lootingModifier, source);
        this.dropFewItems(wasRecentlyHit, lootingModifier);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return RunicLootTables.GUARDIAN;
    }

}