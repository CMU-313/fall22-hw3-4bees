package com.sismics.docs.core.model.jpa;

// import com.google.common.base.MoreObjects; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Score entity.
 */

@Entity
@Table(name = "T_SCORE")
public class Score implements Loggable {
    /**
     * Score ID.
     */
    @Id
    @Column(name="SCORE_ID_C", length = 36)
    private String id;

    /**
     * Document ID.
     */
    @Column(name="SCORE_IDDOC_C", nullable = false, length = 36)
    private String documentId;

    /**
     * User ID.
     */
    @Column(name="SCORE_IDUSER_C", nullable = false, length = 36)
    private String userId;

    /**
     * Skills score.
     */
    @Column(name="SCORE_SKILLS_C")
    private int skills;

    /**
     * Experience score.
     */
    @Column(name="SCORE_EXPERIENCE_C")
    private int experience;

    /**
     * Transcript and GPA score.
     */
    @Column(name="SCORE_TRANSCRIPTGPA_C")
    private int transcriptGPA;

    /**
     * Match with college score.
     */
    @Column(name="SCORE_MATCH_C")
    private int match;

    /**
     * Creation date.
     */
    @Column(name = "SCORE_CREATEDATE_D", nullable = false)
    private Date createDate;

    /**
     * Deletion date.
     */
    @Column(name = "SCORE_DELETEDATE_D")
    private Date deleteDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSkillsScore() {
        return skills;
    }

    public void setSkillsScore(String skills) {
        this.skills = Integer.parseInt(skills);
    }

    public int getExperienceScore() {
        return experience;
    }

    public void setExperienceScore(String experience) {
        this.experience = Integer.parseInt(experience);
    }

    public int getTranscriptGPAScore() {
        return transcriptGPA;
    }

    public void setTranscriptGPAScore(String transcriptGPA) {
        this.transcriptGPA = Integer.parseInt(transcriptGPA);
    }

    public int getMatchScore() {
        return match;
    }

    public void setMatchScore(String match) {
        this.match = Integer.parseInt(match);
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override // Uncertain about its usage, but required for loggable
    public String toMessage() {
        return documentId;
    }

    // For dashboard: create function for total score
    // public int getTotalScore() {
    //     return 1;
    // } 
} 
