package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDto {
    private Long id;
    private String itemNm;
    private int price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDateTime regTime; // 등록 시간
    private LocalDateTime updateTime; // 수정 시간
}
