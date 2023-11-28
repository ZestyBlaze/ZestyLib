package dev.zestyblaze.zestylib.mixin;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
public class InventoryMixin {
    @Shadow @Final public Player player;

    @Inject(method = "tick", at = @At("TAIL"))
    private void zestylib$tick(CallbackInfo ci) {
        player.getArmorSlots().forEach(stack -> stack.getItem().onArmorTick(stack, player.level(), player));
    }
}
