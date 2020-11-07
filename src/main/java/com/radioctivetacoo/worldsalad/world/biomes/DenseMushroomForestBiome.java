package com.radioctivetacoo.worldsalad.world.biomes;

import com.radioctivetacoo.worldsalad.init.BlockInit;
import com.radioctivetacoo.worldsalad.world.feature.MadroneTree;
import com.radioctivetacoo.worldsalad.world.feature.TallMadroneTree;
import com.radioctivetacoo.worldsalad.world.gen.carvers.HyphaeCarver;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.TwoFeatureChoiceConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.HeightWithChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DenseMushroomForestBiome extends Biome {
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getSkyColor() {
		return 0x6d6bb5;
	}

	@Override
	public float getSpawningChance() {
		return 0.1F;
	}

	private static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState()
			.with(HugeMushroomBlock.UP, Boolean.valueOf(false)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
	private static final BlockState HUGE_TOADSTOOL_BLOCK = BlockInit.HUGE_TOADSTOOL_BLOCK.get().getDefaultState()
			.with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
	private static final BlockState HUGE_ENOKI_BLOCK = BlockInit.HUGE_ENOKI_BLOCK.get().getDefaultState()
			.with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
	private static final BlockState MORELBLOCK = BlockInit.MOREL.get().getDefaultState();
	private static final BlockState MADRONELEAFPILE = BlockInit.MADRONE_LEAF_PILE.get().getDefaultState();
	private static final BlockState TOADSTOOLBLOCK = BlockInit.TOADSTOOL.get().getDefaultState();
	private static final BlockState BLUE_GLOWING_MUSHROOM = BlockInit.BLUE_GLOWING_MUSHROOM.get().getDefaultState();
	private static final BlockState DEATHCAPBLOCK = BlockInit.DEATHCAP.get().getDefaultState();
	private static final BlockState AMANITABLOCK = BlockInit.AMANITA.get().getDefaultState();
	private static final BlockState FUNGRASS = BlockInit.FUNGRASS.get().getDefaultState();
	private static final BlockState TALL_FUNGRASS = BlockInit.TALL_FUNGRASS.get().getDefaultState();
	public static final BlockClusterFeatureConfig TALL_FUNGRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(TALL_FUNGRASS), new DoublePlantBlockPlacer())).tries(164).func_227317_b_()
					.build();
	BlockClusterFeatureConfig MOREL = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(MORELBLOCK),
			new SimpleBlockPlacer())).tries(1).func_227317_b_().build();
	BlockClusterFeatureConfig AMANITA = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(AMANITABLOCK), new SimpleBlockPlacer())).tries(2).func_227317_b_().build();
	BlockClusterFeatureConfig DEATHCAP = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(DEATHCAPBLOCK), new SimpleBlockPlacer())).tries(2).func_227317_b_().build();
	BlockClusterFeatureConfig TOADSTOOL = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(TOADSTOOLBLOCK), new SimpleBlockPlacer())).tries(12).func_227317_b_().build();
	BlockClusterFeatureConfig FUNGRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(FUNGRASS), new SimpleBlockPlacer())).tries(32).build();
	BlockClusterFeatureConfig MADRONE_LEAF_PILE_CONFIG = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(MADRONELEAFPILE), new SimpleBlockPlacer())).tries(32).build();
	BigMushroomFeatureConfig HUGE_ENOKI = new BigMushroomFeatureConfig(new SimpleBlockStateProvider(HUGE_ENOKI_BLOCK),
			new SimpleBlockStateProvider(MUSHROOM_STEM), 1);
	BigMushroomFeatureConfig HUGE_TOADSTOOL = new BigMushroomFeatureConfig(
			new SimpleBlockStateProvider(HUGE_TOADSTOOL_BLOCK), new SimpleBlockStateProvider(MUSHROOM_STEM), 2);
	BigMushroomFeatureConfig HUGE_TOADSTOOL_WIDE = new BigMushroomFeatureConfig(
			new SimpleBlockStateProvider(HUGE_TOADSTOOL_BLOCK), new SimpleBlockStateProvider(MUSHROOM_STEM), 4);
	BlockClusterFeatureConfig GLOWSHROOM = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(BLUE_GLOWING_MUSHROOM), new SimpleBlockPlacer())).tries(12).func_227317_b_()
					.build();

	public DenseMushroomForestBiome(Builder biomeBuilder) {
		super(biomeBuilder);

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.MOOSHROOM, 40, 2, 4));

		this.addCarver(GenerationStage.Carving.LIQUID,
				Biome.createCarver(new HyphaeCarver(ProbabilityConfig::deserialize), new ProbabilityConfig(0.2f)));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(MOREL)
				.withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP.configure(new HeightWithChanceConfig(8, 0.04F))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(AMANITA).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP.configure(new HeightWithChanceConfig(5, 0.07F))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DEATHCAP).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP.configure(new HeightWithChanceConfig(5, 0.07F))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(TOADSTOOL).withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP.configure(new HeightWithChanceConfig(9, 0.37F))));
		
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(MADRONE_LEAF_PILE_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(9))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(FUNGRASS_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(5))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.ACACIA_TREE.withConfiguration(TallMadroneTree.TALL_MADRONE_TREE_CONFIG).withPlacement(
						Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(150, 1.2f, 25))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR
				.withConfiguration(
						new TwoFeatureChoiceConfig(Feature.HUGE_BROWN_MUSHROOM.withConfiguration(HUGE_TOADSTOOL),
								Feature.HUGE_BROWN_MUSHROOM.withConfiguration(HUGE_TOADSTOOL)))
				.withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(5))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(TALL_FUNGRASS_CONFIG)
						.withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(7))));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(GLOWSHROOM).withPlacement(
						Placement.COUNT_CHANCE_HEIGHTMAP.configure(new HeightWithChanceConfig(8, 0.25F))));
		
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.ACACIA_TREE.withConfiguration(MadroneTree.MADRONE_TREE_CONFIG).withPlacement(
						Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.00001f, 0))));
		
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_BOOLEAN_SELECTOR
				.withConfiguration(new TwoFeatureChoiceConfig(Feature.HUGE_RED_MUSHROOM.withConfiguration(HUGE_ENOKI),
						Feature.HUGE_BROWN_MUSHROOM.withConfiguration(HUGE_TOADSTOOL_WIDE)))
				.withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(5))));
		
		@SuppressWarnings("rawtypes")
		ConfiguredPlacement customConfig0 = Placement.COUNT_RANGE.configure(new CountRangeConfig(900, 10, 5, 230));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE.withConfiguration(new OreFeatureConfig(
						OreFeatureConfig.FillerBlockType.create("DIRT", null,
								new BlockMatcher(Blocks.DIRT)),
						BlockInit.FUNGAL_DIRT.get().getDefaultState(), 100)).withPlacement(customConfig0));
	}
}
