package supermarket.model.basket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import supermarket.model.DiscountType;
import supermarket.model.Pricing;
import supermarket.model.item.Item;

public class Basket {
	private final List<Item> items;
    private final Pricing pricing;

    public Basket() {
        this.items = new ArrayList<Item>();
        pricing = new Pricing();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    public BigDecimal total(DiscountType type) {
        return pricing.calculateTotal(Collections.unmodifiableList(items),type);
    }
}
