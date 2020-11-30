package com.example.springboot;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.springboot.model.UserForm;
import com.example.springboot.service.CreditUtil;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class CreditControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getCredit() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/credit").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Wellcome to Credit Controller!")));
	}

	@Test
	public void checkCredit_shouldReject() throws Exception {
        UserForm uf = new UserForm("hasan", 1000, "11111111111", "5555555555");
        HashMap<String, String> res = CreditUtil.checkCredit(uf);

        assertThat(res.get("status"), equalTo("reject"));
	}

	@Test
	public void checkCredit_shouldAcccept() throws Exception {
        UserForm uf = new UserForm("hasan", 1000, "22222222222", "5555555555");
        HashMap<String, String> res = CreditUtil.checkCredit(uf);

        assertThat(res.get("status"), equalTo("accept"));
	}

	@Test
	public void checkCredit_shouldCheckAmount() throws Exception {
        UserForm uf = new UserForm("hasan", 5000, "33333333333", "5555555555");
        HashMap<String, String> res = CreditUtil.checkCredit(uf);
        System.out.println(res.get("amount"));
        assertThat(res.get("amount"), equalTo("20000"));
	}

	@Test
	public void checkCredit_shouldNotSendSMS() throws Exception {
        UserForm uf = new UserForm("hasan", 5000, "33333333333", "0555-");
        HashMap<String, String> res = CreditUtil.checkCredit(uf);
        System.out.println(res.get("smsSent"));
        assertThat(res.get("smsSent"), equalTo("false"));
	}
}