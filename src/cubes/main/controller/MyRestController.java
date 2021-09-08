package cubes.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cubes.main.dao.ProductDAO;
import cubes.main.dao.StickerDAO;
import cubes.main.entity.Category;
import cubes.main.entity.Product;
import cubes.main.entity.Sticker;
import cubes.main.rest.CategoryException;
import cubes.main.rest.MessageResponse;
import cubes.main.rest.StickerException;
import cubes.main.rest.request_body.ProductFilter;
import cubes.main.rest.response.BasicProductResponse;
import cubes.main.rest.response.SettingResponse;
import cubes.main.service.CategoryService;

@RestController
@RequestMapping(value = "/api")
public class MyRestController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private StickerDAO stickerDAO;
	@Autowired
	private ProductDAO productDAO;
	

//------------------------------------ CATEGORIES ---------------------------------------------------
	
	//POST trebalo bi da se nesto ppstuje na server
	//PUT bi trebalo da bude update postojeceg entiteta
	
	@GetMapping(value = "/categories")
	public List<Category> getCategories(){
		
		List<Category> list = categoryService.getCategoryList();
		
		return list;
	}
	
	@GetMapping(value = "/categories/{id}")
	public Category getCategoryById(@PathVariable int id) {
		
         Category category = categoryService.getCategoryById(id);
         
         if(category == null) {
        	 throw new CategoryException("Kategorija sa id "+id+" ne postoji u bazi podataka.");
         }
		
		 return category;
		 
		}
	
	@PostMapping(value = "/categories")
	public MessageResponse createCategory(@RequestBody Category category) {
		
		category.setId(0);
		categoryService.saveCategory(category);
		
		return new MessageResponse(HttpStatus.OK.value(), "Uspesno je sacuvana kategorija: "+category, System.currentTimeMillis());
		
	}
	
	@PutMapping(value = "/categories")
	public MessageResponse updateCategory(@RequestBody Category category) {
		
		categoryService.saveCategory(category);
		
		return new MessageResponse(HttpStatus.OK.value(), "Uspesno je izmenjena kategorija "+ category, System.currentTimeMillis());
		
	}
	// vise nacina implementacije, moze da se prosledjuje kompletan objekat u request body (@Request body Category category,
	// a moze i  @PathVariable da se stavi int id kao u primeru ispod
	@DeleteMapping(value = "/categories/{id}")
	public MessageResponse deleteCategory(@PathVariable int id) {
		
		
		Category category = categoryService.getCategoryById(id);
		categoryService.deleteCategory(id);
		
		return new MessageResponse(HttpStatus.OK.value(), "Uspesno je izbrisana kategorija: "+category, System.currentTimeMillis());
		
	}
	
//------------------------------- STICKERS --------------------------------------------------
	
	@GetMapping(value = "/stickers")
	public List<Sticker> getStickers(){
		
	   List<Sticker> stickerList = stickerDAO.getStickerList();
	   
	   return stickerList;
		
	}
	
	@GetMapping(value = "stickers/{id}")
	public Sticker getStickerById(@PathVariable int id) {
		
		Sticker sticker = stickerDAO.getStickerById(id);
		
		if(sticker == null) {
       	 throw new StickerException("Stiker sa id "+id+" ne postoji u bazi podataka.");
        }
		
		 return sticker;
		 
		}

	@PostMapping(value = "/stickers")
	public MessageResponse createSticker(@RequestBody Sticker sticker) {
		
		sticker.setId(0);
		stickerDAO.saveSticker(sticker);
		
		return new MessageResponse(HttpStatus.OK.value(), "Uspesno je sacuvan sticker: "+sticker, System.currentTimeMillis());
		
	}
	
	@PutMapping(value = "/stickers")
	public MessageResponse updateSticker(@RequestBody Sticker sticker) {
		
		stickerDAO.saveSticker(sticker);
		
		return new MessageResponse(HttpStatus.OK.value(), "Uspesno je izmenjen stiker "+ sticker, System.currentTimeMillis());
		
	}
	
	@DeleteMapping(value = "/stickers/{id}")
	public MessageResponse deleteSticker(@PathVariable int id) {
		
		
		Sticker sticker = stickerDAO.getStickerById(id);
		stickerDAO.deleteSticker(id);
		
		return new MessageResponse(HttpStatus.OK.value(), "Uspesno je izbrisan sticker: "+sticker, System.currentTimeMillis());
		
	}
	
//---------------------------- SETTINGS -----------------------------------------	
	
	@GetMapping(value = "/settings")
	public SettingResponse getSettings() {
		
		// napraviti objekat klase SETTINGS kako bismo mogli da dobijemo getere i setere za listu kategorija i stikera
		SettingResponse settings = new SettingResponse();
		
		//popunjavanje SETTINGS-a sa listom kategorija i stikera
		// tako sto pozivamo setere kategorije i stikera iz klase Settings
		// a unutar setera u zagradi pozivamo DAO/service pa get metode za kategorije i stikere
		//kao u primeru ispod
		
		settings.setCategories(categoryService.getCategoryList());
		settings.setStickers(stickerDAO.getStickerList());
		
		return settings;
	}
	
	
//------------------------------ PRODUCTS --------------------------------------------------
	
	@GetMapping(value = "/products")
	public List<Product> getProducts(){
		
		List<Product> products = productDAO.getProductListWithStickers();
		
		return products;
	}
	
	@GetMapping(value = "/basicproducts")
	public List<BasicProductResponse> getBasicProducts() {
		
		List<BasicProductResponse> list = new ArrayList<BasicProductResponse>();
		List<Product> products = productDAO.getProductListWithStickers();
		
		for(Product p : products) {
			list.add(new BasicProductResponse(p));
		}
		
		return list;
	}
	
	@PostMapping(value = "/products")
	public MessageResponse saveProduct(@RequestBody Product product) {
		
		product.setId(0);
		
		productDAO.saveProduct(product);
		
		return new MessageResponse(HttpStatus.OK.value(), "Uspesno ste sacuvali proizvod "+product, System.currentTimeMillis());
		
	}
	
	
	@PutMapping(value = "/products")
	public MessageResponse updateProduct(@RequestBody Product product) {
				
		productDAO.saveProduct(product);
		
		return new MessageResponse(HttpStatus.OK.value(), "Uspesno ste izmenili proizvod "+product, System.currentTimeMillis());
		
	}
	
	@DeleteMapping(value = "/products/{id}")
	public MessageResponse deleteProduct(@PathVariable int id) {
		
		Product p = productDAO.getProductById(id);
		
		productDAO.deleteProduct(id);
		
		return new MessageResponse(HttpStatus.OK.value(), "Uspesno ste obrisali proizvod: "+ p, System.currentTimeMillis());
	}
	
	@GetMapping(value = "/filter-products")
	public List<Product> getProductListFilter(@RequestBody ProductFilter filter){
		
		List<Product> list = productDAO.getProductList(filter.getCategory(), filter.getPrice(), filter.getStickers());
		
		return list;
		
		
	}
	
	
	
	
}
