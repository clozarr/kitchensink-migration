package com.company.kitchensink.controller;


import com.company.kitchensink.controller.dto.MemberDto;
import com.company.kitchensink.entity.MemberEntity;
import com.company.kitchensink.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class ApiMemberController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;


    @GetMapping(path = "/members")
   public ResponseEntity<List<MemberDto>> getAllmembers(){

       List<MemberEntity> memberEntityList = memberService.getAllMembers();

        List<MemberDto> memberDtoList = memberEntityList.stream().
                map(m -> modelMapper.map(m, MemberDto.class)).collect(Collectors.toList());


       return new ResponseEntity<>(memberDtoList, HttpStatus.OK);

   }

   @GetMapping(path = "/members/{id}")
   public ResponseEntity<?> getMemberById(@PathVariable("id") Long id){

        Optional<MemberEntity> memberEntityOptional = memberService.getMemberById(id);

        if (memberEntityOptional.isPresent()){

            MemberDto memberDto = modelMapper.map(memberEntityOptional.get(), MemberDto.class);
            return new ResponseEntity<>(memberDto, HttpStatus.OK);
        }

        return new ResponseEntity<>("Member not found!", HttpStatus.NOT_FOUND);

   }


}
