package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.CustomerMapper;
import DAO.OrderMapper;
import DAO.ProductMapper;

public class Order {
	private Customer customer;
	private Product product;
	private int quantity;
	private double unitPrice;
	private Date orderTime;
	private String deliveryAddress;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public Order(Customer customer, Product product, int quantity, double unitPrice, Date orderTime,
			String deliveryAddress) {
		super();
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.orderTime = orderTime;
		this.deliveryAddress = deliveryAddress;
	}
	
	public void save() {
		OrderMapper om = new OrderMapper(customer.getId(), product.getId(), 
				quantity, unitPrice, orderTime, deliveryAddress);
		om.insert();
	}
	
	public static List<Order> getCustomerHistory(Customer c, Date startDate, Date endDate){
		List<Order> orders = new ArrayList<Order>();
		List<OrderMapper> orderMappers = OrderMapper.find(c.getId(), startDate, endDate);
		
		for(OrderMapper om: orderMappers) {
			ProductMapper pm = ProductMapper.findById(om.getIdProduct());
			Product p = new Product(pm);
			Order o = new Order(c, p, om.getQuantity(), om.getUnitPrice(), 
					om.getOrderTime(), om.getDeliveryAddress());
			orders.add(o);
		}
		return orders;
	}
	
	public static List<Order> getCustomerHistory(Customer c){
		return getCustomerHistory(c, null, null);
	}

}
