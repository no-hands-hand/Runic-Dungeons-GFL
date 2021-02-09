package mrcomputerghost.runicdungeons.enums;

import java.util.Locale;
import net.minecraft.util.IStringSerializable;

/**
 * 状态枚举
 *
 * @author GGO
 * @date 2019/6/26
 */
public enum EnumBricks implements IStringSerializable {

    PLAIN,
    MOSSY,
    CRACKED,
    RUNEPLAIN,
    RUNEMOSSY,
    RUNECRACKED;

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