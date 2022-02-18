/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breadapp;


public class ModelTable {
    String id, fname, lname, bdate, gender;

    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getBdate() {
        return bdate;
    }

    public String getGender() {
        return gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ModelTable(String id, String fname, String lname, String bdate, String gender) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.bdate = bdate;
        this.gender = gender;
        
    }
    
    
}
