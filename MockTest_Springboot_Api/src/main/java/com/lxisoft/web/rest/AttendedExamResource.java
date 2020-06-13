package com.lxisoft.web.rest;

import com.lxisoft.domain.AttendedExam;
import com.lxisoft.repository.AttendedExamRepository;
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
 * REST controller for managing {@link com.lxisoft.domain.AttendedExam}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AttendedExamResource {

    private final Logger log = LoggerFactory.getLogger(AttendedExamResource.class);

    private static final String ENTITY_NAME = "attendedExam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AttendedExamRepository attendedExamRepository;

    public AttendedExamResource(AttendedExamRepository attendedExamRepository) {
        this.attendedExamRepository = attendedExamRepository;
    }

    /**
     * {@code POST  /attended-exams} : Create a new attendedExam.
     *
     * @param attendedExam the attendedExam to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attendedExam, or with status {@code 400 (Bad Request)} if the attendedExam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/attended-exams")
    public ResponseEntity<AttendedExam> createAttendedExam(@RequestBody AttendedExam attendedExam) throws URISyntaxException {
        log.debug("REST request to save AttendedExam : {}", attendedExam);
        if (attendedExam.getId() != null) {
            throw new BadRequestAlertException("A new attendedExam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttendedExam result = attendedExamRepository.save(attendedExam);
        return ResponseEntity.created(new URI("/api/attended-exams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /attended-exams} : Updates an existing attendedExam.
     *
     * @param attendedExam the attendedExam to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attendedExam,
     * or with status {@code 400 (Bad Request)} if the attendedExam is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attendedExam couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attended-exams")
    public ResponseEntity<AttendedExam> updateAttendedExam(@RequestBody AttendedExam attendedExam) throws URISyntaxException {
        log.debug("REST request to update AttendedExam : {}", attendedExam);
        if (attendedExam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttendedExam result = attendedExamRepository.save(attendedExam);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, attendedExam.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /attended-exams} : get all the attendedExams.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attendedExams in body.
     */
    @GetMapping("/attended-exams")
    public List<AttendedExam> getAllAttendedExams() {
        log.debug("REST request to get all AttendedExams");
        return attendedExamRepository.findAll();
    }

    /**
     * {@code GET  /attended-exams/:id} : get the "id" attendedExam.
     *
     * @param id the id of the attendedExam to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attendedExam, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attended-exams/{id}")
    public ResponseEntity<AttendedExam> getAttendedExam(@PathVariable Long id) {
        log.debug("REST request to get AttendedExam : {}", id);
        Optional<AttendedExam> attendedExam = attendedExamRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(attendedExam);
    }

    /**
     * {@code DELETE  /attended-exams/:id} : delete the "id" attendedExam.
     *
     * @param id the id of the attendedExam to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attended-exams/{id}")
    public ResponseEntity<Void> deleteAttendedExam(@PathVariable Long id) {
        log.debug("REST request to delete AttendedExam : {}", id);
        attendedExamRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
