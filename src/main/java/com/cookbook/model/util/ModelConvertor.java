package com.cookbook.model.util;

import java.util.ArrayList;
import java.util.List;

import com.cookbook.model.Ingredient;
import com.cookbook.model.Method;
import com.cookbook.model.Recipe;
import com.cookbook.model.Step;
import com.cookbook.model.Time;
import com.cookbook.xml.Cookbook;

public class ModelConvertor {

	public static List<Recipe> toEntityRecipes(List<Cookbook.Recipe> recipes) {
		List<Recipe> result = new ArrayList<>();
		
		for(Cookbook.Recipe recipeXml : recipes) {
			Recipe recipe = new Recipe();
			recipe.setTitle(recipeXml.getTitle());
			recipe.setIngredients(toEntityIngredients(recipeXml.getIngredient()));
			recipe.setMethod(toEntityMethod(recipeXml.getMethod()));
			recipe.setTime(toEntityTime(recipeXml.getTime()));
			result.add(recipe);
		}
		
		return result;
	}
	
	public static List<Ingredient> toEntityIngredients(List<Cookbook.Recipe.Ingredient> ingredients) {
		List<Ingredient> result = new ArrayList<>();
		
		for(Cookbook.Recipe.Ingredient ingredientXml : ingredients) {
			Ingredient ingredient = new Ingredient();
			ingredient.setName(ingredientXml.getName());
			ingredient.setQuantity(ingredientXml.getQuantity());
			ingredient.setUnit(ingredientXml.getUnit());
			result.add(ingredient);
		}
		
		return result;
	}
	
	public static Method toEntityMethod(Cookbook.Recipe.Method methodXml) {
		Method result = new Method();
		
		result.setSteps(toEntitySteps(methodXml.getStep()));
		
		return result;
	}
	
	public static List<Step> toEntitySteps(List<String> steps) {
		List<Step> result = new ArrayList<>();
		
		for(String stepXml : steps) {
			Step step = new Step();
			step.setInstruction(stepXml);
			result.add(step);
		}
		
		return result;
	}
	
	public static Time toEntityTime(Cookbook.Recipe.Time timeXml) {
		Time result = new Time();
		
		result.setQuantity(timeXml.getQuantity());
		result.setUnit(timeXml.getUnit());
		
		return result;
	}
}
