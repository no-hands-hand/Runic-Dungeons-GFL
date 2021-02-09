package mrcomputerghost.runicdungeons.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

/**
 * @author ji_GGO
 * @date 17/1/2021
 */
public enum EnumCircle implements IStringSerializable {

    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

}