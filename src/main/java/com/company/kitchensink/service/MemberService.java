package com.company.kitchensink.service;

import com.company.kitchensink.entity.MemberEntity;

import java.util.List;

public interface MemberService {

    public List<MemberEntity> getAllMembers();
    MemberEntity saveMember(MemberEntity member);

}
