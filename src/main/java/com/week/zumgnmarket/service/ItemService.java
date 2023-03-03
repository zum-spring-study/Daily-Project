package com.week.zumgnmarket.service;

import com.week.zumgnmarket.application.dto.ItemRequest;
import com.week.zumgnmarket.application.dto.ItemResponse;
import com.week.zumgnmarket.domain.Item;
import com.week.zumgnmarket.domain.User;
import com.week.zumgnmarket.repository.ItemRepository;
import com.week.zumgnmarket.repository.UserQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final UserQueryDslRepository userQueryDslRepository;
    private final ItemRepository itemRepository;

    public ItemResponse saveItem(ItemRequest itemDto) {
        User user = userQueryDslRepository.findById(itemDto.getSellerId());
        itemRepository.save(Item.of(itemDto, user));
        return findByItemName(itemDto.getItemName());
    }

    private ItemResponse findByItemName(String itemName) {
        return ItemResponse.of(itemRepository.findByItemName(itemName));
    }
}
