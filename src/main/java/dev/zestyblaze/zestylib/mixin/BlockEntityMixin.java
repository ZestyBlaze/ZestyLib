package dev.zestyblaze.zestylib.mixin;

import dev.zestyblaze.zestylib.common.util.INBTSerializableCompound;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements INBTSerializableCompound {
    @Unique private final BlockEntity blockEntity = (BlockEntity)(Object)this;

    @Override
    public CompoundTag serializeNBT() {
        return blockEntity.saveWithFullMetadata();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        blockEntity.load(nbt);
    }
}
