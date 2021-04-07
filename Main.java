
import java.util.ArrayList;
import java.util.List;

import DAO.CustomerMapper;
import DAO.ProductMapper;

public class Product {
	
	private ProductMapper pm = null;
	private String name;
	private String description;
	private double price;
	
	public int getId() {
		return pm.getId();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Product(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Product(ProductMapper pm) {
		this.pm = pm;
		this.name = pm.getName();
		this.description = pm.getDescription();
		this.price = pm.getPrice();
	}
	
	public Product() {
		this("", "", 0.0);
	}


	public static List<Product> findAll(){
		List<Product> allProducts = new ArrayList<Product>();
		List<ProductMapper> productMappers = ProductMapper.findAll();
		
		for(ProductMapper pm: productMappers) {
			Product p = new Product( pm.getName(),pm.getDescription(), pm.getPrice());
			p.pm = pm;
			allProducts.add(p);
		}
		
		return allProducts;
	}

	public void save() {
		if(pm == null) {
			pm = new ProductMapper(-1, name, description, price);
			pm.insert();
		}else {
			pm.setName(name);
			pm.setDescription(description);
			pm.setPrice(price);
			pm.update();
		}
	}
	
	public void delete() {
		if(pm != null) {
			pm.delete();
			pm = null;
		}
	}
	
	public void update(){
					pm.setName(name);
			pm.setDescription(description);
			pm.setPrice(price);
			pm.update();
		
		
	}


	

}

