package com.radioctivetacoo.worldsalad.recipes.serializers;

import com.google.gson.JsonObject;
import com.radioctivetacoo.worldsalad.recipes.SpinningWheelRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SpinningWheelRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
		implements IRecipeSerializer<SpinningWheelRecipe> {

	@Override
	public SpinningWheelRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
		Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
		Ingredient input2 = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input2"));

		return new SpinningWheelRecipe(recipeId, input, input2, output);
	}

	@Override
	public SpinningWheelRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack output = buffer.readItemStack();
		Ingredient input = Ingredient.read(buffer);
		Ingredient input2 = Ingredient.read(buffer);

		return new SpinningWheelRecipe(recipeId, input, input2, output);
	}

	@Override
	public void write(PacketBuffer buffer, SpinningWheelRecipe recipe) {
		Ingredient input = recipe.getIngredients().get(0);
		Ingredient input2 = recipe.getIngredients().get(1);
		input.write(buffer);
		input2.write(buffer);

		buffer.writeItemStack(recipe.getRecipeOutput(), false);
	}

}
