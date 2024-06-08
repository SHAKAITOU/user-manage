package cn.caam.gs.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.Assert;
import org.springframework.web.util.UriUtils;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class PdfGenaratorUtil {
	

	final static String CONTENT_DISPOSITION_FORMAT = 
			"attachment; filename=\"%s\"; filename*=UTF-8''%s";
	
	@Autowired
	private TemplateEngine templateEngine;
	
    @Autowired
    ResourceLoader resourceLoader;
    
    public ResponseEntity<InputStreamResource> downloadPdf(
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
    
    public InputStreamResource createPdf(String html) {
        File tempFile = createTempPdf(html);
        InputStreamResource resource;
		try {
			resource = new InputStreamResource(new FileInputStream(tempFile));
			tempFile.delete();
			return resource;
		} catch (FileNotFoundException e) {
			log.error("PDF created fail");
		}
		
		return null;
	}
	
	public InputStreamResource createPdf(String templatePathName, Map<String, Object> mapData) {
        File tempFile = createTempPdf(templatePathName, mapData);
        InputStreamResource resource;
		try {
			resource = new InputStreamResource(new FileInputStream(tempFile));
			tempFile.delete();
			return resource;
		} catch (FileNotFoundException e) {
			log.error("PDF created fail");
		}
		
		return null;
	}
	
	private File createTempPdf(String html) {
		FileOutputStream os = null;
		String fileName = UUID.randomUUID().toString();
		try {
			final File outputFile = File.createTempFile(fileName, ".pdf");
			os = new FileOutputStream(outputFile);
			
			String filepath = "static/ttl/sazanami-mincho.ttf"; // src/main/resources 配下の相対パス
	        Resource resource = resourceLoader.getResource("classpath:" + filepath);
			
			ITextRenderer renderer = new ITextRenderer();
			renderer.getFontResolver().addFont(
					resource.getFile().getAbsolutePath(), BaseFont.IDENTITY_H ,BaseFont.NOT_EMBEDDED);
			renderer.setDocumentFromString(html);
			renderer.layout();
			renderer.createPDF(os, false);
			renderer.finishPDF();
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
	
	private File createTempPdf(String templateName, Map<String, Object> mapData) {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (mapData != null) {
		     Iterator<Map.Entry<String, Object>> itMap = mapData.entrySet().iterator();
		       while (itMap.hasNext()) {
			  Map.Entry<String, Object> pair = (Map.Entry<String, Object>) itMap.next();
		          ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		
		String processedHtml = templateEngine.process(templateName, ctx);
		return createTempPdf(processedHtml);
		
	}
}

