/*package com.oscarisma.thebankproject;

import com.oscarisma.thebankproject.Controllers.UserController;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
@DataJpaTest
class ThebankprojectApplicationTests {

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

	@Test
	public void givenUserDoesNotExist_whenUserInfoIsRetrieved_then404IsReceived()
	throws ClientProtocolException, IOException {

		String name = "500";
		HttpUriRequest request = new HttpGet("http://localhost:8080/api/user/" + name);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		assertEquals(HttpStatus.SC_NOT_FOUND,httpResponse.getStatusLine().getStatusCode());
	}

    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
        throws ClientProtocolException, IOException{
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/users");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals(jsonMimeType,mimeType);
    }

    @Test
    public void testUserCreation() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        String requestJson = "{\"firstName\": \"Oscar\", \"lastName\": \"Gil\", \"username\":\"Karrion\" ,\"password\":\"aaa\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Oscar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Gil"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("aaa"));
    }

}
 */