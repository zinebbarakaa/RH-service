package com.giantLink.RH;

import com.giantLink.RH.utilities.DatabaseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class RhServiceApplication implements CommandLineRunner
{
	@Autowired
	DatabaseUtility databaseUtility;

	public static void main(String[] args){
		SpringApplication.run(RhServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		databaseUtility.initDatabase();
		databaseUtility.initRoles();
		databaseUtility.initPredefinedHoliday();
	}

}
