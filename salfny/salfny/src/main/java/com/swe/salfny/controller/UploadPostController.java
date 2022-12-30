package com.swe.salfny.controller;

import com.swe.salfny.Model.photo.Photo;
import com.swe.salfny.Model.photo.PhotoRepository;
import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import com.swe.salfny.Model.uploadPost.UploadPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class UploadPostController {
    @Autowired
    private PostRepository repo;
    @Autowired
    private PhotoRepository image;

    @RequestMapping("/upload")
    public String upload(@RequestBody UploadPost uploadedPost) {
        Post post = new Post(uploadedPost.getTitle(),uploadedPost.getDescription(),uploadedPost.getPrice(), uploadedPost.getCategory_id(), uploadedPost.getUser_id(),LocalDateTime.now());
        repo.save(post);
        Photo photo =new Photo(repo.findMaxId(),StorePhotoInPath(uploadedPost.getPhoto(), repo.findMaxId()));
        image.save(photo);
        return "back ";

    }

    public String StorePhotoInPath(String photo, int i) {
        String base64String = photo;
        String[] strings = base64String.split(",");
        String extension = switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64" -> "jpeg";
            case "data:image/png;base64" -> "png";
            default ->//should write cases for more images types
                    "jpg";
        };
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        System.out.println(System.getProperty("user.dir"));
        String[] s1 = System.getProperty("user.dir").split("salfny");
        String out;
        if (s1[0].contains("/")) {
            out = s1[0] + "Frontend/src/assets/db/" + i + "." + extension;//for Linux Users
        } else {
            out = s1[0] + "Frontend\\src\\assets\\db\\" + i + "." + extension;//for Windows Users
        }
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(out))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i + "." + extension;
    }
}
