package net.leo.herotech.item.custom;

import net.leo.herotech.util.IElectricItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BatteryItem extends Item implements IElectricItem {
    private static final int maxEnergy = 20;
    private int storedEnergy = maxEnergy;

    public BatteryItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getStoredEnergy() {
        return storedEnergy;
    }

    @Override
    public void charge(int amount, ItemStack stack) {
        storedEnergy += amount;
        stack.setDamageValue(storedEnergy);
    }

    public static int getMaxEnergy() {
        return maxEnergy;
    }
}
