package com.org.dbs.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.org.dbs.domain.Item;

@Service
public class ApplicationService {

	public Item getSuggestion(int time, ArrayList<Item> items) {
		Optional<Item> itemContainer = items.stream().filter(item -> item.getTime() <= time).reduce((a, b) -> b);
		return itemContainer.isPresent() ? itemContainer.get() : null;
	}

}
