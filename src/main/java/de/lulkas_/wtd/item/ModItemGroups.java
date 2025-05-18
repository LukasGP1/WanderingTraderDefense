package de.lulkas_.wtd.item;

import de.lulkas_.wtd.WandyTDefense;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup WANDY_T_DEFENSE_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(WandyTDefense.MOD_ID, "wandy_t_defense"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.MOGSWAMP_SPAWN_EGG))
                    .displayName(Text.translatable("itemgroup.wtd.wandy_t_defense"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.MOGSWAMP_SPAWN_EGG);
                        entries.add(ModItems.MOGSWAMP_ARCHER_SPAWN_EGG);
                    }).build()
    );

    public static void registerModItemGroups() {
        WandyTDefense.LOGGER.info("Registering Mod Item Groups for " + WandyTDefense.MOD_ID);
    }
}
