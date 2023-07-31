package com.giantLink.RH.utilities;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.HolidayBalance;
import com.giantLink.RH.entities.Permission;
import com.giantLink.RH.entities.Role;
import com.giantLink.RH.models.response.RoleResponse;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.HolidayBalanceRepository;
import com.giantLink.RH.repositories.PermissionRepository;
import com.giantLink.RH.repositories.RoleRepository;
import com.giantLink.RH.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class DatabaseUtility {

    private final EmployeeRepository employeeRepository;
    private final HolidayBalanceRepository holidayBalanceRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;


    public void initDatabase() {
        Logger.getLogger("Database utility").info("Seeding database ...");
        initRoles();
        initEmployees();
        Logger.getLogger("Database utility").info("Database seeding complete");
    }

    public void initEmployees() {
//        Check table is empty
        if (employeeRepository.count() > 0) return;

        Employee employee1 = Employee.builder()
                .firstName("anass")
                .lastName("el yaagoubi")
                .cin("cd256841")
                .email("anass@gmail.com")
                .build();
        Employee employee2 = Employee.builder()
                .firstName("Kamal")
                .lastName("el ghazali")
                .email("kamal@gmail.com")
                .cin("z555888")
                .build();
        Employee employee3 = Employee.builder()
                .firstName("kaoutar")
                .lastName("wahbi")
                .cin("g455558")
                .email("kaoutar@gmail.com")
                .build();
        Employee employee4 = Employee.builder()
                .firstName("chaimae")
                .lastName("el hamdouni")
                .cin("cd99887")
                .email("chaimae@gmail.com")
                .build();
        Employee employee5 = Employee.builder()
                .firstName("douae")
                .lastName("lazrak")
                .cin("cd558889")
                .email("douae@gmail.com")
                .build();
        Employee employee6 = Employee.builder()
                .firstName("said")
                .lastName("azzouzi")
                .cin("z778549")
                .email("said@gmail.com")
                .build();
        Employee employee7 = Employee.builder()
                .firstName("hicham")
                .lastName("soussi")
                .cin("z985221")
                .email("hicham@gmail.com")
                .build();

        HolidayBalance holidayBalance1 = new HolidayBalance();
        HolidayBalance holidayBalance2 = new HolidayBalance();
        HolidayBalance holidayBalance3 = new HolidayBalance();
        HolidayBalance holidayBalance4 = new HolidayBalance();
        HolidayBalance holidayBalance5 = new HolidayBalance();
        HolidayBalance holidayBalance6 = new HolidayBalance();
        HolidayBalance holidayBalance7 = new HolidayBalance();

        holidayBalanceRepository.saveAll(Arrays.asList(
                holidayBalance1,
                holidayBalance2,
                holidayBalance3,
                holidayBalance4,
                holidayBalance5,
                holidayBalance6,
                holidayBalance7
        ));

        employee1.setHolidayBalance(holidayBalance1);
        employee2.setHolidayBalance(holidayBalance2);
        employee3.setHolidayBalance(holidayBalance3);
        employee4.setHolidayBalance(holidayBalance4);
        employee5.setHolidayBalance(holidayBalance5);
        employee6.setHolidayBalance(holidayBalance6);
        employee7.setHolidayBalance(holidayBalance7);

        employeeRepository.saveAll(Arrays.asList(
                employee1,
                employee2,
                employee3,
                employee4,
                employee5,
                employee6,
                employee7
        ));

    }

    public void initRoles() {
    // Check if roles already exist. If yes, no need to add them again.
        if (roleRepository.count() > 0)
            return;

    // Create individual permission objects for different actions.
        Permission readPermission = Permission.builder().namePermission("ADMIN_READ").build();
        Permission updatePermission = Permission.builder().namePermission("ADMIN_UPDATE").build();
        Permission createPermission = Permission.builder().namePermission("ADMIN_CREATE").build();
        Permission deletePermission = Permission.builder().namePermission("ADMIN_DELETE").build();
        Permission readPermissionManager = Permission.builder().namePermission("MANAGER_READ").build();
        Permission updatePermissionManager = Permission.builder().namePermission("MANAGER_UPDATE").build();
        Permission createPermissionManager = Permission.builder().namePermission("MANAGER_CREATE").build();
        Permission deletePermissionManager = Permission.builder().namePermission("MANAGER_DELETE").build();

    // Save all the permissions to the database.
        permissionRepository.saveAll(Arrays.asList(
                readPermission,
                updatePermission,
                createPermission,
                deletePermission,
                readPermissionManager,
                updatePermissionManager,
                createPermissionManager,
                deletePermissionManager
        ));

    // Create individual role objects and assign them role names.
        roleRepository.saveAll(Arrays.asList(
                Role.builder().roleName("ADMIN_RH").build(),
                Role.builder().roleName("MANAGER_RH").build(),
                Role.builder().roleName("DIRECTOR").build()
        ));

    // Assign permissions to the "ADMIN_RH" role.
        roleService.addPermissionToRole(1L, 1L); // ADMIN_READ
        roleService.addPermissionToRole(1L, 2L); // ADMIN_UPDATE
        roleService.addPermissionToRole(1L, 3L); // ADMIN_CREATE
        roleService.addPermissionToRole(1L, 4L); // ADMIN_DELETE

    // Assign permissions to the "MANAGER_RH" role.
        roleService.addPermissionToRole(2L, 5L); // MANAGER_READ
        roleService.addPermissionToRole(2L, 6L); // MANAGER_UPDATE
        roleService.addPermissionToRole(2L, 7L); // MANAGER_CREATE
        roleService.addPermissionToRole(2L, 8L); // MANAGER_DELETE

    }
}