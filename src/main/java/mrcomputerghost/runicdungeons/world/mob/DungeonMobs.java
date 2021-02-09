package mrcomputerghost.runicdungeons.world.mob;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Random;

/**
 * @author ji_GGO
 * @date 5/1/2021
 */
public class DungeonMobs {

    public static final List<String> MOBS = Lists.newArrayList(
            "minecraft:zombie", "minecraft:skeleton", "minecraft:spider");

    public static ResourceLocation getRandEntity() {
        Random random = new Random();
        return new ResourceLocation(MOBS.get(random.nextInt(MOBS.size())));
    }

}