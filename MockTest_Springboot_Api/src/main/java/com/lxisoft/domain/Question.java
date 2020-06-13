package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "level")
    private String level;

    @Size(min = 6)
    @Column(name = "qstn")
    private String qstn;

    @Size(max = 50)
    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QstnOption> qstnOptions = new HashSet<>();

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AttendedOption> attendedOptions = new HashSet<>();

    @ManyToMany(mappedBy = "questions",cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Exam> exams = new HashSet<>();
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public Question level(String level) {
        this.level = level;
        return this;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQstn() {
        return qstn;
    }

    public Question qstn(String qstn) {
        this.qstn = qstn;
        return this;
    }

    public void setQstn(String qstn) {
        this.qstn = qstn;
    }

    public Set<QstnOption> getQstnOptions() {
        return qstnOptions;
    }

    public Question qstnOptions(Set<QstnOption> qstnOptions) {
        this.qstnOptions = qstnOptions;
        return this;
    }

    public Question addQstnOption(QstnOption qstnOption) {
        this.qstnOptions.add(qstnOption);
        qstnOption.setQuestion(this);
        return this;
    }

    public Question removeQstnOption(QstnOption qstnOption) {
        this.qstnOptions.remove(qstnOption);
        qstnOption.setQuestion(null);
        return this;
    }

    public void setQstnOptions(Set<QstnOption> qstnOptions) {
        this.qstnOptions = qstnOptions;
    }

    public Set<AttendedOption> getAttendedOptions() {
        return attendedOptions;
    }

    public Question attendedOptions(Set<AttendedOption> attendedOptions) {
        this.attendedOptions = attendedOptions;
        return this;
    }

    public Question addAttendedOption(AttendedOption attendedOption) {
        this.attendedOptions.add(attendedOption);
        attendedOption.setQuestion(this);
        return this;
    }

    public Question removeAttendedOption(AttendedOption attendedOption) {
        this.attendedOptions.remove(attendedOption);
        attendedOption.setQuestion(null);
        return this;
    }

    public void setAttendedOptions(Set<AttendedOption> attendedOptions) {
        this.attendedOptions = attendedOptions;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public Question exams(Set<Exam> exams) {
        this.exams = exams;
        return this;
    }

    public Question addExam(Exam exam) {
        this.exams.add(exam);
        exam.getQuestions().add(this);
        return this;
    }

    public Question removeExam(Exam exam) {
        this.exams.remove(exam);
        exam.getQuestions().remove(this);
        return this;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        return id != null && id.equals(((Question) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + getId() +
            ", level='" + getLevel() + "'" +
            ", qstn='" + getQstn() + "'" +
            "}";
    }
}
