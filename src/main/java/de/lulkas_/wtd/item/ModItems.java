package de.lulkas_.wtd.item;

import de.lulkas_.wtd.WandyTDefense;
import de.lulkas_.wtd.entity.ModEntities;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MOGSWAMP_SPAWN_EGG = registerItem("mogswamp_spawn_egg", new SpawnEggItem(ModEntities.MOGSWAMP, 0x9dc783, 0xbfaf5f, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(WandyTDefense.MOD_ID, name), item);
    }

    public static void registerModItems() {
        WandyTDefense.LOGGER.info("Registering Mod Items for " + WandyTDefense.MOD_ID);
    }
}
