package dev.zestyblaze.zestylib.common.extensions;

import net.minecraft.world.entity.player.Player;

public interface IPlayerExtension {
    private Player self() {
        return (Player) this;
    }
}
