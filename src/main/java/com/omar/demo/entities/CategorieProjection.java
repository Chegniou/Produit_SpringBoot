package com.omar.demo.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nomcat", types = { Categorie.class })
public interface CategorieProjection {
	public String getNomCategorie();

}
