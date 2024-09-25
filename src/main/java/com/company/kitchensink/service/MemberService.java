package com.company.kitchensink.service;

import com.company.kitchensink.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberService {

     List<MemberEntity> getAllMembers();
     Optional<MemberEntity> getMemberById(Long id);
     MemberEntity saveMember(MemberEntity member);

}
