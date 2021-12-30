package net.leo.herotech.item;

import net.leo.herotech.HeroTech;
import net.leo.herotech.item.custom.DrillItem;
import net.leo.herotech.item.custom.ModTiers;
import net.leo.herotech.item.custom.ScannerItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HeroTech.MOD_ID);


    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEROTECH_TAB)));

    public static final RegistryObject<Item> COBALT_LEVITATION_SWORD = ITEMS.register("cobalt_levitation_sword",
            () -> new SwordItem(ModTiers.COBALT, 2, 40f,
                    new Item.Properties().tab(ModItemGroup.HEROTECH_TAB)){
                @Override
                public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
                    pTarget.addEffect(new MobEffectInstance(MobEffects.LEVITATION, (60*20)));
                    return super.hurtEnemy(pStack, pTarget, pAttacker);
                }
    });

    public static final RegistryObject<Item> COBALT_NUGGET = ITEMS.register("cobalt_nugget",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEROTECH_TAB)));

    public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEROTECH_TAB)));

    public static final RegistryObject<Item> DRILL = ITEMS.register("drill",
            () -> new DrillItem(ModTiers.DRILL, 0, 0f,
                    new Item.Properties().tab(ModItemGroup.HEROTECH_TAB)));

    public static final RegistryObject<Item> DIAMOND_DRILL = ITEMS.register("diamond_drill",
            () -> new DrillItem(ModTiers.DIAMOND_DRILL, 0, 0f,
                    new Item.Properties().tab(ModItemGroup.HEROTECH_TAB)));

    public static final RegistryObject<Item> ADVANCED_DIAMOND_DRILL = ITEMS.register("advanced_diamond_drill",
            () -> new DrillItem(ModTiers.ADVANCED_DIAMOND_DRILL, 0, 0f,
                    new Item.Properties().tab(ModItemGroup.HEROTECH_TAB)));

    public static final RegistryObject<Item> SCANNER = ITEMS.register("scanner",
            () -> new ScannerItem(new Item.Properties().tab(ModItemGroup.HEROTECH_TAB).durability(32)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
