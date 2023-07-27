package com.giantLink.RH;

import com.giantLink.RH.utilities.DatabaseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.logging.Logger;

@SpringBootApplication

public class RhServiceApplication implements CommandLineRunner
{
	@Autowired
	DatabaseUtility databaseUtility;

	public static void main(String[] args)
	{
		SpringApplication.run(RhServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception
	{
		databaseUtility.initDatabase();
		databaseUtility.initRoles();
	}
}
