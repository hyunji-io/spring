package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("memberA");

		//when
		Long savedId = memberService.join(member);

		//then
		Member findMember = memberService.findOne(savedId).get();
		assertThat(findMember.getName()).isEqualTo(member.getName());
	}

	@Test
	void 중복_회원_예외() {
		//given
		Member memberA = new Member();
		memberA.setName("memberA");
		memberService.join(memberA);

		Member memberB = new Member();
		memberB.setName("memberA");

		//when & then
		assertThatThrownBy(() -> memberService.join(memberB))
			.isInstanceOf(IllegalStateException.class)
			.hasMessage("이름이 존재하는 회원입니다.");
	}

	@Test
	void findOne() {
	}
}