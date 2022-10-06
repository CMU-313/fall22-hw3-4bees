package com.sismics.docs.rest;

import java.util.Date;

import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
// import javax.ws.rs.core.Response;
// import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Test;

import com.sismics.util.filter.TokenBasedSecurityFilter;


/**
 * Tests of the score resource.
 * 
 */
public class TestScoreResource extends BaseJerseyTest {
    /**
     * Test the comment resource.
     */
    @Test
    public void testScoreResource() {
        // Login score1
        clientUtil.createUser("score1");
        String score1Token = clientUtil.login("score1");

        // Create a document
        long create1Date = new Date().getTime();
        JsonObject json = target().path("/document").request()
                .cookie(TokenBasedSecurityFilter.COOKIE_NAME, score1Token)
                .put(Entity.form(new Form()
                        .param("title", "My super title document 1")
                        .param("description", "My super description for document 1")
                        .param("language", "eng")
                        .param("create_date", Long.toString(create1Date))), JsonObject.class);
        String document1Id = json.getString("id");
        Assert.assertNotNull(document1Id);

        // Testing POST API
        json = target().path("/scores/scores").request()
                .cookie(TokenBasedSecurityFilter.COOKIE_NAME, score1Token)
                .post(Entity.form(new Form()
                        .param("documentId", document1Id)
                        .param("skills", "3")
                        .param("experience", "5")
                        .param("transcriptGPA", "2")
                        .param("match", "4")
                        .param("createDateStr", Long.toString(create1Date))),
                        JsonObject.class);

        String score1Id = json.getString("id");
        Assert.assertNotNull(score1Id);
        Assert.assertEquals(3, json.getInt("skills"));
        Assert.assertEquals(5, json.getInt("experience"));
        Assert.assertEquals(2, json.getInt("transcript_gpa"));
        Assert.assertEquals(4, json.getInt("match"));
        Assert.assertNotNull(json.getJsonNumber("create_date"));

        // Testing GET API
        json = target().path("/scores/" + document1Id).request()
                .cookie(TokenBasedSecurityFilter.COOKIE_NAME, score1Token)
                .get(JsonObject.class);
        Assert.assertEquals(1, json.getJsonArray("scores").size());
        JsonObject score = json.getJsonArray("scores").getJsonObject(0);
        Assert.assertEquals(score1Id, score.getString("id"));
        Assert.assertEquals(3, score.getInt("skills"));
        Assert.assertEquals(5, score.getInt("experience"));
        Assert.assertEquals(2, score.getInt("transcriptGPA"));
        Assert.assertEquals(4, score.getInt("match"));
        Assert.assertNotNull(score.getJsonNumber("create_date"));
    }
}
