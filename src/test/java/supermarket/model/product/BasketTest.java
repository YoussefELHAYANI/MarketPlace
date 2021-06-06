package supermarket.model.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import supermarket.model.DiscountType;
import supermarket.model.basket.Basket;
import supermarket.model.item.ItemMass;
import supermarket.model.item.ItemQuantity;

public class BasketTest extends TestCase {

	private Basket basket;

	@BeforeEach
	public void init() {
		WeighedProduct banane = new WeighedProduct(new BigDecimal(10), "Banane");
		Product mouchoire = new Product(new BigDecimal(5), "mouchoire");
		Product pizza = new Product(new BigDecimal(10), "pizza");
		ItemQuantity itemMouchoire = new ItemQuantity(mouchoire);
		ItemQuantity itemPizza = new ItemQuantity(pizza);
		ItemMass itemBanane = new ItemMass(banane, new BigDecimal(3));
		basket = new Basket();
		basket.add(itemMouchoire);
		basket.add(itemMouchoire);
		basket.add(itemMouchoire);
		basket.add(itemBanane);
		basket.add(itemPizza);
		basket.add(itemPizza);
	}
	
	@Test
	public void regularPriceTest() {

		basket.total(DiscountType.valueOf("REGULAR"));
		Assertions.assertEquals(basket.total(DiscountType.valueOf("REGULAR")),
				new BigDecimal(65).setScale(2, RoundingMode.HALF_UP));
	}

	@Test
	public void twoForOnePriceTest() {
		basket.total(DiscountType.valueOf("DISCOUNT_2FOR_ONE"));
		Assertions.assertEquals(basket.total(DiscountType.valueOf("DISCOUNT_2FOR_ONE")),
				new BigDecimal(50).setScale(2, RoundingMode.HALF_UP));
	}

	@Test
	public void treeOneFreePriceTest() {
		basket.total(DiscountType.valueOf("DISCOUNT_3ONE_EURO"));
		Assertions.assertEquals(basket.total(DiscountType.valueOf("DISCOUNT_3ONE_EURO")),
				new BigDecimal(51).setScale(2, RoundingMode.HALF_UP));
	}

	
}
