package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A AttendedExam.
 */
@Entity
@Table(name = "attended_exam")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AttendedExam implements Serializable {

    public AttendedExam(ZonedDateTime dateTime, Exam exam, UserExtra userExtra) {
		super();
		this.dateTime = dateTime;
		this.exam = exam;
		this.userExtra = userExtra;
	}
    
    public AttendedExam() {
    	
    }

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score")
    private Integer score;

    @Column(name = "total")
    private Integer total;

    @Column(name = "result")
    private Boolean result;

    @Column(name = "percentage")
    private Float percentage;

    @Column(name = "date_time")
    private ZonedDateTime dateTime;

    @OneToMany(mappedBy = "attendedExam",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AttendedOption> attendedOptions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("attendedExams")
    private Exam exam;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("attendedExams")
    private UserExtra userExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public AttendedExam score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotal() {
        return total;
    }

    public AttendedExam total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean isResult() {
        return result;
    }

    public AttendedExam result(Boolean result) {
        this.result = result;
        return this;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Float getPercentage() {
        return percentage;
    }

    public AttendedExam percentage(Float percentage) {
        this.percentage = percentage;
        return this;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public AttendedExam dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Set<AttendedOption> getAttendedOptions() {
        return attendedOptions;
    }

    public AttendedExam attendedOptions(Set<AttendedOption> attendedOptions) {
        this.attendedOptions = attendedOptions;
        return this;
    }

    public AttendedExam addAttendedOption(AttendedOption attendedOption) {
        this.attendedOptions.add(attendedOption);
        attendedOption.setAttendedExam(this);
        return this;
    }

    public AttendedExam removeAttendedOption(AttendedOption attendedOption) {
        this.attendedOptions.remove(attendedOption);
        attendedOption.setAttendedExam(null);
        return this;
    }

    public void setAttendedOptions(Set<AttendedOption> attendedOptions) {
        this.attendedOptions = attendedOptions;
    }

    public Exam getExam() {
        return exam;
    }

    public AttendedExam exam(Exam exam) {
        this.exam = exam;
        return this;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public UserExtra getUserExtra() {
        return userExtra;
    }

    public AttendedExam userExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
        return this;
    }

    public void setUserExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttendedExam)) {
            return false;
        }
        return id != null && id.equals(((AttendedExam) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AttendedExam{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", total=" + getTotal() +
            ", result='" + isResult() + "'" +
            ", percentage=" + getPercentage() +
            ", dateTime='" + getDateTime() + "'" +
            "}";
    }
}
