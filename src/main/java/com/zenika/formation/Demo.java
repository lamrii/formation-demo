package com.zenika.formation;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * A test set to control Test integration in CI tools
 */
@EnableAutoConfiguration
@EnableWebMvc
@Configuration
@Controller
public class Demo {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.debug("########################### starting demo ##############################");

		String port = SpringApplication.run(Demo.class, args).getEnvironment().getProperty("server.port");

		System.out.println("Crappy  = " + new Crappy());

		LOGGER.info("##################### demo started on port {} ########################", port);
	}

	/**
	 * @param record
	 * @return
	 * @throws ProcessException
	 */
	@RequestMapping(value = "/", method = GET, produces = "text/html")
	@ResponseBody
	public String home() {
		return "<html><body><p>Hello home !!!</p></body></html>";
	}

	/**
	 * @param record
	 * @return
	 * @throws ProcessException
	 */
	@RequestMapping(value = "/{number}", method = GET, produces = "text/html")
	@ResponseBody
	public String home(@PathVariable int number) {
		return IntStream.range(1, number + 1).mapToObj(i -> "Hello World " + i)
				.collect(Collectors.joining("<br/>", "<html><body><p>", "</p></body></html>"));
	}

}
