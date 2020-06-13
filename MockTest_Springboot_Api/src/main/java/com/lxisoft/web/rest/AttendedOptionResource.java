package com.lxisoft.web.rest;

import com.lxisoft.domain.AttendedOption;
import com.lxisoft.repository.AttendedOptionRepository;
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
 * REST controller for managing {@link com.lxisoft.domain.AttendedOption}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AttendedOptionResource {

    private final Logger log = LoggerFactory.getLogger(AttendedOptionResource.class);

    private static final String ENTITY_NAME = "attendedOption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AttendedOptionRepository attendedOptionRepository;

    public AttendedOptionResource(AttendedOptionRepository attendedOptionRepository) {
        this.attendedOptionRepository = attendedOptionRepository;
    }

    /**
     * {@code POST  /attended-options} : Create a new attendedOption.
     *
     * @param attendedOption the attendedOption to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attendedOption, or with status {@code 400 (Bad Request)} if the attendedOption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/attended-options")
    public ResponseEntity<AttendedOption> createAttendedOption(@RequestBody AttendedOption attendedOption) throws URISyntaxException {
        log.debug("REST request to save AttendedOption : {}", attendedOption);
        if (attendedOption.getId() != null) {
            throw new BadRequestAlertException("A new attendedOption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttendedOption result = attendedOptionRepository.save(attendedOption);
        return ResponseEntity.created(new URI("/api/attended-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /attended-options} : Updates an existing attendedOption.
     *
     * @param attendedOption the attendedOption to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attendedOption,
     * or with status {@code 400 (Bad Request)} if the attendedOption is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attendedOption couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/attended-options")
    public ResponseEntity<AttendedOption> updateAttendedOption(@RequestBody AttendedOption attendedOption) throws URISyntaxException {
        log.debug("REST request to update AttendedOption : {}", attendedOption);
        if (attendedOption.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttendedOption result = attendedOptionRepository.save(attendedOption);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, attendedOption.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /attended-options} : get all the attendedOptions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attendedOptions in body.
     */
    @GetMapping("/attended-options")
    public List<AttendedOption> getAllAttendedOptions() {
        log.debug("REST request to get all AttendedOptions");
        return attendedOptionRepository.findAll();
    }

    /**
     * {@code GET  /attended-options/:id} : get the "id" attendedOption.
     *
     * @param id the id of the attendedOption to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attendedOption, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/attended-options/{id}")
    public ResponseEntity<AttendedOption> getAttendedOption(@PathVariable Long id) {
        log.debug("REST request to get AttendedOption : {}", id);
        Optional<AttendedOption> attendedOption = attendedOptionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(attendedOption);
    }

    /**
     * {@code DELETE  /attended-options/:id} : delete the "id" attendedOption.
     *
     * @param id the id of the attendedOption to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/attended-options/{id}")
    public ResponseEntity<Void> deleteAttendedOption(@PathVariable Long id) {
        log.debug("REST request to delete AttendedOption : {}", id);
        attendedOptionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
