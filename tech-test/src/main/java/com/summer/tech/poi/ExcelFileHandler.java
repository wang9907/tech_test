package com.summer.tech.poi;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExcelFileHandler {
	public static final String FILE = "常见疾病120种.xlsx";

	public static void main(String[] args) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(ExcelFileHandler.class
				.getClassLoader().getResourceAsStream(FILE));
		XSSFSheet sheet = workbook.getSheetAt(0);
		int count = sheet.getPhysicalNumberOfRows();
		JSONObject depobj = new JSONObject();
		JSONObject aliasobj = new JSONObject();
		JSONArray ja = null;
		String currentDep = "";
		for (int i = 1; i < count; i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell cell = row.getCell(0);
			String seqnum = cell.getStringCellValue();
			cell = row.getCell(1);
			String department = cell.getStringCellValue();
			cell = row.getCell(2);
			String disease = cell.getStringCellValue();
			cell = row.getCell(3);
			String alias1 = cell.getStringCellValue();
			cell = row.getCell(4);
			String alias2 = cell.getStringCellValue();
			cell = row.getCell(5);
			String alias3 = cell.getStringCellValue();
			cell = row.getCell(6);
			String alias4 = cell.getStringCellValue();
			cell = row.getCell(7);
			String alias5 = cell.getStringCellValue();
			cell = row.getCell(8);
			String alias6 = cell == null ? "" : cell.getStringCellValue();
			cell = row.getCell(9);
			String alias7 = cell == null ? "" : cell.getStringCellValue();
			cell = row.getCell(10);
			String alias8 = cell == null ? "" : cell.getStringCellValue();
			cell = row.getCell(11);
			String alias9 = cell == null ? "" : cell.getStringCellValue();
			cell = row.getCell(12);
			String alias10 = cell == null ? "" : cell.getStringCellValue();
			StringBuilder sb = new StringBuilder();
			if (!StringUtils.isBlank(alias1)) {
				sb.append(alias1 + ",");
			}
			if (!StringUtils.isBlank(alias2)) {
				sb.append(alias2 + ",");
			}
			if (!StringUtils.isBlank(alias3)) {
				sb.append(alias3 + ",");
			}
			if (!StringUtils.isBlank(alias4)) {
				sb.append(alias4 + ",");
			}
			if (!StringUtils.isBlank(alias5)) {
				sb.append(alias5 + ",");
			}
			if (!StringUtils.isBlank(alias6)) {
				sb.append(alias6 + ",");
			}
			if (!StringUtils.isBlank(alias7)) {
				sb.append(alias7 + ",");
			}
			if (!StringUtils.isBlank(alias8)) {
				sb.append(alias8 + ",");
			}
			if (!StringUtils.isBlank(alias9)) {
				sb.append(alias9 + ",");
			}
			if (!StringUtils.isBlank(alias10)) {
				sb.append(alias10 + ",");
			}
			if (sb.length() > 0) {
				sb = sb.deleteCharAt(sb.length() - 1);
			}
			aliasobj.put(disease, sb.toString());
			if (!StringUtils.isBlank(department)) {
				if (i != 1) {
					depobj.put(currentDep, ja);
					ja = new JSONArray();
					currentDep = department;
				} else {
					ja = new JSONArray();
					currentDep = department;
				}
			} else {
				// 如果是最后一行的
				if (i == count - 1) {
					depobj.put(currentDep, ja);
				}
			}
			ja.put(disease);

		}
		System.out.println(depobj.toString());
		//System.out.println(aliasobj.toString());
		workbook.close();
	}
}
