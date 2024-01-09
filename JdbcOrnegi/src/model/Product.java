package model;
import java.time.LocalDate;

public class Product {
	private int id;
	private String name;
	private String vendor;
	private Double price;
	private LocalDate date;
	private Location location;
	
	
	
	public Product() {
	}



	public Product(int id,String name, String vendor, double price, LocalDate date, Location location) {
		super();
		this.id=id;
		this.name = name;
		this.vendor = vendor;
		this.price = price;
		this.date = date;
		this.location = location;
	}
	public Product(String name, String vendor, double price, LocalDate date, Location location) {
		super();
		this.name = name;
		this.vendor = vendor;
		this.price = price;
		this.date = date;
		this.location = location;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getVendor() {
		return vendor;
	}



	public void setVendor(String vendor) {
		this.vendor = vendor;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public Location getLocation() {
		return location;
	}



	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return 	id + " " 
				+ name + " " 
				+ vendor + " " 
				+ price + " " 
				+ date + " " 
				+ location + "\n"; 
	}
	
}

