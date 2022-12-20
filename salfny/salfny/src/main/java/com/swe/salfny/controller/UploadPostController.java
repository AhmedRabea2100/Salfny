package com.swe.salfny.controller;

import com.swe.salfny.Model.Uplo;
import com.swe.salfny.Model.UploRepository;
import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.user.User;
import com.swe.salfny.Model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.time.LocalDateTime;

@RestController
public class UploadPostController {
    @Autowired
    private UploRepository repo;

    //@CrossOrigin
    @RequestMapping("/upload")
    public String upload(@RequestBody Uplo p) {
        // System.out.println(p.getTitle()+" "+ p.getDescription()+" "+p.getCategory()+" el sora "+p.getPhoto());

        if (repo.findMaxId() == null)
            p.setPhoto(conv(p.getPhoto(), 1L));
        else
            p.setPhoto(conv(p.getPhoto(), repo.findMaxId() + 1));
        repo.save(p);
        return "back  ";

    }

    public String conv(String photo, Long i) {
        String base64String = photo;
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        System.out.println(System.getProperty("user.dir"));
        String[] s1 = System.getProperty("user.dir").split("salfny");
        String out;
        if (s1[0].contains("/")) {
            out = s1[0] + "Frontend/src/assets/images/" + i + "." + extension;//for Linux Users
        } else {
            out = s1[0] + "Frontend\\src\\assets\\images\\" + i + "." + extension;//for Windows Users
        }
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(out))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i + "." + extension;
    }
}
