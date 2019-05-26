package script.theme_park.wod;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.static_item;

public class pro_seed_jar_nsm extends script.base_script
{
    public pro_seed_jar_nsm()
    {
    }
    public static final string_id SID_MNU_USE = new string_id("spam", "open");
    public static final string_id SID_SYS_NOT_IN_INV = new string_id("spam", "cannot_use_not_in_inv");
    public static final String[] TREES = 
    {
        "item_wod_pro_tree_09_schematic",
        "item_wod_pro_tree_10_schematic",
        "item_wod_pro_tree_11_schematic",
        "item_wod_pro_tree_12_schematic"
    };
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!isValidId(self) || !exists(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_MNU_USE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (!isValidId(self) || !exists(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (item != menu_info_types.ITEM_USE)
        {
            return SCRIPT_CONTINUE;
        }
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id inventory = getObjectInSlot(player, "inventory");
        if (!contains(inventory, self))
        {
            sendSystemMessage(player, SID_SYS_NOT_IN_INV);
            return SCRIPT_CONTINUE;
        }
        int treeSelect = rand(0, 3);
        obj_id createdSchematic = static_item.createNewItemFunction(TREES[treeSelect], player);
        if (isIdValid(createdSchematic))
        {
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
