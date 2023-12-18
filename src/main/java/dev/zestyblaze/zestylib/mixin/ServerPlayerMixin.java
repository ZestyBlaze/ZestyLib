package dev.zestyblaze.zestylib.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
    public ServerPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void zestyLib$restoreFrom(ServerPlayer oldPlayer, boolean alive, CallbackInfo ci) {
        CompoundTag oldData = oldPlayer.getPersistentData();
        CompoundTag persistent = oldData.getCompound("PlayerPersisted");
        if (persistent != null) {
            CompoundTag thisData = this.getPersistentData();
            thisData.put("PlayerPersisted", persistent);
        }
    }
}
