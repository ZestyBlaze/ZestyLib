package dev.zestyblaze.zestylib.mixin;

import dev.zestyblaze.zestylib.nbt.INBTSerializableCompound;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemStack.class)
public class ItemStackMixin implements INBTSerializableCompound {
    @Unique private final ItemStack itemStack = (ItemStack)(Object)this;

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        itemStack.save(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        itemStack.setTag(ItemStack.of(nbt).getTag());
    }
}
