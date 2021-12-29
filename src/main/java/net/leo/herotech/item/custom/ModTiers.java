package net.leo.herotech.item.custom;

import net.leo.herotech.item.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier DRILL = new ForgeTier(2, 1000, 9f, 0f,
            0, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.EMPTY);

    public static final ForgeTier DIAMOND_DRILL = new ForgeTier(3, 1500, 16f, 0f,
            0, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.EMPTY);

    public static final ForgeTier ADVANCED_DIAMOND_DRILL = new ForgeTier(4, 2000, 20f, 0f,
            0, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.EMPTY);

    public static final ForgeTier COBALT = new ForgeTier(2, 100, 10f, 2f, 0,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.COBALT_INGOT.get()));
}
