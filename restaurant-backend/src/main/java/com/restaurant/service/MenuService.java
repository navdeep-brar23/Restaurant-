package com.restaurant.service;

import com.restaurant.model.Menu;
import com.restaurant.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // Fetch all menu items
    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll();
    }

    // Add a new menu item
    public Menu addMenuItem(Menu menu) {
        // Ensure data integrity checks before saving
        if (menu.getName() == null || menu.getName().isEmpty()) {
            throw new IllegalArgumentException("Menu item name cannot be null or empty.");
        }
        if (menu.getPrice() == null || menu.getPrice() <= 0) {
            throw new IllegalArgumentException("Menu item price must be greater than 0.");
        }
        return menuRepository.save(menu);
    }

    // Delete a menu item by ID
    public void deleteMenuItem(Long id) {
        Optional<Menu> menuItem = menuRepository.findById(id);
        if (menuItem.isPresent()) {
            menuRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Menu item with ID " + id + " does not exist.");
        }
    }

    // Fetch a single menu item by ID
    public Menu getMenuItemById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Menu item with ID " + id + " not found."));
    }

    // Update an existing menu item by ID
    public Menu updateMenuItem(Long id, Menu updatedMenu) {
        Menu existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Menu item with ID " + id + " not found."));

        // Update fields only if they are provided
        if (updatedMenu.getName() != null && !updatedMenu.getName().isEmpty()) {
            existingMenu.setName(updatedMenu.getName());
        }
        if (updatedMenu.getCategory() != null && !updatedMenu.getCategory().isEmpty()) {
            existingMenu.setCategory(updatedMenu.getCategory());
        }
        if (updatedMenu.getPrice() != null && updatedMenu.getPrice() > 0) {
            existingMenu.setPrice(updatedMenu.getPrice());
        }
        if (updatedMenu.getSpicy() != null) {
            existingMenu.setSpicy(updatedMenu.getSpicy());
        }
        if (updatedMenu.getDescription() != null && !updatedMenu.getDescription().isEmpty()) {
            existingMenu.setDescription(updatedMenu.getDescription());
        }
        if (updatedMenu.getRating() != null) {
            existingMenu.setRating(updatedMenu.getRating());
        }
        if (updatedMenu.getImage() != null && !updatedMenu.getImage().isEmpty()) {
            existingMenu.setImage(updatedMenu.getImage());
        }

        return menuRepository.save(existingMenu);
    }
}
