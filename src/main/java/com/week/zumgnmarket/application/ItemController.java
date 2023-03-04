package com.week.zumgnmarket.application;

import com.week.zumgnmarket.application.dto.ItemRequest;
import com.week.zumgnmarket.application.dto.ItemResponse;
import com.week.zumgnmarket.constant.TownType;
import com.week.zumgnmarket.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    /**
     * 상품 등록 API
     * @param itemRequest
     * @return
     */
    @PostMapping("/item/save")
    public ItemResponse saveItem(@RequestBody ItemRequest itemRequest) {
        return itemService.saveItem(itemRequest);
    }

    /**
     * 자신의 지역에 해당하는 상품 리스트 조회 API
     * @param town
     * @return
     */
    @GetMapping("/items")
    public List<ItemResponse> findItemsByRegion(@RequestParam TownType town) {
        return itemService.findAllByRegion(town.getRegion());
    }

    /**
     * 상품 구매 API
     * @param userId
     * @param itemId
     * @return
     */
    @GetMapping("/item/buy")
    public ItemResponse buyItem(Integer userId, Integer itemId) {
        return itemService.buyItem(userId, itemId);
    }
}
