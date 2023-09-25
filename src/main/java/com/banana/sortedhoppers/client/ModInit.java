package com.banana.sortedhoppers.client;

import com.banana.sortedhoppers.Logger;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class ModInit implements ClientModInitializer {
	public static final String MOD_ID = "sorted-hoppers";

	@Override
	public void onInitializeClient() {
		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresentOrElse(container -> {
			ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(MOD_ID, "hopper_texturepack"),
					container, ResourcePackActivationType.ALWAYS_ENABLED);
			Logger.info("Initialized texture pack");
		}, () -> {
			Logger.error("Failed to initialize texture pack");
		});
	}

}