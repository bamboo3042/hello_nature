package com.hellonature.hellonature_back.model.network.request;

import com.hellonature.hellonature_back.model.enumclass.Flag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberOrderApiRequest {
    private Long idx;

    private Integer price;
    private Integer state;
    private Flag dawnFlag;
    private Integer alarm;
    private String zipcode;
    private String address1;
    private String address2;
    private Integer requestType;
    private String requestMemo1;
    private String requestMemo2;
    private Integer paymentType;
    private String num;

    private Long memIdx;
    private Long[] proIdx;
    private Integer[] proCount;
    private Integer[] proPrice;
    private Long revIdx;
    private Long cpIdx;
    private Long mpayIdx;

    private LocalDateTime regdate;
}
