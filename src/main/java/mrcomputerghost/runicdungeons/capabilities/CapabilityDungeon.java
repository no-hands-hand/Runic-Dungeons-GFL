package mrcomputerghost.runicdungeons.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author jiGGO
 * @date 2020/8/4
 */
public class CapabilityDungeon {

    public static class Storage implements Capability.IStorage<IRuneDungeon>{

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IRuneDungeon> capability, IRuneDungeon instance, EnumFacing side) {
            NBTTagCompound compound = new NBTTagCompound();
            BlockPos pos = instance.getPosValue();
            compound.setLong("position", pos.toLong());
            compound.setInteger("dimension", instance.getDimension());
            compound.setInteger("score", instance.getScore());
            return compound;
        }

        @Override
        public void readNBT(Capability<IRuneDungeon> capability, IRuneDungeon instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound compound =(NBTTagCompound) nbt;
            BlockPos pos = BlockPos.fromLong(compound.getLong("position"));
            instance.setPosValue(pos);
            instance.setDimension(compound.getInteger("dimension"));
            instance.setScore(compound.getInteger("score"));
        }

    }

    public static class Implementation implements IRuneDungeon{

        Integer dimension = 0;

        BlockPos pos = BlockPos.ORIGIN;

        Integer score = 0;

        @Override
        public int getDimension() {
            return dimension;
        }

        @Override
        public void setDimension(int id) {
            this.dimension = id;
        }

        @Override
        public BlockPos getPosValue() {
            return pos;
        }

        @Override
        public void setPosValue(BlockPos pos) {
            this.pos = pos;
        }

        @Override
        public int getScore() {
            return score;
        }

        @Override
        public void setScore(int score) {
            this.score = score;
        }

    }

    public static class ProvidePlayer implements ICapabilitySerializable<NBTTagCompound>, ICapabilityProvider {

        @CapabilityInject(IRuneDungeon.class)
        private IRuneDungeon dungeon = new Implementation();

        private Capability.IStorage<IRuneDungeon> storage = CapabilityHandler.capability.getStorage();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return CapabilityHandler.capability.equals(capability);
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if(CapabilityHandler.capability.equals(capability)){
                return (T) dungeon;
            }
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) storage.writeNBT(CapabilityHandler.capability, dungeon, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            storage.readNBT(CapabilityHandler.capability, dungeon, null, nbt);
        }

    }

}