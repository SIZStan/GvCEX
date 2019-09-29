package handmadevehicle.render;

import handmadevehicle.Utils;
import handmadevehicle.entity.parts.IMultiTurretVehicle;
import handmadevehicle.entity.parts.IVehicle;
import handmadevehicle.entity.parts.ModifiedBoundingBox;
import handmadevehicle.entity.parts.logics.BaseLogic;
import handmadevehicle.entity.parts.turrets.TurretObj;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;
import java.util.ArrayList;

import static handmadevehicle.CLProxy.drawOutlinedBoundingBox;
import static java.lang.Math.toDegrees;
import static net.minecraft.client.renderer.entity.RenderManager.debugBoundingBox;
import static org.lwjgl.opengl.GL11.*;

public class RenderVehicle extends Render {
	
	private ResourceLocation skeletonTexturesz;
	private IModelCustom vehicleModel;
	private float partialTicks;
	public static BaseLogic currentBaseLogic;
	public static Entity currentEntity;
	public PartsRender_Vehicle partsRender_vehicle = new PartsRender_Vehicle();
	private TurretObj[] allTurrets;
	
	public RenderVehicle() {
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return skeletonTexturesz;
	}
	
	/*private double func_110828_a(double p_110828_1_, double p_110828_3_, double p_110828_5_)
    {
        return p_110828_1_ + (p_110828_3_ - p_110828_1_) * p_110828_5_;
    }
	*/
	int pass;
	float xsxs;
	public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_,
	                     float entityYaw, float in_partialTicks) {
		
		if(entity instanceof IMultiTurretVehicle && entity instanceof IVehicle && ((IVehicle) entity).getBaseLogic().info.modelSetAndData != null){
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
			skeletonTexturesz = ((IVehicle) entity).getBaseLogic().info.modelSetAndData.texture;
			vehicleModel = ((IVehicle) entity).getBaseLogic().info.modelSetAndData.model;
			pass = MinecraftForgeClient.getRenderPass();
			currentBaseLogic = ((IVehicle) entity).getBaseLogic();
			currentEntity = entity;
			allTurrets = currentBaseLogic.allturrets;
			partialTicks = in_partialTicks;
			TurretObj[] turretObjs = currentBaseLogic.turrets;
			
			GL11.glShadeModel(GL11.GL_SMOOTH);
			if (pass == 1) {
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				GL11.glDepthMask(false);
				glAlphaFunc(GL_LESS, 1);
			} else {
				GL11.glDepthMask(true);
				glAlphaFunc(GL_EQUAL, 1);
			}
			
			this.bindEntityTexture(entity);
			GL11.glPushMatrix();
			GL11.glTranslatef((float) p_76986_2_, (float) p_76986_4_, (float) p_76986_6_);
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glTranslatef((float) currentBaseLogic.info.rotcenter[0], (float) currentBaseLogic.info.rotcenter[1], (float) currentBaseLogic.info.rotcenter[2]);
			
			Quat4d currentquat = new Quat4d();
			currentquat.interpolate(currentBaseLogic.prevbodyRot,currentBaseLogic.bodyRot, (double) partialTicks);
			double[] xyz = Utils.eulerfrommatrix(Utils.matrixfromQuat(currentquat));
			xyz[0] = toDegrees(xyz[0]);
			xyz[1] = toDegrees(xyz[1]);
			xyz[2] = toDegrees(xyz[2]);
			
			GL11.glRotatef(180 - (float)xyz[1], 0.0F, 1.0F, 0.0F);
			GL11.glRotatef((float) xyz[0], 1.0F, 0.0F, 0.0F);
			GL11.glRotatef((float) xyz[2], 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-(float) currentBaseLogic.info.rotcenter[0], -(float) currentBaseLogic.info.rotcenter[1], -(float) currentBaseLogic.info.rotcenter[2]);
			
			GL11.glPushMatrix();
			GL11.glScalef((float) currentBaseLogic.info.scale, (float) currentBaseLogic.info.scale, (float) currentBaseLogic.info.scale);
			if(currentBaseLogic.info.partslist != null){
				partsRender_vehicle.model = this.vehicleModel;
				partsRender_vehicle.partSidentification(currentBaseLogic.info.partslist);
			}else {
				vehicleModel.renderPart("obj1");
				vehicleModel.renderPart("body");
				if (turretObjs != null)
					for (int i = 0; i < turretObjs.length; i++) {
						TurretObj aturretobj = turretObjs[i];
						GL11.glPushMatrix();
						GL11.glTranslatef((float) aturretobj.onMotherPos.x, (float) aturretobj.onMotherPos.y, (float) -aturretobj.onMotherPos.z);
						GL11.glTranslatef((float) aturretobj.prefab_turret.turretYawCenterpos.x, (float) aturretobj.prefab_turret.turretYawCenterpos.y, (float) -aturretobj.prefab_turret.turretYawCenterpos.z);
						GL11.glRotatef((float) -(aturretobj.prevturretrotationYaw + (aturretobj.turretrotationYaw - aturretobj.prevturretrotationYaw) * partialTicks), 0.0F, 1.0F, 0.0F);
						GL11.glTranslatef((float) -aturretobj.prefab_turret.turretYawCenterpos.x, (float) -aturretobj.prefab_turret.turretYawCenterpos.y, (float) aturretobj.prefab_turret.turretYawCenterpos.z);
						GL11.glTranslatef((float) -aturretobj.onMotherPos.x, (float) -aturretobj.onMotherPos.y, (float) aturretobj.onMotherPos.z);
						vehicleModel.renderPart("Turret" + i);
						renderchild(aturretobj.getChilds(), "Turret" + i);
						GL11.glTranslatef((float) aturretobj.onMotherPos.x, (float) aturretobj.onMotherPos.y, (float) -aturretobj.onMotherPos.z);
						GL11.glTranslatef((float) aturretobj.prefab_turret.turretPitchCenterpos.x, (float) aturretobj.prefab_turret.turretPitchCenterpos.y, (float) aturretobj.prefab_turret.turretPitchCenterpos.z);
						GL11.glRotatef((float) (aturretobj.prevturretrotationPitch + (aturretobj.turretrotationPitch - aturretobj.prevturretrotationPitch) * partialTicks), 1.0F, 0.0F, 0.0F);
						GL11.glTranslatef((float) -aturretobj.prefab_turret.turretPitchCenterpos.x, (float) -aturretobj.prefab_turret.turretPitchCenterpos.y, (float) -aturretobj.prefab_turret.turretPitchCenterpos.z);
						GL11.glTranslatef((float) -aturretobj.onMotherPos.x, (float) -aturretobj.onMotherPos.y, (float) aturretobj.onMotherPos.z);
						vehicleModel.renderPart("Turret" + i + "Cannon");
						renderReticle(pass, aturretobj, "Turret" + i);
						GL11.glPopMatrix();
					}
				//GL11.glRotatef(-(180.0F - entityYaw), 0.0F, 1.0F, 0.0F);
				
				
			}
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			if (debugBoundingBox && entity.boundingBox instanceof ModifiedBoundingBox) {
				GL11.glDepthMask(false);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glDisable(GL11.GL_BLEND);
				ModifiedBoundingBox axisalignedbb = (ModifiedBoundingBox) entity.boundingBox;
				drawOutlinedBoundingBox(axisalignedbb, 16777215);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glDepthMask(true);
			}
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			GL11.glShadeModel(GL_FLAT);
			GL11.glPopAttrib();
		}

//		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//		GL11.glPopMatrix();
//
//		GL11.glPushMatrix();
//		GL11.glTranslatef((float) p_76986_2_, (float) p_76986_4_, (float) p_76986_6_);
//		GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
//		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//
//		if(entity.deathTicks > 0){
//			GL11.glColor4f(0.1F, 0.1F, 0.1F, 1F);
//		}
//
//		GL11.glRotatef(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
//		GL11.glTranslatef(0, 2.5F, 0);
//		this.renderAngle(entity, 1);
//		GL11.glTranslatef(0, -2.5F, 0);
//			tankk.renderPart("mat1");
//		GL11.glRotatef(-(180.0F - entityYaw), 0.0F, 1.0F, 0.0F);
//
//		{
////			GL11.glRotatef(180.0F - entity.rotation, 0.0F, 1.0F, 0.0F);
//			tankk.renderPart("mat4");
//			GL11.glTranslatef(0F, 2.08F, 1.05F);
////			GL11.glRotatef(entity.rotationp, 1.0F, 0.0F, 0.0F);
//			GL11.glTranslatef(0F, -2.08F, -1.05F);
//			tankk.renderPart("mat5");
//		}
//
//		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//		GL11.glPopMatrix();
	
	
	}
	
	private void renderReticle(int pass,TurretObj aturretobj,String motherName){
		
		if(aturretobj.prefab_turret.hasReflexSight) {
			glClear(GL_STENCIL_BUFFER_BIT);
			glEnable(GL_STENCIL_TEST);
			glStencilMask(1);
			
			glStencilFunc(
					GL_ALWAYS,   // GLenum func
					1,          // GLint ref
					~0);// GLuint mask
			glStencilOp(
					GL_KEEP,
					GL_KEEP,
					GL_REPLACE);
			if (pass != 1) {
				GL11.glDepthMask(false);
				glAlphaFunc(GL_ALWAYS, 1);
				glColorMask(
						false,   // GLboolean red
						false,   // GLboolean green
						false,   // GLboolean blue
						false);
			}
			vehicleModel.renderPart(motherName + "ReticlePlate");
			if (pass != 1) {
				GL11.glDepthMask(true);
				glAlphaFunc(GL_EQUAL, 1);
				glColorMask(
						true,   // GLboolean red
						true,   // GLboolean green
						true,   // GLboolean blue
						true);
			}
			
			GL11.glDisable(GL11.GL_LIGHTING);
			float lastBrightnessX = OpenGlHelper.lastBrightnessX;
			float lastBrightnessY = OpenGlHelper.lastBrightnessY;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
			glDisable(GL_DEPTH_TEST);
			
			
			glStencilFunc(
					GL_EQUAL,   // GLenum func
					1,          // GLint ref
					~0);// GLuint mask
			
			glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
			glAlphaFunc(GL_ALWAYS, 1);
			GL11.glDepthMask(false);
			
			GL11.glDepthFunc(GL11.GL_ALWAYS);//強制描画
			vehicleModel.renderPart(motherName + "Reticle");
			GL11.glDepthFunc(GL11.GL_LEQUAL);
			GL11.glDepthMask(true);
			glDisable(GL_STENCIL_TEST);
			glEnable(GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_LIGHTING);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)lastBrightnessX, (float)lastBrightnessY);
			if(pass == 1) {
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				GL11.glDepthMask(false);
				glAlphaFunc(GL_LESS, 1);
			}else {
				GL11.glDepthMask(true);
				glAlphaFunc(GL_EQUAL, 1);
			}
		}
	}
	private void renderchild(ArrayList<TurretObj> childturretObjs, String motherID){
		for(int ic = 0;ic < childturretObjs.size();ic++){
			TurretObj achild = childturretObjs.get(ic);
			GL11.glPushMatrix();
			GL11.glTranslatef((float)achild.onMotherPos.x,(float)achild.onMotherPos.y,(float)-achild.onMotherPos.z);
			GL11.glTranslatef((float)achild.prefab_turret.turretYawCenterpos.x,(float)achild.prefab_turret.turretYawCenterpos.y,(float)-achild.prefab_turret.turretYawCenterpos.z);
			GL11.glRotatef((float)-(achild.prevturretrotationYaw + (achild.turretrotationYaw - achild.prevturretrotationYaw) * partialTicks), 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef((float)-achild.prefab_turret.turretYawCenterpos.x,(float)-achild.prefab_turret.turretYawCenterpos.y,(float)achild.prefab_turret.turretYawCenterpos.z);
			GL11.glTranslatef((float)-achild.onMotherPos.x,(float)-achild.onMotherPos.y,(float)achild.onMotherPos.z);
			vehicleModel.renderPart(motherID + "child" + ic);
			renderchild(achild.getChilds(),motherID + "child" + ic);
			GL11.glTranslatef((float)achild.onMotherPos.x,(float)achild.onMotherPos.y,(float)-achild.onMotherPos.z);
			GL11.glTranslatef((float)achild.prefab_turret.turretPitchCenterpos.x,(float)achild.prefab_turret.turretPitchCenterpos.y,(float)-achild.prefab_turret.turretPitchCenterpos.z);
			GL11.glRotatef((float)(achild.prevturretrotationPitch + (achild.turretrotationPitch - achild.prevturretrotationPitch) * partialTicks), 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef((float)-achild.prefab_turret.turretPitchCenterpos.x,(float)-achild.prefab_turret.turretPitchCenterpos.y,(float)achild.prefab_turret.turretPitchCenterpos.z);
			GL11.glTranslatef((float)-achild.onMotherPos.x,(float)-achild.onMotherPos.y,(float)achild.onMotherPos.z);
			vehicleModel.renderPart(motherID + "child" + ic + "Cannon");
			
			renderReticle(pass,achild,motherID + "child" + ic);
			GL11.glPopMatrix();
		}
	}
	
}
