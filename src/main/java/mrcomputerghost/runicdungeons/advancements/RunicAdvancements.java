package mrcomputerghost.runicdungeons.advancements;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ji_GGO
 * @date 12/1/2021
 */
public class RunicAdvancements {

    public static final KeyTrigger KEY_TRIGGER = CriteriaTriggers.register(new KeyTrigger());

    public static void init() {
        Method method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register",
                "func_192118_a", ICriterionTrigger.class);
        method.setAccessible(true);
        try {
            method.invoke(null, KEY_TRIGGER);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

        }
    }

}