package com.zemoso.greencommute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routes")
public class Route implements Serializable {

    @Id
    @Column(name = "company_id")
    private String companyId;

    @Id
    @Column(name = "type")
    private String type;

    @Column(name = "is_green_commute")
    private boolean isGreenCommute;
}
