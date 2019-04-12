package handmadeguns.entity.bullets;

//import littleMaidMobX.LMM_EntityLittleMaid;
//import littleMaidMobX.LMM_EntityLittleMaidAvatar;

import handmadeguns.HMGMessageKeyPressedC;
import handmadeguns.HMGPacketHandler;
import handmadeguns.HandmadeGunsCore;
import handmadeguns.entity.IFF;
import io.netty.buffer.ByteBuf;
import littleMaidMobX.LMM_EntityLittleMaid;
import littleMaidMobX.LMM_EntityLittleMaidAvatar;
import littleMaidMobX.LMM_EntityLittleMaidAvatarMP;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import static handmadeguns.HandmadeGunsCore.islmmloaded;

public class HMGEntityBullet_TE extends HMGEntityBulletBase
{
	public HMGEntityBullet_TE(World worldIn) {
		super(worldIn);
	}

	public HMGEntityBullet_TE(World worldIn, Entity throwerIn, int damege, float bspeed, float bure, float e, boolean cane, String modelname) {
		super(worldIn, throwerIn, damege, bspeed, bure,modelname);
		this.ex = e;
		this.canex = cane;
	}
	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */

	public void onUpdate(){
		super.onUpdate();
		if(noex && !this.worldObj.isRemote){
			if (hitedentity != null && hitedentity != this.getThrower() && noex) {
				if(this.getThrower() != null&&getThrower() instanceof EntityPlayerMP){
					HMGPacketHandler.INSTANCE.sendTo(new HMGMessageKeyPressedC(10, this.getThrower().getEntityId()),(EntityPlayerMP)this.getThrower());
				}
				int i = Bdamege;
				if(islmmloaded&&(this.thrower instanceof LMM_EntityLittleMaid || this.thrower instanceof LMM_EntityLittleMaidAvatar || this.thrower instanceof LMM_EntityLittleMaidAvatarMP) && HandmadeGunsCore.cfg_FriendFireLMM){
					if (hitedentity instanceof LMM_EntityLittleMaid)
					{
						i = 0;
					}
					if (hitedentity instanceof LMM_EntityLittleMaidAvatar)
					{
						i = 0;
					}
					if (hitedentity instanceof EntityPlayer)
					{
						i = 0;
					}
				}
				if(this.thrower instanceof IFF){
					if(((IFF) this.thrower).is_this_entity_friend(hitedentity)){
						i = 0;
					}
				}
				hitedentity.hurtResistantTime = 0;
				double moXback = hitedentity.motionX;//�m�b�N�o�b�N�������p
				double moYback = hitedentity.motionY;//���ˏオ�薳�����p
				double moZback = hitedentity.motionZ;//�m�b�N�o�b�N�������p
				if(hitedentity.attackEntityFrom((new EntityDamageSourceIndirect("arrow", this, this.getThrower())).setProjectile(), (float)i)){
					hitedentity.motionX = moXback;
					hitedentity.motionY = moYback;
					hitedentity.motionZ = moZback;
					Vec3 knockvec = this.getLook((float) knockbackXZ,-this.rotationYaw,-this.rotationPitch);
					if(hitedentity instanceof EntityLivingBase){
						if(this.rand.nextDouble() >= ((EntityLivingBase)hitedentity).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()){
							hitedentity.isAirBorne =true;
							hitedentity.motionX += knockvec.xCoord;
							hitedentity.motionY += knockvec.yCoord + knockbackY;
							hitedentity.motionZ += knockvec.zCoord;
						}
					}
				}
//				hitedentity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
				this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
			if(!this.canbounce) this.setDead();
		}
	}
	protected void onImpact(MovingObjectPosition var1)
	{
		super.onImpact(var1);
		if (var1.entityHit != null)
		{
			int var2 = this.Bdamege;

			if(islmmloaded&&(this.thrower instanceof LMM_EntityLittleMaid || this.thrower instanceof LMM_EntityLittleMaidAvatar || this.thrower instanceof LMM_EntityLittleMaidAvatarMP) && HandmadeGunsCore.cfg_FriendFireLMM){
				if (var1.entityHit instanceof LMM_EntityLittleMaid)
				{
					var2 = 0;
				}
				if (var1.entityHit instanceof LMM_EntityLittleMaidAvatar)
				{
					var2 = 0;
				}
				if (var1.entityHit instanceof EntityPlayer)
				{
					var2 = 0;
				}
			}
			if(this.thrower instanceof IFF){
				if(((IFF) this.thrower).is_this_entity_friend(var1.entityHit)){
					var2 = 0;
				}
			}
			var1.entityHit.hurtResistantTime = 0;

			if (var1.entityHit != null && var1.entityHit != this.getThrower() && (noex || this.canbounce)) {
				var1.entityHit.hurtResistantTime = 0;
				double moXback = var1.entityHit.motionX;//�m�b�N�o�b�N�������p
				double moYback = var1.entityHit.motionY;//���ˏオ�薳�����p
				double moZback = var1.entityHit.motionZ;//�m�b�N�o�b�N�������p
				if(var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)var2)){
					var1.entityHit.motionX = moXback;
					var1.entityHit.motionY = moYback;
					var1.entityHit.motionZ = moZback;
					Vec3 knockvec = this.getLook((float) knockbackXZ,-this.rotationYaw,-this.rotationPitch);
					if(var1.entityHit instanceof EntityLivingBase){
						if(this.rand.nextDouble() >= ((EntityLivingBase)var1.entityHit).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()){
							var1.entityHit.isAirBorne =true;
							var1.entityHit.motionX += knockvec.xCoord;
							var1.entityHit.motionY += knockvec.yCoord + knockbackY;
							var1.entityHit.motionZ += knockvec.zCoord;
						}
					}
				}
			}
			var1.entityHit.setFire(5);
            /*Entity lel = (Entity)var1.entityHit;

			lel.addVelocity((motionX/20D),
							(-motionY-2D),
							(motionZ/20D));*/
			if(!noex && !this.worldObj.isRemote && !canbounce){
				this.flameex(var1);
				hitedentity = var1.entityHit;

			}
			if (!this.worldObj.isRemote&& (!(var1.entityHit instanceof EntityLivingBase)||noex)) {
				if(!this.canbounce) this.setDead();
			}
		}else {
			Block lblock = worldObj.getBlock(var1.blockX, var1.blockY, var1.blockZ);
			int lmeta = worldObj.getBlockMetadata(var1.blockX, var1.blockY, var1.blockZ);
			if (checkDestroyBlock(var1, var1.blockX, var1.blockY, var1.blockZ, lblock, lmeta)) {
				if (!this.worldObj.isRemote)
				{
					onBreakBlock(var1, var1.blockX, var1.blockY, var1.blockZ, lblock, lmeta);
				}
			} else {
				for (int i = 0; i < 8; ++i) {
//					worldObj.spawnParticle("snowballpoof", this.posX, this.posY,
					worldObj.spawnParticle("smoke",
							var1.hitVec.xCoord, var1.hitVec.yCoord, var1.hitVec.zCoord,
							0.0D, 0.0D, 0.0D);
				}
			}
			Block block = this.worldObj.getBlock(var1.blockX,
					var1.blockY,
					var1.blockZ);
			if(!block.isAir(worldObj,var1.blockX,
					var1.blockY,
					var1.blockZ)) {
				worldObj.playSoundEffect((float) var1.hitVec.xCoord, (float) var1.hitVec.yCoord, (float) var1.hitVec.zCoord, new ResourceLocation(block.stepSound.getStepResourcePath()).getResourcePath(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(block) + "_" +
								this.worldObj.getBlockMetadata(var1.blockX,
										var1.blockY,
										var1.blockZ)
						, var1.hitVec.xCoord, var1.hitVec.yCoord, var1.hitVec.zCoord, 4.0D * ((double) this.rand.nextFloat() - 0.5D), 0.5D, ((double) this.rand.nextFloat() - 0.5D) * 4.0D);
			}
			if (!this.worldObj.isRemote)
			{
				if(!this.canbounce) this.setDead();
				if(!noex && !this.worldObj.isRemote && !canbounce){
					this.flameex(var1);
					this.setDead();
				}
				if(!this.worldObj.isRemote && fuse<=0){
					this.flameex(var1);
					this.setDead();
				}
			}
		}
	}
	protected void flameex(MovingObjectPosition var1){
		Explosion explosion = new Explosion(this.worldObj, this, var1.hitVec.xCoord,var1.hitVec.yCoord,var1.hitVec.zCoord, this.ex);
		explosion.isFlaming = true;
		explosion.isSmoking = false;
		explosion.doExplosionA();
		explosion.doExplosionB(true);
		noex =true;
	}
	public void writeSpawnData(ByteBuf buffer){
		super.writeSpawnData(buffer);
	}
	public void readSpawnData(ByteBuf additionalData){
		super.readSpawnData(additionalData);
	}
}
