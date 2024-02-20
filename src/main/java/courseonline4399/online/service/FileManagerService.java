package courseonline4399.online.service;

import jakarta.servlet.ServletContext;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileManagerService {
    @Autowired
    ServletContext app;

    @Value("${app.image_folder}")
    String imageFolder;


    private Path getPath(String folder, String filename) {
        File dir = Paths.get(folder).toFile();
        if(!dir.exists()) {
            dir.mkdirs();
        }
        Path path = Paths.get(dir.getAbsolutePath());
//        System.out.println("Real Path: " + path.toString());
        return Paths.get(dir.getAbsolutePath(), filename);
    }
    private Path getPathv2(String folder, String filename) {
        File dir = Paths.get(app.getRealPath("/files/")).toFile();
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return Paths.get(dir.getAbsolutePath(), filename);
    }

    public byte[] read(String folder, String filename) {
        Path path = this.getPath(imageFolder + "/" + folder, filename);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            return null;
        }

    }

    public List<String> saves(String folder, MultipartFile[] files) {
        List<String> filenames = new ArrayList<String>();
        for(MultipartFile file: files) {
            String name = System.currentTimeMillis() + file.getOriginalFilename();
            String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
            Path path = this.getPath(imageFolder + "/" + folder, filename);
            try {
                file.transferTo(path);
                filenames.add(filename);
            }catch (FileUploadException e){
                System.out.println("File upload exception: " + e.getMessage());

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filenames;
    }

    public String save(String folder, MultipartFile file) {

        String name = System.currentTimeMillis() + file.getOriginalFilename();
        String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
        Path path = this.getPath( imageFolder + "/" + folder, filename);

        try {
            file.transferTo(path);

        }
        catch (FileUploadException e){
            System.out.println("File upload exception: " + e.getMessage());

        }catch (Exception e) {
            e.printStackTrace();
        }

        return filename;
    }

    public void delete(String folder, String filename) {
        Path path = this.getPath(imageFolder + "/" + folder, filename);
        path.toFile().delete();

    }

    public List<String> list(String folder) {
        List<String> filenames = new ArrayList<String>();
        File dir = Paths.get(app.getRealPath("/files/"), folder).toFile();
        if(dir.exists()) {
            File[] files = dir.listFiles();
            for(File file: files) {
                filenames.add(file.getName());
            }
        }
        return filenames;
    }
}
