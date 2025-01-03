package hello.hello_spring.service;

import java.util.List;
import java.util.Optional;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import jakarta.transaction.Transactional;

@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * 회원 가입
	 */
	public Long join(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member).getId();
	}

	/**
	 * 중복 회원 검증
	 */
	private void validateDuplicateMember(Member member) {
		//같은 이름이 있는 중복 회원 X
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이름이 존재하는 회원입니다.");
			});
	}

	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
