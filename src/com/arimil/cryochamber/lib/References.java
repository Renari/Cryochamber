package com.arimil.cryochamber.lib;

import ic2.api.item.Items;

public class References {
    //Debug Mode
    public static final boolean DEBUG_MODE = false;
    
    //General mod related constants
    public static final String MOD_ID = "Cryochamber";
    public static final String MOD_NAME = "Cryochamber";
    public static final String VERSION = "0.1";
    public static final String DEPENDENCIES = "required-after:IC2";
    
    public static int nausea_duration = 40;
    public static int blindness_duration = 25;
    public static int slowness_duration = 10;
    public static int fatigue_duration = 20;
    public static int hunger_duration = 120;
    public static int weakness_druation = 30;
    
    public static int power_usage = 100000;
    
    public static Integer[] valid_power_sources = {Items.getItem("energyCrystal").itemID, Items.getItem("lapotronCrystal").itemID};
    
    
}
