package net.leo.herotech.util;

import net.minecraft.world.level.block.state.BlockState;

public interface IElectricBlock {
    /**
     * get maximum energy storage of an Item or Machine
     * @return
     * int
     */
    static int getMaxEnergy(){return 0;}

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

    void charge(int amount, BlockState state);
}
