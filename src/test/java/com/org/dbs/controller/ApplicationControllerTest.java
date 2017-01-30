package com.org.dbs.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.env.Environment;

import com.org.dbs.service.ApplicationService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class ApplicationControllerTest {

	@Tested
	ApplicationController applicationController;

	@Injectable
	HttpServletResponse response;

	@Injectable
	ApplicationService applicationService;

	@Injectable
	Environment env;

	@Mocked
	Paths paths;

	@Mocked
	Path path;

	@Mocked
	Files files;

	Stream stream;

	@Test
	public void isSuggestionDataAvailableOnRestCall() throws IOException {

		stream = Stream.of("1000 1000", "2000 2000");
		new Expectations() {
			{
				env.getProperty((String) any);
				returns("C:\\data.txt");
				Paths.get((String) any);
				returns(path);
				files.lines((Path) any);
				returns(stream);
			}
		};
		Assert.assertEquals(null, applicationController.getSuggestion(1000, response));
	}

}
