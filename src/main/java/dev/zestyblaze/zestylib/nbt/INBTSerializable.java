package dev.zestyblaze.zestylib.nbt;

import net.minecraft.nbt.Tag;

public interface INBTSerializable<T extends Tag> {
    default T serializeNBT() {
        throw new RuntimeException("Something went wrong somewhere");
    }

    default void deserializeNBT(T nbt) {
        throw new RuntimeException("Something went wrong somewhere");
    }
}
