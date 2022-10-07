package com.sismics.docs.core.dao.dto;

public class ReviewDto {
    /**
     * Review ID.
     */
    private String id;
    
    /**
     * GPA. 
     */
    private Integer gpa;
    
    /**
     * Research Experience. 
     */
    private Integer researchRating;
    
    /**
     * Work Experience. 
     */
    private Integer workRating;

    /**
     * Research Experience. 
     */
    private Integer skillsRating;
    
     /**
     * Letter of Recommendation.
     */
    private Integer letterRating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGpa() {
        return gpa;
    }

    public void setGpa(Integer gpa) {
        this.gpa = gpa;
    }

    public Integer getSkillsRating() {
        return skillsRating;
    }

    public void setSkillsRating(Integer skills) {
        this.skillsRating = skills;
    }

    public Integer getWorkRating() {
        return workRating;
    }

    public void setWorkRating(Integer work) {
        this.workRating = work;
    }

    public Integer getResearchRating() {
        return researchRating;
    }

    public void setResearchRating(Integer research) {
        this.researchRating = research;
    }

    public Integer getLetterRating() {
        return letterRating;
    }

    public void setLetterRating(Integer letter) {
        this.letterRating = letter;
    }
}
