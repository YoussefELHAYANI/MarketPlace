package supermarket.model.product;

import java.math.BigDecimal;

import supermarket.model.item.Item;
import supermarket.model.item.ItemQuantity;

public class Product {
	private final String id;
	private final BigDecimal pricePerUnit;

    public Product(final BigDecimal pricePerUnit, final String name) {
        this.id = name;
		this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal pricePerUnit() {
        return pricePerUnit;
    }
    public Item oneOf() {
        return new ItemQuantity(this);
    }

	public String getId() {
		return id;
	}
}
