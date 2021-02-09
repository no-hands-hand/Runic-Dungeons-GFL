package mrcomputerghost.runicdungeons.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * @author jiGGO
 * @date 2020/8/4
 */
public class CapabilityHandler {

    @CapabilityInject(IRuneDungeon.class)
    public static Capability<IRuneDungeon> capability;

    public static void setupCapabilities(){
        CapabilityManager.INSTANCE.register(IRuneDungeon.class, new CapabilityDungeon.Storage(), CapabilityDungeon.Implementation.class);
    }

}