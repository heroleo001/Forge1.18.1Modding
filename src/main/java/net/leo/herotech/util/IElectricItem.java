package net.leo.herotech.util;

import net.minecraft.world.item.ItemStack;

public interface IElectricItem {
    /**
     * get maximum energy storage of an Item or Machine
     * @return
     * int
     */
    static int getMaxEnergy(){return 0;}

    /**
     * gets the value of stored energy inside the
     * item
     * @return int
     */
    int getStoredEnergy();

    void charge(int amount, ItemStack stack);
}
