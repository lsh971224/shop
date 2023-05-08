package com.shop.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "cart_item") //cart_item 테이블을 만든다
@Getter
@Setter
public class CartItem { //장바구니 상품
    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id") // 카트_id와 조인을함
    private Cart cart; //장바구니 

    @ManyToOne
    @JoinColumn(name="item_id")  // item_id와 조인
    private Item item; //상품

    private int count; //몇개를 담을건지
    

}
