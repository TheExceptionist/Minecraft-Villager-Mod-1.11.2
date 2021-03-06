package net.theexceptionist.main.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.theexceptionist.main.entity.EntityHuman;

public class EntityAIPatrol extends EntityAITarget{
	EntityHuman soldier;
    /** The aggressor of the iron golem's village which is now the golem's attack target. */
    BlockPos post;
    
    EntityLivingBase targetEntity;
	private EntityLivingBase villageAgressorTarget;
	//private int timer;
	private boolean dayTime;

    public EntityAIPatrol(EntityHuman soldier, boolean dayTime)
    {
        super(soldier, false, true);
        this.soldier = soldier;
        this.dayTime = dayTime;
      //  this.timer = 1000 + soldier.world.rand.nextInt(500);
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        Village village = this.soldier.getVillage();
//
        if(this.dayTime && this.soldier.world.isDaytime()){
	        if (village == null)
	        {
	            return false;
	        }
	        else
	        {
	            this.villageAgressorTarget = village.findNearestVillageAggressor(this.soldier);
	            
	            if (this.villageAgressorTarget instanceof EntityCreeper)
	            {
	                return false;
	            }
	            else if (this.isSuitableTarget(this.villageAgressorTarget, false))
	            {
	                return true;
	            }//else if(this.timer >= 0){
	            	//post = new BlockPos(this.soldier.posX + this.soldier.getWorld().rand.nextInt(20) - 10, this.soldier.posY, this.soldier.posZ + this.soldier.getWorld().rand.nextInt(20) - 10);
	            	//return true;
	            //}
	            else if (this.taskOwner.getRNG().nextInt(20) == 0)
	            {
	                this.villageAgressorTarget = village.getNearestTargetPlayer(this.soldier);
	                return this.isSuitableTarget(this.villageAgressorTarget, false);
	            }
	            else if(this.soldier.getWorld().rand.nextInt(100) < 50){
	            	post = new BlockPos(this.soldier.posX + this.soldier.getWorld().rand.nextInt(this.soldier.getVillage().getVillageRadius()) - this.soldier.getVillage().getVillageRadius()/2, this.soldier.posY, this.soldier.posZ + this.soldier.getWorld().rand.nextInt(this.soldier.getVillage().getVillageRadius()) - this.soldier.getVillage().getVillageRadius()/2);
	            	//this.timer = 1000 + soldier.world.rand.nextInt(500);
	            	return true;
	            }else
	            {
	                return false;
	            }
	        }
        }else{
        	return false;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
    	if(this.villageAgressorTarget != null && this.soldier.getWorld().rand.nextInt(100) < 75){
    		//System.out.println(this.soldier.toString()+" is Attacking: "+this.villageAgressorTarget);
    		this.soldier.setAttackTarget(this.villageAgressorTarget);
    		super.startExecuting();
    	}else if(post != null){
    		//System.out.println(this.soldier.posX+" "+this.soldier.posY+" "+ this.soldier.posZ+" "+post.getX()+" "+post.getY()+" "+post.getY()+" "+new BlockPos(this.soldier.posX, this.soldier.posY, this.soldier.posZ).distanceSq(post));//this.soldier.toString()+" is Guarding: "+this.post.getX()+" "+this.post.getY()+" "+this.post.getZ());
    		if(new BlockPos(this.soldier.posX, this.soldier.posY, this.soldier.posZ).distanceSq(post) >= 1.0f){
    			this.soldier.getNavigator().tryMoveToXYZ(post.getX(), post.getY(), post.getZ(), 0.35D);
    			//timer--;
    		}else{
    			post = new BlockPos(this.soldier.posX + this.soldier.getWorld().rand.nextInt(this.soldier.getVillage().getVillageRadius()) - this.soldier.getVillage().getVillageRadius()/2, this.soldier.posY, this.soldier.posZ + this.soldier.getWorld().rand.nextInt(this.soldier.getVillage().getVillageRadius()) - this.soldier.getVillage().getVillageRadius()/2);
    			//timer--;
    			//System.out.println(this.soldier+" waiting at post");
    		}
    	}else{
    		post = new BlockPos(this.soldier.posX + this.soldier.getWorld().rand.nextInt(this.soldier.getVillage().getVillageRadius()) - this.soldier.getVillage().getVillageRadius()/2, this.soldier.posY, this.soldier.posZ + this.soldier.getWorld().rand.nextInt(this.soldier.getVillage().getVillageRadius()) - this.soldier.getVillage().getVillageRadius()/2);
    	}
//    }
    }
}
