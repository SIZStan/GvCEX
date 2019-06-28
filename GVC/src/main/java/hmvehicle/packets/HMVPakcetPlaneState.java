package hmvehicle.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

public class HMVPakcetPlaneState implements IMessage {
    public int targetID;
    public Quat4d rot = new Quat4d();
    public Quat4d rotmotion = new Quat4d();
    public Vector3d motionVec = new Vector3d();
    public float th;

    public HMVPakcetPlaneState(){
    }
    public HMVPakcetPlaneState(int tgtid , Quat4d tgtrot ,Quat4d tgtrotmotion ,Vector3d tgtmotion , float t){
        this.targetID = tgtid;
        this.rot = tgtrot;
        this.rotmotion = tgtrotmotion;
        this.motionVec = tgtmotion;
        this.th = t;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        targetID = buf.readInt();
        th = buf.readFloat();
        rot.x = buf.readDouble();
        rot.y = buf.readDouble();
        rot.z = buf.readDouble();
        rot.w = buf.readDouble();
        rotmotion.x = buf.readDouble();
        rotmotion.y = buf.readDouble();
        rotmotion.z = buf.readDouble();
        rotmotion.w = buf.readDouble();
        motionVec.x = buf.readDouble();
        motionVec.y = buf.readDouble();
        motionVec.z = buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(targetID);
        buf.writeFloat(th);
        buf.writeDouble(rot.x);
        buf.writeDouble(rot.y);
        buf.writeDouble(rot.z);
        buf.writeDouble(rot.w);
        buf.writeDouble(rotmotion.x);
        buf.writeDouble(rotmotion.y);
        buf.writeDouble(rotmotion.z);
        buf.writeDouble(rotmotion.w);
        buf.writeDouble(motionVec.x);
        buf.writeDouble(motionVec.y);
        buf.writeDouble(motionVec.z);
    }
}
