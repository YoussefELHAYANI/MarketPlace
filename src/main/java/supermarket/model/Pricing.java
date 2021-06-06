package supermarket.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import supermarket.model.exception.ProductNotFoundException;
import supermarket.model.item.Item;

public class Pricing {
	private final Logger logger =  Logger.getLogger(this.getClass());

	public BigDecimal calculateTotal(List<Item> items, DiscountType type) {
		BigDecimal total;
		switch (type) {
		case DISCOUNT_2FOR_ONE:
			total = discount2One(items);
			break;
		case DISCOUNT_3ONE_EURO:
			total = dicount3forOneEuro(items);
			break;
		default:
			total = regularCount(items);
		}
		return total;

	}

	public BigDecimal regularCount(List<Item> items) {
		return items.stream().map(Item::price).reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2,
				RoundingMode.HALF_UP);
	}

	public BigDecimal discount2One(List<Item> items) {
		List<BigDecimal> discount = new ArrayList<>();
		Map<String, Long> counting = items.stream()
				.collect(Collectors.groupingBy(Item::getProductId, Collectors.counting()));
		counting.forEach((id, freq) -> {
			freq = freq / 2 + freq % 2;
			BigDecimal itemDiscount;
			try {
				itemDiscount = getPrice(id, items).multiply(new BigDecimal(freq));
				discount.add(itemDiscount);
			} catch (ProductNotFoundException e) {
				logger.error(e);
			}

		});
		return discount.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);

	}

	public BigDecimal dicount3forOneEuro(List<Item> items) {
		List<BigDecimal> discount = new ArrayList<>();
		Map<String, Long> counting = items.stream()
				.collect(Collectors.groupingBy(Item::getProductId, Collectors.counting()));
		counting.forEach((id, freq) -> {

			BigDecimal itemDiscount;
			try {
				itemDiscount = getPrice(id, items).multiply(new BigDecimal(freq%3)).add(new BigDecimal(freq/3));
				discount.add(itemDiscount);
			} catch (ProductNotFoundException e) {
				logger.error(e);
			}
		});
		return discount.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);

	}

	private BigDecimal getPrice(String product, List<Item> items) throws ProductNotFoundException {
		Optional<Item> itemOp = items.stream().filter(pre -> pre.getProductId().equals(product)).findFirst();
		if (itemOp.isPresent()) {
			return itemOp.get().price().setScale(2, RoundingMode.HALF_UP);
		} else {
			throw new ProductNotFoundException();
		}
	}

}
