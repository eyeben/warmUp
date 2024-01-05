package softeerWarmup1.demo.member.service;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import softeerWarmup1.demo.member.domain.Member;
import softeerWarmup1.demo.member.dto.CarDto;
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
        Member member = signupDto.toEntity();
        memberRepository.save(member);
    }

    public void deleteMember(Long memberId){
        memberRepository.deleteById(memberId);
    }

    public void registerCar(Long memberId, CarDto carDto){
        Member member = memberRepository.findById(memberId).orElse(null);
        if(member == null){
            throw new ResourceAccessException("you don't exist in our db");
        }
        member.setCarNum(carDto.getCarNum());
        member.setModelId(carDto.getModelId());
        member.setModelYear(carDto.getModelYear());
        member.setStartYear(carDto.getStartYear());
        memberRepository.save(member);

    }
}
