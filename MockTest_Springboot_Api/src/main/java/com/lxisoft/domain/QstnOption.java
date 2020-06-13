package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A QstnOption.
 */
@Entity
@Table(name = "qstn_option")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QstnOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "jhi_option")
    private String option;

    @Column(name = "is_answer")
    private Boolean isAnswer=false;

    @ManyToOne
    @JsonIgnoreProperties("qstnOptions")
    private Question question;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public QstnOption option(String option) {
        this.option = option;
        return this;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Boolean isIsAnswer() {
        return isAnswer;
    }

    public QstnOption isAnswer(Boolean isAnswer) {
        this.isAnswer = isAnswer;
        return this;
    }

    public void setIsAnswer(Boolean isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public QstnOption question(Question question) {
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
        if (!(o instanceof QstnOption)) {
            return false;
        }
        return id != null && id.equals(((QstnOption) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QstnOption{" +
            "id=" + getId() +
            ", option='" + getOption() + "'" +
            ", isAnswer='" + isIsAnswer() + "'" +
            "}";
    }
}
