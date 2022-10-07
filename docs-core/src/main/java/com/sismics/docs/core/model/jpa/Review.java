package com.sismics.docs.core.model.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

/**
 * Review (set of base functions).
 * 
 * @author sooyounk, sunnyl
 */
@Entity
@Table(name = "T_REVIEW")
public class Review implements Loggable{
    /**
     * Review ID.
     */
    @Id
    @Column(name = "REV_ID_C", length = 36)
    private String id;
    
    /**
     * Document ID.
     */
    @Column(name = "REV_IDDOC_C", nullable = false)
    private String documentId;
    
    /**
     * User ID.
     */
    @Column(name = "REV_IDUSER_C", nullable = false)
    private String userId;
    
    /**
     * GPA. 
     */
    @Column(name = "REV_GPA_N")
    private Integer gpa;
    
    /**
     * Research Experience. 
     */
    @Column(name = "REV_REX_N")
    private Integer researchRating;
    
    /**
     * Work Experience. 
     */
    @Column(name = "REV_WEX_N")
    private Integer workRating;

    /**
     * Research Experience. 
     */
    @Column(name = "REV_SKILLS_N")
    private Integer skillsRating;
    
     /**
     * Letter of Recommendation.
     */
    @Column(name = "REV_LOR_N")
    private Integer letterRating;
    
    /**
     * Creation date.
     */
    @Column(name = "REV_CREATEDATE_D", nullable = false)
    private Date createDate;

    /**
     * Deletion date.
     */
    @Column(name = "REV_DELETEDATE_D")
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


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("document id", documentId)
                .add("user id", userId)
                .add("gpa", gpa)
                .add("research rating", researchRating)
                .add("work rating", workRating)
                .add("letter recommendation rating", letterRating)
                .add("skills rating", skillsRating)
                .toString();
    }

    @Override
    public String toMessage() {
        return documentId;
    }
}
