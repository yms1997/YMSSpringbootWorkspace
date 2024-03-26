package kr.study.jpa1.controller;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.form.MemberForm;
import kr.study.jpa1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String joinForm(){
        return "member/joinForm";
    }
    @PostMapping
    public String joinMember(@ModelAttribute MemberForm form ){
        //System.out.println("memberForm = " + memberForm);
        log.trace("memberForm ={}" , form);
        Member member = Member.builder()
                .password(form.getPw())
                .loginId(form.getId())
                .name(form.getName())
                .build();

        try {
            memberService.join(member);
            log.trace("member ={}" , member);
        }catch(Exception e){
            log.error("errMSG={}", e.getMessage());
            // 에러페이지 이동
        }

        return "redirect:/";
    }


    @GetMapping("/members")
    public String members(Model model){
        List<Member> list = memberService.getList();
        if(list == null){
            return "redirect:/member"; // 회원가입부터 해라
        }
        model.addAttribute("list" , list);

        return "member/list";
    }

    @GetMapping("/{keyId}")
    public String deleteMember(@PathVariable Long keyId){
        log.trace("keyid={}" , keyId);
        memberService.deleteMember(keyId);
        return "redirect:/member/members";
    }

    @DeleteMapping("/{keyId}")
    public @ResponseBody String deleteMemberAjax(@PathVariable Long keyId){
        log.trace("keyid={}" , keyId);
        memberService.deleteMember(keyId);
        return "ok";
    }


}
