package dev.zestyblaze.zestylib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.zestyblaze.zestylib.ZestyLib;
import dev.zestyblaze.zestylib.event.entity.living.LivingDamageEvent;
import dev.zestyblaze.zestylib.event.entity.living.LivingEvent;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Unique private final LivingEntity livingEntity = (LivingEntity)(Object)this;
    @Unique private static final AttributeModifier SLOW_FALLING = new AttributeModifier(UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA"), "Slow falling acceleration reduction", -0.07, AttributeModifier.Operation.ADDITION); // Add -0.07 to 0.08 so we get the vanilla default of 0.0
    @Shadow @Nullable public abstract AttributeInstance getAttribute(Holder<Attribute> attribute);

    @ModifyReturnValue(method = "createLivingAttributes", at = @At("RETURN"))
    private static AttributeSupplier.Builder zestyLib$createAttributes(AttributeSupplier.Builder builder) {
        return builder
                .add(ZestyLib.SWIM_SPEED)
                .add(ZestyLib.ENTITY_GRAVITY);
    }

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

    @ModifyArg(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V", ordinal = 0))
    private float zestyLib$travel(float original) {
        return original * (float) this.getAttribute(ZestyLib.SWIM_SPEED).getValue();
    }

    @ModifyArg(method = "jumpInLiquid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;add(DDD)Lnet/minecraft/world/phys/Vec3;"), index = 1)
    private double zestyLib$jumpInLiquid(double y) {
        return y * getAttribute(ZestyLib.SWIM_SPEED).getValue();
    }
}
