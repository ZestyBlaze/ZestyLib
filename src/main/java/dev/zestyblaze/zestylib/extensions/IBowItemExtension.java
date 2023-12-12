package dev.zestyblaze.zestylib.extensions;

import net.minecraft.world.entity.projectile.AbstractArrow;

public interface IBowItemExtension {
    default AbstractArrow customArrow(AbstractArrow arrow) {
        return arrow;
    }
}
