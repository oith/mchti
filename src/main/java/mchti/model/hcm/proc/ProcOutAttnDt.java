package mchti.model.hcm.proc;

import mchti.model.hcm.cr.Emp;
import mchti.model.hcm.enums.DtAttnType;
import mchti.model.hcm.tl.Shift;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PROC_OUT_ATTN_DT")
@XmlRootElement
public class ProcOutAttnDt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    //@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName="HIBERNATE_SEQUENCE")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    @Basic(optional = false)

    private BigInteger id;
    @Column(name = "ATTN_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date attnDate;
    @NotNull
    @JoinColumn(name = "EMP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Emp emp;
    @Enumerated(EnumType.STRING)
    @Column(name = "DT_ATTN_TYPE", nullable = false, length = 30)
    private DtAttnType dtAttnType;
    @JoinColumn(name = "SHIFT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shift shift;
    @Column(name = "IN_TIME")
    @Temporal(TemporalType.TIME)
    private Date inTime;
    @Column(name = "OUT_TIME")
    @Temporal(TemporalType.TIME)
    private Date outTime;
    private Double ot;
    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Double getOt() {
        return ot;
    }

    public void setOt(Double ot) {
        this.ot = ot;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getAttnDate() {
        return attnDate;
    }

    public void setAttnDate(Date attnDate) {
        this.attnDate = attnDate;
    }

    public DtAttnType getDtAttnType() {
        return dtAttnType;
    }

    public void setDtAttnType(DtAttnType dtAttnType) {
        this.dtAttnType = dtAttnType;
    }

}
