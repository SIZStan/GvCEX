package hmgww2.entity;

import hmgww2.Nation;
import net.minecraft.world.World;

import static hmvehicle.Utils.transformVecByQuat;

public class EntityUSA_ShipBase extends EntityBases_Ship
{
	public EntityUSA_ShipBase(World par1World) {
		super(par1World);
	}
	
	@Override
	public Nation getnation() {
		return Nation.USA;
	}
}
