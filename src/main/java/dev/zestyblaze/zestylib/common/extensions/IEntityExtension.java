package dev.zestyblaze.zestylib.common.extensions;

import dev.zestyblaze.zestylib.common.util.INBTSerializable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

public interface IEntityExtension extends INBTSerializable<CompoundTag> {
    private Entity self() {
        return (Entity) this;
    }

    default void deserializeNBT(CompoundTag nbt) {
        self().load(nbt);
    }

    default CompoundTag serializeNBT() {
        CompoundTag ret = new CompoundTag();
        String id = self().getEncodeId();
        if (id != null) {
            ret.putString("id", self().getEncodeId());
        }
        return self().saveWithoutId(ret);
    }

    default CompoundTag getPersistentData() {
        throw new RuntimeException("Something went wrong somewhere");
    }

    default float getStepHeight() {
        float vanillaStep = self().maxUpStep();
        if (self() instanceof LivingEntity living) {
            AttributeInstance stepHeightAttribute = living.getAttribute(Attributes.STEP_HEIGHT);
            if (stepHeightAttribute != null) {
                return (float) Math.max(0, vanillaStep + stepHeightAttribute.getValue());
            }
        }
        return vanillaStep;
    }
}
