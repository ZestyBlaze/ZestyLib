package dev.zestyblaze.zestylib.extensions;

import net.minecraft.nbt.CompoundTag;

public interface IEntityExtension {
    default CompoundTag getPersistentData() {
        throw new RuntimeException("Mixin hasn't worked");
    }
}
