package com.company.kitchensink.controller;


import com.company.kitchensink.controller.dto.MemberDto;
import com.company.kitchensink.entity.MemberEntity;
import com.company.kitchensink.service.MemberService;
import com.company.kitchensink.service.impl.MemberServiceImpl;
import com.company.kitchensink.utils.Dataset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiMemberControllerTest {

    @InjectMocks
    private  ApiMemberController apiMemberController;

    @Mock
    private MemberService memberService;
    @Mock
    private ModelMapper modelMapper;






    @BeforeEach
    public void setup(){

        memberService = mock(MemberServiceImpl.class);
        modelMapper = new ModelMapper();
        apiMemberController = new ApiMemberController(memberService, modelMapper);

    }

    @Test
    void get_all_members_successfully(){

        List<MemberEntity> memberEntityList = Dataset.getMembersEntityList();
        when(memberService.getAllMembers()).thenReturn(memberEntityList);

       ResponseEntity<List<MemberDto>> responseEntity =  apiMemberController.getAllmembers();

       assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<MemberDto> responseList = responseEntity.getBody();

        assertTrue(Objects.nonNull(responseList));
        assertFalse(responseList.isEmpty());

        assertEquals(1L, responseList.getFirst().getId());
        assertEquals( "Test name 1", responseList.getFirst().getName());
        assertEquals("email1@test.com", responseList.getFirst().getEmail());
        assertEquals( "3024456874", responseList.getFirst().getPhoneNumber());

        assertEquals(2L, responseList.getLast().getId());
        assertEquals( "Test name 2", responseList.getLast().getName());
        assertEquals( "email2@test.com", responseList.getLast().getEmail());
        assertEquals("343668822", responseList.getLast().getPhoneNumber());

        verify(memberService, times(1)).getAllMembers();


    }

    @Test
    void get_member_by_id() {

        Long memberId = 1L;
        Optional<MemberEntity> optionalMemberEntity = Optional.of(Dataset.getMemberEntity());
        when(memberService.getMemberById(anyLong())).thenReturn(optionalMemberEntity);

        ResponseEntity<?> responseEntity =  apiMemberController.getMemberById(memberId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        MemberDto response = (MemberDto) responseEntity.getBody();
        assertTrue(Objects.nonNull(response));

        assertEquals( 1L, response.getId());
        assertEquals( "Test name 1", response.getName());
        assertEquals( "email1@test.com", response.getEmail());
        assertEquals( "3024456874", response.getPhoneNumber());

        verify(memberService, times(1)).getMemberById(anyLong());


    }

    @Test
    void get_member_by_id_fail_when_id_not_exist() {

        Long invalidId = 10L;
        when(memberService.getMemberById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = apiMemberController.getMemberById(invalidId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        String errorMessage = (String) responseEntity.getBody();
        assertEquals("Member not found!", errorMessage);

        verify(memberService, times(1)).getMemberById(anyLong());

    }

}