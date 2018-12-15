package com.cookbook.initializer;

import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.cookbook.model.Recipe;
import com.cookbook.model.util.ModelConvertor;
import com.cookbook.repositories.RecipeRepository;
import com.cookbook.xml.Cookbook;

@Component
public class Initializer implements ApplicationListener<ApplicationReadyEvent> {
	private static final String COOKBOOK_XML_URL = "http://localhost:8080/cookbook.xml";

	private static final Logger log = LoggerFactory.getLogger(Initializer.class);
	
	@Autowired
	private RecipeRepository recipeRepository;
	
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
    	Cookbook cookbook = parseXml();
    	List<Recipe> recipes = ModelConvertor.toEntityRecipes(cookbook.getRecipe());
    	saveRecipes(recipes);
    }

	private void saveRecipes(List<Recipe> recipes) {
    	for(Recipe recipe : recipes) {
    		recipeRepository.save(recipe);
    	}
	}

	private Cookbook parseXml() {
		Cookbook result = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Cookbook.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			result = (Cookbook) jaxbUnmarshaller.unmarshal(new URL(COOKBOOK_XML_URL));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

}