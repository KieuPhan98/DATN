package m07.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class ReceiptionController {

	@RequestMapping(value = "/admin/importOrderFromSupplier", method = RequestMethod.GET)
	public String importOrder(Model model) {
		return "/admin/importReceiption";
	}

	@RequestMapping(value = "/admin/downloadTeamplate", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> download() throws IOException {

		
		String fileName = "PhieuDatHang.xlsx";
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource(fileName).getFile());

		
	    InputStreamResource resourceFile = new InputStreamResource(new FileInputStream(file));
	    
	    HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("Content-Disposition", "attachment; filename=" + fileName);


		return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resourceFile);

	}

}
