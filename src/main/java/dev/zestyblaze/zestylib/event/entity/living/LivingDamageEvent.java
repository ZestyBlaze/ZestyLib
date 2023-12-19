package dev.zestyblaze.zestylib.event.entity.living;

import dev.zestyblaze.zestylib.event.ICancelableEvent;
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

    public static class HurtEvent extends LivingEvent implements ICancelableEvent {
        public final DamageSource source;
        public float amount;

        public HurtEvent(LivingEntity entity, DamageSource source, float amount) {
            super(entity);
            this.source = source;
            this.amount = amount;
        }

        public DamageSource getSource() {
            return this.source;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }
}
