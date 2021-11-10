package menusystem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 The logic of the gui, it is recommended not to modify this class to avoid errors.
 */
public class ClickListener implements Listener {

    @EventHandler
    private void onInventoryClickEvent( InventoryClickEvent event ) {
        if ( event.getRawSlot() < 0 ) {
            return;
        }
        Inventory inventory = event.getView().getTopInventory();
        InventoryHolder holder = inventory.getHolder();
        if ( holder instanceof PlayerHolder) {
            ( (PlayerHolder) holder ).onInventoryClick( event );
        }

    }

    @EventHandler
    private void onInventoryDragEvent( InventoryDragEvent event ) {
        Inventory inventory = event.getInventory();
        InventoryHolder holder = inventory.getHolder();
        if ( holder instanceof PlayerHolder) {
            for ( int slot : event.getRawSlots() ) {
                if ( event.getInventorySlots().contains( slot ) ) {
                    event.setCancelled( true );
                }
            }
            ( (PlayerHolder) holder ).onInventoryDrag( event );
        }
    }

    @EventHandler
    private void onInventoryOpenEvent( InventoryOpenEvent event ) {
        Inventory inventory = event.getInventory();
        InventoryHolder holder = inventory.getHolder();
        if ( holder instanceof PlayerHolder) {
            ( (PlayerHolder) holder ).onInventoryOpen( event );
        }
    }

    @EventHandler
    private void onInventoryCloseEvent( InventoryCloseEvent event ) {
        Inventory inventory = event.getInventory();
        InventoryHolder holder = inventory.getHolder();
        if ( holder instanceof PlayerHolder) {
            ( (PlayerHolder) holder ).onInventoryClose( event );
        }
    }
}
