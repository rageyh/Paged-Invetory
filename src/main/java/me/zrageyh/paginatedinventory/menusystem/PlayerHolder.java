package me.zrageyh.paginatedinventory.menusystem;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public abstract class PlayerHolder implements InventoryHolder {

    /*
    These methods can be used in the "PagedInventory" class at your convenience
     */


    public abstract void onInventoryClick( InventoryClickEvent event );

    public void onInventoryDrag( InventoryDragEvent event ) {
    }

    public void onInventoryOpen( InventoryOpenEvent event ) {
    }

    public void onInventoryClose( InventoryCloseEvent event ) {
    }
}
