package courseonline4399.online.rest;

import courseonline4399.online.service.FileManagerService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin("*")
@RestController
public class FileManagerRestController {
	@Autowired
	FileManagerService fileService;

	
	@GetMapping("/rest/files/{folder}/{file}")
	public byte[] download(@PathVariable("folder") String folder, @PathVariable("file") String filename) {

		return fileService.read(folder, filename);
	}
	

	
	@DeleteMapping("/rest/files/{folder}/{file}")
	// "http://localhost:8080/rest/files      /static/images/courses/files/"
	public void delete(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		// nó còn không đi vào trong này
		System.out.println(" chạy vào rest delete ");
		System.out.println("haha "+folder);
			if(file != null){
				fileService.delete(folder, file);
			}
	}
	
	@GetMapping("/rest/files/{folder}")
	public List<String> list(@PathVariable("folder") String folder) {
		return fileService.list(folder);
	}

	@PostMapping("/rest/files/{folder}")
	public ResponseEntity<List<String>> upload(@PathVariable("folder") String folder,
								 @PathParam("files") MultipartFile[] files) {


		List<String> filenames =  fileService.saves(folder, files);
		return ResponseEntity.ok(filenames);
	}


}