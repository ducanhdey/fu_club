package com.jsclub.fptuclub.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert

@Entity
@Table(name = "Post")
@Data

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostID")
    private int postID;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "club_id_cid", referencedColumnName = "CID")
    private CLB clubID;

    @Column(name = "Content", nullable = false)
    private String content;
}
