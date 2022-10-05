package com.sismics.docs.core.dao.dto;

public class ScoreDto {
    /**
     * Score ID.
     */
    private String id;
    
    /**
     * Document ID.
     */
    private String documentId;

    /**
     * Skills.
     */
    private Integer skills;
    
    /**
     * Experience.
     */
    private Integer experience;
    
    /**
     * Transcript Gpa.
     */
    private Integer transcriptGPA;
    
    /**
     * Match.
     */
    private Integer match;
    
    /**
     * Creation date of this comment.
     */
    private Long createTimestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public ScoreDto setDocumentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    public Integer getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = Integer.parseInt(skills);
    }
    
    public Integer getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = Integer.parseInt(experience);
    }

    public Integer getTranscriptGPA() {
        return transcriptGPA;
    }

    public void setTranscriptGpa(String transcriptGPA) {
        this.transcriptGPA = Integer.parseInt(transcriptGPA);
    }

    public Integer getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = Integer.parseInt(match);
    }

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
