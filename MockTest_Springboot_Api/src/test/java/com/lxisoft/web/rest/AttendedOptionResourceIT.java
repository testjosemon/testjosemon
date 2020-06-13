package com.lxisoft.web.rest;

import com.lxisoft.MockTestApp;
import com.lxisoft.domain.AttendedOption;
import com.lxisoft.repository.AttendedOptionRepository;

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
 * Integration tests for the {@link AttendedOptionResource} REST controller.
 */
@SpringBootTest(classes = MockTestApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class AttendedOptionResourceIT {

    private static final String DEFAULT_ATTENDED_OPT = "AAAAAAAAAA";
    private static final String UPDATED_ATTENDED_OPT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ATTENDED_ANSWER = false;
    private static final Boolean UPDATED_ATTENDED_ANSWER = true;

    @Autowired
    private AttendedOptionRepository attendedOptionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAttendedOptionMockMvc;

    private AttendedOption attendedOption;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttendedOption createEntity(EntityManager em) {
        AttendedOption attendedOption = new AttendedOption()
            .attendedOpt(DEFAULT_ATTENDED_OPT)
            .attendedAnswer(DEFAULT_ATTENDED_ANSWER);
        return attendedOption;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttendedOption createUpdatedEntity(EntityManager em) {
        AttendedOption attendedOption = new AttendedOption()
            .attendedOpt(UPDATED_ATTENDED_OPT)
            .attendedAnswer(UPDATED_ATTENDED_ANSWER);
        return attendedOption;
    }

    @BeforeEach
    public void initTest() {
        attendedOption = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttendedOption() throws Exception {
        int databaseSizeBeforeCreate = attendedOptionRepository.findAll().size();

        // Create the AttendedOption
        restAttendedOptionMockMvc.perform(post("/api/attended-options").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attendedOption)))
            .andExpect(status().isCreated());

        // Validate the AttendedOption in the database
        List<AttendedOption> attendedOptionList = attendedOptionRepository.findAll();
        assertThat(attendedOptionList).hasSize(databaseSizeBeforeCreate + 1);
        AttendedOption testAttendedOption = attendedOptionList.get(attendedOptionList.size() - 1);
        assertThat(testAttendedOption.getAttendedOpt()).isEqualTo(DEFAULT_ATTENDED_OPT);
        assertThat(testAttendedOption.isAttendedAnswer()).isEqualTo(DEFAULT_ATTENDED_ANSWER);
    }

    @Test
    @Transactional
    public void createAttendedOptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attendedOptionRepository.findAll().size();

        // Create the AttendedOption with an existing ID
        attendedOption.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttendedOptionMockMvc.perform(post("/api/attended-options").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attendedOption)))
            .andExpect(status().isBadRequest());

        // Validate the AttendedOption in the database
        List<AttendedOption> attendedOptionList = attendedOptionRepository.findAll();
        assertThat(attendedOptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAttendedOptions() throws Exception {
        // Initialize the database
        attendedOptionRepository.saveAndFlush(attendedOption);

        // Get all the attendedOptionList
        restAttendedOptionMockMvc.perform(get("/api/attended-options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attendedOption.getId().intValue())))
            .andExpect(jsonPath("$.[*].attendedOpt").value(hasItem(DEFAULT_ATTENDED_OPT)))
            .andExpect(jsonPath("$.[*].attendedAnswer").value(hasItem(DEFAULT_ATTENDED_ANSWER.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAttendedOption() throws Exception {
        // Initialize the database
        attendedOptionRepository.saveAndFlush(attendedOption);

        // Get the attendedOption
        restAttendedOptionMockMvc.perform(get("/api/attended-options/{id}", attendedOption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(attendedOption.getId().intValue()))
            .andExpect(jsonPath("$.attendedOpt").value(DEFAULT_ATTENDED_OPT))
            .andExpect(jsonPath("$.attendedAnswer").value(DEFAULT_ATTENDED_ANSWER.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAttendedOption() throws Exception {
        // Get the attendedOption
        restAttendedOptionMockMvc.perform(get("/api/attended-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttendedOption() throws Exception {
        // Initialize the database
        attendedOptionRepository.saveAndFlush(attendedOption);

        int databaseSizeBeforeUpdate = attendedOptionRepository.findAll().size();

        // Update the attendedOption
        AttendedOption updatedAttendedOption = attendedOptionRepository.findById(attendedOption.getId()).get();
        // Disconnect from session so that the updates on updatedAttendedOption are not directly saved in db
        em.detach(updatedAttendedOption);
        updatedAttendedOption
            .attendedOpt(UPDATED_ATTENDED_OPT)
            .attendedAnswer(UPDATED_ATTENDED_ANSWER);

        restAttendedOptionMockMvc.perform(put("/api/attended-options").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttendedOption)))
            .andExpect(status().isOk());

        // Validate the AttendedOption in the database
        List<AttendedOption> attendedOptionList = attendedOptionRepository.findAll();
        assertThat(attendedOptionList).hasSize(databaseSizeBeforeUpdate);
        AttendedOption testAttendedOption = attendedOptionList.get(attendedOptionList.size() - 1);
        assertThat(testAttendedOption.getAttendedOpt()).isEqualTo(UPDATED_ATTENDED_OPT);
        assertThat(testAttendedOption.isAttendedAnswer()).isEqualTo(UPDATED_ATTENDED_ANSWER);
    }

    @Test
    @Transactional
    public void updateNonExistingAttendedOption() throws Exception {
        int databaseSizeBeforeUpdate = attendedOptionRepository.findAll().size();

        // Create the AttendedOption

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttendedOptionMockMvc.perform(put("/api/attended-options").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(attendedOption)))
            .andExpect(status().isBadRequest());

        // Validate the AttendedOption in the database
        List<AttendedOption> attendedOptionList = attendedOptionRepository.findAll();
        assertThat(attendedOptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttendedOption() throws Exception {
        // Initialize the database
        attendedOptionRepository.saveAndFlush(attendedOption);

        int databaseSizeBeforeDelete = attendedOptionRepository.findAll().size();

        // Delete the attendedOption
        restAttendedOptionMockMvc.perform(delete("/api/attended-options/{id}", attendedOption.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AttendedOption> attendedOptionList = attendedOptionRepository.findAll();
        assertThat(attendedOptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
