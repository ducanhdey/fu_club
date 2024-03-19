package com.jsclub.fptuclub.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
@Entity
@Table(name = "CLB")
public class CLB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CID;
    @Column(name = "FullName", unique = true, nullable = false)
    private String fullName;
    @Column(name = "shortName", unique = true, nullable = false)
    private String shortName;
    @Column(name = "Date", nullable = false)
    private Date date;
    @Column(name = "Email", unique = true, nullable = false)
    private String mail;
    @Column(name = "Logo", unique = true, nullable = false)
    private String Logo;
    @Column(name = "Intro", nullable = false)
    private String Intro;
    @Column(name = "Background", unique = true, nullable = false)
    private String Background;
    @Column(name = "Facebook", unique = true, nullable = false)
    private String fb;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_type_id")
    private Type type;

    @OneToMany(mappedBy = "clubID", orphanRemoval = true)
    private Set<Post> posts = new LinkedHashSet<>();



    @ManyToMany(mappedBy = "clbs", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Users> usersS = new ArrayList<>();

    public List<Users> getUsersS() {
        return usersS;
    }

    public void setUsersS(List<Users> usersS) {
        this.usersS = usersS;
    }



    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
