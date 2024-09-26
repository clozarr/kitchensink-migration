package com.company.kitchensink.utils;

import com.company.kitchensink.entity.MemberEntity;

import java.util.List;

public class Dataset {


    public static List<MemberEntity> getMembersEntityList(){

      return List.of(
                MemberEntity.builder()
                        .id(1L)
                        .name("Test name 1")
                        .email("email1@test.com")
                        .phoneNumber("3024456874")
                        .build(),
                MemberEntity.builder()
                        .id(2L)
                        .name("Test name 2")
                        .email("email2@test.com")
                        .phoneNumber("343668822")
                        .build()
        );
    }

    public static MemberEntity getMemberEntity(){

        return MemberEntity.builder()
                .id(1L)
                .name("Test name 1")
                .email("email1@test.com")
                .phoneNumber("3024456874")
                .build();
    }


}
