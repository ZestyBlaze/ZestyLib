package dev.zestyblaze.zestylib.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.zestyblaze.zestylib.events.living.LivingChangeTargetEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Mob.class)
public class MobMixin {
    @WrapOperation(method = "setTarget", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/Mob;target:Lnet/minecraft/world/entity/LivingEntity;", opcode = Opcodes.PUTFIELD))
    private void zestyLib$setTarget(Mob mob, LivingEntity target, Operation<Void> original) {
        LivingChangeTargetEvent.ChangeTargetEvent event = new LivingChangeTargetEvent.ChangeTargetEvent((Mob)(Object)this, target);
        LivingChangeTargetEvent.CHANGE_TARGET.invoker().onChangeTarget(event);
        if(!event.isCanceled()) {
            original.call(mob, event.getNewTarget());
        }
    }
}
