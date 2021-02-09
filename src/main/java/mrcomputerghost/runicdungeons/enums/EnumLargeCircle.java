package mrcomputerghost.runicdungeons.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

/**
 * @author ji_GGO
 * @date 19/1/2021
 */
public enum EnumLargeCircle implements IStringSerializable {

    TOP_LEFT,
    TOP,
    TOP_RIGHT,
    LEFT,
    RIGHT,
    BOTTOM_LEFT,
    BOTTOM,
    BOTTOM_RIGHT;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

}