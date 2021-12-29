package net.leo.herotech.item.custom;

import net.leo.herotech.util.ModTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class DrillItem extends DiggerItem {
    public DrillItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Item.Properties pProperties) {
        super((float)pAttackDamageModifier, pAttackSpeedModifier, pTier, ModTags.Blocks.MINEABLE_WITH_DRILL, pProperties);
    }
}
