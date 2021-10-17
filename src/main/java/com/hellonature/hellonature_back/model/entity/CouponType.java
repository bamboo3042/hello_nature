package com.hellonature.hellonature_back.model.entity;

import com.hellonature.hellonature_back.model.enumclass.Flag;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name = "coupons_types_seq",
        sequenceName = "coupons_types_seq",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@DynamicInsert
@DynamicUpdate
@Table(name="tb_coupons_types")
@EqualsAndHashCode(callSuper=false)
public class CouponType extends DateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupons_types_seq")
    private Long idx;

    private String title;
    @Column(name = "ct_target")
    private String target;
    @Column(name = "ct_auto")
    private Flag auto;

    @Column(name = "ct_count")
    private Integer count;
    private Integer discount;
    private Integer minPrice;
    private String dateStart;
    private String dateEnd;
    private Integer type1;
    private String type2;

    public int plusCount(){
        count += 1;
        return count;
    }

    public int minusCount(){
        count -= 1;
        return count;
    }
}