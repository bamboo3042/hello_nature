package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.Card;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.CardApiRequest;
import com.hellonature.hellonature_back.model.network.response.CardApiResponse;
import com.hellonature.hellonature_back.repository.CardRepository;
import com.hellonature.hellonature_back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService extends BaseService<CardApiRequest, CardApiResponse, Card>{

    private final CardRepository cardRepository;
    private final MemberRepository memberRepository;

    @Override
    public Header<CardApiResponse> create(Header<CardApiRequest> request) {

        CardApiRequest cardApiRequest = request.getData();

        Card card = Card.builder()
                .member(memberRepository.findById(cardApiRequest.getMemIdx()).get())
                .num(cardApiRequest.getNum())
                .date(cardApiRequest.getDate())
                .cvc(cardApiRequest.getCvc())
                .password(cardApiRequest.getPassword())
                .name(cardApiRequest.getName())
                .bank(cardApiRequest.getBank())
                .busiFlag(cardApiRequest.getBusiFlag())
                .birth(cardApiRequest.getBirth())
                .build();
        cardRepository.save(card);

        return Header.OK();
    }

    @Override
    public Header<CardApiResponse> read(Long id) {
        return cardRepository.findById(id)
                .map(card -> response(card))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<CardApiResponse> update(Header<CardApiRequest> request) {
        CardApiRequest cardApiRequest = request.getData();
        Optional<Card> optional = cardRepository.findById(cardApiRequest.getIdx());
        return optional.map(card -> {
            card.setNum(cardApiRequest.getNum());
            card.setDate(cardApiRequest.getDate());
            card.setCvc(cardApiRequest.getCvc());
            card.setPassword(cardApiRequest.getPassword());
            card.setName(cardApiRequest.getName());
            card.setBank(cardApiRequest.getBank());
            card.setBusiFlag(cardApiRequest.getBusiFlag());
            card.setBirth(cardApiRequest.getBirth());
            card.setBaseFlag(cardApiRequest.getBaseFlag());
            card.setFavFlag(cardApiRequest.getFavFlag());
            return card;
        }).map(card -> cardRepository.save(card))
                .map(card -> response(card))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("수정 실패"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Card> optional = cardRepository.findById(id);
        return optional.map(card -> {
            cardRepository.delete(card);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("삭제 실패"));
    }

    public CardApiResponse response(Card card){
        CardApiResponse cardApiResponse = CardApiResponse.builder()
                .idx(card.getIdx())
                .memIdx(card.getMember().getIdx())
                .num(card.getNum())
                .date(card.getDate())
                .cvc(card.getCvc())
                .bank(card.getBank())
                .busiFlag(card.getBusiFlag())
                .birth(card.getBirth())
                .baseFlag(card.getBaseFlag())
                .favFlag(card.getFavFlag())
                .regdate(card.getRegdate())
                .build();

        return cardApiResponse;
    }

    public Header<List<CardApiResponse>> list(Long memIdx){
        List<Card> cardList = cardRepository.findAllByMemberOrderByBaseFlag(memberRepository.findById(memIdx).get());
        List<CardApiResponse> list = new ArrayList<>();
        for (Card card: cardList) {
            list.add(response(card));
        }
        return Header.OK(list);
    }

    public Header flagController(Long idx, Integer type, Flag flag){
        Card card = cardRepository.getById(idx);

        switch (type){
            case 1:
                if (flag == Flag.TRUE){
                    Optional<Card> optional = cardRepository.findByMemberAndBaseFlag(card.getMember(), Flag.TRUE);
                    optional.get().setBaseFlag(Flag.TRUE);
                }
                card.setBaseFlag(flag);
                break;
            case 2:
                card.setFavFlag(flag);
                break;
            case 3:
                card.setBusiFlag(flag);
                break;
        }
        cardRepository.save(card);

        return Header.OK();
    }
}
