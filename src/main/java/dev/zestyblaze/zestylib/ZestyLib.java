package dev.zestyblaze.zestylib;

import dev.zestyblaze.zestylib.attributes.AttributeRegistry;
import net.fabricmc.api.ModInitializer;

public class ZestyLib implements ModInitializer {
	public static final String MODID = "zestylib";

	@Override
	public void onInitialize() {
		AttributeRegistry.register();
	}
}