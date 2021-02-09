package mrcomputerghost.runicdungeons.advancements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

import java.util.Map;
import java.util.Set;

public class KeyTrigger implements ICriterionTrigger<KeyTrigger.Instance> {

    public static final ResourceLocation ID = new ResourceLocation(RunicDungeons.MODID, "use_key");
    private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<KeyTrigger.Instance> listener) {
        KeyTrigger.Listeners listeners = this.listeners.computeIfAbsent(playerAdvancementsIn, Listeners::new);
        listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<KeyTrigger.Instance> listener) {
        KeyTrigger.Listeners listeners = this.listeners.get(playerAdvancementsIn);
        if (listeners != null) {
            listeners.remove(listener);
            if (listeners.isEmpty()) {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }

    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
        this.listeners.remove(playerAdvancementsIn);
    }

    @Override
    public KeyTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        return new KeyTrigger.Instance();
    }

    public void trigger(EntityPlayerMP parPlayer) {
        KeyTrigger.Listeners listeners = this.listeners.get(parPlayer.getAdvancements());

        if (listeners != null) {
            listeners.trigger();
        }
    }

    public static class Instance extends AbstractCriterionInstance {

        public Instance() {
            super(KeyTrigger.ID);
        }

    }

    static class Listeners {

        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn) {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }

        public void add(ICriterionTrigger.Listener<KeyTrigger.Instance> listener) {
            this.listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<KeyTrigger.Instance> listener) {
            this.listeners.remove(listener);
        }

        public void trigger() {
            for (ICriterionTrigger.Listener<KeyTrigger.Instance> listener : Lists.newArrayList(this.listeners)) {
                listener.grantCriterion(this.playerAdvancements);
            }
        }
    }

}