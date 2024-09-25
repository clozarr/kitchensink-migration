package com.company.kitchensink.controller;

import com.company.kitchensink.entity.MemberEntity;
import com.company.kitchensink.service.MemberService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
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
        try {
            memberService.saveMember(member);

        } catch (DataIntegrityViolationException | ConstraintViolationException e) {

            log.info("DataIntegrityViolationException | ConstraintViolationException: {}", e.getMessage());
            model.addAttribute("databaseError", "Email already exist!");
            return "register";
        }
        return "redirect:/";
    }
}
