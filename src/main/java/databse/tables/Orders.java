package databse.tables;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity
@SecondaryTables({ @SecondaryTable(name = "Client", pkJoinColumns = @PrimaryKeyJoinColumn(name = "clientID")),
				   @SecondaryTable(name = "Product", pkJoinColumns = @PrimaryKeyJoinColumn(name = "productID")) })
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String descriptionOfOrder;
	private String manager;
	private String status;
	private String deliveryDate;
	private Date orderDate;

	@Column(name = "name", table = "Client")
	private String order_name;
	@Column(name = "phoneNumber", table = "Client")
	private String order_phoneNumber;

	@Column(name = "amount", table = "Product")
	private Double order_amount;
	@Column(name = "price", table = "Product")
	private Double order_price;
	@Column(name = "supplier", table = "Product")
	private String order_supplier;

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

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrder_name() {
		return order_name;
	}

	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}

	public String getOrder_phoneNumber() {
		return order_phoneNumber;
	}

	public void setOrder_phoneNumber(String order_phoneNumber) {
		this.order_phoneNumber = order_phoneNumber;
	}

	public double getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(Double order_amount) {
		this.order_amount = order_amount;
	}

	public double getOrder_price() {
		return order_price;
	}

	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}

	public String getOrder_supplier() {
		return order_supplier;
	}

	public void setOrder_supplier(String order_supplier) {
		this.order_supplier = order_supplier;
	}

}
