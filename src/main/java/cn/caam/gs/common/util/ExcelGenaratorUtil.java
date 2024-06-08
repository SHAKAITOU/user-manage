package cn.caam.gs.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import lombok.extern.slf4j.Slf4j;
import cn.caam.gs.common.exception.ShaException;

@Component
@Slf4j
public class ExcelGenaratorUtil {
	
	final static String CONTENT_DISPOSITION_FORMAT = 
			"attachment; filename=\"%s\"; filename*=UTF-8''%s";
	
	public ResponseEntity<InputStreamResource> downloadExcel(
    		InputStreamResource resource, String fileName){
		MediaType mediaType = MediaType.APPLICATION_PDF;
		
		String headerValue =
			    String.format(CONTENT_DISPOSITION_FORMAT,
			    		fileName,
			                  UriUtils.encode(fileName, StandardCharsets.UTF_8.name()));
		return ResponseEntity.ok()
		        // Content-Disposition
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + headerValue)
		        // Content-Type
		        .contentType(mediaType)
		        // Contet-Length
		        .body(resource);
    }
	
	public Workbook createTemplate(File templateFile) {
		Workbook workbook = null;
        try (InputStream is = new ByteArrayInputStream(
                Files.readAllBytes(templateFile.toPath()));) {
            workbook = WorkbookFactory.create(is);
            // workbook = WorkbookFactory.create(is, excelOpenPass);
            // ファイルにパスワードロックが掛かっている場合、jce_policy-8.zip の適用が必要
        } catch (IOException | EncryptedDocumentException e) {
            log.error("create workbook error", e);
            throw new ShaException(e);
        }
        return workbook;
	}
	
	public InputStreamResource createExcelFile(Workbook workbook, String fileName) {
        File file = createTempExcelFile(workbook, FileUtil.getFileExtentName(fileName));
        InputStreamResource resource;
		try {
			resource = new InputStreamResource(new FileInputStream(file));
			return resource;
		} catch (FileNotFoundException e) {
			log.error("PDF created fail");
		}
		
		return null;
	}
	
	private File createTempExcelFile(Workbook workbook, String suffix) {
		FileOutputStream os = null;
		String fileName = UUID.randomUUID().toString();
		try {
			final File outputFile = File.createTempFile(fileName, suffix);
			os = new FileOutputStream(outputFile);
			
			workbook.write(os);
			log.info("PDF created successfully");
			return outputFile;
		} catch (Exception e){
			log.error("PDF created fail");
			return null;
		}finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					/* ignore */ }
			}
		}
	}
}
