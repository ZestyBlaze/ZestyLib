package dev.zestyblaze.zestylib.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.zestyblaze.zestylib.events.living.LivingDamageEvent;
import dev.zestyblaze.zestylib.events.living.LivingEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void zestyLib$tick(CallbackInfo ci) {
        LivingEvent.LivingTickEvent event = new LivingEvent.LivingTickEvent((LivingEntity)(Object)this);
        LivingEvent.TICK.invoker().onTick(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void zestyLib$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir, @Share("HurtEvent") LocalRef<LivingDamageEvent.HurtEvent> sharedEvent) {
        LivingDamageEvent.HurtEvent event = new LivingDamageEvent.HurtEvent((LivingEntity) (Object) this, source, amount);
        sharedEvent.set(event);
        LivingDamageEvent.HURT.invoker().onHurt(event);
        if (event.isCanceled()) {
            cir.setReturnValue(false);
        }
    }

    @ModifyVariable(method = "hurt", at = @At(value = "HEAD", shift = At.Shift.AFTER), argsOnly = true)
    private float zestyLib$modifyHurt(float amount, DamageSource source, float amount2, @Share("HurtEvent") LocalRef<LivingDamageEvent.HurtEvent> sharedEvent) {
        return sharedEvent.get().amount;
    }
}
