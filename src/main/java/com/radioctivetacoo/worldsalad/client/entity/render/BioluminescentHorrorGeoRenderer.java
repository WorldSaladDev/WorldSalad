package com.radioctivetacoo.worldsalad.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.radioctivetacoo.worldsalad.client.entity.model.BioluminescentHorrorModel;
import com.radioctivetacoo.worldsalad.entities.BioluminescentHorror;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BioluminescentHorrorGeoRenderer extends GeoEntityRenderer<BioluminescentHorror>
{
	@SuppressWarnings("unchecked")
	public BioluminescentHorrorGeoRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, new BioluminescentHorrorModel());
	}

	@Override
	public RenderType getRenderType(BioluminescentHorror animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation)
	{
		return RenderType.getEntityCutout(getTextureLocation(animatable));
	}
}
