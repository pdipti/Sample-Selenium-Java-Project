package com.fox.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	private static String[] columns = { "FOX", "FX", "NATIONAL GEOGRAPHIC", "FOX SPORTS", "ALL SHOWS" };

	public void createExcel(List<String> titles, List<String> dubListFx, List<String> dubListNatGeo,
			List<String> dubListfoxSports, List<String> dubListAllShows) throws IOException {

		File file = new File("showsList.xls");
		if (file.exists()) {
			file.delete();
		}

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Shows");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		int rowNum = 1;
		for (String title : titles) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(title);
		}

		rowNum = 1;
		for (String title : dubListFx) {
			Row row = sheet.getRow(rowNum++);

			row.createCell(1).setCellValue(title);
		}

		rowNum = 1;
		for (String title : dubListNatGeo) {
			Row row = sheet.getRow(rowNum++);

			row.createCell(2).setCellValue(title);
		}

		rowNum = 1;
		for (String title : dubListfoxSports) {
			Row row = sheet.getRow(rowNum++);

			row.createCell(3).setCellValue(title);
		}

		rowNum = 1;
		for (String title : dubListAllShows) {
			Row row = sheet.getRow(rowNum++);

			row.createCell(4).setCellValue(title);
		}

		FileOutputStream fileOut = new FileOutputStream("showsList.xls");
		workbook.write(fileOut);
		fileOut.close();

		workbook.close();

	}

}
