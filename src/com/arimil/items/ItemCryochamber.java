package com.arimil.items;

import com.arimil.cryochamber.Cryochamber;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemCryochamber extends Item {

    public ItemCryochamber(int par1) {
        super(par1);
        setCreativeTab(CreativeTabs.tabInventory);
        setUnlocalizedName("cryochamber");
        setMaxStackSize(1);
    }
    
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else
        {
            ++par5;
            Block block = Cryochamber.BlockCryochamber;

            if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack))
            {
                if (!block.canPlaceBlockAt(par3World, par4, par5, par6))
                {
                    return false;
                }
                else
                {
                    int meta = getFacing(par2EntityPlayer);
                    placeCryochamberBlock(par3World, par4, par5, par6, meta, block);
                    --par1ItemStack.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static void placeCryochamberBlock(World par0World, int par1, int par2, int par3, int par4, Block par5Block)
    {
        par0World.setBlock(par1, par2, par3, par5Block.blockID, par4, 2);
        par0World.setBlock(par1, par2 + 1, par3, par5Block.blockID, par4 | 8, 2);
        par0World.notifyBlocksOfNeighborChange(par1, par2, par3, par5Block.blockID);
        par0World.notifyBlocksOfNeighborChange(par1, par2 + 1, par3, par5Block.blockID);
    }
    private int getFacing(EntityPlayer par2EntityPlayer){
        int f = MathHelper.floor_double((double)(par2EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        switch (f){
            case 0:
                f = 2;
                break;
            case 1:
                f = 5;
                break;
            case 2:
                f = 3;
                break;
            case 3:
                f = 4;
                break;
        }
        return f - 1;
    }
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("cryochamber:cryochamber");
    }
}
