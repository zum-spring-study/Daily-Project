package com.week.zumgnmarket.service;

import com.week.zumgnmarket.application.dto.ItemRequest;
import com.week.zumgnmarket.application.dto.ItemResponse;
import com.week.zumgnmarket.domain.Item;
import com.week.zumgnmarket.domain.Shop;
import com.week.zumgnmarket.domain.User;
import com.week.zumgnmarket.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final UserQueryDslRepository userQueryDslRepository;
    private final ItemRepository itemRepository;
    private final ItemQueryDslRepository itemQueryDslRepository;

    private final ShopQueryDslRepository shopQueryDslRepository;
    private final ShopJpaRepository shopJpaRepository;

    private static boolean ITEM_TRUE_STATUS = true;


    public ItemResponse saveItem(ItemRequest itemDto) {
        User user = userQueryDslRepository.findById(itemDto.getSellerId());
        Item item = Item.of(itemDto, user);
        itemRepository.save(item);
        return ItemResponse.of(item);
    }

    public List<ItemResponse> findAllByRegion(String region) {
        /**
         * Fetch Join 을 하지 않았을 경우 N+1 발생
         * -> N => 상품을 등록한 사람수만큼 !
         */
//        Town town = townRepository.findByRegion(region);
//        return itemRepository.findAllByTown(town).stream()
//                .map(ItemResponse::of).collect(Collectors.toList());

        return itemQueryDslRepository.findAllByRegion(region).stream()
                .map(ItemResponse::of).collect(Collectors.toList());
    }

    public ItemResponse buyItem(Integer userId, Integer itemId) {
        Item item = itemQueryDslRepository.findById(itemId);
        if (item.isTradeStatus()) {
            throw new NoSuchElementException("이미 거래가 완료된 상품입니다.");
        }
        List<ItemResponse> shoppingList = findShoppingList(userId);
        if(shoppingList.stream().map(ItemResponse::getTradingDate)
                .collect(Collectors.toList()).contains(item.getTradingDate())) {
            throw new KeyAlreadyExistsException("해당 시간에 이미 거래가 예정되어 있습니다.");
        }
        item.updateStatus(ITEM_TRUE_STATUS);
        User user = userQueryDslRepository.findById(userId);
        Shop shop = Shop.of(user, item);
        shopJpaRepository.save(shop);
        return ItemResponse.of(shop);
    }

    public List<ItemResponse> findShoppingList(Integer userId) {
        return shopQueryDslRepository.findAllByBuyerId(userId).stream()
                .map(ItemResponse::of).collect(Collectors.toList());
    }

}
