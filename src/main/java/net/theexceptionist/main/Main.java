package net.theexceptionist.main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import net.theexceptionist.main.entity.EntityHuman;

@Mod(modid = Ref.MODID, version = Ref.VERSION)
public class Main
{
	@SidedProxy(clientSide = Ref.CLIENT_PROXY, serverSide = Ref.COMMON_PROXY)
	public static CommonProxy proxy;
	public static Main instance;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	}
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.initEvents();
    	proxy.registerRenderers();
    	instance = this;
    	
    	List<Biome> villageBiomes = Arrays.<Biome>asList(new Biome[] {Biomes.BEACH, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.COLD_BEACH, Biomes.COLD_TAIGA, Biomes.COLD_TAIGA_HILLS, Biomes.DEEP_OCEAN,
        		Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_EDGE, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.FROZEN_RIVER, Biomes.FROZEN_OCEAN, Biomes.ICE_MOUNTAINS, Biomes.ICE_PLAINS,
        		Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MESA, Biomes.MESA_CLEAR_ROCK, Biomes.MESA_ROCK, Biomes.MUSHROOM_ISLAND, Biomes.MUSHROOM_ISLAND_SHORE, Biomes.MUTATED_BIRCH_FOREST, Biomes.PLAINS, Biomes.DESERT, Biomes.SAVANNA, Biomes.TAIGA, Biomes.OCEAN,
        		Biomes.MUTATED_BIRCH_FOREST, Biomes.MUTATED_BIRCH_FOREST_HILLS, Biomes.MUTATED_DESERT, Biomes.MUTATED_EXTREME_HILLS, Biomes.MUTATED_EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_FOREST, Biomes.MUTATED_ICE_FLATS, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_MESA, Biomes.MUTATED_MESA, Biomes.MUTATED_MESA_CLEAR_ROCK,
        		Biomes.MUTATED_MESA_ROCK, Biomes.MUTATED_PLAINS, Biomes.MUTATED_REDWOOD_TAIGA, Biomes.MUTATED_REDWOOD_TAIGA_HILLS, Biomes.MUTATED_ROOFED_FOREST, Biomes.MUTATED_SAVANNA, Biomes.MUTATED_SAVANNA_ROCK, Biomes.MUTATED_SWAMPLAND, Biomes.MUTATED_TAIGA, Biomes.MUTATED_TAIGA_COLD, Biomes.REDWOOD_TAIGA, Biomes.REDWOOD_TAIGA_HILLS, Biomes.RIVER
        		,Biomes.RIVER, Biomes.ROOFED_FOREST, Biomes.SAVANNA_PLATEAU, Biomes.STONE_BEACH, Biomes.SWAMPLAND, Biomes.TAIGA_HILLS});
    	
    	for(int i = 0; i < villageBiomes.size(); i++){
    		BiomeManager.addVillageBiome(villageBiomes.get(i), true);
    		//MapGenStruct
    	}
    	
     	createEntity(EntityHuman.class, 1515, "human", 555555, 666666);
        
    }
    
    public void postnit(FMLPostInitializationEvent event)
    {
    }
    
    public static void createEntity(Class entityClass, int ID, String entityName, int solidColor, int spotColor){

    	EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID+":"+entityName), entityClass, entityName, ID, instance, 128, 1, true);
    	EntityRegistry.registerEgg(new ResourceLocation(Ref.MODID+":"+entityName),  solidColor, spotColor);
    }

    
    public static void addVillagePiece(Class c, String s) 
    { 
	    try 
	    { 
	    MapGenStructureIO.registerStructureComponent(c, s);
	    } 
	    catch (Exception localException) {} 
	    } 
	
	    public static void addVillageCreationHandler(IVillageCreationHandler v) 
	    { 
	    VillagerRegistry.instance().registerVillageCreationHandler(v); 
	    //VillagerRegistry.instance().
	    }
}
