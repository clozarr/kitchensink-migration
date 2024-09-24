package com.company.kitchensink.controller;

import com.company.kitchensink.entity.MemberEntity;
import com.company.kitchensink.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new MemberEntity());
        model.addAttribute("members", memberService.getAllMembers());
        return "register";
    }

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute("member") MemberEntity member,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("members", memberService.getAllMembers());
            return "register";
        }
        memberService.saveMember(member);
        return "redirect:/";
    }
}
