package com.omar.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.omar.demo.entities.Categorie;
import com.omar.demo.entities.Produit;

public interface ProduitService {
Produit saveProduit(Produit p);
Produit updateProduit(Produit p);
void deleteProduit(Produit p);
 void deleteProduitById(Long id);
Produit getProduit(Long id);
List<Produit> getAllProduits();

List<Categorie> getAllCategories();
Page<Produit> getAllProduitsParPage(int page, int size);
List<Produit> findByNomProduit(String nom);
List<Produit> findByNomProduitContains(String nom);
List<Produit> findByNomPrix (String nom, Double prix);
List<Produit> findByCategorie (Categorie categorie);
List<Produit> findByCategorieIdCat(Long id);
List<Produit> findByOrderByNomProduitAsc();
List<Produit> trierProduitsNomsPrix();
}