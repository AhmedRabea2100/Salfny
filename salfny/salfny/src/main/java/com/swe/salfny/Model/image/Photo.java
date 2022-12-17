package com.swe.salfny.Model.image;

import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.user.User;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "photo")
public class Photo {
    public Photo(){
    }
    public Photo(int id, String url) {
        this.id = id;
        this.url = url;
    }

    @Id
    private int id;
    @Id
    @Column(name = "url", nullable = false, length = 200)
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Post postt;

    public String getUrl() {
        return url;
    }
}
