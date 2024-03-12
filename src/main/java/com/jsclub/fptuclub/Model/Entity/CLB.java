package com.jsclub.fptuclub.Model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CLB")
public class CLB {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CID;
}
