package net.davio.create_aquatic_ambitions.compat.EMI;

import com.simibubi.create.AllItems;
import com.simibubi.create.compat.emi.DoubleItemIcon;
import com.simibubi.create.compat.emi.recipes.fan.FanEmiRecipe;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiRenderable;
import net.davio.create_aquatic_ambitions.CreateAquaticAmbitions;
import net.davio.create_aquatic_ambitions.entry.CCARecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CCAEmiPlugin implements EmiPlugin {
    public static final Map<ResourceLocation, EmiRecipeCategory> ALL = new LinkedHashMap<>();
    public static final EmiRecipeCategory
            CHANNELING = register("channeling", DoubleItemIcon.of(AllItems.PROPELLER.get(), Items.HEART_OF_THE_SEA));

    @Override
    public void register(EmiRegistry registry) {
        ALL.forEach((id, category) -> registry.addCategory(category));
        registry.addWorkstation(CHANNELING, FanEmiRecipe.getFan("fan_channeling"));
        addAll(registry, CCARecipeTypes.CHANNELING, FanChannelingEmiRecipe::new);
    }

    @SuppressWarnings("unchecked")
    private <T extends Recipe<?>> void addAll(EmiRegistry registry, CCARecipeTypes type, Function<T, EmiRecipe> constructor) {
        for (T recipe : (List<T>) registry.getRecipeManager().getAllRecipesFor(type.getType())) {
            registry.addRecipe(constructor.apply(recipe));
        }
    }

    private static EmiRecipeCategory register(String name, EmiRenderable icon) {
        ResourceLocation id = CreateAquaticAmbitions.id(name);
        EmiRecipeCategory category = new EmiRecipeCategory(id, icon);
        ALL.put(id, category);
        return category;
    }
}
