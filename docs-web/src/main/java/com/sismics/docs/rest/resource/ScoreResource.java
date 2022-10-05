package com.sismics.docs.rest.resource;

import com.sismics.docs.core.dao.*;
// import com.sismics.docs.core.dao.dto.*;
// import com.sismics.docs.core.dao.dto.ScoreDto;
import com.sismics.docs.core.model.jpa.Score;
import com.sismics.rest.exception.ForbiddenClientException;
// import com.sismics.rest.util.RestUtil;
import com.sismics.rest.util.ValidationUtil;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Score REST resources.
 * 
 * @author bgamard
 */

@Path("/scores")
public class ScoreResource extends BaseResource {
    /**
     * Add a score.
     *
     * @api {put} /score Add a score
     * @apiName PutScore
     * @apiGroup Score
     * @apiParam {String} id Document ID
     * @apiSuccess {String} id Score ID
     * @apiParam {Number} skills Skills
     * @apiParam {Number} experience Experience
     * @apiParam {Number} transcript_GPA Transcript GPA
     * @apiParam {Number} match Match
     * @apiSuccess {Number} create_date Create date (timestamp)
     * @apiError (client) ForbiddenError Access denied
     * @apiError (client) ValidationError Validation error
     * @apiError (client) NotFound Document not found
     * @apiPermission user
     * @apiVersion 1.5.0
     *
     * @param documentId Document ID
     * @param skills Skills
     * @param experience Experience
     * @param transcriptGPA Transcript GPA
     * @param match Match
     * @param createDateStr Creation date
     * @return Response
     */
    /**@POST
    *@Path ("")
     */

    @POST
    @Path ("scores")
    public Response add(
            @FormParam("documentId") String documentId,
            @FormParam("skills") String skills,
            @FormParam("experience") String experience,
            @FormParam("transcriptGPA") String transcriptGPA,
            @FormParam("match") String match,
            @FormParam("createDateStr") String createDateStr) {
        if (!authenticate()) {
            throw new ForbiddenClientException();
        }
        
        // Validate input data
        documentId = ValidationUtil.validateLength(documentId, "document", 1, null, false);
        skills = ValidationUtil.validateLength(skills, "skills", 1, 1, false);
        experience = ValidationUtil.validateLength(experience, "experience", 1, 1, false);
        transcriptGPA = ValidationUtil.validateLength(transcriptGPA, "transcript_gpaa", 1, 1, false);
        match = ValidationUtil.validateLength(match, "match", 1, 1, false);
        Date createDate = ValidationUtil.validateDate(createDateStr, "create_date", true);

        // Create the score
        Score score = new Score();
        score.setDocumentId(documentId);
        score.setUserId(principal.getId());
        score.setSkillsScore(skills);
        score.setExperienceScore(experience);
        score.setTranscriptGPAScore(transcriptGPA);
        score.setMatchScore(match);
        ScoreDao scoreDao = new ScoreDao();
        scoreDao.create(score, principal.getId());
        if (createDate == null) {
            score.setCreateDate(new Date());
        } else {
            score.setCreateDate(createDate);
        }
        
        // Return the score
        JsonObjectBuilder response = Json.createObjectBuilder()
                .add("id", score.getId())
                .add("documentId", score.getDocumentId())
                .add("userId", score.getUserId())
                .add("skills", score.getSkillsScore())
                .add("experience", score.getExperienceScore())
                .add("transcript_gpa", score.getTranscriptGPAScore())
                .add("create_date", score.getCreateDate().getTime());
                // .add("total_score", score.getTotalScore());
        return Response.ok().entity(response.build()).build();
    }
}
