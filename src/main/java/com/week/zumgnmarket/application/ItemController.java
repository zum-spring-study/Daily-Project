package com.week.zumgnmarket.application;

import com.week.zumgnmarket.application.dto.ItemRequest;
import com.week.zumgnmarket.application.dto.ItemResponse;
import com.week.zumgnmarket.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item/save")
    public ItemResponse saveItme(@RequestBody ItemRequest itemRequest) {
        return itemService.saveItem(itemRequest);
    }

}
