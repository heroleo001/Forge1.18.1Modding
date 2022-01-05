package net.leo.herotech.recipe;

import net.leo.herotech.HeroTech;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HeroTech.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CompressorRecipe>> COMPRESSOR_SERIALIZER =
            SERIALIZERS.register("compressing", () -> CompressorRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
