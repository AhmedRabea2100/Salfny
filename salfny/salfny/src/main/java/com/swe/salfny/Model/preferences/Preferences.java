package com.swe.salfny.Model.preferences;

import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "preferences")
public class Preferences {
    public Preferences() {
    }

    public Preferences(int user_id,int category_id) {
        this.user_id = user_id;
        this.category_id = category_id;
    }

    public int getUser_id() {
        return user_id;
    }

    @Id
    private int user_id;

    @Column(name = "category_id", nullable = false)
    private int category_id;

    public int getCategory_id() {
        return category_id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

}
