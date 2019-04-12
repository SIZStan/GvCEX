package handmadeguns;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import handmadeguns.items.*;
import handmadeguns.items.guns.*;
import handmadeguns.client.render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static handmadeguns.HandmadeGunsCore.*;

public class HMGAddGunsNew {
	public static ArrayList Guns = new ArrayList();





	public static void load(File configfile, boolean isClient, File file1) {
		String  GunName;
		String  Namegun = null;
		int     power;
		float   speed;
		float   bure;
		float   spread_cof;
		double  recoil;
		double  recoilwhensneak;
		int     retime;
		int     kazu;
		float   bayonet = 0;
		float   zoom;
		float   zoomred;
		float   zoomscope;
		int     cycle;
		float   ex;
		boolean canex;
		String  texture;
		float   soundbaselevel = 4.0f;
		String  sound;
		float   soundsuplevel = 4.0f;
		String  soundsu;
		float soundrelv = 1.0f;
		String  soundre;
		String  soundco;
		boolean canobj;
		String  objmodel;
		boolean isuserenderscript = false;
		String  renderscript = null;
		boolean isusegunscript = false;
		String  gunscript = null;
		String  objtexture;
		float   modelscala = 1;
		float   modelhigh = 0;
		float   modelhighr = 0;
		float   modelhighs = 0;
		float   modelwidthx = 0;
		float   modelwidthxr = 0;
		float   modelwidthxs = 0;
		float   modelwidthz = 0;
		float   modelwidthzr = 0;
		float   modelwidthzs = 0;
		float   rotationx;
		float   rotationxr;
		float   rotationxs;
		float   rotationy;
		float   rotationyr;
		float   rotationys;
		float   rotationz;
		float   rotationzr;
		float   rotationzs;
		boolean arm;
		float   armrotationxr;
		float   armrotationyr;
		float   armrotationzr;
		float   armoffsetxr;
		float   armoffsetyr;
		float   armoffsetzr;
		float   armrotationxl;
		float   armrotationyl;
		float   armrotationzl;
		float   armoffsetxl;
		float   armoffsetyl;
		float   armoffsetzl;
		float   nox = -0.7F;
		float   noy = 0.7F;
		float   noz = 0F;
		float[] thirdGunOffset = {0,0,0};
		float   eqrotax = 0;
		float   eqrotay = 0;
		float   eqrotaz = 0;
		float   mat31posx;
		float   mat31posy;
		float   mat31posz;
		float   mat31rotex;
		float   mat31rotey;
		float   mat31rotez;
		float   mat32posx;
		float   mat32posy;
		float   mat32posz;
		float   mat32rotex;
		float   mat32rotey;
		float   mat32rotez;
		boolean mat22;
		float   mat22posx;
		float   mat22posy;
		float   mat22posz;
		float   mat22rotex;
		float   mat22rotey;
		float   mat22rotez;
		float   mat25posx;
		float   mat25posy;
		float   mat25posz;
		float   mat25rotex;
		float   mat25rotey;
		float   mat25rotez;
		int     cockti;
		boolean needcock;
		float   spposx;
		float   spposy;
		float   spposz;
		float   sprotex;
		float   sprotey;
		float   sprotez;
		int     pellet;
		boolean rendercross = false;
		int     firetype = 0;
		int     righttype = 0;
		boolean semi = false;
		String  ads = "null";
		String  adsr = "null";
		String  adss = "null";
		int     itemstack;
		Item[]  addi;
		String[]  bptemnames = null;
		boolean zoomrender = false;
		boolean zoomrendern = false;
		boolean zoomrenderr = false;
		boolean zoomrenders = false;
		boolean zoomrendernt = false;
		boolean zoomrenderrt = false;
		boolean zoomrenderst = false;
		Item    magazine;
		Item    magazinesg;
		Item    magazinegl;
		double  motionn;
		boolean mat25;
		boolean mat2;
		boolean alljump;
		float   jump;
		boolean cockleft;
		float   gra;
		Item    itema = null;
		Item    itemb = null;
		Item    itemc = null;
		Item    itemd = null;
		Item    iteme = null;
		Item    itemf = null;
		Item    itemg = null;
		Item    itemh = null;
		Item    itemi = null;

		ItemStack    itemblocka = null;
		ItemStack    itemblockb = null;
		ItemStack    itemblockc = null;
		ItemStack    itemblockd = null;
		ItemStack    itemblocke = null;
		ItemStack    itemblockf = null;
		ItemStack    itemblockg = null;
		ItemStack    itemblockh = null;
		ItemStack    itemblocki = null;
		String  re1 = "abc";
		String  re2 = "def";
		String  re3 = "ghi";
		boolean cartridge = true;
		boolean cartridgedroponcocked = false;
		int     cartridgetype;
		int     magtype;
		int     magcnt;
		int     cartcnt;
		boolean canmagazine;
		float   soundspeed;
		boolean remat31;
		boolean          attachRestriction = false;
		ArrayList<String> canset = new ArrayList<String>();
		boolean          useundergunsmodel = false;
		float            underoffsetpx;
		float            underoffsetpy;
		float            underoffsetpz;
		float            underrotationx;
		float            underrotationy;
		float            underrotationz;

		float onunderoffsetpx  = 0;
		float onunderoffsetpy  = 0;
		float onunderoffsetpz  = 0;
		float onunderrotationx = 0;
		float onunderrotationy = 0;
		float onunderrotationz = 0;
		String soundunder_gl;
		String soundunder_sg;
		int under_gl_power = 20;
		boolean under_gl_canbounce = false;
		int under_gl_fuse = -1;
		float under_gl_speed = 2;
		float under_gl_bure = 5;
		double under_gl_recoil = 5;
		float under_gl_gra;
		int under_sg_power = 4;
		float under_sg_speed = 3;
		float under_sg_bure = 20;
		double under_sg_recoil = 5;
		float under_sg_gra;
		boolean reloadanim = true;
		ArrayList<Float[]> reloadanimation = new ArrayList<Float[]>();
		boolean hasbmodel = false;
		boolean hascmodel = false;
		boolean hasmagmodel = false;
		String bulletmodelNameN    = "default";
		String bulletmodelNameAR   = "default";
		String bulletmodelNameAP   = "default";
		String bulletmodelNameAT   = "default";
		String bulletmodelNameFrag = "default";
		String bulletmodelNameHE   = "default";
		String bulletmodelNameTE   = "default";
		String bulletmodelNameCart = "default";
		String bulletmodelNameGL   = "default";
		String bulletmodelNameRPG  = "default";
		String bulletmodelNameMAG  = "default";
		double knock;
		double knocky;
		ArrayList<Integer> burstcount = new ArrayList<Integer>();
		ArrayList<Integer> rates = new ArrayList<Integer>();
		boolean canbounce = false;
		int fuse = 0;
		float bouncerate = 0.2f;
		float bouncelimit = 90f;
		boolean isOneuse = false;
		int maxstacksize = 1;
		boolean muzzleflash = false;
		int magazineCount = 1;
		int guntype = -1;
		String flashname = null;
		int flashfuse = 1;
		float flashScale = 1;
		boolean canfix = false;
		boolean needfix = false;
		boolean fixAsEntity = false;
		double turretRotationYawPoint[] = new double[3];
		double turretRotationPitchPoint[] = new double[3];
		double[] barrelpos = new double[]{0,0.1,1};
		float yoffset = 0;
		double[] seatoffset = new double[]{0,0,0};


		float inworldScale = 1;
		GunName ="noname";
		Namegun = null;
		power = 0;
		speed = 1;
		burstcount = new ArrayList<Integer>();
		rates = new ArrayList<Integer>();
//			burstcount.add(0);
//			burstcount.add(-1);
//			burstcount.add(1);
//			burstcount.add(3);
//			rates.add(2);
//			rates.add(2);
//			rates.add(2);
//			rates.add(2);
		bure = 1;
		spread_cof = 0.5f;
		recoil = 1;
		recoilwhensneak = 0.5;

		retime = 15;
		kazu = 30;
		zoom = 1;
		zoomred = 1;
		zoomscope = 4;
		cycle = 2;
		ex = 2.5f;
		canex = false;
		texture = "AR_sample";
		soundbaselevel = 4.0f;
		sound = "handmadeguns.fire";
		soundsuplevel = 1.0f;
		soundsu = "handmadeguns.supu";
		soundrelv = 1.0f;
		soundre = "proceedreload";
		soundco = "handmadeguns.cooking";
		canobj = false;
		objmodel = "ar.obj";
		objtexture = "ar.png";
		ads = "null";
		adsr = "null";
		adss = "null";
		itemstack = 1;
		addi = null;
		zoomrender = false;
		zoomrendern = false;
		zoomrenderr = false;
		zoomrenders = false;
		zoomrendernt = false;
		zoomrenderrt = false;
		zoomrenderst = false;
		cartridge = false;
		cartridgedroponcocked = false;
		magazine = null;
		magazinesg = null;
		magazinegl = null;
		motionn =1;
		canmagazine = true;
		modelwidthx = 0.694F;
		modelwidthxr = 0.694F;
		modelwidthxs = 0.694F;
		modelwidthz = 0F;
		modelwidthzr = 0F;
		modelwidthzs = 0F;
		rotationx = 180F;
		rotationxr = 180F;
		rotationxs = 180F;
		rotationy = 45F;
		rotationyr = 45F;
		rotationys = 45F;
		rotationz = 180F;
		rotationzr = 180F;
		rotationzs = 180F;

		arm = false;
		armrotationxr = -1.57F;
		armrotationyr = 0F;
		armrotationzr = 0F;
		armoffsetxr = 0.5F;
		armoffsetyr = 0.5F;
		armoffsetzr = 0.5F;
		armrotationxl = -0.8F;
		armrotationyl = 0F;
		armrotationzl = 90F;
		armoffsetxl = 0.1F;
		armoffsetyl = 0.3F;
		armoffsetzl = -1F;

		mat31posx = 0F;
		mat31posy = 0F;
		mat31posz = 0F;
		mat31rotex = 0F;
		mat31rotez = 0F;
		mat31rotey = 0F;

		mat32posx = 0F;
		mat32posy = 0.5F;
		mat32posz = 0F;
		mat32rotex = 0F;
		mat32rotez = 0F;
		mat32rotey = 0F;

		mat22 = false;
		mat22posx = 0F;
		mat22posy = 1.5F;
		mat22posz = 2F;
		mat22rotex = 90F;
		mat22rotey = 0F;
		mat22rotez = 0F;

		mat25posx = 0F;
		mat25posy = 0.75F;
		mat25posz = 1.1F;
		mat25rotex = 0F;
		mat25rotey = 0F;
		mat25rotez = -90F;
		cockti = 20;
		needcock = false;

		spposx = 0.5F;
		spposy = 0.0F;
		spposz = 0.5F;
		sprotex = 20F;
		sprotey = 60F;
		sprotez = 0F;
		pellet = 1;

		cartridge = true;

		motionn = 1D;

		mat2 = false;
		mat25 = false;
		alljump = false;
		cockleft = false;
		jump = 0;
		gra = 0.029F;










		re1 = "abc";
		re2 = "def";
		re3 = "ghi";
		Namegun = null;


		cartridgetype = 1;
		magtype = 5;
		magcnt = 1;
		cartcnt = 1;
		soundspeed = 1F;
		remat31 = true;

		attachRestriction = false;
		canset = new ArrayList<String>();
		useundergunsmodel = false;
		underoffsetpx = 0;
		underoffsetpy = 0;
		underoffsetpz = 0;
		underrotationx = 0;
		underrotationy = 0;
		underrotationz = 0;

		soundunder_gl = null;
		soundunder_sg = null;
		under_gl_power = 20;
		under_gl_canbounce = false;
		under_gl_speed = 2;
		under_gl_bure = 5;
		under_gl_recoil = 5;
		under_gl_gra = 0.01F;
		under_sg_power = 4;
		under_sg_speed = 3;
		under_sg_bure = 20;
		under_sg_recoil = 5;
		under_sg_gra = 0.029F;

		reloadanim = false;
		reloadanimation = new ArrayList<Float[]>();
		hasbmodel = false;
		hascmodel = false;
		hasmagmodel = false;
		bulletmodelNameN    = "default";
		bulletmodelNameAR   = "default";
		bulletmodelNameAP   = "default";
		bulletmodelNameAT   = "default";
		bulletmodelNameFrag = "default";
		bulletmodelNameHE   = "default";
		bulletmodelNameTE   = "default";
		bulletmodelNameCart = "default";
		bulletmodelNameGL   = "default";
		bulletmodelNameRPG  = "default";
		bulletmodelNameMAG  = "default";

		knock = cfg_defaultknockback;
		knocky = cfg_defaultknockbacky;
		canbounce = false;
		fuse = 0;
		bouncerate = 0.2f;
		isOneuse = false;
		maxstacksize = 1;
		muzzleflash = true;
		magazineCount = 1;
		isuserenderscript = false;
		renderscript = null;
		float armscale = 1;
		boolean g_can_use = true;
		boolean can_be_Root = true;
		boolean soldiercanstorage = true;
		boolean useinternalsettings = false;
		float induction_precision = 0;
		int canuseclass = -1;
		float acceleration = 0;
		boolean canlock = false;
		boolean canlockBlock = false;
		boolean canlockEntity = false;
		float barrelattachoffset[] = new float[3];
		float barrelattachrotation[] = new float[3];
		float sightattachoffset[] = new float[3];
		float sightattachrotation[] = new float[3];
		float lightattachoffset[] = new float[3];
		float lightattachrotation[] = new float[3];
		float gripattachoffset[] = new float[3];
		float gripattachrotation[] = new float[3];
		String tabname = null;
		guntype = -1;

		boolean restrictTurretMoveSpeed = false;
		float turretMoveSpeedP = 0;
		float turretMoveSpeedY = 0;
		boolean restrictTurretAngle = false;
		float turretanglelimtMxP = 0;
		float turretanglelimtMxY = 0;
		float turretanglelimtmnP = 0;
		float turretanglelimtmnY = 0;
		float boxW = 1;
		float boxH = 1;
		float onTurretScale = 1;
		int turretHP = 0;
		boolean[] hasNightVision = new boolean[]{false,false,false};

		ArrayList<HMGGunParts> Partslist = new ArrayList<HMGGunParts>();
		HMGGunParts currentParts = null;
		int currentIndex = 0;

		try {
			File file = file1;
			// File file = new File(configfile,"hmg_handmadeguns.txt");
			if (checkBeforeReadfile(file)) {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"Shift-JIS"));

				String str;
				while ((str = br.readLine()) != null) { // 1行ずつ読み込む
					// System.out.println(str);
					String[] type = str.split(",");
					if(!type[0].equals("Recipe1")&& !type[0].equals("Recipe2") && !type[0].equals("Recipe3") && !type[0].equals("addRecipe") && !type[0].equals("addNewRecipe") && !type[0].equals("Magazine")){
						for(int i=0;i<type.length;i++){
							type[i] = type[i].trim();
						}
					}


					if (type.length != 0){// 1

						switch (type[0]) {
							case "BulletPower":
								power = Integer.parseInt(type[1]);
								break;
							case "BulletSpeed":
								speed = Float.parseFloat(type[1]);
								break;
							case "BlletSpread":
								bure = Float.parseFloat(type[1]);
								break;
							case "ADS_Spread_coefficient":
								spread_cof = Float.parseFloat(type[1]);
								break;
							case "Recoil":
								recoil = Double.parseDouble(type[1]);
								recoilwhensneak = Double.parseDouble(type[1]) / 2;
								break;
							case "Recoil_sneaking":
								recoilwhensneak = Double.parseDouble(type[1]);
								break;
							case "ReloadTime":
								retime = Integer.parseInt(type[1]);
								break;
							case "RemainingBullet":
								kazu = Integer.parseInt(type[1]);
								break;
							case "Attacking":
								bayonet = Float.parseFloat(type[1]);
								break;
							case "Motion":
								motionn = Double.parseDouble(type[1]);
								break;
							case "Zoom":
								zoom = Float.parseFloat(type[1]);
								zoomred = Float.parseFloat(type[2]);
								zoomscope = Float.parseFloat(type[3]);
								break;
							case "ZoomRender":
								zoomrender = Boolean.parseBoolean(type[1]);
								break;
							case "ZoomRenderType":
								zoomrendern = Boolean.parseBoolean(type[1]);
								zoomrenderr = Boolean.parseBoolean(type[2]);
								zoomrenders = Boolean.parseBoolean(type[3]);
								break;
							case "ZoomRenderTypeTxture":
								zoomrendernt = Boolean.parseBoolean(type[1]);
								zoomrenderrt = Boolean.parseBoolean(type[2]);
								zoomrenderst = Boolean.parseBoolean(type[3]);
								break;
							case "NightVision":
								hasNightVision[0] = Boolean.parseBoolean(type[1]);
								hasNightVision[1] = Boolean.parseBoolean(type[2]);
								hasNightVision[2] = Boolean.parseBoolean(type[3]);
								break;
							case "Cycle":
								cycle = Integer.parseInt(type[1]);
								break;
							case "Bursts":
								for (int i = 1; i < type.length; i++)
									burstcount.add(Integer.parseInt(type[i]));
								break;
							case "Rates":
								for (int i = 1; i < type.length; i++)
									rates.add(Integer.parseInt(type[i]));
								break;
							case "Explosion":
								ex = Float.parseFloat(type[1]);
								break;
							case "BlockDestory":
								canex = Boolean.parseBoolean(type[1]);
								break;
							case "Texture":
								texture = type[1];
								break;
							case "GunSound":
								sound = type[1];
								soundsu = type[2];
								break;
							case "GunSoundLV":
								soundbaselevel = Float.parseFloat(type[1]);
								soundsuplevel = Float.parseFloat(type[2]);
								break;
							case "GunSoundReload":
								soundre = type[1];
								break;
							case "GunSoundReloadLV":
								soundrelv = Float.parseFloat(type[1]);
								break;
							case "GunSoundCooking":
								soundco = type[1];
								break;
							case "CanObj":
								canobj = Boolean.parseBoolean(type[1]);
								break;
							case "ObjModel":
								objmodel = type[1];
								break;
							case "ObjTexture":
								objtexture = type[1];
								break;
							case "ModelScala":
								modelscala = Float.parseFloat(type[1]);
								break;
							case "InworldScale":
								inworldScale = Float.parseFloat(type[1]);
								break;
							case "ModelEquipped":
								nox = Float.parseFloat(type[1]);
								noy = Float.parseFloat(type[2]);
								noz = Float.parseFloat(type[3]);
								break;
							case "ThirdModelEquipped":
								thirdGunOffset = new float[]{Float.parseFloat(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3])};
								break;
							case "ModelEquippedRotation":
								eqrotax = Float.parseFloat(type[1]);
								eqrotay = Float.parseFloat(type[2]);
								eqrotaz = Float.parseFloat(type[3]);
								break;
							case "ModelHigh":
								modelhigh = Float.parseFloat(type[1]);
								modelhighr = Float.parseFloat(type[2]);
								modelhighs = Float.parseFloat(type[3]);
								break;
							case "ModelWidthX":
								modelwidthx = Float.parseFloat(type[1]);
								modelwidthxr = Float.parseFloat(type[2]);
								modelwidthxs = Float.parseFloat(type[3]);
								break;
							case "ModelWidthZ":
								modelwidthz = Float.parseFloat(type[1]);
								modelwidthzr = Float.parseFloat(type[2]);
								modelwidthzs = Float.parseFloat(type[3]);
								break;
							case "ModelRotationX":
								rotationx = Float.parseFloat(type[1]);
								rotationxr = Float.parseFloat(type[2]);
								rotationxs = Float.parseFloat(type[3]);
								break;
							case "ModelRotationY":
								rotationy = Float.parseFloat(type[1]);
								rotationyr = Float.parseFloat(type[2]);
								rotationys = Float.parseFloat(type[3]);
								break;
							case "ModelRotationZ":
								rotationz = Float.parseFloat(type[1]);
								rotationzr = Float.parseFloat(type[2]);
								rotationzs = Float.parseFloat(type[3]);
								break;
							case "ModelArm":
								arm = Boolean.parseBoolean(type[1]);
								break;
							case "ModelArmRotationR":
								armrotationxr = Float.parseFloat(type[1]);
								armrotationyr = Float.parseFloat(type[2]);
								armrotationzr = Float.parseFloat(type[3]);
								break;
							case "ModelArmOffsetR":
								armoffsetxr = Float.parseFloat(type[1]);
								armoffsetyr = Float.parseFloat(type[2]);
								armoffsetzr = Float.parseFloat(type[3]);
								break;
							case "ModelArmRotationL":
								armrotationxl = Float.parseFloat(type[1]);
								armrotationyl = Float.parseFloat(type[2]);
								armrotationzl = Float.parseFloat(type[3]);
								break;
							case "ModelArmOffsetL":
								armoffsetxl = Float.parseFloat(type[1]);
								armoffsetyl = Float.parseFloat(type[2]);
								armoffsetzl = Float.parseFloat(type[3]);
								break;
							case "Mat31Point":
								mat31posx = Float.parseFloat(type[1]);
								mat31posy = Float.parseFloat(type[2]);
								mat31posz = Float.parseFloat(type[3]);
								break;
							case "Mat31Rotation":
								mat31rotex = Float.parseFloat(type[1]);
								mat31rotey = Float.parseFloat(type[2]);
								mat31rotez = Float.parseFloat(type[3]);
								break;
							case "Mat32Point":
								mat32posx = Float.parseFloat(type[1]);
								mat32posy = Float.parseFloat(type[2]);
								mat32posz = Float.parseFloat(type[3]);
								break;
							case "Mat32Rotation":
								mat32rotex = Float.parseFloat(type[1]);
								mat32rotey = Float.parseFloat(type[2]);
								mat32rotez = Float.parseFloat(type[3]);
								break;
							case "Mat22":
								mat22 = Boolean.parseBoolean(type[1]);
								break;
							case "Mat22Point":
								mat22posx = Float.parseFloat(type[1]);
								mat22posy = Float.parseFloat(type[2]);
								mat22posz = Float.parseFloat(type[3]);
								break;
							case "Mat22Rotation":
								mat22rotex = Float.parseFloat(type[1]);
								mat22rotey = Float.parseFloat(type[2]);
								mat22rotez = Float.parseFloat(type[3]);
								break;
							case "Mat25Point":
								mat25posx = Float.parseFloat(type[1]);
								mat25posy = Float.parseFloat(type[2]);
								mat25posz = Float.parseFloat(type[3]);
								break;
							case "Mat25Rotation":
								mat25rotex = Float.parseFloat(type[1]);
								mat25rotey = Float.parseFloat(type[2]);
								mat25rotez = Float.parseFloat(type[3]);
								break;
							case "SprintingPoint":
								spposx = Float.parseFloat(type[1]);
								spposy = Float.parseFloat(type[2]);
								spposz = Float.parseFloat(type[3]);
								break;
							case "SprintingRotation":
								sprotex = Float.parseFloat(type[1]);
								sprotey = Float.parseFloat(type[2]);
								sprotez = Float.parseFloat(type[3]);
								break;
							case "CockingTime":
								cockti = Integer.parseInt(type[1]);
								needcock = true;
								break;
							case "ShotGun_Pellet":
								pellet = Integer.parseInt(type[1]);
								break;
							case "Cartridge":
								cartridge = Boolean.parseBoolean(type[1]);
								break;
							case "DropCartridgeEndCocked":
								cartridgedroponcocked = Boolean.parseBoolean(type[1]);
								break;
							case "MuzzleJump":
								jump = Float.parseFloat(type[1]);
								break;
							case "Mat2":
								mat2 = Boolean.parseBoolean(type[1]);
								break;
							case "Mat25":
								mat25 = Boolean.parseBoolean(type[1]);
								break;
							case "CockedLeftHand":
								cockleft = Boolean.parseBoolean(type[1]);
								break;
							case "ALLCocked":
								alljump = Boolean.parseBoolean(type[1]);
								break;
							case "BulletGravity":
								gra = Float.parseFloat(type[1]);
								break;
							case "UnderGL_Sound":
								useinternalsettings = true;
								soundunder_gl = type[1];
								break;
							case "UnderGL_Power":
								useinternalsettings = true;
								under_gl_power = Integer.parseInt(type[1]);
								break;
							case "UnderGL_Canbounce":
								useinternalsettings = true;
								under_gl_canbounce = Boolean.parseBoolean(type[1]);
								break;
							case "UnderGL_Fuse":
								useinternalsettings = true;
								under_gl_fuse = Integer.parseInt(type[1]);
								break;
							case "UnderGL_Speed":
								useinternalsettings = true;
								under_gl_speed = Float.parseFloat(type[1]);
								break;
							case "UnderGL_Spread":
								useinternalsettings = true;
								under_gl_bure = Float.parseFloat(type[1]);
								break;
							case "UnderGL_Recoil":
								useinternalsettings = true;
								under_gl_recoil = Double.parseDouble(type[1]);
								break;
							case "UnderGL_Gravity":
								useinternalsettings = true;
								under_gl_gra = Float.parseFloat(type[1]);
								break;
							case "UnderSG_Sound":
								useinternalsettings = true;
								soundunder_sg = type[1];
								break;
							case "UnderSG_Power":
								useinternalsettings = true;
								under_sg_power = Integer.parseInt(type[1]);
								break;
							case "UnderSG_Speed":
								useinternalsettings = true;
								under_sg_speed = Float.parseFloat(type[1]);
								break;
							case "UnderSG_Spread":
								useinternalsettings = true;
								under_sg_bure = Float.parseFloat(type[1]);
								break;
							case "UnderSG_Recoil":
								useinternalsettings = true;
								under_sg_recoil = Double.parseDouble(type[1]);
								break;
							case "UnderSG_Gravity":
								useinternalsettings = true;
								under_sg_gra = Float.parseFloat(type[1]);
								break;
							case "CartridgeType":
								cartridgetype = Integer.parseInt(type[1]);
								break;
							case "MagType":
								magtype = Integer.parseInt(type[1]);
								break;
							case "MagCount":
								magcnt = Integer.parseInt(type[1]);
								break;
							case "CartCount":
								cartcnt = Integer.parseInt(type[1]);
								break;
							case "DropMagazine":
								canmagazine = Boolean.parseBoolean(type[1]);
								break;
							case "SoundSpeed":
								soundspeed = Float.parseFloat(type[1]);
								break;
							case "Acceleration":
								acceleration = Float.parseFloat(type[1]);
								break;
							case "ReloadMat31":
								remat31 = Boolean.parseBoolean(type[1]);
								break;
							case "ReloadMotion":
								while ((str = br.readLine()) != null) {
									reloadanim = true;
									if (str.equals("{")) continue;
									if (str.equals("}")) break;
									if (str.isEmpty()) continue;
									String[] key = str.split(",");
									reloadanimation.add(Integer.parseInt(key[0]), new Float[]{
													Float.parseFloat(key[1]),

													Float.parseFloat(key[2]),
													Float.parseFloat(key[3]), Float.parseFloat(key[4]), Float.parseFloat(key[5]),
													Float.parseFloat(key[6]), Float.parseFloat(key[7]), Float.parseFloat(key[8]),

													Float.parseFloat(key[9]),

													Float.parseFloat(key[10]), Float.parseFloat(key[11]), Float.parseFloat(key[12]),
													Float.parseFloat(key[13]), Float.parseFloat(key[14]), Float.parseFloat(key[15]),

													Float.parseFloat(key[16]), Float.parseFloat(key[17]), Float.parseFloat(key[18]),
													Float.parseFloat(key[19]), Float.parseFloat(key[20]), Float.parseFloat(key[21]),

													Float.parseFloat(key[22]),
													Float.parseFloat(key[23]), Float.parseFloat(key[24]), Float.parseFloat(key[25]),
													Float.parseFloat(key[26]), Float.parseFloat(key[27]), Float.parseFloat(key[28]),
													Float.parseFloat(key[29]), Float.parseFloat(key[30]), Float.parseFloat(key[31])
											}
									);
								}
								break;
							case "setunderRestriction":
								attachRestriction = Boolean.parseBoolean(type[1]);
								break;
							case "attachRestriction":
								attachRestriction = Boolean.parseBoolean(type[1]);
								break;
							case "allowundername":
								for (int i = 1; i < type.length; i++)
									canset.add(type[i]);
								break;
							case "allowattach":
								for (int i = 1; i < type.length; i++)
									canset.add(type[i]);
								break;
							case "UseUnderGun'smodel":
								useundergunsmodel = Boolean.parseBoolean(type[1]);
								System.out.println("useundergunsmodel" + useundergunsmodel);
								break;
							case "UnderGunOffset":
								underoffsetpx = Float.parseFloat(type[1]);
								underoffsetpy = Float.parseFloat(type[2]);
								underoffsetpz = Float.parseFloat(type[3]);
								break;
							case "UnderGunRotation":
								underrotationx = Float.parseFloat(type[1]);
								underrotationy = Float.parseFloat(type[2]);
								underrotationz = Float.parseFloat(type[3]);
								break;
							case "OnUnderGunOffset":
								onunderoffsetpx = Float.parseFloat(type[1]);
								onunderoffsetpy = Float.parseFloat(type[2]);
								onunderoffsetpz = Float.parseFloat(type[3]);
								break;
							case "OnUnderGunRotation":
								onunderrotationx = Float.parseFloat(type[1]);
								onunderrotationy = Float.parseFloat(type[2]);
								onunderrotationz = Float.parseFloat(type[3]);
								break;
							case "GripSetPoint":
								for (int i = 0; i < 3; i++)
									gripattachoffset[i] = Float.parseFloat(type[i + 1]);
								break;
							case "GripSetAngle":
								for (int i = 0; i < 3; i++)
									gripattachrotation[i] = Float.parseFloat(type[i + 1]);
								break;
							case "ScopeTexture":
								ads = type[1];
								adsr = type[2];
								adss = type[3];
								break;
							case "RenderCross":
								rendercross = Boolean.parseBoolean(type[1]);
								break;
							case "UnderBarrelWeapon":
								firetype = Integer.parseInt(type[1]);
								break;
							case "RightType":
								righttype = Integer.parseInt(type[1]);
								break;
							case "Automatic":
								semi = Boolean.parseBoolean(type[1]);
								break;
							case "BPItemStack":
								itemstack = Integer.parseInt(type[1]);
								addi = new Item[itemstack];
								bptemnames = new String[itemstack];
								break;
							case "BPItems":
							/*
							 * for(int ii = 0; ii < itemstack; ++ii){ //addi =
							 * new Item[GameRegistry.findItem("HandmadeGuns",
							 * type[1+ ii])]; Item add =
							 * GameRegistry.findItem("HandmadeGuns", type[1+
							 * ii]); addit.add(add);
							 *
							 * }
							 */
								for (int ii = 0; ii < itemstack; ++ii) {
									addi[ii] = GameRegistry.findItem("HandmadeGuns", type[1 + ii]);
									bptemnames[ii] = type[1 + ii];
								}
								break;
							case "Magazine": {
								int ii = Integer.parseInt(type[1]);

								if (ii == 0) {
									magazine = GameRegistry.findItem(type[2], type[3]);
								} else {
									magazine = Item.getItemById(ii);
								}
								break;
							}
							case "MagazineSG": {
								int ii = Integer.parseInt(type[1]);

								if (ii == 0) {
									magazinesg = GameRegistry.findItem(type[2], type[3]);
								} else {
									magazinesg = Item.getItemById(ii);
								}

								break;
							}
							case "MagazineGL": {
								int ii = Integer.parseInt(type[1]);

								if (ii == 0) {
									magazinegl = GameRegistry.findItem(type[2], type[3]);
								} else {
									magazinegl = Item.getItemById(ii);
								}

								break;
							}
							case "Induction_precision":
								induction_precision = Float.parseFloat(type[1]);
								break;
							case "Name":
								Namegun = type[1];
								break;
							case "BulletNameALL":
								bulletmodelNameN =
										bulletmodelNameAR =
												bulletmodelNameAP =
														bulletmodelNameAT =
																bulletmodelNameFrag =
																		bulletmodelNameHE =
																				bulletmodelNameTE =
																						bulletmodelNameCart =
																								bulletmodelNameGL =
																										bulletmodelNameRPG = type[1];
								hasbmodel = true;
								break;
							case "BulletNameNormal":
								bulletmodelNameN = type[1];
								hasbmodel = true;
								break;
							case "BulletNameAR":
								bulletmodelNameAR = type[1];
								hasbmodel = true;
								break;
							case "BulletNameAP":
								bulletmodelNameAP = type[1];
								hasbmodel = true;
								break;
							case "BulletNameAT":
								bulletmodelNameAT = type[1];
								hasbmodel = true;
								break;
							case "BulletNameFrag":
								bulletmodelNameFrag = type[1];
								hasbmodel = true;
								break;
							case "BulletNameHE":
								bulletmodelNameHE = type[1];
								hasbmodel = true;
								break;
							case "BulletNameTE":
								bulletmodelNameTE = type[1];
								hasbmodel = true;
								break;
							case "BulletNameGL":
								bulletmodelNameGL = type[1];
								hasbmodel = true;
								break;
							case "BulletNameRPG":
								bulletmodelNameRPG = type[1];
								hasbmodel = true;
								break;
							case "BulletNameCart":
								bulletmodelNameCart = type[1];
								hascmodel = true;
								break;
							case "BulletNameMAG":
								bulletmodelNameMAG = type[1];
								hasmagmodel = true;
								break;
							case "KnockBack":
								knock = Double.parseDouble(type[1]);
								knocky = Double.parseDouble(type[2]);
								break;
							case "canBounce":
								canbounce = Boolean.parseBoolean(type[1]);
								break;
							case "BounceRate":
								bouncerate = Float.parseFloat(type[1]);
								break;
							case "BounceLimit":
								bouncelimit = Float.parseFloat(type[1]);
								break;
							case "bulletFuse":
								fuse = Integer.parseInt(type[1]);
								break;
							case "isOneuse":
								isOneuse = Boolean.parseBoolean(type[1]);
								break;
							case "MaxstackSize":
								maxstacksize = Integer.parseInt(type[1]);
								break;
							case "MuzzleFlash":
								muzzleflash = Boolean.parseBoolean(type[1]);
								break;
							case "MagazineItemCount":
								magazineCount = Integer.parseInt(type[1]);
								break;
							case "Script":
								isuserenderscript = true;
								renderscript = type[1];
								break;
							case "GunScript":
								isusegunscript = true;
								gunscript = type[1];
								break;
							case "armoffsetScale":
								armscale = Float.parseFloat(type[1]);
								break;
							case "guerrila_cant_use_this":
								g_can_use = false;
								break;
							case "Dont_Be_Inside_Root_Chest":
								can_be_Root = false;
								break;
							case "soldier_cant_storage_this":
								soldiercanstorage = false;
								break;
							case "Canlock":
								canlock = canlockEntity = Boolean.parseBoolean(type[1]);
								break;
							case "canlockEntity":
								canlockEntity = Boolean.parseBoolean(type[1]);
								break;
							case "CanlockBlock":
								canlockBlock = Boolean.parseBoolean(type[1]);
								break;
							case "guntype":
								guntype = Integer.parseInt(type[1]);
								break;
							case "Class":
								canuseclass = Integer.parseInt(type[1]);
								break;
							case "SightSetPoint":
								for (int i = 0; i < 3; i++)
									sightattachoffset[i] = Float.parseFloat(type[i + 1]);
								break;
							case "sightattachrotation":
								for (int i = 0; i < 3; i++)
									sightattachrotation[i] = Float.parseFloat(type[i + 1]);
								break;
							case "LightSetPoint":
								for (int i = 0; i < 3; i++)
									lightattachoffset[i] = Float.parseFloat(type[i + 1]);
								break;
							case "LightSetAngle":
								for (int i = 0; i < 3; i++)
									lightattachrotation[i] = Float.parseFloat(type[i + 1]);
								break;
							case "MuzzleSetPoint":
								for (int i = 0; i < 3; i++)
									barrelattachrotation[i] = Float.parseFloat(type[i + 1]);
								break;
							case "CustomFlash":
								flashname = type[1];
								flashfuse = Integer.parseInt(type[2]);
								if (type.length == 4) {
									flashScale = Float.parseFloat(type[3]);
								}
								break;
							case "Tabname":
								tabname = type[1];
								break;
							case "Canfix":
								canfix = Boolean.parseBoolean(type[1]);
								break;
							case "NeedFix":
								needfix = Boolean.parseBoolean(type[1]);
								break;
							case "FixAsEntity":
								fixAsEntity = Boolean.parseBoolean(type[1]);
								break;
							case "OnEntity_RotationYawPoint":
								turretRotationYawPoint = new double[]{Double.parseDouble(type[1]), Double.parseDouble(type[2]), Double.parseDouble(type[3])};
								break;
							case "OnEntity_RotationPitchPoint":
								turretRotationPitchPoint = new double[]{Double.parseDouble(type[1]), Double.parseDouble(type[2]), Double.parseDouble(type[3])};
								break;
							case "OnEntity_BarrelPoint":
								barrelpos = new double[]{Double.parseDouble(type[1]), Double.parseDouble(type[2]), Double.parseDouble(type[3])};
								break;
							case "OnEntity_Yoffset":
								yoffset = Float.parseFloat(type[1]);
								break;
							case "OnEntity_PlayerPosOffset":
								seatoffset = new double[]{Double.parseDouble(type[1]), Double.parseDouble(type[2]), Double.parseDouble(type[3])};
								break;
							case "restrictTurretMoveSpeed":
								restrictTurretMoveSpeed = true;
								turretMoveSpeedY = Float.parseFloat(type[1]);
								turretMoveSpeedP = Float.parseFloat(type[2]);
								break;
							case "Turretanglelimit":
								restrictTurretAngle = true;
								turretanglelimtMxP = Float.parseFloat(type[1]);
								turretanglelimtmnP = Float.parseFloat(type[2]);
								turretanglelimtMxY = Float.parseFloat(type[3]);
								turretanglelimtmnY = Float.parseFloat(type[4]);
								break;
							case "TurretBoxSize":
								boxW = Float.parseFloat(type[1]);
								boxH = Float.parseFloat(type[2]);
								break;
							case "OnTurretScale":
								onTurretScale = Float.parseFloat(type[1]);
								break;
							case "TurretHP":
								turretHP = Integer.parseInt(type[1]);
								break;
						}
						if(isClient) {
							switch (type[0]) {
								case "AddParts":
									currentIndex = Partslist.size();
									Partslist.add(currentParts = createGunPart(type));
									break;
								case "AddPartsRotationCenterAndRotationAmount":
									currentParts.AddRenderinfDef(Float.parseFloat(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]));
									break;
								case "AddPartsRotationDefOffset":
									currentParts.AddRenderinfDefoffset(Float.parseFloat(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]));
									break;
								case "RenderOnNormal":
									currentParts.rendering_Def = true;
									break;
								case "AddPartsOnADSOffsetAndRotation":
									currentParts.AddRenderinfADS(Float.parseFloat(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]));
									break;
								case "AddPartsOnRecoilOffsetAndRotation":
									currentParts.AddRenderinfRecoil(Float.parseFloat(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]));
									break;
								case "AddPartsOnCockOffsetAndRotation":
									currentParts.AddRenderinfCock(Float.parseFloat(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]));
									break;
								case "AddPartsOnReloadOffsetAndRotation":
									currentParts.AddRenderinfReload(Float.parseFloat(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]));
									break;
								case "AddPartsRenderAsBulletInf":
									currentParts.AddRenderinfBullet(Float.parseFloat(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]));
									break;
								case "AddRecoilMotionKey":
									if (type.length > 3) {
										currentParts.AddMotionKeyRecoil(Integer.parseInt(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]), Float.parseFloat(type[7]), Integer.parseInt(type[8]), Float.parseFloat(type[9]), Float.parseFloat(type[10]), Float.parseFloat(type[11]), Float.parseFloat(type[12]), Float.parseFloat(type[13]), Float.parseFloat(type[14]));
									} else {
										currentParts.AddMotionKeyRecoil(Integer.parseInt(type[1]), Boolean.parseBoolean(type[2]), Integer.parseInt(type[3]));
									}
									break;
								case "AddYawInfoKey":
									if (type.length > 3) {
										currentParts.AddInfoKeyTurretYaw(Integer.parseInt(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]), Float.parseFloat(type[7]), Integer.parseInt(type[8]), Float.parseFloat(type[9]), Float.parseFloat(type[10]), Float.parseFloat(type[11]), Float.parseFloat(type[12]), Float.parseFloat(type[13]), Float.parseFloat(type[14]));
									} else {
										currentParts.AddInfoKeyTurretYaw(Integer.parseInt(type[1]), Boolean.parseBoolean(type[2]), Integer.parseInt(type[3]));
									}
									break;
								case "AddPitchInfoKey":
									if (type.length > 3) {
										currentParts.AddInfoKeyTurretPitch(Integer.parseInt(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]), Float.parseFloat(type[7]), Integer.parseInt(type[8]), Float.parseFloat(type[9]), Float.parseFloat(type[10]), Float.parseFloat(type[11]), Float.parseFloat(type[12]), Float.parseFloat(type[13]), Float.parseFloat(type[14]));
									} else {
										currentParts.AddInfoKeyTurretPitch(Integer.parseInt(type[1]), Boolean.parseBoolean(type[2]), Integer.parseInt(type[3]));
									}
									break;
								case "AddCockMotionKey":
									if (type.length > 3) {
										currentParts.AddMotionKeyCock(Integer.parseInt(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]), Float.parseFloat(type[7]), Integer.parseInt(type[8]), Float.parseFloat(type[9]), Float.parseFloat(type[10]), Float.parseFloat(type[11]), Float.parseFloat(type[12]), Float.parseFloat(type[13]), Float.parseFloat(type[14]));
									} else {
										currentParts.AddMotionKeyCock(Integer.parseInt(type[1]), Boolean.parseBoolean(type[2]), Integer.parseInt(type[3]));
									}
									break;
								case "AddReloadMotionKey":
									if (type.length > 4) {
										currentParts.AddMotionKeyReload(Integer.parseInt(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]), Float.parseFloat(type[7]), Integer.parseInt(type[8]), Float.parseFloat(type[9]), Float.parseFloat(type[10]), Float.parseFloat(type[11]), Float.parseFloat(type[12]), Float.parseFloat(type[13]), Float.parseFloat(type[14]));
									} else {
										currentParts.AddMotionKeyReload(Integer.parseInt(type[1]), Boolean.parseBoolean(type[2]), Integer.parseInt(type[3]));
									}
									break;
								case "AddBulletPositions":
									currentParts.AddBulletPositions(Integer.parseInt(type[1]), Float.parseFloat(type[2]), Float.parseFloat(type[3]), Float.parseFloat(type[4]), Float.parseFloat(type[5]), Float.parseFloat(type[6]), Float.parseFloat(type[7]), Integer.parseInt(type[8]), Float.parseFloat(type[9]), Float.parseFloat(type[10]), Float.parseFloat(type[11]), Float.parseFloat(type[12]), Float.parseFloat(type[13]), Float.parseFloat(type[14]));
									break;
								case "Isbullet":
									currentParts.setIsbullet(Boolean.parseBoolean(type[1]), Integer.parseInt(type[2]));
									break;
								case "attachpart":
									currentParts.isattachpart = true;
									break;
								case "scope":
									currentParts.isscope = true;
									break;
								case "dot":
									currentParts.isdot = true;
									break;
								case "sight":
									currentParts.issight = true;
									break;
								case "grip":
									currentParts.isgrip = true;
									break;
								case "gripcover":
									currentParts.isgripcover = true;
									break;
								case "sword":
									currentParts.issword = true;
									break;
								case "swordbase":
									currentParts.isswordbase = true;
									break;
								case "underSG":
									currentParts.isunderSG = true;
									break;
								case "underGL":
									currentParts.isunderGL = true;
									break;
								case "muzzlepart":
									currentParts.ismuzzlepart = true;
									break;
								case "light":
									currentParts.islight = true;
									break;
								case "lasersight":
									currentParts.islasersight = true;
									break;
								case "gripBase":
									currentParts.isgripBase = true;
									break;
								case "underGunbase":
									currentParts.isunderGunbase = true;
									break;
								case "overbarrelbase":
									currentParts.isoverbarrelbase = true;
									break;
								case "muzzulebase":
									currentParts.ismuzzulebase = true;
									break;
								case "sightbase":
									currentParts.issightbase = true;
									break;
								case "turretbase":
									currentParts.base = true;
									break;
								case "carryingHandle":
									currentParts.carryingHandle = true;
									break;
								case "underOnly":
									currentParts.underOnly = true;
									break;
								case "underOnly_not":
									currentParts.underOnly_not = true;
									break;
								case "AddChildParts":
									currentIndex = currentParts.childs.size();
									currentParts.childs.add(currentParts = createGunPart(type, currentIndex, currentParts));
									break;
								case "BackParts":
									currentIndex = currentParts.motherIndex;
									currentParts = currentParts.mother;
									break;
							}
						}
						if(cfg_forceunifiedguns) {
							HMGItem_Unified_Guns newgun = null;
							switch (type[0]) {
								case "HG":
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName)
											.setTextureName("handmadeguns:" + texture).setMaxDamage(kazu)
									;
									newgun.setMaxStackSize(maxstacksize);
									if (guntype == -1)
										newgun.guntype = 0;//通常弾
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 0;//通常ゲリラ

									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", Namegun);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									Guns.add(newgun);
									break;
								case "AR":
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);

									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", Namegun);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									if (guntype == -1)
										newgun.guntype = 0;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 0;//通常ゲリラ

									if (burstcount.isEmpty()) {
										burstcount.add(-1);
									}
									Guns.add(newgun);
									break;
								case "SG":
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);

									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", Namegun);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									if (guntype == -1)
										newgun.guntype = 1;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 1;//ショットガンゲリラ
									Guns.add(newgun);
									break;
								case "SGF":
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);

									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "jp_JP", Namegun);
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									if (guntype == -1)
										newgun.guntype = 1;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 1;//ショットガンゲリラ
									if (burstcount.isEmpty()) {
										burstcount.add(-1);
									}
									Guns.add(newgun);
									break;
								case "SR":
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);

									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", Namegun);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									if (guntype == -1)
										newgun.guntype = 0;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 2;//スナイパーゲリラ
									Guns.add(newgun);
									break;
								case "AMR":
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);

									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", Namegun);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									if (guntype == -1)
										newgun.guntype = 4;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 2;//スナイパーゲリラ
									Guns.add(newgun);
									break;
								case "LMG":
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);

									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", Namegun);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									if (guntype == -1)
										newgun.guntype = 0;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 3;//機関銃ゲリラ
									if (burstcount.isEmpty()) {
										burstcount.add(-1);
									}
									Guns.add(newgun);
									break;
								case "RR":
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);

									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", Namegun);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									if (guntype == -1)
										newgun.guntype = 3;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 4;//対物ゲリラ
									Guns.add(newgun);
									break;
								case "GL":
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);

									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "jp_JP", Namegun);
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									if (guntype == -1)
										newgun.guntype = 2;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 4;//対物ゲリラ
									Guns.add(newgun);
									break;
								case "BOW":
									GunName = type[1];
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);


									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "jp_JP", Namegun);
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									newgun.chargeType = true;
									if (guntype == -1)
										newgun.guntype = 0;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 0;//使えるクラスは通常ゲリラ
									Guns.add(newgun);
									break;
								case "Unified_guns":
									GunName = type[1];
									newgun = new HMGItem_Unified_Guns();

									GunName = type[1];
									newgun.setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
											.setMaxDamage(kazu);


									if (Namegun != null) {
										LanguageRegistry.instance().addNameForObject(newgun, "jp_JP", Namegun);
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									} else {
										LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
									}
									newgun.guntype = guntype;
									if (canuseclass != -1) {
										newgun.canuseclass = canuseclass;
									} else newgun.canuseclass = 0;//使えるクラスは通常ゲリラ
									Guns.add(newgun);
									break;
							}
							if(newgun != null) {
								try {
									Item check = GameRegistry.findItem("HandmadeGuns",GunName);
									if(check == null)
										GameRegistry.registerItem(newgun, GunName);
									else if(check instanceof HMGItem_Unified_Guns){
										newgun = (HMGItem_Unified_Guns) check;
									}
									System.out.println("debug loading " + newgun.getUnlocalizedName());
								}catch (Exception e){
									System.out.println("Warning! Error!" + newgun.getUnlocalizedName());
									e.printStackTrace();
								}
								if (canobj && isClient) {
									IModelCustom gunobj = AdvancedModelLoader.loadModel(new ResourceLocation("handmadeguns:textures/model/" + objmodel));
									ResourceLocation guntexture = new ResourceLocation("handmadeguns:textures/model/" + objtexture);
									if(Partslist.isEmpty()) {
										HMGRenderItemGun_U hmgRenderItemGun_u = new HMGRenderItemGun_U(gunobj, guntexture,
												modelscala, modelhigh, modelhighr, modelhighs, modelwidthx, modelwidthxr, modelwidthxs, modelwidthz
												, modelwidthzr, modelwidthzs, rotationx, rotationxr, rotationxs, rotationy, rotationyr, rotationys
												, rotationz, rotationzr, rotationzs,
												arm, armrotationxr, armrotationyr, armrotationzr, armoffsetxr, armoffsetyr, armoffsetzr
												, armrotationxl, armrotationyl, armrotationzl, armoffsetxl, armoffsetyl, armoffsetzl,
												nox, noy, noz, mat31posx, mat31posy, mat31posz, mat31rotex, mat31rotey, mat31rotez
												, mat32posx, mat32posy, mat32posz, mat32rotex, mat32rotey, mat32rotez, armscale);
										hmgRenderItemGun_u.barrelattachoffset = barrelattachoffset;
										hmgRenderItemGun_u.barrelattachrotation = barrelattachrotation;
										hmgRenderItemGun_u.sightattachoffset = sightattachoffset;
										hmgRenderItemGun_u.sightattachrotation = sightattachrotation;
										hmgRenderItemGun_u.lightattachoffset = lightattachoffset;
										hmgRenderItemGun_u.lightattachrotation = lightattachrotation;
										hmgRenderItemGun_u.gripattachoffset = gripattachoffset;
										hmgRenderItemGun_u.gripattachrotation = gripattachrotation;
										MinecraftForgeClient.registerItemRenderer(newgun, hmgRenderItemGun_u);
									}else {

										HMGRenderItemGun_U_NEW renderItemGun_u_new = new HMGRenderItemGun_U_NEW(gunobj,guntexture,modelscala);
										renderItemGun_u_new.setEqippedOffset_Normal(nox,noy,noz);
										renderItemGun_u_new.setEqippedOffset_Third(thirdGunOffset[0],thirdGunOffset[1],thirdGunOffset[2]);
										renderItemGun_u_new.setEqippedRotation_Normal(eqrotax,eqrotay,eqrotaz);

										renderItemGun_u_new.setmodelADSPosAndRotation(modelwidthx,modelhigh,modelwidthz,rotationx,rotationy,rotationz);
										renderItemGun_u_new.setADSoffsetRed(modelwidthxr,modelhighr,modelwidthzr);
										renderItemGun_u_new.setADSoffsetScope(modelwidthxs,modelhighs,modelwidthzs);

										renderItemGun_u_new.setADSrotationRed(rotationxr,rotationyr,rotationzr);
										renderItemGun_u_new.setADSrotationScope(rotationxs,rotationys,rotationzs);

										renderItemGun_u_new.setarmOffsetAndRotationL(armoffsetxl,armoffsetyl,armoffsetzl,armrotationxl,armrotationyl,armrotationzl);
										renderItemGun_u_new.setarmOffsetAndRotationR(armoffsetxr,armoffsetyr,armoffsetzr,armrotationxr,armrotationyr,armrotationzr);
										renderItemGun_u_new.setArmoffsetScale(armscale);

										renderItemGun_u_new.Partslist = Partslist;
										renderItemGun_u_new.muzzleattachoffset = barrelattachoffset;
										renderItemGun_u_new.muzzleattachrotation = barrelattachrotation;
										renderItemGun_u_new.sightattachoffset = sightattachoffset;
										renderItemGun_u_new.sightattachrotation = sightattachrotation;
										renderItemGun_u_new.overbarrelattachoffset = lightattachoffset;
										renderItemGun_u_new.overbarrelattachrotation = lightattachrotation;
										renderItemGun_u_new.gripattachoffset = gripattachoffset;
										renderItemGun_u_new.gripattachrotation = gripattachrotation;
										MinecraftForgeClient.registerItemRenderer(newgun, renderItemGun_u_new);
									}
								}

								newgun.posGetter.turretRotationYawPoint = turretRotationYawPoint;
								newgun.posGetter.turretRotationPitchPoint = turretRotationPitchPoint;
								newgun.posGetter.barrelpos = barrelpos;
								newgun.yoffset = yoffset;
								newgun.sightattachoffset = sightattachoffset;
								newgun.modelscale = modelscala;
								newgun.inworldScale = inworldScale;
								newgun.setMaxStackSize(maxstacksize);
								newgun.attackDamage = bayonet;

								newgun.setmodelADSPosAndRotation(modelwidthx + seatoffset[0], modelhigh + seatoffset[1], modelwidthz + seatoffset[2]);
								newgun.setADSoffsetRed(modelwidthxr + seatoffset[0], modelhighr + seatoffset[1], modelwidthzr + seatoffset[2]);
								newgun.setADSoffsetScope(modelwidthxs + seatoffset[0], modelhighs + seatoffset[1], modelwidthzs + seatoffset[2]);
								// newgun.retime =
								newgun.powor = power;
								newgun.speed = speed;
								newgun.canlock = canlock;
								newgun.canlockEntity = canlockEntity;
								newgun.canlockBlock = canlockBlock;
								newgun.acceleration = acceleration;
								newgun.spread_setting = bure;
								newgun.recoil = recoil;
								newgun.recoil_sneak = recoil / 2;
								newgun.reloadtime = retime;
								newgun.scopezoom =
										newgun.scopezoombase = zoom;
								newgun.scopezoomred = zoomred;
								newgun.scopezoomscope = zoomscope;
								newgun.soundbase = "handmadeguns:" + sound;
								newgun.soundsu = "handmadeguns:" + soundsu;
								newgun.soundre = "handmadeguns:" + soundre;
								newgun.soundco = "handmadeguns:" + soundco;
								newgun.cycle = cycle;
								newgun.rendercross = rendercross;
								newgun.adstexture =
										"handmadeguns:textures/misc/" + ads;
								newgun.adstexturer = "handmadeguns:textures/misc/" + adsr;
								newgun.adstextures =
										"handmadeguns:textures/misc/" + adss;
								newgun.magazine = magazine;
								newgun.canobj = canobj;
								newgun.setFull3D();
								newgun.recoil_sneak = recoilwhensneak;
								newgun.ex = ex;
								newgun.canex = canex;
								newgun.canbounce = canbounce;
								newgun.bouncerate = bouncerate;
								newgun.bouncelimit = bouncelimit;
								newgun.fuse = fuse;
								newgun.zoomren = zoomrendern;
								newgun.zoomrer = zoomrenderr;
								newgun.zoomres = zoomrenders;

								newgun.zoomrent = zoomrendernt;
								newgun.zoomrert = zoomrenderrt;
								newgun.zoomrest = zoomrenderst;
								newgun.dropcart = cartridge;
								newgun.cart_cocked = cartridgedroponcocked;
								newgun.motion = motionn;

								newgun.mat22 = mat22;
								newgun.mat22offsetx = mat22posx;
								newgun.mat22offsety = mat22posy;
								newgun.mat22offsetz = mat22posz;
								newgun.mat22rotationx = mat22rotex;
								newgun.mat22rotationy = mat22rotey;
								newgun.mat22rotationz = mat22rotez;
								newgun.mat25offsetx = mat25posx;
								newgun.mat25offsety = mat25posy;
								newgun.mat25offsetz = mat25posz;
								newgun.mat25rotationx = mat25rotex;
								newgun.mat25rotationy = mat25rotey;
								newgun.mat25rotationz = mat25rotez;
								newgun.cocktime = cockti;
								newgun.needcock = needcock;
								newgun.Sprintoffsetx = spposx;
								newgun.Sprintoffsety = spposy;
								newgun.Sprintoffsetz = spposz;
								newgun.Sprintrotationx = sprotex;
								newgun.Sprintrotationy = sprotey;
								newgun.Sprintrotationz = sprotez;

								//01/27
								newgun.jump = jump;
								newgun.mat2 = mat2;
								newgun.mat25 = mat25;
								newgun.cock_left = cockleft;
								newgun.all_jump = alljump;
								newgun.gra = gra;

								//0214
								newgun.cartType = cartridgetype;
								newgun.magType = magtype;
								newgun.dropMagEntity = canmagazine;
								newgun.magentityCnt = magcnt;
								newgun.cartentityCnt = cartcnt;
								newgun.soundspeed = soundspeed;
								newgun.remat31 = remat31;
								newgun.reloadanim = reloadanim;
								newgun.reloadanimation = reloadanimation;
								newgun.hascustombulletmodel = hasbmodel;
								newgun.hascustomcartridgemodel = hascmodel;
								newgun.hascustommagemodel = hasmagmodel;
								newgun.bulletmodelN = bulletmodelNameN;
								newgun.bulletmodelAR = bulletmodelNameAR;
								newgun.bulletmodelAP = bulletmodelNameAP;
								newgun.bulletmodelAT = bulletmodelNameAT;
								newgun.bulletmodelFrag = bulletmodelNameFrag;
								newgun.bulletmodelHE = bulletmodelNameHE;
								newgun.bulletmodelTE = bulletmodelNameTE;
								newgun.bulletmodelCart = bulletmodelNameCart;
								newgun.bulletmodelGL = bulletmodelNameGL;
								newgun.bulletmodelRPG = bulletmodelNameRPG;
								newgun.bulletmodelMAG = bulletmodelNameMAG;
								newgun.hasAttachRestriction = attachRestriction;
								newgun.attachwhitelist = canset;
								newgun.useundergunsmodel = useundergunsmodel;
								newgun.underoffsetpx = underoffsetpx;
								newgun.underoffsetpy = underoffsetpy;
								newgun.underoffsetpz = underoffsetpz;
								newgun.underrotationx = underrotationx;
								newgun.underrotationy = underrotationy;
								newgun.underrotationz = underrotationz;

								newgun.onunderoffsetpx  = onunderoffsetpx;
								newgun.onunderoffsetpy  = onunderoffsetpy;
								newgun.onunderoffsetpz  = onunderoffsetpz;
								newgun.onunderrotationx = onunderrotationx;
								newgun.onunderrotationy = onunderrotationy;
								newgun.onunderrotationz = onunderrotationz;

								newgun.soundbaselevel = soundbaselevel;
								newgun.soundsuplevel = soundsuplevel;
								newgun.soundrelevel = soundrelv;

								newgun.knockback = knock;
								newgun.knockbackY = knocky;
								newgun.shotgun_pellet = pellet;
								newgun.soundunder_gl = "handmadeguns:" + soundunder_gl;
								newgun.under_gl_power = under_gl_power;
								newgun.under_gl_canbounce = under_gl_canbounce;
								newgun.under_gl_fuse = under_gl_fuse;
								newgun.under_gl_speed = under_gl_speed;
								newgun.under_gl_bure = under_gl_bure;
								newgun.under_gl_recoil = under_gl_recoil;
								newgun.under_gl_gra = under_gl_gra;
								newgun.soundunder_sg = "handmadeguns:" + soundunder_sg;
								newgun.under_sg_power = under_sg_power;
								newgun.under_sg_speed = under_sg_speed;
								newgun.under_sg_bure = under_sg_bure;
								newgun.under_sg_recoil = under_sg_recoil;
								newgun.under_sg_gra = under_sg_gra;
								if (!burstcount.isEmpty())
									newgun.burstcount.addAll(burstcount);
								else
									newgun.burstcount.add(1);
								if (!rates.isEmpty())
									newgun.rates.addAll(rates);
								else
									newgun.rates.add(cycle);
								newgun.mat31rotex = mat31rotex;
								newgun.mat31rotey = mat31rotey;
								newgun.mat31rotez = mat31rotez;
								newgun.isOneuse = isOneuse;
								newgun.muzzleflash = muzzleflash;
								newgun.setMaxStackSize(maxstacksize);
								newgun.magazineItemCount = magazineCount;
								newgun.ads_spread_cof = spread_cof;
								if(guntype != -1)
									newgun.guntype = guntype;
								if (isuserenderscript){
									newgun.userenderscript = true;
									try {
										FileReader sc = new FileReader(new File(proxy.ProxyFile(), "mods" + File.separatorChar + "handmadeguns"
												+ File.separatorChar + "assets" + File.separatorChar + "handmadeguns" + File.separatorChar +
												"scripts" + File.separatorChar + renderscript)); // ファイルを開く
										newgun.renderscript = doScript(sc);
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
								} else {
									newgun.userenderscript = false;
								}
								if (isusegunscript) {
									try {
										FileReader sc = new FileReader(new File(proxy.ProxyFile(), "mods" + File.separatorChar + "handmadeguns"
												+ File.separatorChar + "assets" + File.separatorChar + "handmadeguns" + File.separatorChar +
												"scripts" + File.separatorChar + gunscript)); // ファイルを開く
										newgun.script = doScript(sc);
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
								}
								newgun.cycle = cycle;
								newgun.guerrila_can_use = g_can_use;
								newgun.isinRoot = can_be_Root;
								newgun.soldiercanstorage = soldiercanstorage;
								newgun.use_internal_secondary = useinternalsettings;
								newgun.induction_precision = induction_precision;
								newgun.flashname = flashname;
								newgun.flashfuse = flashfuse;
								newgun.flashScale = flashScale;
								newgun.needfix = needfix;
								newgun.fixAsEntity = fixAsEntity;
								newgun.canfix = canfix;
								newgun.hasNightVision = hasNightVision;
								newgun.restrictTurretMoveSpeed = restrictTurretMoveSpeed;
								newgun.turretMoveSpeedP = turretMoveSpeedP;
								newgun.turretMoveSpeedY = turretMoveSpeedY;
								newgun.restrictTurretAngle = restrictTurretAngle;
								newgun.turretanglelimtMxP = turretanglelimtMxP;
								newgun.turretanglelimtMxY = turretanglelimtMxY;
								newgun.turretanglelimtmnP = turretanglelimtmnP;
								newgun.turretanglelimtmnY = turretanglelimtmnY;
								newgun.turreboxW = boxW;
								newgun.turreboxH = boxH;
								newgun.onTurretScale = onTurretScale;
								newgun.turretMaxHP = turretHP;
								if(tabname == null) newgun.setCreativeTab(HandmadeGunsCore.tabhmg);
								else if(tabshmg.containsKey(tabname)){
									newgun.setCreativeTab(tabshmg.get(tabname));
								}
							}
						}
						if (type[0].equals("SWORD")) {
							GunName = type[1];
							Item newgun = new HMGXItemGun_Sword(power, speed, bure, recoil, retime, bayonet, zoom,
									"handmadeguns:" + sound, "handmadeguns:" + soundre, rendercross, righttype).setUnlocalizedName(GunName).setTextureName("handmadeguns:" + texture)
									.setMaxDamage(kazu).setCreativeTab(HandmadeGunsCore.tabhmg);

							Item check = GameRegistry.findItem("HandmadeGuns",GunName);
							if(check == null)
								GameRegistry.registerItem(newgun, GunName);
							else if(check instanceof HMGXItemGun_Sword){
								newgun = (HMGXItemGun_Sword) check;
							}
							System.out.println("debug" + newgun.getUnlocalizedName());

							if(Namegun != null){
								LanguageRegistry.instance().addNameForObject(newgun, "en_US", Namegun);
							}else{
								LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
							}

							if (canobj && isClient) {
								ResourceLocation guntexture = new ResourceLocation("handmadeguns:textures/model/" + objtexture);
								IModelCustom gunobj = AdvancedModelLoader
										.loadModel(new ResourceLocation("handmadeguns:textures/model/" + objmodel));
								MinecraftForgeClient.registerItemRenderer(newgun, new HMGRenderItemGun_S(gunobj,guntexture,
										modelscala, modelhigh, modelhighr, modelhighs, modelwidthx, modelwidthxr, modelwidthxs, modelwidthz
										, modelwidthzr, modelwidthzs, rotationx, rotationxr, rotationxs, rotationy, rotationyr, rotationys
										, rotationz, rotationzr, rotationzs,
										arm, armrotationxr, armrotationyr, armrotationzr, armoffsetxr, armoffsetyr, armoffsetzr
										, armrotationxl, armrotationyl, armrotationzl, armoffsetxl, armoffsetyl, armoffsetzl,
										nox, noy, noz, mat31posx, mat31posy, mat31posz, 	mat31rotex, mat31rotey, mat31rotez
										,mat32posx, mat32posy, mat32posz, mat32rotex, mat32rotey, mat32rotez));
							}

							Guns.add(newgun);
						} else if (type[0].equals("BP")) {
							GunName = type[1];
							/*
							 * for(int ii = 0; ii < itemstack; ++ii){ //addi =
							 * new Item[GameRegistry.findItem("HandmadeGuns",
							 * type[1+ ii])]; Item add =
							 * GameRegistry.findItem("HandmadeGuns", type[2+
							 * ii]); addit.add(add);
							 *
							 * }
							 */

							Item newgun = new HMGItemBattlePack(addi,bptemnames, itemstack).setUnlocalizedName(GunName)
									.setTextureName("handmadeguns:" + texture).setCreativeTab(HandmadeGunsCore.tabhmg);
							Item check = GameRegistry.findItem("HandmadeGuns",GunName);
							if(check == null)
								GameRegistry.registerItem(newgun, GunName);

							if(Namegun != null){
								LanguageRegistry.instance().addNameForObject(newgun, "en_US", Namegun);
							}else{
								LanguageRegistry.instance().addNameForObject(newgun, "en_US", GunName);
							}

							Guns.add(newgun);
							/*
							 * for(int ii = 0; ii < itemstack; ++ii){ //addi =
							 * new Item[GameRegistry.findItem("HandmadeGuns",
							 * type[1+ ii])]; Item add =
							 * GameRegistry.findItem("HandmadeGuns", type[2+
							 * ii]); addit.remove(add);
							 *
							 * }
							 */
						}
						if (type[0].equals("addRecipe")) {
							Item additem = GameRegistry.findItem("HandmadeGuns", type[1]);
							if(additem != null) {
								int kazu1 = Integer.parseInt(type[2]);
								re1 = type[3];
								re2 = type[4];
								re3 = type[5];
								int ia = Integer.parseInt(type[6]);
								int ib = Integer.parseInt(type[7]);
								int ic = Integer.parseInt(type[8]);
								int id = Integer.parseInt(type[9]);
								int ie = Integer.parseInt(type[10]);
								int ief = Integer.parseInt(type[11]);
								int ig = Integer.parseInt(type[12]);
								int ih = Integer.parseInt(type[13]);
								int ii = Integer.parseInt(type[14]);

								itema = Item.getItemById(ia);
								itemb = Item.getItemById(ib);
								itemc = Item.getItemById(ic);
								itemd = Item.getItemById(id);
								iteme = Item.getItemById(ie);
								itemf = Item.getItemById(ief);
								itemg = Item.getItemById(ig);
								itemh = Item.getItemById(ih);
								itemi = Item.getItemById(ii);

								GameRegistry.addRecipe(new ItemStack(additem, kazu1), re1, re2, re3,
										'a', itema, 'b', itemb,
										'c', itemc, 'd', itemd,
										'e', iteme, 'f', itemf,
										'g', itemg, 'h', itemh,
										'i', itemi);
							}
						} else if (type[0].equals("addSmelting")) {
							Item additem = GameRegistry.findItem("HandmadeGuns", type[1]);
							if(additem != null) {
								float xp = Float.parseFloat(type[2]);

								int ia = Integer.parseInt(type[3]);
								itema = Item.getItemById(ia);

								if(itema != null && additem != null)
								GameRegistry.addSmelting(itema, new ItemStack(additem), xp);
							}
						} else if (type[0].equals("addSmelting2")) {
							Item additem = GameRegistry.findItem(type[1], type[2]);
							if(additem != null) {
								float xp = Float.parseFloat(type[5]);
								itema = GameRegistry.findItem(type[3], type[4]);

								if(itema != null && additem != null)
								GameRegistry.addSmelting(itema, new ItemStack(additem), xp);
							}
						}

						if(type[0].equals("Recipe1")){
							re1 = type[1];
						}
						if(type[0].equals("Recipe2")){
							re2 = type[1];
						}
						if(type[0].equals("Recipe3")){
							re3 = type[1];
						}
						if(type[0].equals("ItemA") && !type[1].equals("null")){
							itema = GameRegistry.findItem(type[1], type[2]);
							if(itema == null){
								itemblocka = new ItemStack(GameRegistry.findBlock(type[1], type[2]));
							}
						}
						if(type[0].equals("ItemB") && !type[1].equals("null")){
							itemb = GameRegistry.findItem(type[1], type[2]);
							if(itemb == null){
								itemblockb = new ItemStack(GameRegistry.findBlock(type[1], type[2]));
							}
						}
						if(type[0].equals("ItemC") && !type[1].equals("null")){
							itemc = GameRegistry.findItem(type[1], type[2]);
							if(itemc == null){
								itemblockc = new ItemStack(GameRegistry.findBlock(type[1], type[2]));
							}
						}
						if(type[0].equals("ItemD") && !type[1].equals("null")){
							itemd = GameRegistry.findItem(type[1], type[2]);
							if(itemd == null){
								itemblockd = new ItemStack(GameRegistry.findBlock(type[1], type[2]));
							}
						}
						if(type[0].equals("ItemE") && !type[1].equals("null")){
							iteme = GameRegistry.findItem(type[1], type[2]);
							if(iteme == null){
								itemblocke = new ItemStack(GameRegistry.findBlock(type[1], type[2]));
							}
						}
						if(type[0].equals("ItemF") && !type[1].equals("null")){
							itemf = GameRegistry.findItem(type[1], type[2]);
							if(itemf == null){
								itemblockf = new ItemStack(GameRegistry.findBlock(type[1], type[2]));
							}
						}
						if(type[0].equals("ItemG") && !type[1].equals("null")){
							itemg = GameRegistry.findItem(type[1], type[2]);
							if(itemg == null){
								itemblockg = new ItemStack(GameRegistry.findBlock(type[1], type[2]));
							}
						}
						if(type[0].equals("ItemH") && !type[1].equals("null")){
							itemh = GameRegistry.findItem(type[1], type[2]);
							if(itemh == null){
								itemblockh = new ItemStack(GameRegistry.findBlock(type[1], type[2]));
							}
						}
						if(type[0].equals("ItemI") && !type[1].equals("null")){
							itemi = GameRegistry.findItem(type[1], type[2]);
							if(itemi == null){
								itemblocki = new ItemStack(GameRegistry.findBlock(type[1], type[2]));
							}
						}
						if(type[0].equals("addNewRecipe")){
							Item additem = GameRegistry.findItem(type[1], type[2]);
							if(additem != null) {
								int kazu1 = Integer.parseInt(type[3]);
								try {
									GameRegistry.addRecipe(new ItemStack(additem, kazu1),
											re1,
											re2,
											re3,
											'a', itema,
											'b', itemb,
											'c', itemc,
											'd', itemd,
											'e', iteme,
											'f', itemf,
											'g', itemg,
											'h', itemh,
											'i', itemi
									);
									itema = itemb = itemc = itemd = iteme = itemf = itemg = itemh = itemi = null;
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							re1 = "   ";
							re2 = "   ";
							re3 = "   ";
						}


					} // 1

				}
				br.close(); // ファイルを閉じる
			} else {

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//TODO:INJECTING
	public static void addRecipe(File file1){
		boolean[] onslot = {false,false,false,false,false,false,false,false,false};
		Item[] items = new Item[9];
		ItemStack[] itemstacks = new ItemStack[9];
		String[] itemids;
		byte isfixationrecipe = 0;
		List<Object> itemlist = new ArrayList<Object>(Arrays.asList(new Object[9]));

		try{
			File file = file1;
			if(checkBeforeReadfile(file)){
				BufferedReader br = new BufferedReader(new FileReader(file));

				String str;
				while ((str = br.readLine()) != null){
					String[] type = str.split(",");
					int index;
					if(type[0] == null){
					}else switch(type[0].toUpperCase()){
						case "ADDRECIPE":
							isfixationrecipe = 0;
							break;
						case "ADDSMALLRECIPE":
							isfixationrecipe = 1;
							break;
						case "ADDSHAPELESSRECIPE":
							isfixationrecipe = 2;
							break;
						case "SLOT1":
							setSlot(0, type, onslot, items, itemstacks);
							break;
						case "SLOT2":
							setSlot(1, type, onslot, items, itemstacks);
							break;
						case "SLOT3":
							setSlot(2, type, onslot, items, itemstacks);
							break;
						case "SLOT4":
							setSlot(3, type, onslot, items, itemstacks);
							break;
						case "SLOT5":
							setSlot(4, type, onslot, items, itemstacks);
							break;
						case "SLOT6":
							setSlot(5, type, onslot, items, itemstacks);
							break;
						case "SLOT7":
							setSlot(6, type, onslot, items, itemstacks);
							break;
						case "SLOT8":
							setSlot(7, type, onslot, items, itemstacks);
							break;
						case "SLOT9":
							setSlot(8, type, onslot, items, itemstacks);
							break;
						case "CRAFTITEM":
							itemids = type[1].split(":");
							itemlist = new ArrayList<Object>(Arrays.asList(new Object[9]));
							char[] rf = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
							for(int var0 = 0; var0 < 9; var0++){
								if(items[var0] != null){
									itemlist.set(var0, items[var0]);
								}else{
									itemlist.set(var0, itemstacks[var0]);
								}
								if(!onslot[var0]){
									rf[var0] = ' ';
								}
							}

							System.out.println("AddRecipe----------------------------------------------------------------------------------------------------------------------------");
							for(int count = 0; count < itemlist.size() ; count++){
								System.out.println("Item [" + count + "] : " + (Object)itemlist.get(count));
							}
							switch(isfixationrecipe){
								case 0:
									System.out.println("Recipe1 : " + String.valueOf(new char[]{rf[0], rf[1], rf[2]}));
									System.out.println("Recipe2 : " + String.valueOf(new char[]{rf[3], rf[4], rf[5]}));
									System.out.println("Recipe3 : " + String.valueOf(new char[]{rf[6], rf[7], rf[8]}));
									break;
								case 1:
									System.out.println("Recipe1 : " + String.valueOf(new char[]{rf[0], rf[1]}));
									System.out.println("Recipe2 : " + String.valueOf(new char[]{rf[2], rf[3]}));
									break;
							}

							System.out.println(new ItemStack(GameRegistry.findItem(itemids[0], itemids[1]), Integer.parseInt(itemids[3]), Integer.parseInt(itemids[2])));

							if(isfixationrecipe == 0){
								try{
									GameRegistry.addRecipe(
											new ItemStack(GameRegistry.findItem(itemids[0], itemids[1]), Integer.parseInt(itemids[3]), Integer.parseInt(itemids[2])),
											String.valueOf(new char[]{rf[0], rf[1], rf[2]}),
											String.valueOf(new char[]{rf[3], rf[4], rf[5]}),
											String.valueOf(new char[]{rf[6], rf[7], rf[8]}),
											'a',itemlist.get(0),
											'b',itemlist.get(1),
											'c',itemlist.get(2),
											'd',itemlist.get(3),
											'e',itemlist.get(4),
											'f',itemlist.get(5),
											'g',itemlist.get(6),
											'h',itemlist.get(7),
											'i',itemlist.get(8)
									);
								}catch(Exception e){
									e.printStackTrace();
								}
							}else if(isfixationrecipe == 1){
								try{
									GameRegistry.addRecipe(
											new ItemStack(GameRegistry.findItem(itemids[0], itemids[1]), Integer.parseInt(itemids[3]), Integer.parseInt(itemids[2])),
											String.valueOf(new char[]{rf[0], rf[1]}),
											String.valueOf(new char[]{rf[2], rf[3]}),
											'a',itemlist.get(0),
											'b',itemlist.get(1),
											'c',itemlist.get(2),
											'd',itemlist.get(3)
									);
								}catch(Exception e){
									e.printStackTrace();
								}
							}else if(isfixationrecipe == 2){
								List<Object> recipeitems = new ArrayList<Object>(9);
								for(int var0 = 0 ; var0 < itemlist.size() ; var0++){
									Object object = itemlist.get(var0);
									if(object != null){
										recipeitems.add(object);
									}
								}
								try{
									GameRegistry.addShapelessRecipe(
											new ItemStack(GameRegistry.findItem(itemids[0], itemids[1]), Integer.parseInt(itemids[3]), Integer.parseInt(itemids[2])),
											recipeitems.toArray(new Object[recipeitems.size()])
									);
								}catch(Exception e){
									e.printStackTrace();
								}
							}
							onslot[0]=onslot[1]=onslot[2]=onslot[3]=onslot[4]=onslot[5]=onslot[6]=onslot[7]=onslot[8]=false;
							items = new Item[9];
							itemstacks = new ItemStack[9];
							itemlist = new ArrayList<Object>(Arrays.asList(new Object[9]));
							break;
					}
				}
				br.close();
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void setSlot(int index,String[] type, boolean[] onslot ,Item[] items, ItemStack[] itemstacks){
		if(type[1] != null){
			System.out.println("debug Recipe Item " + type[1]);
			String[] itemids = type[1].split(":");
			if(itemids.length == 2){
				System.out.println("debug Recipe modId " + itemids[0]);
				items[index] = GameRegistry.findItem(itemids[0], itemids[1]);
				itemstacks[index] = null;
				if(items[index] == null){
					System.out.println("debug : BlockMode");
					itemstacks[index] = new ItemStack(GameRegistry.findBlock(itemids[0], itemids[1]));
				}
			}
			if(itemids.length == 3){
				itemstacks[index] = new ItemStack(GameRegistry.findItem(itemids[0], itemids[1]), 1, Integer.parseInt(itemids[2]));
				if(itemstacks[index] == null){
					itemstacks[index] = new ItemStack(GameRegistry.findBlock(itemids[0], itemids[1]), 1, Integer.parseInt(itemids[2]));
				}
				items[index] = null;
			}
			onslot[index] = true;
		}
	}
	//INJECTEND


	private static HMGGunParts createGunPart(String[] strings){
		HMGGunParts part = new HMGGunParts(strings[1]);
		part.isfounder = true;
		return part;
	}
	private static HMGGunParts createGunPart(String[] strings,int motherID,HMGGunParts mother){
		HMGGunParts part = new HMGGunParts(strings[1]);
		part.mother = mother;
		part.motherIndex = motherID;
		return part;
	}
	private static boolean checkBeforeReadfile(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canRead()) {
				return true;
			}
		}

		return false;
	}
	public static ScriptEngine doScript(ResourceLocation resource)
	{
		try
		{
			String script = getText(resource, true);
			return doScript(script);
		}
		catch(IOException e)
		{
			throw new RuntimeException("Script load error : " + resource.getResourcePath(), e);
		}
	}
	private static String getText(ResourceLocation resource, boolean indention) throws IOException
	{
		List<String> list = readText(resource);
		StringBuilder sb = new StringBuilder();
		for(String s : list)
		{
			sb.append(s);
			if(indention)
			{
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	private static List<String> readText(ResourceLocation resource) throws IOException
	{
		List<String> list = new ArrayList<String>();
		InputStream is = getInputStream(resource);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String currentLine = null;

		while((currentLine = reader.readLine()) != null)
		{
			list.add(currentLine);
		}

		is.close();
		reader.close();

		return list;
	}
	private static InputStream getInputStream(ResourceLocation par1) throws IOException
	{
		return Minecraft.getMinecraft().getResourceManager().getResource(par1).getInputStream();
	}
	private static ScriptEngine doScript(String s)
	{
		ScriptEngine se = (new ScriptEngineManager(null)).getEngineByName("js");//引数にnull入れないと20でぬるぽ

		try
		{
			if(se.toString().contains("Nashorn"))
			{
				//Java8ではimportPackage()が使えないので、その対策
				se.eval("load(\"nashorn:mozilla_compat.js\");");
			}

			//se.put("packreg", REGISTER);

			se.eval(s);
			return se;
		}
		catch(ScriptException e)
		{
			throw new RuntimeException("Script exec error", e);
		}
	}
	private static ScriptEngine doScript(FileReader s)
	{
		ScriptEngine se = (new ScriptEngineManager(null)).getEngineByName("js");//引数にnull入れないと20でぬるぽ

		try
		{
			if(se.toString().contains("Nashorn"))
			{
				//Java8ではimportPackage()が使えないので、その対策
				se.eval("load(\"nashorn:mozilla_compat.js\");");
			}

			//se.put("packreg", REGISTER);

			se.eval(s);
			return se;
		}
		catch(ScriptException e)
		{
			throw new RuntimeException("Script exec error", e);
		}
	}
}
