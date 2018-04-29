package databse.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {

	private Integer orderId;
	private String descriptionOfOrder;
	private String deliveryDate;
	
	//private Supplier supplier;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOrderId() {
		return orderId;
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

//	public Supplier getSupplier() {
//		return supplier;
//	}
//
//	public void setSupplier(Supplier supplier) {
//		this.supplier = supplier;
//	}
	
	

}
