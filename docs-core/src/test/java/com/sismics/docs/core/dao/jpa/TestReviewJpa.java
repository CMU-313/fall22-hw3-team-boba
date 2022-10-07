package com.sismics.docs.core.dao.jpa;

import com.sismics.docs.BaseTransactionalTest;
import com.sismics.docs.core.dao.DocumentDao;
import com.sismics.docs.core.dao.ReviewDao;
import com.sismics.docs.core.dao.UserDao;
import com.sismics.docs.core.model.jpa.Document;
import com.sismics.docs.core.model.jpa.Review;
import com.sismics.docs.core.model.jpa.User;
import com.sismics.docs.core.util.TransactionUtil;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the persistance layer.
 * 
 * @author jtremeaux
 */
public class TestReviewJpa extends BaseTransactionalTest {
    @Test
    public void testReviewJpa() throws Exception {
        // Create a new review with existing user and document
        ReviewDao reviewDao = new ReviewDao();
        DocumentDao docDao = new DocumentDao();
        UserDao userDao = new UserDao();

        // Create new user for testing creation of new review
        User user = new User();
        user.setUsername("username");
        user.setPassword("12345678");
        user.setEmail("toto@docs.com");
        user.setRoleId("admin");
        user.setStorageQuota(10L);
        String uid = userDao.create(user, "me");
        
        TransactionUtil.commit();

        // Create new document for testing creation of new review
        Document doc = new Document();
        doc.setUpdateDate(new Date());
        doc.setUserId("admin");
        doc.setTitle("example");
        doc.setLanguage("eng");
        doc.setCreateDate(new Date());
        String docid = docDao.create(doc,uid);
        TransactionUtil.commit();

        Review review = new Review();
        review.setUserId("admin");
        review.setDocumentId(docid);
        review.setGpa(3);
        review.setLetterRating(2);
        review.setResearchRating(3);
        review.setSkillsRating(4);
        review.setWorkRating(4);
        review.setCreateDate(new Date());
        String id = reviewDao.create(review, uid, docid);
        
        TransactionUtil.commit();

        // Search a review by the reivew ID
        review = reviewDao.getActiveById(id);
        Assert.assertNotNull(review);

        // Search a review by the doucment id
        Assert.assertEquals(docid, review.getDocumentId());

        
    }
}
