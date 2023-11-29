package dev.zestyblaze.zestylib.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ZLBlockTags {
    public static TagKey<Block> register(ResourceLocation id) {
        return TagKey.create(Registries.BLOCK, id);
    }
}
