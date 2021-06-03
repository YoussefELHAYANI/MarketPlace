package supermarket.model.product;

import java.math.BigDecimal;

import supermarket.model.item.Item;
import supermarket.model.item.ItemQuantity;

public class Product {
	private final String name;
	private final BigDecimal pricePerUnit;

    public Product(final BigDecimal pricePerUnit, final String name) {
        this.name = name;
		this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal pricePerUnit() {
        return pricePerUnit;
    }
    public Item oneOf() {
        return new ItemQuantity(this);
    }

	public String getName() {
		return name;
	}
}
