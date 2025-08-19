package com.web.fake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class FakeRestApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(FakeRestApplication.class);
	private static final Random random = new Random();

	public static void main(String[] args) {
		SpringApplication.run(FakeRestApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("inside run before printLogs Log generation completed. Shutting down.");
		printLogs();
		log.info("inside run after printLogs Log generation completed. Shutting down.");
	}

	private void printLogs() {
		String[] users = {"Alice", "Bob", "Charlie", "Diana", "Eve"};
		long startTime = System.currentTimeMillis();
		long duration = 60_000; // 1 minute

		while (System.currentTimeMillis() - startTime < duration) {
			try {
				int id = random.nextInt(1000);
				String user = users[random.nextInt(users.length)];

				switch (random.nextInt(6)) {
					case 0 -> log.trace("TRACE: Checking session for {}", user);
					case 1 -> log.debug("DEBUG: Fetching data for userId={}", id);
					case 2 -> log.info("INFO: User {} logged in successfully", user);
					case 3 -> log.warn("WARN: Slow response for {}", user);
					case 4 -> log.error("ERROR: Failed to process request {}", id);
					case 5 -> throw new IllegalStateException("Simulated failure for ID=" + id);
				}
			} catch (Exception e) {
				log.error("EXCEPTION: Something went wrong", e);
			}

			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
		log.info("Log generation completed. Shutting down.");
	}
}
