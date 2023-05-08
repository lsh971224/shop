package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart { //멤버랑 다대일 개념 왜냐하면 한사람에 장바구니에는 여러가지 상품들이 들어있다.
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO) //값 자동 증가
    private Long id;

    @OneToOne(fetch = FetchType.EAGER) //EAGER : 즉싱로딩 -> 반대 LAZY
    @JoinColumn(name = "member_id") // DB상에 외래키 같은거임
    private Member member;


}
