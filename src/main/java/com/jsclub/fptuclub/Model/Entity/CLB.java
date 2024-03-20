package com.jsclub.fptuclub.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CLB")
@Getter
@Setter
public class CLB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CID;
    @Column(name = "FullName", unique = true, nullable = false)
    private String fullName;
    @Column(name = "ShortName", unique = true, nullable = false)
    private String shortName;
    @Column(name = "Date", nullable = false)
    private Date date;
    @Column(name = "Email", unique = true, nullable = false)
    private String mail;
    @Column(name = "Logo", unique = true)
    private String logo;
    @Column(name = "Intro", nullable = false)
    private String intro;
    @Column(name = "Background", unique = true)
    private String background;
    @Column(name = "Facebook", unique = true, nullable = false)
    private String fb;
    @Column(name="List")
    private String list;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_type_id")
    private Type type;

    @OneToMany(mappedBy = "clubID", orphanRemoval = true)
    private Set<Post> posts = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "manage", cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Users> userses = new LinkedHashSet<>();

    public Set<Users> getUserses() {
        return userses;
    }

    public void setUserses(Set<Users> userses) {
        this.userses = userses;
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