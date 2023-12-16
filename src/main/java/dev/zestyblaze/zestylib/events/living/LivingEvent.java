package dev.zestyblaze.zestylib.events.living;

import dev.zestyblaze.zestylib.events.ICancelableEvent;
import dev.zestyblaze.zestylib.events.ZLEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.LivingEntity;

public class LivingEvent extends ZLEvent {
    private final LivingEntity livingEntity;

    public LivingEvent(LivingEntity entity) {
        this.livingEntity = entity;
    }

    public LivingEntity getEntity() {
        return livingEntity;
    }

    public static final Event<Tick> TICK = EventFactory.createArrayBacked(Tick.class, callbacks -> event -> {
        for (Tick callback : callbacks) {
            callback.onTick(event);
        }
    });

    @FunctionalInterface
    public interface Tick {
        void onTick(LivingTickEvent event);
    }

    public static class LivingTickEvent extends LivingEvent implements ICancelableEvent {
        public LivingTickEvent(LivingEntity entity) {
            super(entity);
        }
    }
}
