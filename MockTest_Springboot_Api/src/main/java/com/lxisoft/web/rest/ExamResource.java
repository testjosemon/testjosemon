package com.lxisoft.web.rest;

import com.lxisoft.domain.Exam;
import com.lxisoft.repository.ExamRepository;
import com.lxisoft.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lxisoft.domain.Exam}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ExamResource {

    private final Logger log = LoggerFactory.getLogger(ExamResource.class);

    private static final String ENTITY_NAME = "exam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExamRepository examRepository;

    public ExamResource(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    /**
     * {@code POST  /exams} : Create a new exam.
     *
     * @param exam the exam to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new exam, or with status {@code 400 (Bad Request)} if the exam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/exams")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) throws URISyntaxException {
        log.debug("REST request to save Exam : {}", exam);
        if (exam.getId() != null) {
            throw new BadRequestAlertException("A new exam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Exam result = examRepository.save(exam);
        return ResponseEntity.created(new URI("/api/exams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /exams} : Updates an existing exam.
     *
     * @param exam the exam to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exam,
     * or with status {@code 400 (Bad Request)} if the exam is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exam couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/exams")
    public ResponseEntity<Exam> updateExam(@RequestBody Exam exam) throws URISyntaxException {
        log.debug("REST request to update Exam : {}", exam);
        if (exam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Exam result = examRepository.save(exam);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, exam.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /exams} : get all the exams.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of exams in body.
     */
    @GetMapping("/exams")
    public List<Exam> getAllExams(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Exams");
        return examRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /exams/:id} : get the "id" exam.
     *
     * @param id the id of the exam to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exam, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exams/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable Long id) {
        log.debug("REST request to get Exam : {}", id);
        Optional<Exam> exam = examRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(exam);
    }

    /**
     * {@code DELETE  /exams/:id} : delete the "id" exam.
     *
     * @param id the id of the exam to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/exams/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        log.debug("REST request to delete Exam : {}", id);
        examRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
