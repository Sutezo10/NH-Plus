package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class contains the structure of the treatments of a nursing home
 */
public class Treatment {
    private long tid;
    private final long pid;

    private long cid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private String lockState;

    private LocalDate lockDate;

    /**
     * Default constructor, without the tid
     * @param pid sets the val of the private var
     * @param cid sets the val of the private var
     * @param date sets the val of the private var
     * @param begin sets the val of the private var
     * @param end sets the val of the private var
     * @param description sets the val of the private var
     * @param remarks sets the val of the private var
     * @param lockState sets the val of the private var
     * @param lockDate sets the val of the private var
     */
    public Treatment(long pid, long cid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, String lockState, LocalDate lockDate) {
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.lockState = lockState;
        this.lockDate = lockDate;
        this.cid = cid;
    }

    /**
     * Default constructor, with the tid
     * @param tid sets the val of the private var
     * @param pid sets the val of the private var
     * @param cid sets the val of the private var
     * @param date sets the val of the private var
     * @param begin sets the val of the private var
     * @param end sets the val of the private var
     * @param description sets the val of the private var
     * @param remarks sets the val of the private var
     * @param lockState sets the val of the private var
     * @param lockDate sets the val of the private var
     */
    public Treatment(long tid, long pid, long cid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, String lockState, LocalDate lockDate) {
        this.tid = tid;
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.lockState = lockState;
        this.lockDate = lockDate;
        this.cid = cid;
    }

    /**
     * Getter of the tid
     * @return the tid val
     */
    public long getTid() {
        return tid;
    }

    /**
     * Getter of the pid
     * @return the pid val
     */
    public long getPid() {
        return this.pid;
    }

    /**
     * Getter of the date
     * @return date val
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Getter of begin
     * @return begin val
     */
    public String getBegin() {
        return begin.toString();
    }

    /**
     * Getter of end
     * @return end val
     */
    public String getEnd() {
        return end.toString();
    }

    /**
     * Setter of date
     * @param date sets the val of date
     */
    public void setDate(String date) {
        this.date = DateConverter.convertStringToLocalDate(date);
    }

    /**
     * Setter of begin
     * @param begin sets the val of begin
     */
    public void setBegin(String begin) {
        this.begin = DateConverter.convertStringToLocalTime(begin);
    }

    /**
     * Setter of end
     * @param end sets the val of end
     */
    public void setEnd(String end) {
        this.end = DateConverter.convertStringToLocalTime(end);
    }

    /**
     * Getter of description
     * @return the val of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter of description
     * @param description sets the val of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter of remarks
     * @return the val of remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Setter of remarks
     * @param remarks sets the val of remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Getter of lockState
     * @return the val of lockState
     */
    public String getLockState() {
        return lockState;
    }

    /**
     * Setter of lockState
     * @param lockState sets the val of lockState
     */
    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    /**
     * Getter of lockDate
     * @return the val OF lockDate
     */
    public LocalDate getLockDate() {
        return lockDate;
    }

    /**
     * Setter of lockDate
     * @param lockDate sets the val of lockDate
     */
    public void setLockDate(String lockDate) {
        this.lockDate = DateConverter.convertStringToLocalDate(lockDate);
    }

    /**
     * Getter of cid
     * @return the val of cid
     */
    public long getCid() {
        return cid;
    }

    /**
     * Setter of cid
     * @param cid sets the val of cid
     */
    public void setCid(long cid) {
        this.cid = cid;
    }

    /**
     * Returns all vars as a string with a their matching term
     * @return the concluded string
     * */
    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks +
                "\nLockState: " + this.lockState +
                "\nCID: " + this.cid;
    }
}