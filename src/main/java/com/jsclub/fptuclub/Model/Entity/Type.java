package com.jsclub.fptuclub.Model.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "Type")
@DynamicInsert
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private int typeID;
    @Column(name = "Type name")
    @Enumerated(EnumType.STRING)
    private EType typeName;
}
