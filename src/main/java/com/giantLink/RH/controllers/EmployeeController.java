package com.giantLink.RH.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.giantLink.RH.exceptions.UnauthorizedAccessException;
import com.giantLink.RH.models.response.SuccessResponse;
import com.giantLink.RH.services.UserService;


import com.giantLink.RH.models.response.RequestHolidayResponse;
import com.giantLink.RH.models.response.SuccessResponse;
import com.giantLink.RH.services.HolidayBalanceService;
import com.giantLink.RH.services.RequestAbsenceService;
import com.giantLink.RH.services.impl.RequestHolidayServiceImpl;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.response.EmployeeResponse;
import com.giantLink.RH.models.response.RequestAbsenceResponse;
import com.giantLink.RH.services.EmployeeService;

@CrossOrigin("*")
@RestController

@PreAuthorize("hasRole('ADMIN_RH')")
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired

    private UserService userService;

    private RequestHolidayServiceImpl requestHolidayService;
    @Autowired
    private HolidayBalanceService holidayBalanceService;
    
    @Autowired
    private RequestAbsenceService requestAbsenceService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_CREATE')")
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody @Validated EmployeeRequest request)
    {


        EmployeeResponse employeeResponse = employeeService.add(request);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees()
    {
        List<EmployeeResponse> employees = employeeService.get();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("get-one/{id}")
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN_RH"))) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (!employeeService.doesEmployeeIdBelongToUser(id, userDetails.getUsername())) {
                throw new UnauthorizedAccessException("You are not authorized to access this employee's information.");
            }
        }
        EmployeeResponse employee = employeeService.get(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest request) {

        EmployeeResponse updatedEmployee = employeeService.update(request, id);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        SuccessResponse successResponse = SuccessResponse.builder()
                .message("Employee deleted successfully")
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
    public String currentDate = dateFormat.format(new Date());
    // Must be has role Administration RH

    @GetMapping("/holidays/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<RequestHolidayResponse> requestHolidays = requestHolidayService.getAllRequestHolidays();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        response.setHeader("Content-Disposition", "attachment; filename=\"Holidays request " + currentDate + ".xlsx\"");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Request Holiday for employees");
        // Create a date style

        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy - HH:mm:ss"));


        CellStyle style = workbook.createCellStyle();
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);

        // Create headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Employee");
        headerRow.getCell(0).setCellStyle(style);
        headerRow.createCell(1).setCellValue("CIN");
        headerRow.getCell(1).setCellStyle(style);
        headerRow.createCell(2).setCellValue("Request Status");
        headerRow.getCell(2).setCellStyle(style);
        headerRow.createCell(3).setCellValue("Numbers of days");
        headerRow.getCell(3).setCellStyle(style);
        headerRow.createCell(4).setCellValue("Start Date");
        headerRow.getCell(4).setCellStyle(style);
        headerRow.createCell(5).setCellValue("Finish Date");
        headerRow.getCell(5).setCellStyle(style);
        headerRow.createCell(6).setCellValue("Return Date");
        headerRow.getCell(6).setCellStyle(style);
        headerRow.createCell(7).setCellValue("Request Date");
        headerRow.getCell(7).setCellStyle(style);

//        headerRow.createCell(8).setCellValue("Created at");
//        headerRow.getCell(8).setCellStyle(style);
//        headerRow.createCell(9).setCellValue("Updated at");
//        headerRow.getCell(9).setCellStyle(style);

        // Recuperation of content

        int rowNum = 1;
        for (RequestHolidayResponse data : requestHolidays) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.getEmployee().getFirstName() +" "+ data.getEmployee().getLastName());
            sheet.autoSizeColumn(0);

            row.createCell(1).setCellValue(data.getEmployee().getCin());
            sheet.autoSizeColumn(1);

            row.createCell(2).setCellValue(data.getStatus().getType().toString());
            sheet.autoSizeColumn(2);


            row.createCell(3).setCellValue(data.getNumberOfDays());
            sheet.autoSizeColumn(3);

            row.createCell(4).setCellValue(data.getStartDate());
            row.getCell(4).setCellStyle(dateCellStyle);
            sheet.autoSizeColumn(4);

            row.createCell(5).setCellValue(data.getFinishDate());
            row.getCell(5).setCellStyle(dateCellStyle);
            sheet.autoSizeColumn(5);

            row.createCell(6).setCellValue(data.getReturnDate());
            row.getCell(6).setCellStyle(dateCellStyle);
            sheet.autoSizeColumn(6);

            row.createCell(7).setCellValue(data.getReturnDate());
            row.getCell(7).setCellStyle(dateCellStyle);
            sheet.autoSizeColumn(7);

//            row.createCell(8).setCellValue(data.getCreatedAt());
//            row.getCell(8).setCellStyle(dateCellStyle);
//            sheet.autoSizeColumn(8);
//
//            row.createCell(9).setCellValue(data.getUpdatedAt());
//            row.getCell(9).setCellStyle(dateCellStyle);
//            sheet.autoSizeColumn(9);

        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
    // Must be has role Administration RH

    @GetMapping("/holidays/export/pdf")
    public void exportPDF(HttpServletResponse response) throws IOException, DocumentException {
        List<RequestHolidayResponse> requestHolidays = requestHolidayService.getAllRequestHolidays();
        response.setContentType("app/pdf");
        response.setHeader("Content-Disposition", "attachement; filename=\"Holidays request " + currentDate + ".pdf\"");


        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
        Paragraph title = new Paragraph("Holidays request for employees", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Empty Line
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(8);

        table.setWidthPercentage(100);

        table.addCell("Employee");
        table.addCell("CIN");
        table.addCell("Reqeust Status");
        table.addCell("Numbers of days");
        table.addCell("Start Date");
        table.addCell("Finish Date");
        table.addCell("Return Date");
        table.addCell("Request Date");



        for (RequestHolidayResponse data : requestHolidays) {
            table.addCell(String.valueOf(data.getEmployee().getFirstName()+" "+data.getEmployee().getLastName()));
            table.addCell(String.valueOf(data.getEmployee().getCin()));
            table.addCell(String.valueOf(data.getStatus().getType().toString()));
            table.addCell(String.valueOf(data.getNumberOfDays()));
            table.addCell(String.valueOf(data.getStartDate()));
            table.addCell(String.valueOf(data.getFinishDate()));
            table.addCell(String.valueOf(data.getReturnDate()));
            table.addCell(String.valueOf(data.getRequestDate()));

        }
        document.add(table);
        document.close();
    }

    // Must be has role Administration RH
    @GetMapping("/holidays")
    public ResponseEntity<List<RequestHolidayResponse>> getEmployeeRequestHoliday() {
        List<RequestHolidayResponse> requestHoliday = requestHolidayService.getAllRequestHolidays();
        return new ResponseEntity(requestHoliday, HttpStatus.OK);
    }

    @GetMapping("/holidays/{id}")
    public ResponseEntity<List<RequestHolidayResponse>> getEmployeeRequestHoliday(@PathVariable("id") Long employee_id) {
        List<RequestHolidayResponse> requestHoliday = requestHolidayService.getAllRequestHolidaysByIdEmployee(employee_id);
        return new ResponseEntity(requestHoliday, HttpStatus.OK);
    }

    @GetMapping("/request_absence/{id}")
    public List<RequestAbsenceResponse> getRequestAbsenceByEmployeeId(@PathVariable Long id) {
        return requestAbsenceService.getRequestAbsenceByEmployeeId(id);
    }
    
    @GetMapping("/sickness/{id}")
    public List<RequestAbsenceResponse> getSicknessByEmployee(@PathVariable Long id ) {
        return requestAbsenceService.getByEmployeeIsSickness(true, id);
    }
    
    @GetMapping("/absence/{id}")
    public List<RequestAbsenceResponse> getAbsenceByEmployee(@PathVariable Long id ) {
        return requestAbsenceService.getByEmployeeIsSickness(false, id);
    }


}
