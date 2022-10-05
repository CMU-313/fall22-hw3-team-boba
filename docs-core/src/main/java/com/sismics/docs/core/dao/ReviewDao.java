package com.sismics.docs.core.dao;

import com.sismics.docs.core.constant.AuditLogType;
import com.sismics.docs.core.dao.dto.ReviewDto;
import com.sismics.docs.core.model.jpa.Review;
import com.sismics.docs.core.util.AuditLogUtil;
import com.sismics.util.context.ThreadLocalContext;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReviewDao {
    /**
     * Create a new review.
     * 
     * @param review Review
     * @param userId User ID
     * @return New ID
     */
    public String create(Review review, String userId, String documentId) {
        // Create the UUID
        review.setId(UUID.randomUUID().toString());

        // Create the review
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        review.setCreateDate(new Date());
        em.persist(review);

        // Create audit log
        AuditLogUtil.create(review, AuditLogType.CREATE, userId);

        // Set document ID and user ID
        review.setUserId(userId);
        review.setDocumentId(documentId);

        return review.getId();
    }

    /**
     * Get reviews by document ID.
     * 
     * @param documentId Document ID
     * @return List of reviews
     */
    public List<ReviewDto> getByDocumentId(String documentId) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        StringBuilder sb = new StringBuilder("select r.REV_ID_C, r.REV_GPA_N, r.REV_SKILLS_N, r.REV_WEX_N, r.REV_REX_N, r.REV_LOR_N from T_REVIEW r");
        sb.append(" where r.REV_IDDOC_C = :documentId and r.REV_DELETEDATE_D is null ");
        sb.append(" order by r.REV_CREATEDATE_D asc ");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("documentId", documentId);
        @SuppressWarnings("unchecked")
        List<Object[]> l = q.getResultList();
        
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (Object[] o : l) {
            int i = 0;
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setId((String) o[i++]);
            reviewDto.setGpa((Integer) o[i++]);
            reviewDto.setSkillsRating(((Integer) o[i++]));
            reviewDto.setWorkRating((Integer) o[i++]);
            reviewDto.setResearchRating((Integer) o[i++]);
            reviewDto.setLetterRating((Integer) o[i]);
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }
    
    /**
     * Returns an active review by its id.
     * 
     * @param id Review ID
     * @return Review
     */
    public Review getActiveById(String id) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        try {
            Query q = em.createQuery("select r from Review r where r.id = :id and r.deleteDate is null");
            q.setParameter("id", id);
            return (Review) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
