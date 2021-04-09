package com.cts.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cts.vo.InputVO;
import com.gembox.spreadsheet.ExcelColumn;
import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelFont;
import com.gembox.spreadsheet.ExcelRow;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.HorizontalAlignmentStyle;
import com.gembox.spreadsheet.LengthUnit;
import com.gembox.spreadsheet.SpreadsheetInfo;

public class FetchFromDB {
	static int a0 = 0;
	static int b1 = 1;
	static int b0 = 0;
	static int b2 = 2;
	static int b3 = 3;

	public boolean fetch(InputVO o) throws FileNotFoundException {
		
		
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/metcheck";
		final String USER = "root";
		final String PASS = "root";
		int id = o.getJobid();
		System.out.println(id);
		Connection conn = null;
		Statement stmt = null;
		SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
		ExcelFile workbook = new ExcelFile();
		ExcelWorksheet worksheet = workbook.addWorksheet("Excel2");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			stmt = conn.createStatement();
			String query = ("SELECT * FROM T_FEED_FLE_CNFG WHERE JOB_CNFGR_ID=('" + id + "')");
			final PreparedStatement ps = conn.prepareStatement(query);
			final ResultSet resultSet = ps.executeQuery();
			int i = 1;
			int j = 3;
			String field = null;
			String length = null;
			String recontype;
			int columncount = 0;
			int entry = 1;
			int width = 0;
			int width1 = 0;
			while (resultSet.next()) {
				resultSet.absolute(i);
				recontype = resultSet.getString(2);
				recontype = resultSet.getString(2);
				field = resultSet.getString(j);
				length = resultSet.getString(j + 1);
				/* System.out.println(field + "   " + length); */
				i++;
				String fieldsplit[] = field.split(",");
				String lengthsplit[] = length.split(",");
				/*
				 * System.out.println(fieldsplit[0] + "   " + lengthsplit[0]);
				 */
				ExcelRow row = worksheet.getRow(0);
				row.getStyle().getFont().setWeight(ExcelFont.BOLD_WEIGHT);
				ExcelColumn column = worksheet.getColumn(columncount);
				column.setWidth(200, LengthUnit.PIXEL);
				column.getStyle().setHorizontalAlignment(HorizontalAlignmentStyle.LEFT);
				columncount++;
				column = worksheet.getColumn(columncount);
				column.setWidth(150, LengthUnit.PIXEL);
				column.getStyle().setHorizontalAlignment(HorizontalAlignmentStyle.LEFT);
				columncount++;
				column = worksheet.getColumn(columncount);
				column.setWidth(150, LengthUnit.PIXEL);
				column.getStyle().setHorizontalAlignment(HorizontalAlignmentStyle.LEFT);
				columncount++;
				column = worksheet.getColumn(columncount);
				column.setWidth(150, LengthUnit.PIXEL);
				column.getStyle().setHorizontalAlignment(HorizontalAlignmentStyle.LEFT);
				columncount = columncount + 2;
				if (entry == 1) {
					/* System.out.println("First entry"); */
					worksheet.getCell("A1").setValue(recontype);
					worksheet.getCell("A2").setValue("field value");
					worksheet.getCell("B2").setValue("field length start");
					worksheet.getCell("C2").setValue("field length end");
					worksheet.getCell("D2").setValue("Total width");
					entry++;
					a0 = 2;
					b0 = 0;
					b1 = 1;
					b2 = 2;
					b3 = 3;
				} else if (entry == 2) {
					/* System.out.println("sencond entry"); */
					worksheet.getCell("F1").setValue(recontype);
					worksheet.getCell("F2").setValue("field value");
					worksheet.getCell("G2").setValue("field length start");
					worksheet.getCell("H2").setValue("field length end");
					worksheet.getCell("I2").setValue("Total width");
					entry++;
					a0 = 2;
					b0 = 5;
					b1 = 6;
					b2 = 7;
					b3 = 8;
				} else {
					worksheet.getCell("L1").setValue(recontype);
					worksheet.getCell("L2").setValue("field value");
					worksheet.getCell("M2").setValue("field length start");
					worksheet.getCell("N2").setValue("field length end");
					worksheet.getCell("O2").setValue("Total width");
					entry++;
					a0 = 2;
					b0 = 9;
					b1 = 10;
					b2 = 11;
					b3 = 12;
				}
				for (int k = 0; k < fieldsplit.length; k++) {
					worksheet.getCell(a0, b0).setValue(fieldsplit[k]);
					String lengthchar = lengthsplit[k];
					String lengthsplit1[] = lengthchar.split("-");
					worksheet.getCell(a0, b1).setValue(lengthsplit1[0]);
					worksheet.getCell(a0, b2).setValue(lengthsplit1[1]);
					width = Integer.parseInt(lengthsplit1[1]);
					width1 = Integer.parseInt(lengthsplit1[0]);
					width = (width - width1) + 1;
					worksheet.getCell(a0, b3).setValue(width);
					a0++;
				}
			}
			worksheet.getPrintOptions().setPrintHeadings(true);
			worksheet.getPrintOptions().setPrintGridlines(true);
			String filename = o.getPath();
			System.out.println(filename);
			workbook.save(filename);
			System.out.println("Excel created");
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return false;
	}
}
