package mrcomputerghost.runicdungeons.entity;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = RunicDungeons.MODID)
public class RunicEntities {

    @SubscribeEvent
    public static void onEntityRegister(final RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(EntityEntryBuilder.create()
                .entity(EntityDungeonGuardian.class)
                .id(new ResourceLocation(RunicDungeons.MODID, "dungeon_guardian"), 233)
                .name("DungeonGuardian")
                .egg(10, 0)
                .tracker(40, 3, true)
                .build()
        );
    }

}