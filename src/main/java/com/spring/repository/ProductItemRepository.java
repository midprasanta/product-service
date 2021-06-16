package com.spring.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.spring.entity.ProductItem;

@Repository
public class ProductItemRepository {

	private final Map<Long, ProductItem> data = new ConcurrentHashMap<>();
	private final AtomicLong nextId = new AtomicLong(3l);
	
	@PostConstruct
	private void init() {
		data.put(1L, 
				ProductItem.builder()
				.id(1L)
				.title("Book")
				.build()
				);
		
		data.put(2L, 
				ProductItem.builder()
				.id(2L)
				.title("Pencil")
				.build()
				);
		
	}

	public ProductItem getOne(Long id) {

		Optional<ProductItem> optional = Optional.ofNullable(data.get(id));

		return optional.orElseThrow(() -> new IllegalArgumentException("Item Not found " + id));
	}
	
	public Page<ProductItem> findAll(Pageable pageable){
		long offset = pageable.getOffset();
		int limit = pageable.getPageSize();
		
		List<ProductItem> productItems =  data.values().stream()
		.skip(offset).limit(limit)
		.sorted(Comparator.comparing(ProductItem::getId))
		.collect(Collectors.toList());
		
		return new PageImpl<>(productItems, pageable, limit);
		
						
	}
	
	public ProductItem save (ProductItem productItem) {
		
		if(productItem.getId()==null) {
			productItem.setId(nextId.getAndIncrement());			
		}
		
		data.put(productItem.getId(), productItem);
		
		return productItem;
	}
	
	public void delete(ProductItem productItem) {
		
		if(data.containsKey(productItem.getId())){
			data.remove(productItem.getId());
		}
		
		else throw new IllegalArgumentException("Item not found "+productItem.getId());
		
	}

}
