package me.zrageyh.paginatedinventory.menusystem;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public class OpenGUI {

    /*
    To open a GUI to a player or simply create it will be necessary to do
     */

    /*
    This method is for example only
     */
    public void openGui(Player p, List<ItemStack> listItemstack){
        p.openInventory( new PagedInventory(listItemstack).getInventory());
    }
}
