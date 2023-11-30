package dev.zestyblaze.zestylib.events.living;

import dev.zestyblaze.zestylib.events.ZLEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.LivingEntity;

public class LivingChangeTargetEvent {
    public static final Event<ChangeTarget> CHANGE_TARGET = EventFactory.createArrayBacked(ChangeTarget.class, callbacks -> (event) -> {
        for (ChangeTarget callback : callbacks) {
            callback.onChangeTarget(event);
        }
    });

    @FunctionalInterface
    public interface ChangeTarget {
        void onChangeTarget(ChangeTargetEvent event);
    }

    public static class ChangeTargetEvent extends ZLEvent {
        private final LivingEntity entity;
        private final LivingEntity originalTarget;
        private LivingEntity newTarget;

        public ChangeTargetEvent(LivingEntity entity, LivingEntity originalTarget) {
            this.entity = entity;
            this.originalTarget = originalTarget;
            this.newTarget = originalTarget;
        }

        public LivingEntity getEntity() {
            return this.entity;
        }

        public LivingEntity getNewTarget() {
            return this.newTarget;
        }

        public void setNewTarget(LivingEntity newTarget) {
            this.newTarget = newTarget;
        }

        public LivingEntity getOriginalTarget() {
            return originalTarget;
        }
    }
}
