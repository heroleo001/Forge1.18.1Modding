package net.leo.herotech.util;

import javax.annotation.Nullable;

public enum ElectricTier {
    LOW(1),
    MIDDLE(5),
    HIGH(20),
    EMPTY(0);

    private final int maxEnergyIn;

    ElectricTier(int maxEnergyIn){
        this.maxEnergyIn = maxEnergyIn;
    }

    public int getMaxEnergyIn() {
        return maxEnergyIn;
    }
}
