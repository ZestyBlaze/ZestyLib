package dev.zestyblaze.zestylib.mixin;

import dev.zestyblaze.zestylib.extensions.IEntityExtension;
import dev.zestyblaze.zestylib.nbt.INBTSerializableCompound;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin implements IEntityExtension, INBTSerializableCompound {
    @Unique private final Entity entity = (Entity)(Object)this;
    @Unique private CompoundTag persistentData;

    @Unique
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag ret = new CompoundTag();
        String id = entity.getEncodeId();
        if (id != null) {
            ret.putString("id", id);
        }
        return entity.saveWithoutId(ret);
    }

    @Unique
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        entity.load(nbt);
    }

    @Inject(method = "saveWithoutId", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"))
    private void zestyLib$saveWithoutId(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        if (persistentData != null && !persistentData.isEmpty()) {
            tag.put("ExtraData", persistentData);
        }
    }

    @Inject(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"))
    private void zestyLib$load(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("ExtraData")) {
            persistentData = tag.getCompound("ExtraData");
        }
    }

    @Override
    public CompoundTag getPersistentData() {
        if (persistentData == null)
            persistentData = new CompoundTag();
        return persistentData;
    }
}
