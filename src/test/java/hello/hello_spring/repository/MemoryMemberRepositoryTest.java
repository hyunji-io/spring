package hello.hello_spring.repository;


import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hello_spring.domain.Member;

class MemoryMemberRepositoryTest {
	MemoryMemberRepository memberRepository = new MemoryMemberRepository();

	@AfterEach
	void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void save() {
		Member member = new Member();
		member.setName("memberA");
		Member savedMember = memberRepository.save(member);
		Member findMember = memberRepository.findById(member.getId()).get();
		assertThat(findMember).isEqualTo(savedMember);
	}

	@Test
	void findByName() {
		Member member1 = new Member();
		member1.setName("member1");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("member2");
		memberRepository.save(member2);

		Member findMember1 = memberRepository.findByName(member1.getName()).get();
		assertThat(findMember1).isEqualTo(member1);
	}

	@Test
	void findAll() {
		Member member1 = new Member();
		member1.setName("member1");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("member2");
		memberRepository.save(member2);

		List<Member> members = memberRepository.findAll();
		assertThat(members).hasSize(2);
	}
}
