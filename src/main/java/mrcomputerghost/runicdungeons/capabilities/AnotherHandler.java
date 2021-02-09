package mrcomputerghost.runicdungeons.capabilities;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author jiGGO
 * @date 2020/8/4
 */
@Mod.EventBusSubscriber(modid = RunicDungeons.MODID)
public class AnotherHandler {

    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof EntityPlayer){
            ICapabilitySerializable<NBTTagCompound> provider = new CapabilityDungeon.ProvidePlayer();
            event.addCapability(new ResourceLocation(
                    RunicDungeons.MODID + ":runic_dungeons"), provider);
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
        Capability<IRuneDungeon> capability = CapabilityHandler.capability;
        Capability.IStorage<IRuneDungeon> storage = capability.getStorage();
        if(event.getOriginal().hasCapability(capability, null) && event.getEntityPlayer().hasCapability(capability, null)){
            NBTBase nbt = storage.writeNBT(capability, event.getOriginal().getCapability(capability, null), null);
            storage.readNBT(capability, event.getEntityPlayer().getCapability(capability, null), null, nbt);
        }
    }

}