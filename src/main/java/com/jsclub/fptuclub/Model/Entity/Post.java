package com.jsclub.fptuclub.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Post")
@Data

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostID")
    private int postID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id_cid", referencedColumnName = "CID")
    private CLB clubID;
    @Column(name = "Title", nullable = false)
    private String title;
    @Column(name = "Image", nullable = false)
    private String Image;
    @Column(name = "Content", nullable = false)
    private String content;
}
