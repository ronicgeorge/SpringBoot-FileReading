package com.org.dbs.service;



import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.org.dbs.domain.Item;

import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class ApplicationServiceTest {
	
	@Tested
	ApplicationService applicationService;
	

	@Test
	public void isSuggestionDataAvailableOnRestCall() throws IOException {
		 ArrayList<Item> items = new ArrayList<Item>();
		 items.add(new Item(200,100));
		 items.add(new Item(300,200));
		 Item item = applicationService.getSuggestion(300,items);
		 assertEquals(200,item.getTime());
	}

}
