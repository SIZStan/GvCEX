package hmggvcmob.entity;

public interface IdriveableVehicle {
    int getfirecyclesettings1();
    int getfirecycleprogress1();
    int getfirecyclesettings2();
    int getfirecycleprogress2();
    float getturretrotationYaw();
    float getbodyrotationYaw();
    float getthrottle();
    
    default String getsightTex(){
        return null;
    }
    default float getthirdDist(){
        return 4;
    }
}
