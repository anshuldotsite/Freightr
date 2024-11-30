package org.example.freightr.TableCreation.ObjectClasses;

/**
 * @author Kautuk Prasad
 * @description POJO for Status Table
 */
public class StatusPOJO {
    private int id;
    private String status;

    public StatusPOJO(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
