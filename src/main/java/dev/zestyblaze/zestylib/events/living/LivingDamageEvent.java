package dev.zestyblaze.zestylib.events.living;

import dev.zestyblaze.zestylib.events.ZLEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingDamageEvent {
    public static final Event<Hurt> HURT = EventFactory.createArrayBacked(Hurt.class, callbacks -> event -> {
        for (Hurt callback : callbacks) {
            callback.onHurt(event);
        }
    });

    @FunctionalInterface
    public interface Hurt {
        void onHurt(HurtEvent event);
    }

    public static class HurtEvent extends ZLEvent {
        public final LivingEntity damaged;
        public final DamageSource damageSource;
        public float damageAmount;

        public HurtEvent(LivingEntity damaged, DamageSource damageSource, float damageAmount) {
            this.damaged = damaged;
            this.damageSource = damageSource;
            this.damageAmount = damageAmount;
        }

        public LivingEntity getEntity() {
            return this.damaged;
        }

        public DamageSource getSource() {
            return this.damageSource;
        }

        public void setAmount(int amount) {
            this.damageAmount = amount;
        }
    }
}
