package com.arimil.cryochamber;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

import com.arimil.blocks.BlockCryochamber;
import com.arimil.cryochamber.lib.References;
import com.arimil.items.ItemCryochamber;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod( modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION )
@NetworkMod( clientSideRequired = true, serverSideRequired = true)
public class Cryochamber {
    
    //Block ID
    public static int cryochamberBlockId;
    
    //Item ID
    public static int cryochamberItemId;
    
    //Blocks
    public static Block BlockCryochamber;
    
    //Items
    public static Item ItemCryochamber;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event){
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        //Set ID Registration
        cryochamberBlockId = config.getBlock(Configuration.CATEGORY_BLOCK, "cryochamber", 3000).getInt();
        cryochamberItemId = config.getItem(Configuration.CATEGORY_ITEM, "cryochamber", 6000).getInt();
        
        
        References.blindness_duration = config.get("Debuff Durations", "blindness", References.blindness_duration).getInt();
        References.fatigue_duration = config.get("Debuff Durations", "fatigue", References.fatigue_duration).getInt();
        References.hunger_duration = config.get("Debuff Durations", "hunger", References.hunger_duration).getInt();
        References.nausea_duration = config.get("Debuff Durations", "nausea", References.nausea_duration).getInt();
        References.weakness_druation = config.get("Debuff Durations", "weakness", References.weakness_druation).getInt();
        References.slowness_duration = config.get("Debuff Durations", "slowness", References.slowness_duration).getInt();


        config.save();
        
    }
    @Init
    public void init(FMLInitializationEvent event){
        //Initialize Blocks
        BlockCryochamber = new BlockCryochamber(cryochamberBlockId, Material.iron);
        
        //Initialize Items
        ItemCryochamber = new ItemCryochamber(cryochamberItemId);
        
        //Register Blocks
        GameRegistry.registerBlock(BlockCryochamber, "cryochamber");
        
        //Recipe items
        ItemStack obsidian = new ItemStack(Block.obsidian);
        ItemStack glass = new ItemStack(Block.glass);
        ItemStack diamond = new ItemStack(Item.diamond);
        ItemStack bed = new ItemStack(Item.bed);
        ItemStack redstone = new ItemStack(Item.redstone);
        
        //Add Recipe
        GameRegistry.addRecipe(new ItemStack(ItemCryochamber), "ogo", "dbd", "oro",
                'o', obsidian, 'g', glass, 'd', diamond, 'b', bed, 'r', redstone);
        
        
        //item names
        LanguageRegistry.addName(BlockCryochamber, "Cryochamber Block");
        LanguageRegistry.addName(ItemCryochamber, "Cryochamber");
        
        //item harvest level
        MinecraftForge.setBlockHarvestLevel(BlockCryochamber, "pickaxe", 0);
        
    }
    @PostInit
    public void postInit(FMLPostInitializationEvent event){
        
    }
}