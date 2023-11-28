package dev.zestyblaze.zestylib.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingDamageEvent {
    /**
     * Fired when an entity takes damage.
     */
    public static final Event<Hurt> HURT = EventFactory.createArrayBacked(Hurt.class, callbacks -> event -> {
        for (Hurt callback : callbacks) {
            callback.onHurt(event);
        }
    });

    @FunctionalInterface
    public interface Hurt {
        /**
         * Called when an entity is hurt. Listeners may change the damage amount, or cancel the damage entirely.
         */
        void onHurt(HurtEvent event);
    }

    public static class HurtEvent {
        public final LivingEntity damaged;
        public final DamageSource damageSource;
        public float damageAmount;

        public HurtEvent(LivingEntity damaged, DamageSource damageSource, float damageAmount) {
            this.damaged = damaged;
            this.damageSource = damageSource;
            this.damageAmount = damageAmount;
        }
    }
}
