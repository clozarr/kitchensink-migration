package com.company.kitchensink.service.impl;

import com.company.kitchensink.entity.MemberEntity;
import com.company.kitchensink.repository.MemberRepository;
import com.company.kitchensink.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {


    private final MemberRepository memberRepository;

    @Override
    public List<MemberEntity> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public MemberEntity saveMember(MemberEntity member) {
        return memberRepository.save(member);
    }
}
