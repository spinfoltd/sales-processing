package com.mprocessor.sales.db.mdl;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Job {
    @Id
    private String id;
    private String complDescription;

    public Job() {
    }

    public Job(String id, String complDescription) {
        this.id = id;
        this.complDescription = complDescription;
    }

    public String getComplDescription() {
        return complDescription;
    }

    public void setComplDescription(String complDescription) {
        this.complDescription = complDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
