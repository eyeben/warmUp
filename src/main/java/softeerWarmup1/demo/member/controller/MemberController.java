package softeerWarmup1.demo.member.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softeerWarmup1.demo.member.domain.Member;
import softeerWarmup1.demo.member.dto.LoginDto;
import softeerWarmup1.demo.member.dto.SignupDto;
import softeerWarmup1.demo.member.service.MemberService;

import java.util.Optional;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpSession session){
        Member member = memberService.getMemberWithDto(loginDto);
        if(member == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("login failed");
        }
        session.setAttribute("memberID",member.getId());//이러면 서버에서 내장된 기능으로 클라이언트의 아이디를 저장. 세션을 관리
        return ResponseEntity.ok("successs");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("logout done");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto){
        try{
            memberService.signup(signupDto);
            return ResponseEntity.ok("signup done");
        } catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something else went wrong");
        }
    }


}