package softeerWarmup1.demo.member.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import softeerWarmup1.demo.member.domain.Member;
import softeerWarmup1.demo.member.dto.CarDto;
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
        // 이미 로그인 되어 있는지 체크
        if(session.getAttribute("memberId")!=null)
            return ResponseEntity.badRequest().body("already logged in");

        // 매칭되는 회원이 없는 경우
        Member member = memberService.getMemberWithDto(loginDto);
        if(member == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("login failed");
        }

        // 로그인 성공
        session.setAttribute("memberID",member.getId());//이러면 서버에서 내장된 기능으로 클라이언트의 아이디를 저장. 세션을 관리
        return ResponseEntity.ok("login successs");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        //이미 로그아웃 되었는지 체크
        if(session.getAttribute("memberId") == null)
            return ResponseEntity.badRequest().body("already logged out");

        //성공
        session.invalidate();
        return ResponseEntity.ok("logout done");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto){
        try{
            memberService.signup(signupDto); //회원가입을 일단 시도
            return ResponseEntity.ok("signup done");
        } catch (IllegalStateException e){ // 닉네임이 중복된 경우
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){// 그 밖의 에러
            return ResponseEntity.internalServerError().body("something else went wrong");
        }
    }

    @DeleteMapping("/user/withdrawal")
    public ResponseEntity<?> withdrawal(HttpSession session){

        // 세션에서 멤버키 존재 확인 후 멤버키 기록
        Long memberId = (Long) session.getAttribute("memberId");
        if(memberId == null){
            return ResponseEntity.badRequest().body("not logged in");
        }

        // DB에서 멤버 삭제 후 로그아웃
        try{
            memberService.deleteMember(memberId);
            session.invalidate();
            return ResponseEntity.ok().body("withdrawal done");
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("something else went wrong");
        }

    }

    @PatchMapping("/user/car/add")
    public ResponseEntity<?> registerCar(CarDto carDto, HttpSession session){
        //로그인 확인
        Long memberId = (Long)session.getAttribute("memberId");
        if(memberId == null){
            return ResponseEntity.badRequest().body("not logged in");
        }

        // 맴버 아이디 기준으로 수정실행
        try {
            memberService.registerCar(memberId, carDto);
            return ResponseEntity.ok().body("car registered");
        } catch(ResourceAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("something else went wrong");
        }
    }

    @PatchMapping("/user/car/delete")
    public ResponseEntity<?>deleteCar(HttpSession session){
        //로그인 확인 + 멤버키 찾기
        Long memberId = (Long)session.getAttribute("memberId");
        if(memberId == null){
            return ResponseEntity.badRequest().body("not logged in");
        }

        CarDto carDto = new CarDto();
        try {
            memberService.registerCar(memberId, carDto);
            return ResponseEntity.ok().body("car deleted");
        } catch(ResourceAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("something else went wrong");
        }
    }


}