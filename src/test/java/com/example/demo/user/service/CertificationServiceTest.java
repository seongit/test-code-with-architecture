package com.example.demo.user.service;

import com.example.demo.mock.FakeMailSender;
import com.example.demo.user.service.port.MailSender;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CertificationServiceTest {
    @Test
    void 이메일과_컨텐츠가_제대로_발송되는지_테스트한다() {
    	// given
        FakeMailSender fakeMailSender = new FakeMailSender();
        CertificationService certificationService = new CertificationService(fakeMailSender);
    	// when
        certificationService.send("dev.seongeun@gmail.com", 1,"aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");

    	// then
        assertThat(fakeMailSender.email).isEqualTo("dev.seongeun@gmail.com");
        assertThat(fakeMailSender.title).isEqualTo("Please certify your email address");
        assertThat(fakeMailSender.content).contains("Please click the following link to certify your email address:");
    }
}
