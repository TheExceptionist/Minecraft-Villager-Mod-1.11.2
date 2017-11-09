package net.theexceptionist.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.common.MinecraftForge;
import net.theexceptionist.main.entity.EntityHuman;
import net.theexceptionist.main.entity.RenderHuman;
import net.theexceptionist.main.events.EventHandler;
import net.theexceptionist.main.events.EventOverrideVillages;

public class ClientProxy extends CommonProxy{
	public void registerRenderInformation(){
		
	}
	
	public void registerRenderers(){
		
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		
		renderManager.entityRenderMap.put(EntityHuman.class, new RenderHuman(renderManager));
        
//        renderManager.entityRenderMap.put(EntityVillagerSoldier.class, new RenderVillagerSoldier(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerSoldierWandering.class, new RenderVillagerSoldier(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerCaptain.class, new RenderVillagerCaptain(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerArcher.class, new RenderVillagerArcher(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerArcherWandering.class, new RenderVillagerArcher(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerKnight.class, new RenderVillagerKnight(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerHorse.class, new RenderHorse(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerMage.class, new RenderVillagerMage(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerGuardian.class, new RenderVillagerGuardian(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerAlchemist.class, new RenderVillagerAlchemist(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerArrow.class, new RenderVillagerArrow(renderManager));
//        renderManager.entityRenderMap.put(EntityVillagerMerchant.class, new RenderVillagerMerchant(renderManager));
//        renderManager.entityRenderMap.put(EntityMerchantGuard.class, new RenderMerchantGuard(renderManager));
//    
     //   RenderingRegistry.registerEntityRenderingHandler(EntityVillagerArrow.class, new RenderArrow());
        
        //float shadowSize = 0.5F;
		//RenderingRegistry.registerEntityRenderingHandler(EntityVillagerSoldier.class, new RenderVillagerSoldier(null));
		//MOBS
		//RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, new RenderHuman(new ModelBiped(), shadowSize));
		
	}
	
	public void initEvents(){
		MinecraftForge.EVENT_BUS.register(new EventHandler());	
		MinecraftForge.TERRAIN_GEN_BUS.register(new EventOverrideVillages());	
//		//VillageEventHandler handler = new VillageEventHandler();
		//MinecraftForge.EVENT_BUS.register(handler);
		//System.out.println("Init");
		//FMLCommonHandler.instance().bus().register(new EventPlayerLogOut());
	}
}
