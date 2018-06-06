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


	/**
	 * @return
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return
	 */
	public String getDescriptionOfOrder() {
		return descriptionOfOrder;
	}

	/**
	 * @param descriptionOfOrder
	 */
	public void setDescriptionOfOrder(String descriptionOfOrder) {
		this.descriptionOfOrder = descriptionOfOrder;
	}

	/**
	 * @return
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * @param manager
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return
	 */
	public String getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate
	 */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return
	 */
	public String getOrder_name() {
		return order_name;
	}

	/**
	 * @param order_name
	 */
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}

	/**
	 * @return
	 */
	public String getOrder_phoneNumber() {
		return order_phoneNumber;
	}

	/**
	 * @param order_phoneNumber
	 */
	public void setOrder_phoneNumber(String order_phoneNumber) {
		this.order_phoneNumber = order_phoneNumber;
	}

	/**
	 * @return
	 */
	public Double getOrder_amount() {
		return order_amount;
	}

	/**
	 * @param order_amount
	 */
	public void setOrder_amount(Double order_amount) {
		this.order_amount = order_amount;
	}

	/**
	 * @return
	 */
	public Double getOrder_price() {
		return order_price;
	}

	/**
	 * @param order_price
	 */
	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}

	/**
	 * @return
	 */
	public String getOrder_supplier() {
		return order_supplier;
	}

	/**
	 * @param order_supplier
	 */
	public void setOrder_supplier(String order_supplier) {
		this.order_supplier = order_supplier;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", descriptionOfOrder=" + descriptionOfOrder + ", manager=" + manager
				+ ", status=" + status + ", deliveryDate=" + deliveryDate + ", orderDate=" + orderDate + ", order_name="
				+ order_name + ", order_phoneNumber=" + order_phoneNumber + ", order_amount=" + order_amount
				+ ", order_price=" + order_price + ", order_supplier=" + order_supplier + "]";
	}

}
