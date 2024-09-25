package com.company.kitchensink.service.impl;

import com.company.kitchensink.entity.MemberEntity;
import com.company.kitchensink.repository.MemberRepository;
import com.company.kitchensink.service.MemberService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {


    private final MemberRepository memberRepository;

    @Override
    public List<MemberEntity> getAllMembers() {

        return memberRepository.findAll();
    }

    @Override
    public Optional<MemberEntity> getMemberById(Long id) {
        return  memberRepository.findById(id);

    }

    @Override
    public MemberEntity saveMember(MemberEntity member) throws DataIntegrityViolationException, ConstraintViolationException {
        return memberRepository.save(member);
    }
}
