package hello.hello_spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hello_spring.repository.JdbcMemberRepository;
import hello.hello_spring.repository.JdbcTemplateMemberRepository;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;

@Configuration
public class SpringConfig {
	// 스프링이 자체적으로 관리
	private final DataSource dataSource;
	private final EntityManager em;

	@Autowired
	public SpringConfig(DataSource dataSource, EntityManager em) {
		this.dataSource = dataSource;
		this.em = em;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new JpaMemberRepository(em);
	}
}
