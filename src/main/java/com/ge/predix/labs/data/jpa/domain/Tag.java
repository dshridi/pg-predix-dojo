package com.ge.predix.labs.data.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Tag implements Serializable {

	private static final long serialVersionUID = 1500516986755256732L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Date tStamp;

    @NotNull
    private String name;
    @NotNull
    private float value;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameIn) {
        this.name = nameIn;
    }

    public Date getTStamp() {
        return tStamp;
    }

    public void setTStamp(Date signupDate) {
        this.tStamp = signupDate;
    }

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
}
