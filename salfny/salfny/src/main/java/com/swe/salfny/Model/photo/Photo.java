package com.swe.salfny.Model.photo;

import com.swe.salfny.Model.post.Post;
import jakarta.persistence.*;

@Entity
@Table(name = "Photo")
public class Photo {
    public Photo() {
    }

    public Photo(int id,String photo) {
        this.id = id;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    @Id
    private int id;

    @Column(name = "url", nullable = false)
    private String photo;

    public String getUrl() {
        return photo;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Post postt;

    public void setUrl(String photo) {
        this.photo = photo;
    }


}
