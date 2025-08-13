package com.ttd.microsoftlistsunittest;

import com.ttd.microsoftlistsunittest.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicrosoftlistsunittestApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MicrosoftlistsunittestApplication.class);
    private final AccountService accountService;

    public MicrosoftlistsunittestApplication(AccountService accountService) {
        this.accountService = accountService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrosoftlistsunittestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting database check...");
        try {
            var accounts = accountService.findAll();
            if (accounts.isEmpty()) {
                logger.warn("No accounts found in the database!");
            } else {
                logger.info("Found {} accounts:", accounts.size());
                accounts.forEach(account ->
                        logger.info("Account: {} {}", account.getFirstName(), account.getLastName()));
            }
        } catch (Exception e) {
            logger.error("Error accessing database: {}", e.getMessage());
            throw e;
        }
        logger.info("Database check completed.");
    }
}