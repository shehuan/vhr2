package com.sh.vhr.utils;

import com.sh.vhr.model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class POIUtils {
    public static ResponseEntity<byte[]> employee2Excel(List<Employee> employeeList) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        SXSSFSheet sheet = workbook.createSheet("员工信息表");
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 5 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 10 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 5 * 256);
        sheet.setColumnWidth(6, 5 * 256);
        sheet.setColumnWidth(7, 5 * 256);
        sheet.setColumnWidth(8, 5 * 256);
        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 30 * 256);
        sheet.setColumnWidth(12, 10 * 256);
        sheet.setColumnWidth(13, 10 * 256);
        sheet.setColumnWidth(14, 10 * 256);
        sheet.setColumnWidth(15, 10 * 256);
        sheet.setColumnWidth(16, 15 * 256);
        sheet.setColumnWidth(17, 15 * 256);
        sheet.setColumnWidth(18, 15 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        sheet.setColumnWidth(20, 5 * 256);
        sheet.setColumnWidth(21, 10 * 256);
        sheet.setColumnWidth(22, 10 * 256);
        sheet.setColumnWidth(23, 10 * 256);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        SXSSFRow headerRow = sheet.createRow(0);
        String[] sheetHeaderData = {"姓名", "性别", "工号", "出生日期", "身份证号码", "婚姻", "民族", "籍贯", "政治面貌", "电子邮件",
                "电话号码", "联系地址", "所属部门", "职位", "职称", "聘用形式", "入职日期", "转正日期", "合同起始日期",
                "合同终止日期", "合同期限", "毕业院校", "专业", "最高学历"};
        for (int i = 0; i < sheetHeaderData.length; i++) {
            SXSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(sheetHeaderData[i]);
            cell.setCellStyle(headerStyle);
        }

        DataFormat dataFormat = workbook.createDataFormat();
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(dataFormat.getFormat("m/d/yy"));

        for (int i = 0; i < employeeList.size(); i++) {
            SXSSFRow row = sheet.createRow(i + 1);
            Employee e = employeeList.get(i);
            row.createCell(0).setCellValue(e.getName());
            row.createCell(1).setCellValue(e.getGender());
            row.createCell(2).setCellValue(e.getWorkID());
            SXSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(e.getBirthday());
            cell3.setCellStyle(dateCellStyle);
            row.createCell(4).setCellValue(e.getIdCard());
            row.createCell(5).setCellValue(e.getWedlock());
            row.createCell(6).setCellValue(e.getNation().getName());
            row.createCell(7).setCellValue(e.getNativePlace());
            row.createCell(8).setCellValue(e.getPoliticsstatus().getName());
            row.createCell(9).setCellValue(e.getEmail());
            row.createCell(10).setCellValue(e.getPhone());
            row.createCell(11).setCellValue(e.getAddress());
            row.createCell(12).setCellValue(e.getDepartment().getName());
            row.createCell(13).setCellValue(e.getPosition().getName());
            row.createCell(14).setCellValue(e.getJobLevel().getName());
            row.createCell(15).setCellValue(e.getEngageForm());
            SXSSFCell cell16 = row.createCell(16);
            cell16.setCellValue(e.getBeginDate());
            cell16.setCellStyle(dateCellStyle);
            SXSSFCell cell17 = row.createCell(17);
            cell17.setCellValue(e.getConversionTime());
            cell17.setCellStyle(dateCellStyle);
            SXSSFCell cell18 = row.createCell(18);
            cell18.setCellValue(e.getBeginContract());
            cell18.setCellStyle(dateCellStyle);
            SXSSFCell cell19 = row.createCell(19);
            cell19.setCellValue(e.getEndContract());
            cell19.setCellStyle(dateCellStyle);
            row.createCell(20).setCellValue(e.getContractTerm());
            row.createCell(21).setCellValue(e.getSchool());
            row.createCell(22).setCellValue(e.getSpecialty());
            row.createCell(23).setCellValue(e.getTiptopDegree());
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("员工表.xlsx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static List<Employee> excel2Employee(MultipartFile file,
                                                List<Nation> allNations,
                                                List<Department> allDepartments,
                                                List<Politicsstatus> allPoliticsstatus,
                                                List<Position> allPositions,
                                                List<JobLevel> allJobLevels) {

        List<Employee> employeeList = new ArrayList<>();
        Employee employee = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            // 数据行数
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < physicalNumberOfRows; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                employee = new Employee();
                for (int j = 0; j < physicalNumberOfCells; j++) {
                    XSSFCell cell = row.getCell(j);
                    switch (cell.getCellType()) {
                        case STRING:
                            String value = cell.getStringCellValue();
                            switch (j) {
                                case 0:
                                    employee.setName(value);
                                    break;
                                case 1:
                                    employee.setGender(value);
                                    break;
                                case 2:
                                    employee.setWorkID(value);
                                    break;
                                case 4:
                                    employee.setIdCard(value);
                                    break;
                                case 5:
                                    employee.setWedlock(value);
                                    break;
                                case 6:
                                    Optional<Nation> nation = allNations.stream().filter(n -> n.getName().equals(value)).findFirst();
                                    if (nation.isPresent()) {
                                        employee.setNationId(nation.get().getId());
                                    }
                                    break;
                                case 7:
                                    employee.setNativePlace(value);
                                    break;
                                case 8:
                                    Optional<Politicsstatus> polit = allPoliticsstatus.stream().filter(n -> n.getName().equals(value)).findFirst();
                                    if (polit.isPresent()) {
                                        employee.setPoliticId(polit.get().getId());
                                    }
                                    break;
                                case 9:
                                    employee.setEmail(value);
                                    break;
                                case 10:
                                    employee.setPhone(value);
                                    break;
                                case 11:
                                    employee.setAddress(value);
                                    break;
                                case 12:
                                    Optional<Department> department = allDepartments.stream().filter(n -> n.getName().equals(value)).findFirst();
                                    if (department.isPresent()) {
                                        employee.setDepartmentId(department.get().getId());
                                    }
                                    break;
                                case 13:
                                    Optional<Position> position = allPositions.stream().filter(n -> n.getName().equals(value)).findFirst();
                                    if (position.isPresent()) {
                                        employee.setPosId(position.get().getId());
                                    }
                                    break;
                                case 14:
                                    Optional<JobLevel> jobLevel = allJobLevels.stream().filter(n -> n.getName().equals(value)).findFirst();
                                    if (jobLevel.isPresent()) {
                                        employee.setJobLevelId(jobLevel.get().getId());
                                    }
                                    break;
                                case 15:
                                    employee.setEngageForm(value);
                                    break;
                                case 21:
                                    employee.setSchool(value);
                                    break;
                                case 22:
                                    employee.setSpecialty(value);
                                    break;
                                case 23:
                                    employee.setTiptopDegree(value);
                                    break;
                            }
                            break;
                        default: {
                            switch (j) {
                                case 3:
                                    employee.setBirthday(cell.getDateCellValue());
                                    break;
                                case 16:
                                    employee.setBeginDate(cell.getDateCellValue());
                                    break;
                                case 17:
                                    employee.setConversionTime(cell.getDateCellValue());
                                    break;
                                case 18:
                                    employee.setBeginContract(cell.getDateCellValue());
                                    break;
                                case 19:
                                    employee.setEndContract(cell.getDateCellValue());
                                    break;
                                case 20:
                                    employee.setContractTerm(cell.getNumericCellValue());
                                    break;
                            }
                        }
                    }
                }
                employeeList.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
