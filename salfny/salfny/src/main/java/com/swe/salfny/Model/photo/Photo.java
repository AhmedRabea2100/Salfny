package com.swe.salfny.Model.photo;

import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Photo")
public class Photo {
    public Photo() {
    }

    public Photo(String photo) {
        this.photo = photo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "url", nullable = false)
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
