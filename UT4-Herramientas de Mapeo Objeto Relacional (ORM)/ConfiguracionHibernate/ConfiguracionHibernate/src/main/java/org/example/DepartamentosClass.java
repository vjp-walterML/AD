package org.example;

import javax.persistence.*;

@Entity
@Table(name = "departamentos", schema = "empresa", catalog = "")
public class DepartamentosClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dept_no", nullable = false)
    private int deptNo;
    @Basic
    @Column(name = "dnombre", nullable = true, length = 15)
    private String dnombre;
    @Basic
    @Column(name = "loc", nullable = true, length = 15)
    private String loc;

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
