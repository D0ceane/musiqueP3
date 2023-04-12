package com.example.musiqueP3;

import com.example.musiqueP3.controller.MusicController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MusiqueP3ApplicationTests {

	@Autowired MusicController controller;
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
