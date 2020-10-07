package com.radioctivetacoo.worldsalad.init;

import com.radioctivetacoo.worldsalad.WorldSalad;
import com.radioctivetacoo.worldsalad.entities.Exoskeleton;
import com.radioctivetacoo.worldsalad.entities.Moth;
import com.radioctivetacoo.worldsalad.entities.RockMonster;
import com.radioctivetacoo.worldsalad.entities.SoldierAnt;
import com.radioctivetacoo.worldsalad.entities.TraderAnt;
import com.radioctivetacoo.worldsalad.entities.Urchin;
import com.radioctivetacoo.worldsalad.entities.Wraith;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.IPlacementPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
			WorldSalad.MOD_ID);

	public static void registerEntityWorldSpawns(EntityType<?> entity, int weight, int minGroupCountIn,
			int maxGroupCountIn, EntityClassification classification, Biome... biomes) {
		for (Biome biome : biomes) {
			if (biome != null) {
				biome.getSpawns(classification)
						.add(new SpawnListEntry(entity, weight, minGroupCountIn, maxGroupCountIn));
			}
		}
	}

	public static void registerEntityWorldSpawn() {
		registerEntityWorldSpawns(ROCK_MONSTER.get(), 80, 1, 3, EntityClassification.MONSTER,
				BiomeInit.CRAG_BIOME.get(), Biomes.MOUNTAINS, Biomes.GRAVELLY_MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.SNOWY_MOUNTAINS);
		registerEntityWorldSpawns(MOTH.get(), 250, 3, 4, EntityClassification.MONSTER,
				BiomeInit.GLOWING_MUSHROOM_BIOME.get());
		registerEntityWorldSpawns(EXOSKELETON .get(), 250, 1, 3, EntityClassification.MONSTER,
				BiomeInit.MOLD_TUNDRA_BIOME.get(), BiomeInit.MILDEW_FOREST_BIOME.get(), BiomeInit.MUSHROOM_CANYON_FLATS_BIOME.get(), BiomeInit.MUSHROOM_CANYON_SHATTERED_BIOME.get());
		registerEntityWorldSpawns(WRAITH.get(), 250, 1, 1, EntityClassification.MONSTER,
				BiomeInit.MOLD_TUNDRA_BIOME.get(), BiomeInit.MILDEW_FOREST_BIOME.get());
		registerEntityWorldSpawns(URCHIN.get(), 300, 3, 4, EntityClassification.MONSTER,
				Biomes.BEACH, Biomes.DEEP_COLD_OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.FROZEN_OCEAN, Biomes.WARM_OCEAN);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void registerPlacementTypes(EntityType type, Heightmap.Type heightMap, EntitySpawnPlacementRegistry.PlacementType spawnType, IPlacementPredicate placementType)
	{
		EntitySpawnPlacementRegistry.register(type, spawnType, heightMap, placementType);
	}
	
	public static void registerPlacementType()
	{
		registerPlacementTypes(ROCK_MONSTER.get(), Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, MonsterEntity::canMonsterSpawnInLight);
		registerPlacementTypes(MOTH.get(), Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, MonsterEntity::canMonsterSpawn);
		registerPlacementTypes(EXOSKELETON.get(), Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, MonsterEntity::canMonsterSpawn);
		registerPlacementTypes(URCHIN.get(), Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Urchin::canSpawnHere);
	}

	// hostile mobs
	public static final RegistryObject<EntityType<RockMonster>> ROCK_MONSTER = ENTITY_TYPES.register("rock_monster",
			() -> EntityType.Builder.<RockMonster>create(RockMonster::new, EntityClassification.MONSTER)
					.size(0.8f, 1.0f).build(new ResourceLocation(WorldSalad.MOD_ID, "rock_monster").toString()));

	public static final RegistryObject<EntityType<Moth>> MOTH = ENTITY_TYPES.register("moth",
			() -> EntityType.Builder.<Moth>create(Moth::new, EntityClassification.MONSTER).size(0.6f, 0.3f)
					.build(new ResourceLocation(WorldSalad.MOD_ID, "moth").toString()));
	
	public static final RegistryObject<EntityType<Exoskeleton>> EXOSKELETON = ENTITY_TYPES.register("exoskeleton",
			() -> EntityType.Builder.<Exoskeleton>create(Exoskeleton::new, EntityClassification.MONSTER)
					.size(0.7f, 0.5f).build(new ResourceLocation(WorldSalad.MOD_ID, "exoskeleton").toString()));
	
	public static final RegistryObject<EntityType<Wraith>> WRAITH = ENTITY_TYPES.register("wraith",
			() -> EntityType.Builder.<Wraith>create(Wraith::new, EntityClassification.MONSTER)
					.size(0.7f, 1.7f).build(new ResourceLocation(WorldSalad.MOD_ID, "wraith").toString()));

	// passive mobs
	public static final RegistryObject<EntityType<Urchin>> URCHIN = ENTITY_TYPES.register("urchin",
			() -> EntityType.Builder.<Urchin>create(Urchin::new, EntityClassification.MONSTER).size(0.5f, 0.6f)
					.build(new ResourceLocation(WorldSalad.MOD_ID, "urchin").toString()));
	
	public static final RegistryObject<EntityType<TraderAnt>> TRADER_ANT = ENTITY_TYPES.register("trader_ant",
			() -> EntityType.Builder.<TraderAnt>create(TraderAnt::new, EntityClassification.MONSTER)
					.size(0.7f, 0.5f).build(new ResourceLocation(WorldSalad.MOD_ID, "trader_ant").toString()));
	
	public static final RegistryObject<EntityType<SoldierAnt>> SOLDIER_ANT = ENTITY_TYPES.register("soldier_ant",
			() -> EntityType.Builder.<SoldierAnt>create(SoldierAnt::new, EntityClassification.MONSTER)
					.size(1f, 0.7f).build(new ResourceLocation(WorldSalad.MOD_ID, "soldier_ant").toString()));
}