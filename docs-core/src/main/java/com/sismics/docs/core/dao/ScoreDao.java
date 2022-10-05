package com.sismics.docs.core.dao;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.sismics.docs.core.constant.AuditLogType;
import com.sismics.docs.core.model.jpa.Score;
import com.sismics.docs.core.util.AuditLogUtil;
import com.sismics.util.context.ThreadLocalContext;

public class ScoreDao {
    /**
     * Add a score.
     *
     * @param score Score
     * @param userId User ID
     * @return New ID
     */
    public String create(Score score, String userId) {
        // Create the UUID
        score.setId(UUID.randomUUID().toString());
        
        // Create the score
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        score.setCreateDate(new Date());
        em.persist(score);
        
        // Create audit log
        AuditLogUtil.create(score, AuditLogType.CREATE, userId);
        
        return score.getId();
    }
}
