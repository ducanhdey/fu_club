package com.jsclub.fptuclub.Model.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CLB")
public class CLB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CID;
    @Column(name = "FullName", unique = true, nullable = false)
    private String FullName;
    @Column(name = "ShortName", unique = true, nullable = false)
    private String ShortName;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
