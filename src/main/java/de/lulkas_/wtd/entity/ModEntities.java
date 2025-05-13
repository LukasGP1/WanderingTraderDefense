package de.lulkas_.wtd.entity;

import de.lulkas_.wtd.WandyTDefense;
import de.lulkas_.wtd.entity.custom.MogswampEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MogswampEntity> MOGSWAMP = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(WandyTDefense.MOD_ID, "mogswamp"),
            EntityType.Builder.create(MogswampEntity::new, SpawnGroup.CREATURE).dimensions(1f, 1.95f).build()
    );

    public static void registerModEntities() {
        WandyTDefense.LOGGER.info("Registering Mod Entities for " + WandyTDefense.MOD_ID);
    }
}
