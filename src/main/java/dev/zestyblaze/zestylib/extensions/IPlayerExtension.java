package dev.zestyblaze.zestylib.extensions;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.world.entity.player.Player;

public interface IPlayerExtension {
    private Player self() {
        return (Player) this;
    }

    default double getEntityReach() {
        double range = self().getAttributeValue(ReachEntityAttributes.REACH);
        return range == 0 ? 0 : range + (self().isCreative() ? 3 : 0);
    }
}
