package com.cookbook.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cookbook.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
	
}
