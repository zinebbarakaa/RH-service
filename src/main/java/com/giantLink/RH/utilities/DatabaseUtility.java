package com.giantLink.RH.utilities;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.HolidayBalance;
import com.giantLink.RH.entities.Permission;
import com.giantLink.RH.entities.Role;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.HolidayBalanceRepository;
import com.giantLink.RH.repositories.PermissionRepository;
import com.giantLink.RH.repositories.RoleRepository;
import com.giantLink.RH.services.RoleService;
import com.giantLink.RH.services.UserService;
import lombok.RequiredArgsConstructor;
import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.repositories.RequestHolidayRepository;
import com.giantLink.RH.repositories.RequestStatusRepository;
import com.giantLink.RH.entities.*;
import com.giantLink.RH.enums.State;
import com.giantLink.RH.models.request.WarningRequest;
import com.giantLink.RH.repositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

import java.util.Calendar;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class DatabaseUtility {


    private final EmployeeRepository employeeRepository;

    private final HolidayBalanceRepository holidayBalanceRepository;

    private final RequestStatusRepository requestStatusRepository;

    private final RequestHolidayRepository requestHolidayRepository;

    private final WarningTypeRepository warningTypeRepository;
    private final PermissionRepository permissionRepository;

    private final RoleRepository roleRepository;

    private final RoleService roleService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    WarningRepository warningRepository;


    private final PredefinedHolidayRepository predefinedHolidayRepository;

    public void initDatabase() {
        Logger.getLogger("Database utility").info("Seeding database ...");
        initEmployees();
        initRoles();
        iniRequestStatus();
        initWarningTypes();
        initRequestHoliday();
        Logger.getLogger("Database utility").info("Database seeding complete");
    }


    public void iniRequestStatus() {
        if (requestStatusRepository.count() > 0) return;
        RequestStatus requestStatus1 = RequestStatus.builder().type(State.PENDING).build();
        RequestStatus requestStatus2 = RequestStatus.builder().type(State.ACCEPTED).build();
        RequestStatus requestStatus3 = RequestStatus.builder().type(State.REFUSED).build();
        requestStatusRepository.saveAll(Arrays.asList(
                requestStatus1,
                requestStatus2,
                requestStatus3
        ));

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
                .lastName("asbika")
                .cin("z985221")
                .email("hicham.asbika@gmail.com")
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

        Permission readPermissionSupper_admin = Permission.builder().namePermission("SUPER_ADMIN_READ").build();
        Permission updatePermissionSuper_Admin = Permission.builder().namePermission("SUPER_ADMIN_UPDATE").build();
        Permission createPermissionSuper_Admin = Permission.builder().namePermission("SUPER_ADMIN_CREATE").build();
        Permission deletePermissionSuper_Admin = Permission.builder().namePermission("SUPER_ADMIN_DELETE").build();

        // Save all the permissions to the database.
        permissionRepository.saveAll(Arrays.asList(
                readPermission,
                updatePermission,
                createPermission,
                deletePermission,
                readPermissionManager,
                updatePermissionManager,
                createPermissionManager,
                deletePermissionManager,
                readPermissionSupper_admin,
                createPermissionSuper_Admin,
                updatePermissionSuper_Admin,
                deletePermissionSuper_Admin
        ));

        // Create individual role objects and assign them role names.

        Role admin_rh = Role.builder().roleName("ADMIN_RH").build();
        Role manager = Role.builder().roleName("MANAGER_RH").build();
        Role director = Role.builder().roleName("DIRECTOR").build();
        Role super_admin = Role.builder().roleName("SUPER_ADMIN").build();
        roleRepository.saveAll(Arrays.asList(
                admin_rh,
                manager,
                director,
                super_admin

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

        roleService.addPermissionToRole(4L, 1L);
        roleService.addPermissionToRole(4L, 2L);
        roleService.addPermissionToRole(4L, 3L);
        roleService.addPermissionToRole(4L, 4L);

        // Assign permissions to the "MANAGER_RH" role.
        roleService.addPermissionToRole(4L, 5L);
        roleService.addPermissionToRole(4L, 6L);
        roleService.addPermissionToRole(4L, 7L);
        roleService.addPermissionToRole(4L, 8L);

        roleService.addPermissionToRole(4L, 9L);
        roleService.addPermissionToRole(4L, 10L);
        roleService.addPermissionToRole(4L, 11L);
        roleService.addPermissionToRole(4L, 12L);

        List<Role> roles = new ArrayList<>();
//        roles.add(admin_rh);
//        roles.add(manager);
//        roles.add(director);
        roles.add(super_admin);

        Employee employee_admin = Employee.builder()
                .firstName("zineb")
                .lastName("baraka")
                .cin("cd11111")
                .email("zineb@gmail.com")
                .build();

        employeeRepository.save(employee_admin);
        User user = User.builder().roles(roles).password(passwordEncoder.encode("1234")).employee(employee_admin).username(employee_admin.getCin()).build();
        userRepository.save(user);


    }


    public void initHolidayRequest() {
        if (requestHolidayRepository.count() > 0) return;
        Employee employee1 = employeeRepository.findById(1L).get();
        Employee employee2 = employeeRepository.findById(5L).get();
        Employee employee3 = employeeRepository.findById(3L).get();

        RequestStatus requestStatus1 = requestStatusRepository.findById(1L).get();
        RequestStatus requestStatus2 = requestStatusRepository.findById(2L).get();
        RequestStatus requestStatus3 = requestStatusRepository.findById(3L).get();

        RequestHoliday requestHoliday1 = RequestHoliday.builder()
                .numberOfDays(5)
                .startDate(new Date())
                .returnDate(new Date())
                .finishDate(new Date())
                .numberOfPaidLeaves(0L)
                .numberOfUnpaidLeaves(1L)
                .build();
        RequestHoliday requestHoliday2 = RequestHoliday.builder()
                .numberOfDays(4)
                .startDate(new Date())
                .returnDate(new Date())
                .finishDate(new Date())
                .numberOfPaidLeaves(0L)
                .numberOfUnpaidLeaves(1L)
                .build();
        RequestHoliday requestHoliday3 = RequestHoliday.builder()
                .numberOfDays(3)
                .startDate(new Date())
                .returnDate(new Date())
                .finishDate(new Date())
                .numberOfPaidLeaves(0L)
                .numberOfUnpaidLeaves(1L)
                .build();

        requestHoliday1.setEmployee(employee3);
        requestHoliday2.setEmployee(employee1);
        requestHoliday3.setEmployee(employee2);

        requestHoliday1.setStatus(requestStatus1);
        requestHoliday2.setStatus(requestStatus2);
        requestHoliday3.setStatus(requestStatus3);

        requestHolidayRepository.saveAll(Arrays.asList(
                requestHoliday1,
                requestHoliday2,
                requestHoliday3
        ));
    }

    public void initWarningTypes() {

        if (warningTypeRepository.count() > 0) return;

        warningTypeRepository.saveAll(Arrays.asList(
                new WarningType("Unjustified Absence", " An unjustified absence warning is given to an employee"
                        + " when they have been absent from work without providing a valid reason or without obtaining proper approval. "),
                new WarningType("Ethical Violation", " If an employee is found to have violated the company's code of ethics or engaged "
                        + "in unethical behavior, they may receive an ethical violation warning "),
                new WarningType("Verbal Warning", " A verbal warning is usually the initial step in addressing a performance or behavioral concern"),
                new WarningType("Written Warning", " If the performance or behavior issue persists after a verbal warning, a written warning may be issued. "),
                new WarningType("Performance Improvement Plan (PIP)", " A Performance Improvement Plan is a structured document that outlines"
                        + " specific performance goals and expectations for an employee over a specified period. "),
                new WarningType("Warning for Absence Without Request", " Warning for Absence Without Request. ")));
    }

    public void initRequestHoliday() {
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.set(Calendar.YEAR, 2023);
        startDateCalendar.set(Calendar.MONTH, Calendar.JULY);
        startDateCalendar.set(Calendar.DAY_OF_MONTH, 26);

        Calendar startDateCalendar2 = Calendar.getInstance();
        startDateCalendar2.set(Calendar.YEAR, 2023);
        startDateCalendar2.set(Calendar.MONTH, Calendar.JULY);
        startDateCalendar2.set(Calendar.DAY_OF_MONTH, 29);


        RequestHoliday requestHoliday = new RequestHoliday();
        requestHoliday.setNumberOfDays(2);
        requestHoliday.setStartDate(startDateCalendar.getTime());
        requestHoliday.setFinishDate(startDateCalendar2.getTime());
        requestHoliday.setEmployee(employeeRepository.findById(7L).get());

        RequestStatus requestStatus = RequestStatus.builder()
                .type(State.PENDING)
                .request(requestHoliday)
                .build();

        requestStatusRepository.save(requestStatus);
        requestHoliday.setStatus(requestStatus);

        requestHolidayRepository.save(requestHoliday);


    }


    // Fill the Predefined-Holidays table
    public void initPredefinedHoliday() {
        List<PredefinedHoliday> list = new ArrayList<>();

        PredefinedHoliday holiday_1 = new PredefinedHoliday("Holiday_1", "Country_1", "Description_1");
        holiday_1.setNumberOfDays(1L);
        holiday_1.setStartDate(new Date());
        holiday_1.setEndDate(new Date());
        list.add(holiday_1);

        PredefinedHoliday holiday_2 = new PredefinedHoliday("Holiday_2", "Country_2", "Description_2");
        holiday_2.setNumberOfDays(2L);
        holiday_2.setStartDate(new Date());
        holiday_2.setEndDate(new Date());
        list.add(holiday_2);

        PredefinedHoliday holiday_3 = new PredefinedHoliday("Holiday_3", "Country_3", "Description_3");
        holiday_3.setNumberOfDays(1L);
        holiday_3.setStartDate(new Date());
        holiday_3.setEndDate(new Date());
        list.add(holiday_3);

        predefinedHolidayRepository.saveAll(list);
    }
}
