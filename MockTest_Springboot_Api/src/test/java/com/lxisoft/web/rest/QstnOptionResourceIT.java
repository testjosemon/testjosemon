package com.lxisoft.web.rest;

import com.lxisoft.MockTestApp;
import com.lxisoft.domain.QstnOption;
import com.lxisoft.repository.QstnOptionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QstnOptionResource} REST controller.
 */
@SpringBootTest(classes = MockTestApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class QstnOptionResourceIT {

    private static final String DEFAULT_OPTION = "AAAAAAAAAA";
    private static final String UPDATED_OPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ANSWER = false;
    private static final Boolean UPDATED_IS_ANSWER = true;

    @Autowired
    private QstnOptionRepository qstnOptionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQstnOptionMockMvc;

    private QstnOption qstnOption;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QstnOption createEntity(EntityManager em) {
        QstnOption qstnOption = new QstnOption()
            .option(DEFAULT_OPTION)
            .isAnswer(DEFAULT_IS_ANSWER);
        return qstnOption;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QstnOption createUpdatedEntity(EntityManager em) {
        QstnOption qstnOption = new QstnOption()
            .option(UPDATED_OPTION)
            .isAnswer(UPDATED_IS_ANSWER);
        return qstnOption;
    }

    @BeforeEach
    public void initTest() {
        qstnOption = createEntity(em);
    }

    @Test
    @Transactional
    public void createQstnOption() throws Exception {
        int databaseSizeBeforeCreate = qstnOptionRepository.findAll().size();

        // Create the QstnOption
        restQstnOptionMockMvc.perform(post("/api/qstn-options").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(qstnOption)))
            .andExpect(status().isCreated());

        // Validate the QstnOption in the database
        List<QstnOption> qstnOptionList = qstnOptionRepository.findAll();
        assertThat(qstnOptionList).hasSize(databaseSizeBeforeCreate + 1);
        QstnOption testQstnOption = qstnOptionList.get(qstnOptionList.size() - 1);
        assertThat(testQstnOption.getOption()).isEqualTo(DEFAULT_OPTION);
        assertThat(testQstnOption.isIsAnswer()).isEqualTo(DEFAULT_IS_ANSWER);
    }

    @Test
    @Transactional
    public void createQstnOptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qstnOptionRepository.findAll().size();

        // Create the QstnOption with an existing ID
        qstnOption.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQstnOptionMockMvc.perform(post("/api/qstn-options").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(qstnOption)))
            .andExpect(status().isBadRequest());

        // Validate the QstnOption in the database
        List<QstnOption> qstnOptionList = qstnOptionRepository.findAll();
        assertThat(qstnOptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQstnOptions() throws Exception {
        // Initialize the database
        qstnOptionRepository.saveAndFlush(qstnOption);

        // Get all the qstnOptionList
        restQstnOptionMockMvc.perform(get("/api/qstn-options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qstnOption.getId().intValue())))
            .andExpect(jsonPath("$.[*].option").value(hasItem(DEFAULT_OPTION)))
            .andExpect(jsonPath("$.[*].isAnswer").value(hasItem(DEFAULT_IS_ANSWER.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getQstnOption() throws Exception {
        // Initialize the database
        qstnOptionRepository.saveAndFlush(qstnOption);

        // Get the qstnOption
        restQstnOptionMockMvc.perform(get("/api/qstn-options/{id}", qstnOption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qstnOption.getId().intValue()))
            .andExpect(jsonPath("$.option").value(DEFAULT_OPTION))
            .andExpect(jsonPath("$.isAnswer").value(DEFAULT_IS_ANSWER.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQstnOption() throws Exception {
        // Get the qstnOption
        restQstnOptionMockMvc.perform(get("/api/qstn-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQstnOption() throws Exception {
        // Initialize the database
        qstnOptionRepository.saveAndFlush(qstnOption);

        int databaseSizeBeforeUpdate = qstnOptionRepository.findAll().size();

        // Update the qstnOption
        QstnOption updatedQstnOption = qstnOptionRepository.findById(qstnOption.getId()).get();
        // Disconnect from session so that the updates on updatedQstnOption are not directly saved in db
        em.detach(updatedQstnOption);
        updatedQstnOption
            .option(UPDATED_OPTION)
            .isAnswer(UPDATED_IS_ANSWER);

        restQstnOptionMockMvc.perform(put("/api/qstn-options").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedQstnOption)))
            .andExpect(status().isOk());

        // Validate the QstnOption in the database
        List<QstnOption> qstnOptionList = qstnOptionRepository.findAll();
        assertThat(qstnOptionList).hasSize(databaseSizeBeforeUpdate);
        QstnOption testQstnOption = qstnOptionList.get(qstnOptionList.size() - 1);
        assertThat(testQstnOption.getOption()).isEqualTo(UPDATED_OPTION);
        assertThat(testQstnOption.isIsAnswer()).isEqualTo(UPDATED_IS_ANSWER);
    }

    @Test
    @Transactional
    public void updateNonExistingQstnOption() throws Exception {
        int databaseSizeBeforeUpdate = qstnOptionRepository.findAll().size();

        // Create the QstnOption

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQstnOptionMockMvc.perform(put("/api/qstn-options").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(qstnOption)))
            .andExpect(status().isBadRequest());

        // Validate the QstnOption in the database
        List<QstnOption> qstnOptionList = qstnOptionRepository.findAll();
        assertThat(qstnOptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQstnOption() throws Exception {
        // Initialize the database
        qstnOptionRepository.saveAndFlush(qstnOption);

        int databaseSizeBeforeDelete = qstnOptionRepository.findAll().size();

        // Delete the qstnOption
        restQstnOptionMockMvc.perform(delete("/api/qstn-options/{id}", qstnOption.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QstnOption> qstnOptionList = qstnOptionRepository.findAll();
        assertThat(qstnOptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
