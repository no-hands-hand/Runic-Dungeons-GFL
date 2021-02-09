package mrcomputerghost.runicdungeons.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

/**
 * @author ji_GGO
 * @date 27/12/2020
 */
public class TeleporterDungeon implements ITeleporter {

    @Override
    public void placeEntity(World world, Entity entity, float yaw) {

    }

    @Override
    public boolean isVanilla() {
        return true;
    }

}
