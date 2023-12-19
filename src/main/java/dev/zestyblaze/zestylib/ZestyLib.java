package dev.zestyblaze.zestylib;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class ZestyLib implements ModInitializer {
	public static final String MODID = "zestylib";

	public static final Holder<Attribute> SWIM_SPEED = register("swim_speed", new RangedAttribute("generic.zestylib.swim_speed", 1.0D, 0.0D, 1024.0D).setSyncable(true));
	public static final Holder<Attribute> ENTITY_GRAVITY = register("entity_gravity", new RangedAttribute("generic.zestylib.entity_gravity", 0.08D, -8.0D, 8.0D).setSyncable(true));

	@Override
	public void onInitialize() {}

	private static Holder<Attribute> register(String string, Attribute attribute) {
		return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, new ResourceLocation(MODID, string), attribute);
	}
}