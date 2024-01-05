package softeerWarmup1.demo.member.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softeerWarmup1.demo.member.domain.Member;
import softeerWarmup1.demo.member.dto.LoginDto;
import softeerWarmup1.demo.member.dto.SignupDto;
import softeerWarmup1.demo.member.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member getMemberWithDto(LoginDto loginDto){
        Member member = memberRepository
                .findByLoginIdAndPassword(loginDto.getLoginId(), loginDto.getPassword()).orElse(null);
        return member;

    }

    public void signup(SignupDto signupDto){
        if(memberRepository.existsByNickname(signupDto.getNickname()))
            throw new IllegalStateException("find different Nickname");
    }

}
