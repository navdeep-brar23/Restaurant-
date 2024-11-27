package com.restaurant.controller;

import com.restaurant.model.Menu;
import com.restaurant.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Get all menu items
    @GetMapping
    public List<Menu> getAllMenuItems() {
        return menuService.getAllMenuItems();
    }

    // Add a menu item (restricted to ADMIN)
    @PostMapping
    public Menu addMenuItem(@RequestBody Menu menu) {
        return menuService.addMenuItem(menu);
    }

    // Delete a menu item by ID (restricted to ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
        menuService.deleteMenuItem(id);
        return ResponseEntity.ok("Menu item deleted successfully");
    }
}
