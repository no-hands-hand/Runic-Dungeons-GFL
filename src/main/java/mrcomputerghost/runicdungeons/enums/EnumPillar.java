package mrcomputerghost.runicdungeons.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

/**
 * @author ji_GGO
 * @date 4/1/2021
 */
public enum  EnumPillar implements IStringSerializable {

    NORMAL,
    UP,
    CENTER,
    DOWN;

    /**
     * 获取枚举小写名称
     * @return 名称
     */
    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    /**
     * 获取ordinal值
     * @return string类型的ordinal
     */
    public String ordinalString(){
        return String.valueOf(super.ordinal());
    }

}
