package com.funsoft.cabinet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Specialite;
import com.funsoft.cabinet.exception.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CabinetApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	//convertir object to json
	ObjectMapper om = new ObjectMapper();


	@Test
	void addDoctor() throws Exception {
		Doctor doctor = new Doctor();
		doctor.setFirstname("rima");
		doctor.setLastname("yahmadi");
		doctor.setEmail("rima@gmail.com");
		doctor.setAddress("mateur");
		doctor.setSpecialite(Specialite.dentiste);

		//pour transformer l'objet doctor en format json
		String objetToJson = om.writeValueAsString(doctor);

		//envoyer une requete d'ajout (requete http)
		MvcResult queryresponse =
				mockMvc.perform(post("/doctors").
						content(objetToJson)
						.contentType(MediaType.APPLICATION_JSON_VALUE)).
						andExpect(status().isOk()).andReturn();


		//recuperer le conenu de la reponse sous forme chaine de caractere
		String resultContent = queryresponse.getResponse().getContentAsString();

		//transformer la reponse en format json en un objet de la classe Doctor
		Doctor response = om.readValue(resultContent,Doctor.class);

		//Assert.assertTrue(response.status() == Boolean.TRUE);
		//Assert.assertTrue(response.getMessage().endsWith("inserted"));
		Assert.assertTrue(response.getId()>0);
		Assert.assertTrue(response.getFirstname().equals(doctor.getFirstname()));
		Assert.assertTrue(response.getLastname().equals(doctor.getLastname()));
		Assert.assertTrue(response.getAddress().equals(doctor.getAddress()));
		Assert.assertTrue(response.getEmail().equals(doctor.getEmail()));
		Assert.assertTrue(response.getSpecialite().equals(doctor.getSpecialite()));
	}
	@Test
	void contextLoads() {
	}

}
