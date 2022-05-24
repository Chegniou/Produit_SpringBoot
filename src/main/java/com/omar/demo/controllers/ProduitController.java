package com.omar.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omar.demo.entities.Categorie;
import com.omar.demo.entities.Produit;
import com.omar.demo.service.CategorieService;
import com.omar.demo.service.ProduitService;

@Controller
public class ProduitController {
@Autowired
ProduitService produitService;

//CategorieService categorieService;
@RequestMapping("/showCreate")
public String showCreate(ModelMap modelMap)
{
	List<Categorie> cats = produitService.getAllCategories();
	Produit prod = new Produit();
	Categorie cat = new Categorie();
	cat = cats.get(0); // prendre la première catégorie de la liste
	prod.setCategorie(cat); //affedter une catégorie par défaut au produit pour éviter le pb avec une catégorie null
	modelMap.addAttribute("produit",prod);
	modelMap.addAttribute("mode", "new");
	modelMap.addAttribute("categories", cats);
	return "formProduit";
}



@RequestMapping("/saveProduit")
public String saveProduit(@Valid Produit produit,
						  BindingResult bindingResult)
{
	System.out.println(produit);
	System.out.println(bindingResult.getAllErrors());
	if (bindingResult.hasErrors()) return "formProduit";				  
	
	produitService.saveProduit(produit);	
return "redirect:ListeProduits";
}

/*@RequestMapping("/saveCategorie")
public String saveCategorie(@ModelAttribute("categorie") Categorie categorie)
{
	categorieService.saveCategorie(categorie);
return "createCategorie";
}*/

/*@RequestMapping("/ListeCategories")
public String listeCategories(ModelMap modelMap)
{
	
	List<Categorie> cats = produitService.getAllCategories();
	
	modelMap.addAttribute("mode", "edit");
	modelMap.addAttribute("categories", cats);
	return "listeCategories";

}
*/

@RequestMapping("/ListeProduits")
public String listeProduits(ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
		@RequestParam (name="size", defaultValue = "2") int size)
{
	Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
	// List<Categorie> cats = produitService.getAllCategories();
	modelMap.addAttribute("produits", prods);
	 modelMap.addAttribute("pages", new int[prods.getTotalPages()]);

	// modelMap.addAttribute("categories", cats);
	modelMap.addAttribute("currentPage", page);
	return "listeProduits";

}
@RequestMapping("/supprimerProduit")
public String supprimerProduit(@RequestParam("id") Long id,
 ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
 @RequestParam (name="size", defaultValue = "2") int size)
{ 
	produitService.deleteProduitById(id);
	Page<Produit> prods = produitService.getAllProduitsParPage(page, 
	size);
	modelMap.addAttribute("produits", prods);
	modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	
	modelMap.addAttribute("size", size);
	return "listeProduits";
}
@RequestMapping("/modifierProduit")
public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
{
	Produit p= 	produitService.getProduit(id);
	List<Categorie> cats = produitService.getAllCategories();
	modelMap.addAttribute("produit", p);
	modelMap.addAttribute("mode", "edit");
	modelMap.addAttribute("categories", cats);
	return "formProduit";	
}

/*@RequestMapping("/updateProduit")
public String updateProduit(@ModelAttribute("produit") Produit produit,
@RequestParam("date") String date,

ModelMap modelMap) throws ParseException 
{
//conversion de la date 
 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
 Date dateCreation = dateformat.parse(String.valueOf(date));
 produit.setDateCreation(dateCreation);
 
 produitService.updateProduit(produit);
 List<Produit> prods = produitService.getAllProduits();
 modelMap.addAttribute("produits", prods);
return "listeProduits";

}*/
@RequestMapping("/updateProduit")
public String updateProduit(@ModelAttribute("produit") Produit produit,ModelMap modelMap) 
{
	  produitService.updateProduit(produit);
	  List<Produit> prods = produitService.getAllProduits();
	  modelMap.addAttribute("produits", prods);	
	
	return "listeProduits";
}

}
