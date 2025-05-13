package de.lulkas_.wtd.util;

import net.minecraft.nbt.NbtCompound;

public class HasHitTraderData {
    public static void setHasHitTrader(IEntityDataSaver player, boolean value) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putBoolean("HasHitTrader", value);
    }

    public static boolean getHasHitTrader(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getBoolean("HasHitTrader");
    }
}
