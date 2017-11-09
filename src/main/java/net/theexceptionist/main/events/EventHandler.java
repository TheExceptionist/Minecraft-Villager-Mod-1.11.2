package net.theexceptionist.main.events;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.village.Village;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.theexceptionist.main.entity.CombatType;
import net.theexceptionist.main.entity.EntityHuman;
import net.theexceptionist.main.entity.HumanFaction;
import net.theexceptionist.main.entity.HumanProfession;

public class EventHandler {
	
	@SubscribeEvent
	public void entityJoinedWorldEventHandler(EntityJoinWorldEvent event)
	{
		if(event.getEntity() instanceof EntityVillager && !(event.getEntity() instanceof EntityHuman)){
			//System.out.println(event.getWorld().isRemote);
			
				EntityHuman human;
				
			//	if(event.getWorld().villageCollectionObj != null){
					Village village = event.getWorld().villageCollectionObj.getNearestVillage(event.getEntity().getPosition(), 10);
					human = new EntityHuman(event.getWorld());
					human.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					human.setSpawnPoint(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					human.setVillage(village);
					if(!event.getWorld().isRemote){
						event.getWorld().spawnEntity(human);
					}
					
					event.getEntity().setDead();
			//	}
				//if(event.getWorld().rand.nextInt(2) == 0){
					
				
				
				
				
		}
	}

}
