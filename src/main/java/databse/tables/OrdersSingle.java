package databse.tables;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrdersSingle {

	private Integer orderId;
	private String descriptionOfOrder;
	private String deliveryDate;
	private Date orderDate;
	private String manager;
	private String status;
	
	//private Supplier supplier;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOrderId() {
		return orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDescriptionOfOrder() {
		return descriptionOfOrder;
	}

	public void setDescriptionOfOrder(String descriptionOfOrder) {
		this.descriptionOfOrder = descriptionOfOrder;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

//	public Supplier getSupplier() {
//		return supplier;
//	}
//
//	public void setSupplier(Supplier supplier) {
//		this.supplier = supplier;
//	}
	
	

}
