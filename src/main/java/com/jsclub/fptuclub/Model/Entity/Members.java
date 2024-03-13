package com.jsclub.fptuclub.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Members")
@Data
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MemberID")
    private int memberID;
    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Users> userses = new LinkedHashSet<>();

}
