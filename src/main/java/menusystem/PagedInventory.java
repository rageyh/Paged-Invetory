package menusystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PagedInventory extends PlayerHolder {

    /*

     */

    // item to click to go to the nextPage
    // item to click to go to the previous page
    // item decoration gui (fill empty slots)
    ItemStack item = new ItemStack(Material.AIR);
    private final ItemStack nextPage = item ;
    private final ItemStack previousPage = item;
    private final ItemStack decoration = item;

    //private final ItemStack contatto = new ItemStack(Material.PAPER);

    protected Inventory inventory;
    protected int page = 0;
    protected List<ItemStack> items;


    /**
     * @param itemsToDisplay List of itemStack to add in GUI
     */
    public PagedInventory(List<ItemStack> itemsToDisplay) {
        // Create inventary
        inventory = Bukkit.createInventory(this, 27, "name");
        this.items = itemsToDisplay;
    }


    /**
     * @return New inventory with pages
     */
    @Override
    public Inventory getInventory() {
        // Delete the GUI and recreate a new one
        inventory.clear();

        // Returns the number of pages to use in the GUI
        int maxPages = (int) Math.ceil(items.size() / (double) (inventory.getSize() - 18));

        // Returns the current page number
        page = Math.min(maxPages, page);

        /*
        Add the previousPage and nextPage to the inventory

        If you change the arrangement of the items
        you will have to change the rest of the code as well
        it's simple!
         */
        if (page > 0) inventory.setItem(inventory.getSize() - 9, previousPage);
        if (page < maxPages - 1) inventory.setItem(inventory.getSize() - 1, nextPage);


        // Calculate how many items there will be on a page
        int endIndex = page * (inventory.getSize() - 18);

        // Set the items for the decoration of the gui
        for (int i = 0; i < 9; i++) inventory.setItem(i, decoration);
        for (int j = inventory.getSize() - 8; j < inventory.getSize() - 1; j++) inventory.setItem(j, decoration);

        // Set the items for each page
        //
        for (int startIndex = 9; startIndex < 18; startIndex++) {

            // Check if the index exists in the list of Items
            if ((startIndex + endIndex) - 9 >= items.size()) break;

            // Set items in the appropriate slot
            inventory.setItem(startIndex, items.get((endIndex + startIndex) - 9));
        }
        return inventory;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        ClickType click = event.getClick();
        Player p = (Player) event.getWhoClicked();
        // Check if the click happened in our inventory
        // If not, or if the click is a Shift / keyboard key click, cancel and return
        if (event.getSlot() != event.getRawSlot()) {
            if (click.isKeyboardClick() || click.isShiftClick()) {
                event.setCancelled(true);
            }
            return;
        }
        event.setCancelled(true);

        // item currently clicked
        ItemStack item = event.getCurrentItem();
        if (item == null) return;
        // if clicked item is nextPageo or previuousPage, then change the page
        if (nextPage.isSimilar(item)) page++;
        if (previousPage.isSimilar(item)) page--;

        // Make your own logic depending on what element they clicked on.
        // You should have your own way to validate if it's a valid article






        // Update inventory
        // Since it's still open for the player, it's not necessary
        // reopen it, just update its content.
        getInventory();
    }

}
