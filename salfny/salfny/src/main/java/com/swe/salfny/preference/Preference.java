//package com.swe.salfny.preference;
//
//import com.swe.salfny.post.Post;
//import com.swe.salfny.user.UserData;
//import jakarta.persistence.*;
//import org.apache.catalina.LifecycleState;
//
//import java.util.List;
//
//
//@Entity
//@Table(name = "preferences")
//public class Preference {
//    public Preference(){}
//    @Id
//    private int user_id;
//
//    @Id
//    private int category_id;
//
//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }
//
//    public int getCategory_id() {
//        return category_id;
//    }
//
//    public void setCategory_id(int category_id) {
//        this.category_id = category_id;
//    }
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private UserData userPreferName;
//
//    public String getUserName(){
//        return userPreferName.getUsername();
//    }
//
//    @ManyToOne(fetch =  FetchType.LAZY)
//    @JoinColumn(name = "category_id", insertable = true, updatable = false)
//    private Post preferredPosts;
//
//    public Post getPreferredPosts(){
//        return preferredPosts;
//    }
//}
