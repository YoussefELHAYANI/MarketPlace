package supermarket.model.product;

import java.math.BigDecimal;

import supermarket.model.item.Item;
import supermarket.model.item.ItemMass;

public class WeighedProduct {
	private final String name;
	private final BigDecimal pricePerKilo;

	public WeighedProduct(final BigDecimal pricePerKilo,final String name) {
		this.name = name;
		this.pricePerKilo = pricePerKilo;
	}

	public BigDecimal pricePerKilo() {
		return pricePerKilo;
	}

	public Item weighing(final BigDecimal kilos) {
		return new ItemMass(this, kilos);
	}

	public String getName() {
		return name;
	}
}
