package mchti.model.hcm.proc;

import mchti.model.hcm.cr.*;
import mchti.model.hcm.enums.Day;
import mchti.model.hcm.enums.EmpGroup;
import mchti.model.hcm.enums.Gender;
import mchti.model.hcm.tl.Roster;
import mchti.model.hcm.tl.Shift;
import com.oith.annotation.MacImagable;
import com.oith.annotation.MacSearchable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.math.BigInteger;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PROC_OUT_EMP")
@XmlRootElement
public class ProcOutEmp implements Serializable {

    @Id
    //@Column(name = "ID")
    //@GeneratedValue(strategy = GenerationType.TABLE)
    //@Basic(optional = false)
    //    @SequenceGenerator(name = "HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE")
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    //    @GeneratedValue(strategy = GenerationType.TABLE)
    //    @GeneratedValue(strategy = GenerationType.AUTO)
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    @Version
    private Integer version;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "CODE", length = 20, nullable = false)
    private String code;
    @NotNull
    @JoinColumn(name = "EMP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Emp emp;

    @MacImagable
    @Column(name = "PIC_FILE", length = 255)
    private String picFile;
    @MacSearchable
    @Basic(optional = false)
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String fullName;
    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 6)
    private Gender gender;
    @Temporal(TemporalType.DATE)
    @Past
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date doj;
    @MacSearchable
    @Temporal(TemporalType.DATE)
    @Past
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @Column(name = "EMAIL", length = 30)
    @Email
    private String email;
    @MacSearchable
    @Column(name = "ADDRESS", length = 1000)
    private String address;

    //cr
    @JoinColumn(name = "DEPT_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Dept dept;
    @JoinColumn(name = "DESG_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Desg desg;
    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "EMP_GROUP", length = 20)
    private EmpGroup empGroup;

    //tl
    @Enumerated(EnumType.STRING)
    @Column(name = "SHIFT_OFF_DAY", length = 9)
    private Day shiftOffDay;
    @JoinColumn(name = "SHIFT_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Shift shift;
    @JoinColumn(name = "ROSTER_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Roster roster;
    @Column(name = "IS_OVERTIME")
    private Boolean isOvertime;

    public ProcOutEmp() {
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Desg getDesg() {
        return desg;
    }

    public void setDesg(Desg desg) {
        this.desg = desg;
    }

    public Day getShiftOffDay() {
        return shiftOffDay;
    }

    public void setShiftOffDay(Day shiftOffDay) {
        this.shiftOffDay = shiftOffDay;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public String getPicFile() {
        return picFile;
    }

    public void setPicFile(String picFile) {
        this.picFile = picFile;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmpGroup getEmpGroup() {
        return empGroup;
    }

    public void setEmpGroup(EmpGroup empGroup) {
        this.empGroup = empGroup;
    }

    public Boolean getIsOvertime() {
        return isOvertime;
    }

    public void setIsOvertime(Boolean isOvertime) {
        this.isOvertime = isOvertime;
    }

    @Override
    public String toString() {
        return "ProcOutEmp{" + "id=" + id + ", version=" + version + ", code=" + code + ", emp=" + emp + ", picFile=" + picFile + ", fullName=" + fullName + ", gender=" + gender + ", doj=" + doj + ", dob=" + dob + ", email=" + email + ", address=" + address + ", dept=" + dept + ", desg=" + desg + ", empGroup=" + empGroup + ", shiftOffDay=" + shiftOffDay + ", shift=" + shift + ", roster=" + roster + '}';
    }
}
