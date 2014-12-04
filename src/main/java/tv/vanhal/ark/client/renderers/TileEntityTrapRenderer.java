package tv.vanhal.ark.client.renderers;

import org.lwjgl.opengl.GL11;

import tv.vanhal.ark.client.models.ModelTrap;
import tv.vanhal.ark.tileentity.TileEntityTrap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityTrapRenderer extends TileEntitySpecialRenderer {

	private ModelTrap model = new ModelTrap();

	private static final ResourceLocation texture = new ResourceLocation("ark", "textures/models/trap.png");

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		bindTexture(texture);
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.0F, (float)z + 0.5F);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		TileEntityTrap tile = (TileEntityTrap)tileentity;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		//model.renderAll(false, 15);
		model.renderAll(tile.isShut(), tile.ticksSinceOpened());
		GL11.glPopMatrix();
	}

}
