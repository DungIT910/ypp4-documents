package com.ttd.microsoftlistsunittest;

import com.ttd.microsoftlistsunittest.dto.list.ListSummaryDto;
import com.ttd.microsoftlistsunittest.service.ListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

@EnableCaching
@SpringBootApplication
public class MicrosoftlistsunittestApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MicrosoftlistsunittestApplication.class);
    private final ListService listService;

    public MicrosoftlistsunittestApplication(ListService listService) {
        this.listService = listService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrosoftlistsunittestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting database check...");
        try {
            List<ListSummaryDto> list = listService.getRecentListsByAccountId(3);
            if (list == null) {
                logger.warn("No list with id = 1 found in the database!");
            } else {
                logger.info("Found 1 lists");
                logger.info("list: {} {}", list.get(0).getAccessedAt(), list.get(0).getIcon());
            }
        } catch (Exception e) {
            logger.error("Error accessing database: {}", e.getMessage());
            throw e;
        }
        logger.info("Database check completed.");
    }
}
