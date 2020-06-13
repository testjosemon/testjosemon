package com.lxisoft.web.rest;

import com.lxisoft.domain.QstnOption;
import com.lxisoft.repository.QstnOptionRepository;
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
 * REST controller for managing {@link com.lxisoft.domain.QstnOption}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class QstnOptionResource {

    private final Logger log = LoggerFactory.getLogger(QstnOptionResource.class);

    private static final String ENTITY_NAME = "qstnOption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QstnOptionRepository qstnOptionRepository;

    public QstnOptionResource(QstnOptionRepository qstnOptionRepository) {
        this.qstnOptionRepository = qstnOptionRepository;
    }

    /**
     * {@code POST  /qstn-options} : Create a new qstnOption.
     *
     * @param qstnOption the qstnOption to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qstnOption, or with status {@code 400 (Bad Request)} if the qstnOption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qstn-options")
    public ResponseEntity<QstnOption> createQstnOption(@RequestBody QstnOption qstnOption) throws URISyntaxException {
        log.debug("REST request to save QstnOption : {}", qstnOption);
        if (qstnOption.getId() != null) {
            throw new BadRequestAlertException("A new qstnOption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QstnOption result = qstnOptionRepository.save(qstnOption);
        return ResponseEntity.created(new URI("/api/qstn-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qstn-options} : Updates an existing qstnOption.
     *
     * @param qstnOption the qstnOption to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qstnOption,
     * or with status {@code 400 (Bad Request)} if the qstnOption is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qstnOption couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qstn-options")
    public ResponseEntity<QstnOption> updateQstnOption(@RequestBody QstnOption qstnOption) throws URISyntaxException {
        log.debug("REST request to update QstnOption : {}", qstnOption);
        if (qstnOption.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QstnOption result = qstnOptionRepository.save(qstnOption);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qstnOption.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qstn-options} : get all the qstnOptions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qstnOptions in body.
     */
    @GetMapping("/qstn-options")
    public List<QstnOption> getAllQstnOptions() {
        log.debug("REST request to get all QstnOptions");
        return qstnOptionRepository.findAll();
    }

    /**
     * {@code GET  /qstn-options/:id} : get the "id" qstnOption.
     *
     * @param id the id of the qstnOption to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qstnOption, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qstn-options/{id}")
    public ResponseEntity<QstnOption> getQstnOption(@PathVariable Long id) {
        log.debug("REST request to get QstnOption : {}", id);
        Optional<QstnOption> qstnOption = qstnOptionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(qstnOption);
    }

    /**
     * {@code DELETE  /qstn-options/:id} : delete the "id" qstnOption.
     *
     * @param id the id of the qstnOption to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qstn-options/{id}")
    public ResponseEntity<Void> deleteQstnOption(@PathVariable Long id) {
        log.debug("REST request to delete QstnOption : {}", id);
        qstnOptionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
