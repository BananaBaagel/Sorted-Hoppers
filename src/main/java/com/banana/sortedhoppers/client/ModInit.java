package com.banana.sortedhoppers.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class ModInit implements ClientModInitializer {
	public static final String ID = "sorted-hoppers";

	@Override
	public void onInitializeClient() {
		FabricLoader.getInstance().getModContainer(ID).ifPresent(container -> {
			ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("sorted-hoppers", "hopper_texturepack"),
					container, ResourcePackActivationType.ALWAYS_ENABLED);
		});
	}

}