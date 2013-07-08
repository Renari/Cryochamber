package com.arimil.blocks;

import java.util.Random;

import com.arimil.cryochamber.Cryochamber;
import com.arimil.cryochamber.lib.References;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ic2.api.item.Items;;

public class BlockCryochamber extends Block {
    
    private Icon frontTopIcon;
    private Icon frontBottomIcon;
    private Icon sideIcon;
    
    public BlockCryochamber(int par1, Material par2Material) {
        super(par1, par2Material);
        setUnlocalizedName("cryochamber");
        setHardness(5F);
        setResistance(10F);
    }
    public void onBlockHarvested(World par1World, int x, int y, int z, int metadata, EntityPlayer player) {
        System.out.println(metadata);
        if (metadata != 0){
            //remove the blocks
            if ((metadata & 8) != 0){
                par1World.setBlockToAir(x, y-1, z);
            }
            else
            {
                par1World.setBlockToAir(x, y+1, z);
            }
        }
    }
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata){
        return false;
    }
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Cryochamber.ItemCryochamber.itemID;
    }
    @Override
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        return getIcon(side, par1IBlockAccess.getBlockMetadata(x, y, z));
    }
    @Override
    public Icon getIcon(int side, int metadata){
        int facing = (metadata & 7) + 1;
        
        //inventory rendering
        if (metadata == 0){
            if (side == 4){
                return blockIcon;
            }
            return sideIcon;
        }
        
        if (side != facing){
            return sideIcon;
        }
        if ((metadata & 8) != 0){
            return frontTopIcon;
        }
        return frontBottomIcon;
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, 
            EntityPlayer player, int side, float px, float py, float pz)
    {
        ItemStack crystal = Items.getItem("energyCrystal");
        if (player.inventory.hasItem(crystal.itemID))
        {
            for (int i = 0; player.inventory.mainInventory.length > i; i++){
                if (player.inventory.mainInventory[i] != null &&
                    player.inventory.mainInventory[i].itemID == crystal.itemID &&
                    player.inventory.mainInventory[i].getItemDamage() == 1){
                    
                    player.inventory.mainInventory[i].setItemDamage(26);
                    
                    PotionEffect nausea = new PotionEffect(9, References.nausea_duration * 20, 0);
                    PotionEffect blindness = new PotionEffect(15, References.blindness_duration * 20, 0);
                    PotionEffect slowness = new PotionEffect(2, References.slowness_duration * 20, 0);
                    PotionEffect fatigue = new PotionEffect(4, References.fatigue_duration * 20, 0);
                    PotionEffect hunger = new PotionEffect(17, References.hunger_duration * 20, 0);
                    PotionEffect weakness = new PotionEffect(18, References.weakness_druation * 20, 0);
                    player.addPotionEffect(nausea);
                    player.addPotionEffect(blindness);
                    player.addPotionEffect(hunger);
                    player.addPotionEffect(slowness);
                    player.addPotionEffect(fatigue);
                    player.addPotionEffect(weakness);
                    
                    for (int j = 0; j < MinecraftServer.getServer().worldServers.length; ++j)
                    {
                        MinecraftServer.getServer().worldServers[j].setWorldTime(0);
                    }
                }
            }
        }
        return true;
    }
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        blockIcon = par1IconRegister.registerIcon("cryochamber:cryochamber");
        sideIcon = par1IconRegister.registerIcon("cryochamber:cryochamber_side");
        frontTopIcon = par1IconRegister.registerIcon("cryochamber:cryochamber_front_top");
        frontBottomIcon = par1IconRegister.registerIcon("cryochamber:cryochamber_front_bottom");
    }

}
