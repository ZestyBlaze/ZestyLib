package dev.zestyblaze.zestylib.event.entity.living;

import dev.zestyblaze.zestylib.event.ICancelableEvent;
import dev.zestyblaze.zestylib.event.ZLEvent;
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

    // TODO: LivingJumpEvent
    // https://github.com/neoforged/NeoForge/blob/1.20.x/src/main/java/net/neoforged/neoforge/event/entity/living/LivingEvent.java#L69
}
