package net.snakefangox.mechanized;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Mechanized implements ModInitializer, ClientModInitializer {

	public static final String MODID = "mechanized";
	public static final String MOD_NAME = "Mechanized";

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, "general"),
			() -> new ItemStack(MRegister.ALLOY_FURNACE));

	@Override
	public void onInitialize() {
		MRegister.registerEverything();
		
		Registry.BIOME.forEach(MGeneration::addOreToBiome);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> MGeneration.addOreToBiome(biome));
		
		if (FabricLoader.getInstance().isDevelopmentEnvironment())
			AutoGenJson.autoGenerateJson(MODID, "D:\\Code\\Fabric_mods\\modfest2022");
	}

	@Override
	public void onInitializeClient() {
		MRegister.registerClient();
	}

}
