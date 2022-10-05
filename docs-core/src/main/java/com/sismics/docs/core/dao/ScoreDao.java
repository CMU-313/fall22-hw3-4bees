package com.sismics.docs.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.List;

import javax.persistence.EntityManager;

import com.sismics.docs.core.constant.AuditLogType;
import com.sismics.docs.core.model.jpa.Score;
import com.sismics.docs.core.util.AuditLogUtil;
import com.sismics.util.context.ThreadLocalContext;

import com.sismics.docs.core.dao.dto.ScoreDto;
import javax.persistence.Query;
import java.sql.Timestamp;
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

    /**
     * Get all scores on a document.
     * 
     * @param documentId Document ID
     * @return List of scores
     */
    public List<ScoreDto> getByDocumentId(String documentId) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        StringBuilder sb = new StringBuilder("select s.SCORE_ID_C, s.SCORE_IDDOC_C, s.SCORE_SKILLS_C, s.SCORE_EXPERIENCE_C, s.SCORE_TRANSCRIPTGPA_C, s.SCORE_MATCH_C, s.SCORE_CREATEDATE_D from T_SCORE s");
        sb.append(" where s.SCORE_IDDOC_C = :documentId and s.SCORE_DELETEDATE_D is null ");
        sb.append(" order by s.SCORE_CREATEDATE_D asc ");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("documentId", documentId);
        @SuppressWarnings("unchecked")
        List<Object[]> l = q.getResultList();
        
        List<ScoreDto> scoreDtoList = new ArrayList<>();
        for (Object[] o : l) {
            int i = 0;
            ScoreDto scoreDto = new ScoreDto();
            scoreDto.setId((String) o[i++]);
            scoreDto.setDocumentId((String) o[i++]);
            scoreDto.setSkills(o[i++].toString());
            scoreDto.setExperience(o[i++].toString());
            scoreDto.setTranscriptGpa(o[i++].toString());
            scoreDto.setMatch((String) o[i++].toString());
            scoreDto.setCreateTimestamp(((Timestamp) o[i++]).getTime());
            scoreDtoList.add(scoreDto);
        }
        return scoreDtoList;
    } 
}
