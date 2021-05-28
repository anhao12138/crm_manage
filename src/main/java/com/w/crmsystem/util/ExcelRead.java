package com.w.crmsystem.util;

import com.w.crmsystem.domain.EmployeeExample;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/7 14:56
 * @Version 1.0
 */
public class ExcelRead<T> {

    /**
     * 传入流
     * @param inputStream
     */
    public List<EmployeeExample> ReadXls(FileInputStream inputStream){
        List<EmployeeExample> list=new ArrayList<>();
        try{

            Workbook xwb = new XSSFWorkbook(inputStream);
            // 循环工作表sheet
            for(int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {
                Sheet sheet = xwb.getSheetAt(numSheet);
                if(sheet == null) {
                    continue;
                }
                // 循环row，如果第一行是字段，则 numRow = 1
                for(int numRow = 0; numRow <= sheet.getLastRowNum(); numRow++) {
                    Row row = sheet.getRow(numRow);
                    if(row == null) {
                        continue;
                    }


                    EmployeeExample employee=new EmployeeExample();
                    employee.setUsername(getValue(row.getCell(1)));
                    employee.setRealname(getValue(row.getCell(2)));
                    employee.setTel(getValue(row.getCell(5)));
                    employee.setEmail(getValue(row.getCell(6)));
                    employee.setInputtime(new Date(getValue(row.getCell(8))));
                    employee.setDept(getValue(row.getCell(7)));
                    //初始化密码为12345
                    employee.setPassword(PasswordUtil.encodePwd("123456"));
                    employee.setBirthDay(DateChange(getValue(row.getCell(4))));
                    employee.setSex(getValue(row.getCell(3)));
                    employee.setState(1);
                    list.add(employee);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 传入流
     * @param inputStream
     */
    public List<EmployeeExample> ReadXlsx(FileInputStream inputStream){
        List<EmployeeExample> list=new ArrayList<>();
        try{

            Workbook hwb = new XSSFWorkbook(inputStream);
            // 循环工作表sheet
            for(int numSheet = 0; numSheet < hwb.getNumberOfSheets(); numSheet++) {
                Sheet sheet = hwb.getSheetAt(numSheet);
                if(sheet == null) {
                    continue;
                }
                // 循环row，如果第一行是字段，则 numRow = 1
                for(int numRow = 1; numRow <= sheet.getLastRowNum(); numRow++) {
                    Row row = sheet.getRow(numRow);
                    if(row == null) {
                        continue;
                    }
                    //xls表格中读取的数据=====>201842311
                    //xls表格中读取的数据=====>张三14
                    //xls表格中读取的数据=====>女
                    //xls表格中读取的数据=====>57
                    //xls表格中读取的数据=====>13458332381
                    //xls表格中读取的数据=====>19765664@qq.com
                    //xls表格中读取的数据=====>测试部
                    //xls表格中读取的数据=====>2021-03-25
                    EmployeeExample employee=new EmployeeExample();
                    employee.setUsername(getValue(row.getCell(1)));
                    employee.setRealname(getValue(row.getCell(2)));
                    employee.setTel(getValue(row.getCell(5)));
                    employee.setEmail(getValue(row.getCell(6)));
                    employee.setInputtime(DateChange(getValue(row.getCell(8))));
                    employee.setDept(getValue(row.getCell(7)));
                    //初始化密码为12345
                    employee.setPassword(PasswordUtil.encodePwd("123456"));
                    employee.setBirthDay(DateChange(getValue(row.getCell(4))));
                    employee.setSex(getValue(row.getCell(3)));
                    employee.setState(1);
                    list.add(employee);
//
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * CELL_TYPE_NUMERIC	数值型	    0
     * CELL_TYPE_STRING	    字符串型	    1
     * CELL_TYPE_FORMULA	公式型	    2
     * CELL_TYPE_BLANK	    空值	        3
     * CELL_TYPE_BOOLEAN	布尔型	    4
     * CELL_TYPE_ERROR	    错误  	    5
     */
    public String getValue(Cell cell){
        String value = null;
        switch(cell.getCellType()){
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                //如果为时间格式的内容
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    value=sdf.format(HSSFDateUtil.getJavaDate(cell.
                            getNumericCellValue())).toString();
                    break;
                } else {
                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;
    }

    public Date DateChange(String date){
        Date  dateTime=null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateTime= simpleDateFormat.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
}
