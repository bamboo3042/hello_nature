package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.*;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.enumclass.MemberRole;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.Pagination;
import com.hellonature.hellonature_back.model.network.request.AddressApiRequest;
import com.hellonature.hellonature_back.model.network.request.MemberApiRequest;
import com.hellonature.hellonature_back.model.network.request.MemberCreateRequest;
import com.hellonature.hellonature_back.model.network.response.MemberApiResponse;
import com.hellonature.hellonature_back.model.network.response.MemberMyPageResponse;
import com.hellonature.hellonature_back.repository.AddressRepository;
import com.hellonature.hellonature_back.repository.CardRepository;
import com.hellonature.hellonature_back.repository.CouponRepository;
import com.hellonature.hellonature_back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService extends BaseService<MemberApiRequest, MemberApiResponse, Member> {

    private final MemberRepository memberRepository;
    private final EntityManager em;
    private final CardRepository cardRepository;
    private final CouponRepository couponRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressService addressService;

    @Override
    public Header<MemberApiResponse> create(Header<MemberApiRequest> request) {
        MemberApiRequest memberApiRequest = request.getData();

        Member member = Member.builder()
                .email(memberApiRequest.getEmail())
                .password(passwordEncoder.encode(memberApiRequest.getPassword()))
                .name(memberApiRequest.getName())
                .hp(memberApiRequest.getHp())
                .gender(memberApiRequest.getGender())
                .birth(memberApiRequest.getBirth())
                .smsFlag(memberApiRequest.getSmsFlag())
                .emailFlag(memberApiRequest.getEmailFlag())
                .site(memberApiRequest.getSite())
                .role(MemberRole.MEMBER)
                .build();
        memberRepository.save(member);
        return Header.OK();
    }

    @Override
    public Header<MemberApiResponse> read(Long id) {
        return memberRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<MemberApiResponse> update(Header<MemberApiRequest> request) {
        MemberApiRequest memberApiRequest = request.getData();
        Optional<Member> optional = memberRepository.findById(memberApiRequest.getIdx());
        return optional.map(member -> {
            member.setPassword(passwordEncoder.encode(memberApiRequest.getPassword()));
            member.setName(memberApiRequest.getName());
            member.setHp(memberApiRequest.getHp());
            member.setGender(memberApiRequest.getGender());
            member.setBirth(memberApiRequest.getBirth());
            member.setGreenFlag(memberApiRequest.getGreenFlag());
            member.setSmsFlag(memberApiRequest.getSmsFlag());
            member.setEmailFlag(memberApiRequest.getEmailFlag());

            return member;
        }).map(memberRepository::save)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("수정 실패"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Member> optional = memberRepository.findById(id);
        return optional.map(member -> {
            memberRepository.delete(member);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("삭제 실패"));
    }

    private MemberApiResponse response(Member member){
        return MemberApiResponse.builder()
                .idx(member.getIdx())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .hp(member.getHp())
                .gender(member.getGender())
                .birth(member.getBirth())
                .smsFlag(member.getSmsFlag())
                .emailFlag(member.getEmailFlag())
                .site(member.getSite())
                .hellocash(member.getHellocash())
                .mhpIdx(member.getMemberHellopass() == null ? null : member.getMemberHellopass().getIdx())
                .greenFlag(member.getGreenFlag())
                .regdate(member.getRegdate())
                .build();
    }

    public Header<List<MemberApiResponse>> list(String dateStart, String dateEnd, String email, String name, String hp, Integer startPage){
        String jpql = "select m from Member m";
        boolean check = false;

        if (dateStart != null || dateEnd != null || email != null || name != null || hp != null){
            jpql += " where";
            if (dateStart != null){
                jpql += " TO_char(regdate, 'YYYY-MM-DD') >= :dateStart";
                check = true;
            }
            if (dateEnd != null){
                if (check) jpql += " and";
                jpql += " TO_char(regdate, 'YYYY-MM-DD') <= :dateEnd";
                check = true;
            }
            if (email != null){
                if (check) jpql += " and";
                jpql += " email like :email";
            }else if (name != null){
                if (check) jpql += " and";
                jpql += " name like :name";
            }else if (hp != null){
                if (check) jpql += " and";
                jpql += " hp like :hp";
            }
        }

        jpql += " order by idx desc";

        TypedQuery<Member> query = em.createQuery(jpql, Member.class);
        if(dateStart != null) query = query.setParameter("dateStart", dateStart);
        if(dateEnd != null) query = query.setParameter("dateEnd", dateEnd);
        if(name != null) query = query.setParameter("name", "%" + name + "%");
        if(email != null) query = query.setParameter("email", "%" + email + "%");
        if(hp != null) query = query.setParameter("hp", "%" + hp + "%");

        List<Member> result = query.getResultList();
        int count = 10;
        int start = count * startPage;
        int end = Math.min(result.size(), start + count);

        Pagination pagination = new Pagination().builder()
                .totalPages(result.size() % count == 0 ? result.size() / count : result.size() / count + 1)
                .totalElements((long) result.size())
                .currentPage(startPage)
                .currentElements(end - start)
                .build();

        List<MemberApiResponse> newList = new ArrayList<>();

        for (Member member: result.subList(start, end)) {
            newList.add(response(member));
        }

        return Header.OK(newList, pagination);
    }


    public Header<MemberMyPageResponse> myPage(Long memIdx){
        return memberRepository.findById(memIdx)
                .map(this::myPageResponse)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("회원이 없습니다."));
    }

    private MemberMyPageResponse myPageResponse(Member member){
        List<Card> cards = cardRepository.findAllByMember(member);
        List<Coupon> coupons = couponRepository.findAllByMemberOrderByIdxDesc(member);
        return MemberMyPageResponse.builder()
                .idx(member.getIdx())
                .name(member.getName())
                .hellocash(member.getHellocash())
                .couponCount(coupons.size())
                .hellopassFlag(cards.isEmpty() ? Flag.FALSE : Flag.TRUE)
                .hellopassFlag(member.getMemberHellopass() != null ? Flag.TRUE : Flag.FALSE)
                .build();
    }

    @Transactional
    public Header<MemberApiResponse> signUp(Header<MemberCreateRequest> request){

        MemberCreateRequest memberCreateRequest = request.getData();

        Member member = Member.builder()
                .name(memberCreateRequest.getName())
                .email(memberCreateRequest.getEmail())
                .password(passwordEncoder.encode(memberCreateRequest.getPassword()))
                .hp(memberCreateRequest.getHp())
                .birth(memberCreateRequest.getBirth())
                .gender(memberCreateRequest.getGender())
                .smsFlag(memberCreateRequest.getSmsFlag())
                .emailFlag(memberCreateRequest.getEmailFlag())
                .site(memberCreateRequest.getSite())
                .role(MemberRole.MEMBER)
                .build();

        Member newMember = memberRepository.save(member);

        AddressApiRequest address = AddressApiRequest.builder()
                .memIdx(newMember.getIdx())
                .name(memberCreateRequest.getName())
                .hp(memberCreateRequest.getHp())
                .zipcode(memberCreateRequest.getZipcode())
                .addr1(memberCreateRequest.getAddr1())
                .addr2(memberCreateRequest.getAddr2())
                .requestType(memberCreateRequest.getRequestType())
                .requestMemo1(memberCreateRequest.getRequestMemo1())
                .requestMemo2(memberCreateRequest.getRequestMemo2())
                .addrName("기본 배송지")
                .baseFlag(Flag.TRUE)
                .build();

        addressService.create(Header.OK(address));

        return Header.OK();
    }

    public Header<Flag> emailCheck(String email){
        return memberRepository.findByEmail(email)
                .map(member -> Header.OK(Flag.TRUE))
                .orElseGet(
                        () -> Header.OK(Flag.FALSE)
                );
    }

    public Header<Flag> passwordCheck(Long idx, String password){
        Optional<Member> member = memberRepository.findById(idx);
        return passwordEncoder.matches(password, member.get().getPassword()) ? Header.OK(Flag.TRUE) : Header.OK(Flag.FALSE);
    }

    public boolean memberEmailcheck(String userEmail){
        Optional<Member> member = memberRepository.findByEmail(userEmail);
        System.out.println(member);
        if(member.isPresent()) {
            return true;
        }
        else {
            return false;
        }
    }

    public Header editPassword(String email, String password){
        Optional<Member> optional = memberRepository.findByEmail(email);
        if (optional.isEmpty()) return Header.ERROR("회원 정보가 잘못되었습니다");
        Member member = optional.get();
        member.setPassword(passwordEncoder.encode(password));
        memberRepository.save(member);
        return Header.OK();
    }
}
