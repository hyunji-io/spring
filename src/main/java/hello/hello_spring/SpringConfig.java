package hello.hello_spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hello_spring.aop.TimeTraceAop;
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
	private final MemberRepository memberRepository;

	@Autowired
	public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
		this.dataSource = dataSource;
		this.em = em;
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}

	// @Bean
	// public MemberRepository memberRepository() {
	// 	return new JpaMemberRepository(em);
	// }
}
