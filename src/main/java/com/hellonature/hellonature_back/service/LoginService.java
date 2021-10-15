package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.Admin;
import com.hellonature.hellonature_back.model.entity.Member;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.response.LoginApiResponse;
import com.hellonature.hellonature_back.repository.AdminRepository;
import com.hellonature.hellonature_back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public Header<LoginApiResponse> userLogin(String id, String password){
        return memberRepository.findByEmailAndPassword(id, passwordEncoder.encode(password))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("로그인 실패"));
    }

    public Header<LoginApiResponse> adminLogin(String id, String password){
        return adminRepository.findByIdAndPassword(id, passwordEncoder.encode(password))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("로그인 실패"));
    }

    public LoginApiResponse response(Member member){
        return LoginApiResponse.builder()
                .idx(member.getIdx())
                .name(member.getName())
                .build();
    }

    public LoginApiResponse response(Admin admin){
        return LoginApiResponse.builder()
                .idx(admin.getIdx())
                .name(admin.getName())
                .build();
    }
}
