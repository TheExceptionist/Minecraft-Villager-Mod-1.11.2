package net.theexceptionist.main.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.theexceptionist.main.Ref;

public class RenderHuman extends RenderBiped<EntityHuman>{
	public RenderHuman(RenderManager renderManagerIn){
        super(renderManagerIn, new ModelHuman(0.0F, false), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelHuman(0.5F, true);
                this.modelArmor = new ModelHuman(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityHuman entity)
    {
    	//System.out.println(entity+" "+entity.isFemale());
    	//System.out.println(entity.getTitle()+" "+entity.isFemale());
    	return entity.getSkin();
	}

}
