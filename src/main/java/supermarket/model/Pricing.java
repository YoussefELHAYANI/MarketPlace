package supermarket.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import supermarket.model.item.Item;

public class Pricing {


	public BigDecimal calculateTotal(List<Item> items, DiscountType type) {
		BigDecimal total = new BigDecimal(0);
		switch(type)
		 {
		  case DISCOUNT_2FOR_ONE:
		    
		    break;
		  case DISCOUNT_3ONE_EURO:
		    
		    break;
		  default:
			  total = regularCount(items);
		}
		return total;
		 
	}
	public BigDecimal regularCount(List<Item> items) {
		 return items.stream().map(Item::price)
	                .reduce(BigDecimal::add)
	                .orElse(BigDecimal.ZERO)
	                .setScale(2, RoundingMode.HALF_UP);
	}
	public BigDecimal discount2One(List<Item> items) {
		 Map<String, Long> counting = items.stream().collect(
	                Collectors.groupingBy(Item::getProductName, Collectors.counting()));
		 
		 counting.forEach((name,freq)->{
			
		 });
		return null;
		
	}
	public BigDecimal dicount3forOneEuro() {
		return null;
	}

}
