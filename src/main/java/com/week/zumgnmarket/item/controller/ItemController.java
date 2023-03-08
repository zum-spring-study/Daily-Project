package com.week.zumgnmarket.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.week.zumgnmarket.item.dto.*;
import com.week.zumgnmarket.item.exception.ItemNotFoundException;
import com.week.zumgnmarket.item.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private final ItemService itemService;

	@PostMapping("/register")
	public ResponseEntity<ItemResponse> registerItem(@RequestBody ItemRequest request) {
		return ResponseEntity.ok(ItemResponse.of(itemService.create(itemService.toItem(request))));
	}

	@PostMapping("/{itemId}")
	public ResponseEntity<ItemResponse> getItem(@PathVariable Long itemId) {
		return ResponseEntity.ok(ItemResponse.of(itemService.findById(itemId)));
	}

	@GetMapping("/search")
	public ResponseEntity<ItemsResponse> searchItems(
		@RequestParam(value = "userId", required = false) Long userId,
		@RequestParam(value = "cityId", required = false) Long cityId,
		@RequestParam(value = "keyword", required = false) String keyword) {
		ItemSearchCondition condition = ItemSearchCondition.of(userId, cityId, keyword);
		return ResponseEntity.ok(ItemsResponse.of(itemService.findItemsByCondition(condition)));
	}

	@DeleteMapping("/delete/{itemId}")
	public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
		itemService.deleteItemById(itemId);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(ItemNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
