package net.leo.herotech.util;

public interface IElectricBlock {
    /**
     * get maximum energy storage of an Item or Machine
     * @return
     * int
     */
    int getMaxEnergy();

    /**
     * gets a value of an advanced enum which determines the electric tier
     * @return
     * ElectricTier
     */
    ElectricTier getTier();

    /**
     * tells you if the block generates any power and is
     * able to give it to other machines
     * @return
     * boolean
     */
    boolean isGenerator();

    /**
     * gets the value of stored energy inside the
     * item
     * @return int
     */
    int getStoredEnergy();

    void charge(int amount);
}
