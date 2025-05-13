package de.lulkas_.wtd;

import de.lulkas_.wtd.entity.ModEntities;
import de.lulkas_.wtd.entity.custom.MogswampEntity;
import de.lulkas_.wtd.event.ModEvents;
import de.lulkas_.wtd.item.ModItemGroups;
import de.lulkas_.wtd.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WandyTDefense implements ModInitializer {
	public static final String MOD_ID = "wtd";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();
		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.MOGSWAMP, MogswampEntity.createAttributes());
		ModEvents.registerModEvents();
	}
}