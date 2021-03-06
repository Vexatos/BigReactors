package erogenousbeef.bigreactors.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erogenousbeef.bigreactors.common.BRLoader;
import erogenousbeef.bigreactors.common.BigReactors;
import erogenousbeef.bigreactors.common.tileentity.TileEntityReactorControlRod;

public class BlockReactorControlRod extends BlockContainer {

	public static int renderId;
	
	protected Icon topIcon;
	
	public BlockReactorControlRod(int id, Material material) {
		super(id, material);
		
		this.setHardness(1.0f);
		this.setUnlocalizedName("blockReactorControlRod");
		this.setTextureName(BigReactors.TEXTURE_NAME_PREFIX + "blockReactorControlRod");
		this.setCreativeTab(BigReactors.TAB);
	}

	@Override
	public int getRenderType() {
		return renderId;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityReactorControlRod();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(BigReactors.TEXTURE_NAME_PREFIX + "tile.blockReactorPart.casingDefault");
		this.topIcon = par1IconRegister.registerIcon(BigReactors.TEXTURE_NAME_PREFIX + getUnlocalizedName());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
	{
		if(side == 1) { return this.topIcon; }
		
		return this.blockIcon;
	}

	@Override
	public boolean renderAsNormalBlock() { return false; }
	
	@Override
	public boolean isOpaqueCube() { return false; }
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
		if(entityPlayer.isSneaking()) {
			return false;
		}
		
		// Open GUI for this block
		if(!world.isRemote) {
			entityPlayer.openGui(BRLoader.instance, 0, world, x, y, z);
		}
		return true;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te instanceof TileEntityReactorControlRod) {
			TileEntityReactorControlRod rp = (TileEntityReactorControlRod)te;
			rp.onBlockAdded(world, x, y, z);
		}
	}
}
