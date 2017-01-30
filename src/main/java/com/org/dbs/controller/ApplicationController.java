package com.org.dbs.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.dbs.common.DBSConstants;
import com.org.dbs.domain.Item;
import com.org.dbs.service.ApplicationService;

@RestController
@RequestMapping("/dbs")
@PropertySource("classpath:dbs.properties")
public class ApplicationController {

	private final ApplicationService applicationService;

	@Autowired
	private Environment env;

	@Autowired
	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/suggestion/{time}", produces = MediaType.APPLICATION_JSON_VALUE)
	Item getSuggestion(@PathVariable int time, HttpServletResponse response) {
		ArrayList<Item> items = new ArrayList<Item>();
		Stream<String> stream = null;
		stream = getFileData(items, stream);
		Collections.sort(items, Item.ItemTimeComparator);
		stream.close();
		Item responseItem = applicationService.getSuggestion(time, items);
		response.setStatus((responseItem == null) ? 204 : 200);
		return responseItem;
	}

	private Stream<String> getFileData(ArrayList<Item> items, Stream<String> stream) {
		try {
			stream = Files.lines(Paths.get(env.getProperty(DBSConstants.FILEPATH)));
			stream.forEach(item -> {
				items.add(new Item(Integer.valueOf(item.split(" ")[0]), Integer.valueOf(item.split(" ")[1])));
			});
		} catch (Exception e) {
			// Log file related issue
			System.exit(1);
		}
		return stream;
	}
}