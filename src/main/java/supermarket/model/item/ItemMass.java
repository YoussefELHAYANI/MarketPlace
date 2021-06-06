package supermarket.model.item;

import java.math.BigDecimal;

import supermarket.model.product.WeighedProduct;

public class ItemMass implements Item {

	private final WeighedProduct product;
	
	private final BigDecimal weightInKilos;

	public ItemMass(final WeighedProduct product, final BigDecimal weightInKilos) {
		this.product = product;
		this.weightInKilos = weightInKilos;
	}

	public String getProductId() {
		return product.getId();
	}
	public BigDecimal price() {
		return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
