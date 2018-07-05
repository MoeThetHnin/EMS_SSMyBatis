package com.ansur.ems_ansur_ssmybatis.actions;

import java.io.File;
import java.io.FileInputStream;
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
	
	private FileOutputStream fos;
	
	private ZipOutputStream zos;
	
	public String excelToPdf() throws IOException, DocumentException {
		int columnWidth[] = { 3000, 2500, 6000, 5000, 3000, 1500, 3000, 2000, 2000, 2000, 6000 };
		String fontPath = getFilePath()+"\\Fonts\\sazanami-mincho.ttf";
		
		for(int i=0; i<function.getUploadImage().length;i++) {
			FileInputStream inputDocument = new FileInputStream(function.getUploadImage()[i]);
			
			HSSFWorkbook workBook = new HSSFWorkbook(inputDocument);
			Document newPdf = new Document(PageSize.A4.rotate());
			
			BaseFont bf;
			Font font;
			bf = BaseFont.createFont(fontPath,BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			font = new Font(bf,10);
			
			PdfWriter.getInstance(newPdf, new FileOutputStream(getFilePath()+"\\PdfFiles\\"+function.getUploadImageFileName()[i].split("\\.")[0]+".pdf"));
			newPdf.open();
			PdfPTable table = new PdfPTable(11);
			table.setWidths(columnWidth);
			PdfPCell table_cell;
			/*for(int i=0;i<xlsWB.getNumberOfSheets();i++) {*/
				HSSFSheet xlsSheet = workBook.getSheetAt(0);
				
				Iterator<Row> rowIterator = xlsSheet.iterator();
				
				while(rowIterator.hasNext()) {
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while(cellIterator.hasNext()){
						Cell cell = cellIterator.next();
						switch(cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							table_cell = new PdfPCell(new Phrase(cell.getStringCellValue(),font));
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
		
		/*FileInputStream inputDocument = new FileInputStream(getFunction().getUploadImage());
		
		HSSFWorkbook workBook = new HSSFWorkbook(inputDocument);
		Document newPdf = new Document(PageSize.A4.rotate());
		
		BaseFont bf;
		Font font;
		bf = BaseFont.createFont(fontPath,BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
		font = new Font(bf,10);
		PdfWriter.getInstance(newPdf, new FileOutputStream(getFilePath()+"\\PdfFiles\\"+getFunction().getUploadImageFileName().split("\\.")[0]+".pdf"));
		newPdf.open();
		PdfPTable table = new PdfPTable(11);
		table.setWidths(columnWidth);
		PdfPCell table_cell;
		for(int i=0;i<xlsWB.getNumberOfSheets();i++) {
			HSSFSheet xlsSheet = workBook.getSheetAt(0);
			
			Iterator<Row> rowIterator = xlsSheet.iterator();
			
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						table_cell = new PdfPCell(new Phrase(cell.getStringCellValue(),font));
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
		inputDocument.close();*/
		
		
		// 作成したエクセルファイルをダウンロードする
		/*		fileInputStream = new FileInputStream(new File(getFilePath() + "\\PdfFiles\\" +getFunction().getUploadImageFileName().split("\\.")[0]+ ".pdf"));
				filename = getFunction().getUploadImageFileName().split("\\.")[0] + ".pdf";// ダウンロードファイル名前のなめにstruts.xmlに送ること
*/		
		String zipFile = getFilePath()+"\\ZipFiles\\test.zip";
		int totalFiles = function.getUploadImageFileName().length;
		String srcFiles[] =new String [totalFiles];  
		
		
		byte[] buffer = new byte[2048];
		
		fos = new FileOutputStream(zipFile);
		
		zos = new ZipOutputStream(fos);
		
		for(int i=0;i<srcFiles.length;i++) {
			File srcFile = new File(getFilePath()+"\\PdfFiles\\"+function.getUploadImageFileName()[i].split("\\.")[0]+".pdf");
			fileInputStream = new FileInputStream(srcFile);
			zos.putNextEntry(new ZipEntry(srcFile.getName()));
			
			int length;
			while((length=fileInputStream.read(buffer))>0) {
				zos.write(buffer,0,length);
			}
			zos.closeEntry();
			fileInputStream.close();
		}
		
		File dfile = new File(getFilePath()+"\\ZipFiles\\test.zip");
		fileInputStream = new FileInputStream(dfile);
		filename = "test.zip";
		
		return ActionSupport.SUCCESS;
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


	
	
	
	
}
