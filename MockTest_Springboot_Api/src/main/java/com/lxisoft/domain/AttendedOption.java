package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AttendedOption.
 */
@Entity
@Table(name = "attended_option")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AttendedOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attended_opt")
    private String attendedOpt;

    @Column(name = "attended_answer")
    private Boolean attendedAnswer;

    @ManyToOne
    @JsonIgnoreProperties("attendedOptions")
    private AttendedExam attendedExam;

    @ManyToOne
    @JsonIgnoreProperties("attendedOptions")
    private Question question;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttendedOpt() {
        return attendedOpt;
    }

    public AttendedOption attendedOpt(String attendedOpt) {
        this.attendedOpt = attendedOpt;
        return this;
    }

    public void setAttendedOpt(String attendedOpt) {
        this.attendedOpt = attendedOpt;
    }

    public Boolean isAttendedAnswer() {
        return attendedAnswer;
    }

    public AttendedOption attendedAnswer(Boolean attendedAnswer) {
        this.attendedAnswer = attendedAnswer;
        return this;
    }

    public void setAttendedAnswer(Boolean attendedAnswer) {
        this.attendedAnswer = attendedAnswer;
    }

    public AttendedExam getAttendedExam() {
        return attendedExam;
    }

    public AttendedOption attendedExam(AttendedExam attendedExam) {
        this.attendedExam = attendedExam;
        return this;
    }

    public void setAttendedExam(AttendedExam attendedExam) {
        this.attendedExam = attendedExam;
    }

    public Question getQuestion() {
        return question;
    }

    public AttendedOption question(Question question) {
        this.question = question;
        return this;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttendedOption)) {
            return false;
        }
        return id != null && id.equals(((AttendedOption) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AttendedOption{" +
            "id=" + getId() +
            ", attendedOpt='" + getAttendedOpt() + "'" +
            ", attendedAnswer='" + isAttendedAnswer() + "'" +
            "}";
    }
}
