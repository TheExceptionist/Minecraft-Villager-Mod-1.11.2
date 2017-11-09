package net.theexceptionist.main.entity;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.theexceptionist.main.FemaleNames;
import net.theexceptionist.main.MaleNames;
import net.theexceptionist.main.Ref;
import net.theexceptionist.main.entity.ai.EntityAIAttackBackExclude;
import net.theexceptionist.main.entity.ai.EntityAIAttackRangedBowHuman;
import net.theexceptionist.main.entity.ai.EntityAIGuardPost;
import net.theexceptionist.main.entity.ai.EntityAIPatrol;

import com.google.common.base.Predicate;

public class EntityHuman extends EntityVillager implements IRangedAttackMob{
	
	private String title;
	private Profession profession;
	private HumanProfession prof;
	private HumanFaction faction;
	private CombatType combatType;
	private boolean isFemale;
	private int healthOffset;
	private double speedOffset;
	private double attackOffset;
	private double armorToughOffset;
	private double attackSpeedOffset;
	private double followRangeOffset;
	private double resistanceOffset;
	private double armorOffset;
	private double luckOffset;
	

	
	private EntityAIAttackRangedBowHuman aiArrowAttack = new EntityAIAttackRangedBowHuman(this, 1.0D, 20, 15.0F);
    private EntityAIAttackMelee aiAttackOnCollide = new EntityAIAttackMelee(this, 1.0D, true);
	
    private static Random rand = new Random();
    
    private boolean wasInit = false;
	private Village village;
	private BlockPos spawnPos;
    
	public EntityHuman(World world){
		super(world);
		int choice = 0;
		int[] offsets = new int[9];
				
		offsets[0] = this.world.rand.nextInt(10) - 5;
		offsets[1] = this.world.rand.nextInt(10) - 5;
		offsets[2] = this.world.rand.nextInt(10) - 5;
		offsets[3] = this.world.rand.nextInt(10) - 5;
		offsets[4] = this.world.rand.nextInt(10) - 5;
		offsets[5] = this.world.rand.nextInt(10) - 5;
		offsets[6] = this.world.rand.nextInt(10) - 5;
		offsets[7] = this.world.rand.nextInt(10) - 5;
		offsets[8] = this.world.rand.nextInt(10) - 5;
		
		
		if(world.rand.nextInt(100) <= 25){
			//init(world, EntityHuman.getRandomName(), HumanProfession.peasant, HumanFaction.Tifguardians, CombatType.Melee, false);			
			choice = 1;
		} else if(world.rand.nextInt(100) <= 25){
			//init(world, EntityHuman.getRandomNameFemale(), HumanProfession.peasant, HumanFaction.Tifguardians, CombatType.Melee, true);			
			choice = 5;
		}else if(world.rand.nextInt(100) <= 35){
			//init(world , EntityHuman.getRandomName(), HumanProfession.farmer, HumanFaction.Tifguardians, CombatType.Melee, false);			
			choice = 6;
		}else if(world.rand.nextInt(100) <= 35){
			//init(world, EntityHuman.getRandomNameFemale(), HumanProfession.farmer, HumanFaction.Tifguardians, CombatType.Melee, true);			
			choice = 4;
		}else if(world.rand.nextInt(100) <= 75){
			//init(world, EntityHuman.getRandomName(), HumanProfession.soldier, HumanFaction.Tifguardians, CombatType.Melee, false);
			choice = 3;
		}else{
			//init(world, EntityHuman.getRandomName(), HumanProfession.archer, HumanFaction.Tifguardians, CombatType.Ranged, false);	
			choice = 2;
		}
		
		//choice = 5;
		
		switch(choice){
		case 0:
			break;
		case 1:
			init(world, EntityHuman.getRandomName(), HumanProfession.peasant, HumanFaction.Tifguardians, CombatType.Melee, false, offsets);	
			break;
		case 2:
			init(world, EntityHuman.getRandomNameFemale(), HumanProfession.peasant, HumanFaction.Tifguardians, CombatType.Melee, true, offsets);			
			
			break;
		case 3:
			init(world , EntityHuman.getRandomName(), HumanProfession.farmer, HumanFaction.Tifguardians, CombatType.Melee, false, offsets);			
			
			break;
		case 4:
			init(world, EntityHuman.getRandomNameFemale(), HumanProfession.farmer, HumanFaction.Tifguardians, CombatType.Melee, true, offsets);			
			
			break;
		case 5:
			init(world, EntityHuman.getRandomName(), HumanProfession.soldier, HumanFaction.Tifguardians, CombatType.Melee, false, offsets);
			
			break;
		case 6:
			init(world, EntityHuman.getRandomName(), HumanProfession.archer, HumanFaction.Tifguardians, CombatType.Ranged, false, offsets);	
			
			break;
		}
//		else {
//			if(world.rand.nextInt(2) == 0){
//				init(world , EntityHuman.getRandomName(), HumanProfession.farmer, HumanFaction.Tifguardians, CombatType.Melee, false);			
//			}else
//			if(world.rand.nextInt(2) == 0){
//				init(world, EntityHuman.getRandomNameFemale(), HumanProfession.farmer, HumanFaction.Tifguardians, CombatType.Melee, true);			
//			}else{
//				if(world.rand.nextInt(2) == 0){
//					init(world, EntityHuman.getRandomName(), HumanProfession.soldier, HumanFaction.Tifguardians, CombatType.Melee, false);
//				}else{
//					init(world, EntityHuman.getRandomName(), HumanProfession.archer, HumanFaction.Tifguardians, CombatType.Ranged, false);	
//				}
//			}
//		}

	}

	/*public EntityHuman(World world, String name, HumanProfession prof, HumanFaction f, CombatType c, boolean gender){
		this(world, getRandomName(), HumanProfession.soldier, HumanFaction.Tifguardians, CombatType.Melee, gender, new int[]{0,0,0,0,0,0,0,0,0});
	}
	
	public EntityHuman(World world, String name, HumanProfession prof, HumanFaction f, CombatType c, boolean gender, int[] Offsets){
		super(world);
		this.title = name;
		this.prof = prof;
		this.faction = f;
		this.combatType = c;
		this.setFemale(gender);
		
		
		this.speedOffset = Offsets[0];
		this.healthOffset = Offsets[1];
		this.attackOffset = Offsets[2];
		this.armorToughOffset = Offsets[3];
		this.attackSpeedOffset = Offsets[4];
		this.followRangeOffset = Offsets[5];
		this.resistanceOffset = Offsets[6];
		this.armorOffset = Offsets[7];
		this.luckOffset = Offsets[8];
		
		if(!this.world.isRemote){
			init();
		}else{
			this.setProfession(new Profession("SLAVE", -2));
		}
	}*/
	
	public static String getRandomName(){
		return MaleNames.names[rand.nextInt(MaleNames.names.length)];
	}
	
	public static String getRandomNameFemale() {
		return FemaleNames.names[rand.nextInt(FemaleNames.names.length)];
	}
	
	private void init(World world, String name, HumanProfession prof,
			HumanFaction f, CombatType c, boolean gender) {
		// TODO Auto-generated method stub
		init(world,  name, prof, f, c, gender, new int[]{0,0,0,0,0,0,0,0,0});
	}
	
	private void init(World world, String name, HumanProfession prof,
			HumanFaction f, CombatType c, boolean gender, int[] Offsets) {
		// TODO Auto-generated method stub
		this.setTitle(name);
		this.setProf(prof);
		this.faction = f;
		this.combatType = c;
		this.setFemale(gender);
	
		this.speedOffset = Offsets[0];
		this.healthOffset = Offsets[1];
		this.attackOffset = Offsets[2];
		this.armorToughOffset = Offsets[3];
		this.attackSpeedOffset = Offsets[4];
		this.followRangeOffset = Offsets[5];
		this.resistanceOffset = Offsets[6];
		this.armorOffset = Offsets[7];
		this.luckOffset = Offsets[8];

		if(!world.isRemote){
			init();
		}else{
			this.setProfession(new Profession("SLAVE", -2));
	//		System.out.println(this.world.isRemote);
	//		System.out.println(this.getTitle());
		}
	}
	
	protected void init(){
		if(!wasInit){
		
		
			if(getProf() == getProf().soldier){
			//	System.out.println("2 "+this.getTitle()+" "+this.isFemale+" "+this.getProf());
				this.setProfession(new Profession("Soldier", 0));
				this.setCustomNameTag(getTitle()+" - "+this.getHumanProfession().getName());
				if(this.faction == HumanFaction.Tifguardians){
					this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
					this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.SHIELD));
					this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
					this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
					this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
					this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
				}else if(this.faction == HumanFaction.Bandits){
					
				}else{
					
				}
		        this.setCombatTask();
			}else if(getProf() == getProf().archer){
				this.setProfession(new Profession("Archer", 1));
				this.setCustomNameTag(getTitle()+" - "+this.getHumanProfession().getName());
				if(this.faction == HumanFaction.Tifguardians){
					this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
					this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.TIPPED_ARROW));
					this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
					this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
					this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
					this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
				}else if(this.faction == HumanFaction.Bandits){
					
				}else{
					
				}
		        this.setCombatTask();
			}else if(getProf() == getProf().farmer){
				this.setProfession(new Profession("Farmer", 2));
				this.setCustomNameTag(getTitle()+" - "+this.getHumanProfession().getName());
				if(this.faction == HumanFaction.Tifguardians){
					this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_HOE));
				}else if(this.faction == HumanFaction.Bandits){
					
				}else{
					
				}
				this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
				this.tasks.addTask(2, new EntityAIMoveIndoors(this));
			}else if(getProf() == getProf().peasant){
				this.setProfession(new Profession("Peasant", 3));
				this.setCustomNameTag(getTitle()+" - "+this.getHumanProfession().getName());
				if(this.faction == HumanFaction.Tifguardians){
					//this.setHeldItem(EnumHand.MAIN_HAND, null);
				}else if(this.faction == HumanFaction.Bandits){
					
				}else{
					
				}
				this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
				this.tasks.addTask(2, new EntityAIMoveIndoors(this));
			}else{
				this.setProfession(new Profession("Villager", -1));
				this.setCustomNameTag(getTitle()+" - "+this.getHumanProfession().getName());
				if(this.faction == HumanFaction.Tifguardians){
	
				}else if(this.faction == HumanFaction.Bandits){
					
				}else{
					
				}
				this.tasks.addTask(2, new EntityAIMoveIndoors(this));
			}
			wasInit = true;
		}
		
		
	//	System.out.println(this+" "+world.isRemote+" "+this.isFemale);
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45D + speedOffset);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D + healthOffset);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4D + attackOffset);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0D + armorToughOffset);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(2D + attackSpeedOffset);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32D + followRangeOffset);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0D + resistanceOffset);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2D + armorOffset);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.LUCK);
        this.getEntityAttribute(SharedMonsterAttributes.LUCK).setBaseValue(0D + luckOffset);
        

        aiArrowAttack = new EntityAIAttackRangedBowHuman(this, 1.0D, 20, 15.0F);
        aiAttackOnCollide = new EntityAIAttackMelee(this, 1.2D, false);
    }
	
	protected void initEntityAI()
    {		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
       //this.tasks.addTask(3, new EntityAIRoutedPatrol(this));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        //this.tasks.addTask(5, new EntityAILookAtVillager(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        //this.areAdditionalTasksSet = true;
        
        
        
      //  this.targetTasks.addTask(3, new EntityAIAttackBackExclude(this, false, new Class[0]));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
		
        this.targetTasks.addTask(2, new EntityAIAttackBackExclude(this, false, new Class[0]));
    }
	
	 public void setCombatTask()
	    {
			   this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
			   this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
				        {
				            public boolean apply(@Nullable EntityLiving p_apply_1_)
				            {
				                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
				            }
				}));
			   
			   if(this.combatType == CombatType.Melee){
				   this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
				   this.targetTasks.addTask(2, new EntityAIPatrol(this, true));
			   }else if(this.combatType == CombatType.Ranged){
				   this.tasks.addTask(1, new EntityAIAttackRangedBowHuman(this, 1.0D, 20, 15.0F));
				   this.targetTasks.addTask(2, new EntityAIGuardPost(this, true));
			   }
			   
			   /*System.out.println("Updating: "+this);
			   
		        if (this.world != null && !this.world.isRemote)
		        {
		            this.tasks.removeTask(this.aiAttackOnCollide);
		            this.tasks.removeTask(this.aiArrowAttack);
		            ItemStack itemstack = this.getHeldItemMainhand();
	
		            if (itemstack.getItem() == Items.BOW)
		            {
		                this.tasks.addTask(4, this.aiArrowAttack);
		            }
		            else
		            {
		                this.tasks.addTask(4, this.aiAttackOnCollide);
		            }
		        }*/
	    }
	 
	 protected void updateAITasks()
	    {
	        System.out.println(this+""+this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue()+" "+this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()+" "+this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue()+" "+this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getBaseValue()+" "+this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue()+
	        		" "+this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getBaseValue()+" "+this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getBaseValue()+" "+this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getBaseValue()+" "+this.getEntityAttribute(SharedMonsterAttributes.ARMOR).getBaseValue()+" "+this.getEntityAttribute(SharedMonsterAttributes.LUCK).getBaseValue());
	        
		 super.updateAITasks();
		 //this.setActiveHand(hand);
	//	 System.out.println(this+" "+this.isFemale);
		 if(this.getAttackTarget() instanceof EntityVillager){
			 this.setAttackTarget(null);
		 }
		 if(!(this.getProf() == getProf().farmer) && !(this.getProf() == getProf().peasant)){
		 //System.out.println(this.getMaximumHomeDistance());
			 for(Object task : this.tasks.taskEntries.toArray())
				{
					 EntityAIBase ai = ((EntityAITaskEntry) task).action;
					 if(ai instanceof EntityAIHarvestFarmland)
						 this.tasks.removeTask(ai);	
					 //System.out.println("Removed");
				}
		 }
	    }
	
	 public boolean attackEntityAsMob(Entity entityIn)
	    {
	        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
	        int i = 0;

	        if (entityIn instanceof EntityLivingBase)
	        {
	            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
	            i += EnchantmentHelper.getKnockbackModifier(this);
	        }

	        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

	        if (flag)
	        {
	            if (i > 0 && entityIn instanceof EntityLivingBase)
	            {
	                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double)MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
	                this.motionX *= 0.6D;
	                this.motionZ *= 0.6D;
	            }

	            int j = EnchantmentHelper.getFireAspectModifier(this);

	            if (j > 0)
	            {
	                entityIn.setFire(j * 4);
	            }

	            if (entityIn instanceof EntityPlayer)
	            {
	                EntityPlayer entityplayer = (EntityPlayer)entityIn;
	                ItemStack itemstack = this.getHeldItemMainhand();
	                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

	                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD)
	                {
	                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

	                    if (this.rand.nextFloat() < f1)
	                    {
	                        entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
	                        this.world.setEntityState(entityplayer, (byte)30);
	                    }
	                }
	            }

	            this.applyEnchantments(this, entityIn);
	        }

	        return flag;
	    }


	public Profession getHumanProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public boolean isFemale() {
		return isFemale;
	}

	public void setFemale(boolean isFemale) {
		this.isFemale = isFemale;
	}

	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
        EntityArrow entityarrow = this.getArrow(distanceFactor);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.setThrowableHeading(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityarrow);
    }

    protected EntityArrow getArrow(float p_190726_1_)
    {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
    }
    
    public void setSpawnPoint(double d, double k, double e) {
		this.spawnPos = new BlockPos(d, k, e);
	}


	public Village getVillage() {
		// TODO Auto-generated method stub
		return this.village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public HumanProfession getProf() {
		return prof;
	}

	public void setProf(HumanProfession prof) {
		this.prof = prof;
	}
	
	private static final ResourceLocation HUMAN_TEXTURES = new ResourceLocation(Ref.MODID, "textures/entity/tif/soldier.png");
	private static final ResourceLocation HUMAN_TEXTURES_FEMALE = new ResourceLocation(Ref.MODID, "textures/entity/tif/female/villager.png");
	

	public ResourceLocation getSkin() {
	//	if(!this.world.isRemote){
			if(this.isFemale){
				return HUMAN_TEXTURES_FEMALE;
			}else{
				if(this.prof == prof.soldier){
					return HUMAN_TEXTURES;
				}else{
					return HUMAN_TEXTURES;
				}
			}
	//	}else{
	//		return new ResourceLocation("minecraft:textures/entity/steve.png");
	//	}
	}


	
	
	
}
