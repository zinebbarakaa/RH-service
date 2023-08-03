package com.giantLink.RH;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Permission;
import com.giantLink.RH.entities.Role;
import com.giantLink.RH.entities.User;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.PermissionRepository;
import com.giantLink.RH.repositories.RoleRepository;
import com.giantLink.RH.repositories.UserRepository;
import com.giantLink.RH.utilities.DatabaseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


@SpringBootApplication

public class RhServiceApplication implements CommandLineRunner
{
	@Autowired
	DatabaseUtility databaseUtility;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

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
