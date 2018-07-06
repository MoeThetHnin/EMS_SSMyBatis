package com.ansur.ems_ansur_ssmybatis.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.ansur.ems_ansur_ssmybatis.models.Function;
import com.ansur.ems_ansur_ssmybatis.services.FileServlet;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;

public class FunctionAction {

	private Function function;

	String filename;

	private String filePath;

	private FileInputStream fileInputStream;

	private FileInputStream fs;

	private FileOutputStream fos;

	private ZipOutputStream zos;

	public String excelToPdf() throws IOException, DocumentException {
		int columnWidth[] = { 3000, 2500, 6000, 5000, 3000, 1500, 3000, 2000, 2000, 2000, 6000 };
		String fontPath = getFilePath() + "\\Fonts\\sazanami-mincho.ttf";

		for (int i = 0; i < function.getUploadImage().length; i++) {
			FileInputStream inputDocument = new FileInputStream(function.getUploadImage()[i]);

			HSSFWorkbook workBook = new HSSFWorkbook(inputDocument);
			Document newPdf = new Document(PageSize.A4.rotate());

			BaseFont bf;
			Font font;
			bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			font = new Font(bf, 10);

			PdfWriter.getInstance(newPdf, new FileOutputStream(
					getFilePath() + "\\PdfFiles\\" + function.getUploadImageFileName()[i].split("\\.")[0] + ".pdf"));
			newPdf.open();
			PdfPTable table = new PdfPTable(11);
			table.setWidths(columnWidth);
			PdfPCell table_cell;
			/* for(int i=0;i<xlsWB.getNumberOfSheets();i++) { */
			HSSFSheet xlsSheet = workBook.getSheetAt(0);

			Iterator<Row> rowIterator = xlsSheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						table_cell = new PdfPCell(new Phrase(cell.getStringCellValue(), font));
						table.addCell(table_cell);
						break;
					case Cell.CELL_TYPE_NUMERIC:
						table_cell = new PdfPCell(new Phrase((float) cell.getNumericCellValue()));
						table.addCell(table_cell);
						break;
					}
				}
			}

			newPdf.add(table);

			newPdf.close();
			inputDocument.close();

		}

		crateZipFile();

		// Zip ファイルをダウンロードすること
		
		  File dfile = new File(getFilePath()+"\\ZipFiles\\pdfFiles.zip"); 
		  fileInputStream = new FileInputStream(dfile); 
		  filename = dfile.getName();
		 

		return ActionSupport.SUCCESS;
	}

	private void crateZipFile() throws IOException {
		// TODO Auto-generated method stub

		fos = new FileOutputStream(getFilePath() + "\\ZipFiles\\pdfFiles.zip");
		zos = new ZipOutputStream(fos);
		for (int i = 0; i < function.getUploadImage().length; i++) {
			String pdfFilePath = getFilePath() + "\\PdfFiles\\" + function.getUploadImageFileName()[i].split("\\.")[0]
					+ ".pdf";
			System.out.println(pdfFilePath);
			File f = new File(pdfFilePath);
			fileInputStream = new FileInputStream(f);
			ZipEntry zipEntry = new ZipEntry(function.getUploadImageFileName()[i].split("\\.")[0] + ".pdf");
			zos.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fileInputStream.read(bytes)) >= 0) {
				zos.write(bytes, 0, length);
			}

			zos.closeEntry();
			fileInputStream.close();
		}
		zos.close();
		fos.close();

	}

	private String getFilePath() {
		Properties p = new Properties();
		try {
			p.load(FileServlet.class.getClassLoader().getResourceAsStream("application.properties"));
			filePath = p.getProperty("label.path");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public ZipOutputStream getZos() {
		return zos;
	}

	public FileInputStream getFs() {
		return fs;
	}

}
