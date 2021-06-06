package supermarket.model.item;

import java.math.BigDecimal;

import supermarket.model.product.Product;


public class ItemQuantity implements Item {
	private final Product product;

	public ItemQuantity(Product product) {
		this.product = product;
	}

	public BigDecimal price() {
		return product.pricePerUnit();
	}

	public String getProductId() {
		return product.getId();
	}
}
