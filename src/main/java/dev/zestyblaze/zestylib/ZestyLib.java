package dev.zestyblaze.zestylib;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.api.ModInitializer;

public class ZestyLib implements ModInitializer {
	public static final String MODID = "zestylib";

	@Override
	public void onInitialize() {
		MixinExtrasBootstrap.init();
	}
}