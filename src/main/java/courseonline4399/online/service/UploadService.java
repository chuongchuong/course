package courseonline4399.online.service;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class UploadService {
    @Autowired(required = true)
    ServletContext app;
    @Value("${app.image_folder}")
    String imageFolder;

//    public String save(MultipartFile file, String path) {
//        ServletContext app = this.app;
//        File dir = new File(imageFolder , path);
//        if(!dir.exists()) dir.mkdir();
//        String name = System.currentTimeMillis() + file.getOriginalFilename();
//        String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
//        File saveFile = new File(dir, filename);
//        try {
//            file.transferTo(saveFile);
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(dir + "/"+filename);
//        return filename;
//    }
private String uploadDir = "D:\\java6\\courseonline\\src\\main\\resources\\static\\images\\user\\"; // Đặt đường dẫn thư mục upload của bạn

    public String saveFile(MultipartFile file) throws IOException {
        String fileName = generateUniqueFileName(file.getOriginalFilename());

        // Tạo đường dẫn tuyệt đối cho file
        Path filePath = Paths.get(uploadDir, fileName);

        // Lưu file vào đường dẫn
        file.transferTo(filePath);

        return fileName;
    }

    private String generateUniqueFileName(String originalFileName) {
        // Tạo tên file duy nhất bằng cách sử dụng UUID
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + extension;
    }
    public boolean removeFile(String fileName, String path) {
        File dir = new File(app.getRealPath(path));
        File linkFile = new File(dir,fileName);
        if(isFileExisits(fileName, path)) {
            return linkFile.delete();
        }        return false;
    }

    private boolean isFileExisits(String fileName, String path) {
        File dir = new File(app.getRealPath(path));
        if(!dir.exists()) {
            return false;
        }
        File linkFile = new File(dir,fileName);
        if(!linkFile.exists()) {
            return false;
        }
        return true;
    }
}