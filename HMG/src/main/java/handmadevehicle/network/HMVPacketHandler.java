package handmadevehicle.network;
 
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import handmadevehicle.network.handles.*;
import handmadevehicle.network.packets.*;


public class HMVPacketHandler {
 
    //このMOD用のSimpleNetworkWrapperを生成。チャンネルの文字列は固有であれば何でも良い。MODIDの利用を推奨。
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("HMVehicle");
 
 
    public static void init() {
        int id=0;
        INSTANCE.registerMessage(HMVMMessageKeyPressedHandler.class, HMVMMessageKeyPressed.class, ++id, Side.SERVER);
        INSTANCE.registerMessage(HMVMHandleVehicleState.class, HMVPakcetVehicleState.class, ++id, Side.CLIENT);
//        INSTANCE.registerMessage(HMVHandleSeatData.class, HMVPacketSeatData.class, ++id, Side.CLIENT);
//        INSTANCE.registerMessage(HMVHandleSeatData.class, HMVPacketSeatData.class, ++id, Side.SERVER);
        INSTANCE.registerMessage(HMVMHandleVehicleTurretSync.class, HMVPakcetVehicleTurretSync.class, ++id, Side.CLIENT);
        INSTANCE.registerMessage(HMVHandlePickNewEntity.class, HMVPacketPickNewEntity.class, ++id, Side.CLIENT);
        INSTANCE.registerMessage(HMVHandleDisMountEntity.class, HMVPacketDisMountEntity.class, ++id, Side.SERVER);
        INSTANCE.registerMessage(HMVHandleMouseD.class, HMVPacketMouseD.class, ++id, Side.SERVER);
        INSTANCE.registerMessage(HMVHandleTriggerSeatGun.class, HMVPacketTriggerSeatGun.class, ++id, Side.SERVER);
        INSTANCE.registerMessage(HMVHandleChangeSeat.class, HMVPacketChangeSeat.class, ++id, Side.SERVER);
        INSTANCE.registerMessage(HandleOpenVehicleGui.class, HMVPacketOpenVehicleGui.class, ++id, Side.SERVER);
        INSTANCE.registerMessage(HMVHandleMissileAndLockMarker.class, HMVPacketMissileAndLockMarker.class, ++id, Side.CLIENT);
        INSTANCE.registerMessage(HMVHandleSyncInventory.class, HMVPacketSyncInventory.class, ++id, Side.CLIENT);
        INSTANCE.registerMessage(HandleSyncSearchedTargetData.class, PacketSyncSearchedTargetData.class, ++id, Side.CLIENT);

    
    
    }
}