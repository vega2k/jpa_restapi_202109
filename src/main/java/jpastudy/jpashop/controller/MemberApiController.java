package jpastudy.jpashop.controller;

import jpastudy.jpashop.domain.Member;
import jpastudy.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public Long saveMember(@RequestBody @Valid Member member) {
        Long memberId = memberService.join(member);
        return memberId;
    }

    /**
     * 등록 V2: 요청 값으로 Member 엔티티 대신에 별도의 DTO를 받는다.
     */
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid
                                                     CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    /**
     * 수정 API
     */
    @PatchMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }
    /**
     * 조회 V1: 응답 값으로 엔티티를 직접 외부에 노출한다.
     */
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    /**
     * 조회 V2: 응답 값으로 엔티티가 아닌 별도의 DTO를 반환한다
     */
    @GetMapping("/api/v2/members")
    public List<MemberDto> membersV2() {
        List<Member> findMembers = memberService.findMembers();
        //엔티티 -> DTO 변환
        List<MemberDto> memberDtoList = findMembers.stream()   //List<Member> -> Stream<Member>
                .map(m -> new MemberDto(m.getName()))   //Stream<Member> -> Stream<MemberDto>
                .collect(Collectors.toList());  //Stream<MemberDto> -> List<MemberDto>
        //return new Result(memberDtoList);
        return memberDtoList;
    }

    @GetMapping("/api/v2.1/members")
    public Result membersV2_1() {
        List<Member> findMembers = memberService.findMembers();
        //엔티티 -> DTO 변환
        List<MemberDto> memberDtoList = findMembers.stream()   //List<Member> -> Stream<Member>
                .map(m -> new MemberDto(m.getName()))   //Stream<Member> -> Stream<MemberDto>
                .collect(Collectors.toList());  //Stream<MemberDto> -> List<MemberDto>
        return new Result(memberDtoList.size(), memberDtoList);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    class MemberDto {
        private String name;
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;
        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }



}
