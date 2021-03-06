package com.radioctivetacoo.worldsalad.init;

import com.radioctivetacoo.worldsalad.WorldSalad;
import com.radioctivetacoo.worldsalad.tileentity.AlloyFurnaceTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.ArchaeologyStationTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.BoilerTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.DeepFryerTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.DisplayStandTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.DistillerTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.DistillingBarrelTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.FloatationBinTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.HydraulicPressTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.IncensePlateTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.IndustrialGrinderTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.KilnTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.LesserInfuserTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.PolymerizationReactorTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.SeedPressTileEntity;
import com.radioctivetacoo.worldsalad.tileentity.SpinningWheelTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {

	@SuppressWarnings("deprecation")
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, WorldSalad.MOD_ID);

	public static final RegistryObject<TileEntityType<DeepFryerTileEntity>> DEEP_FRYER = TILE_ENTITY_TYPES.register(
			"deep_fryer",
			() -> TileEntityType.Builder.create(DeepFryerTileEntity::new, BlockInit.DEEP_FRYER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<KilnTileEntity>> KILN = TILE_ENTITY_TYPES.register(
			"kiln",
			() -> TileEntityType.Builder.create(KilnTileEntity::new, BlockInit.KILN.get()).build(null));

	public static final RegistryObject<TileEntityType<SeedPressTileEntity>> SEED_PRESS = TILE_ENTITY_TYPES.register(
			"seed_press",
			() -> TileEntityType.Builder.create(SeedPressTileEntity::new, BlockInit.SEED_PRESS.get()).build(null));

	public static final RegistryObject<TileEntityType<SpinningWheelTileEntity>> SPINNING_WHEEL = TILE_ENTITY_TYPES
			.register("spinning_wheel", () -> TileEntityType.Builder
					.create(SpinningWheelTileEntity::new, BlockInit.SPINNING_WHEEL.get()).build(null));
	
	public static final RegistryObject<TileEntityType<LesserInfuserTileEntity>> LESSER_INFUSER = TILE_ENTITY_TYPES.register(
			"lesser_infuser",
			() -> TileEntityType.Builder.create(LesserInfuserTileEntity::new, BlockInit.LESSER_INFUSER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<HydraulicPressTileEntity>> HYDRAULIC_PRESS = TILE_ENTITY_TYPES.register(
			"hydraulic_press",
			() -> TileEntityType.Builder.create(HydraulicPressTileEntity::new, BlockInit.HYDRAULIC_PRESS.get()).build(null));
	
	public static final RegistryObject<TileEntityType<ArchaeologyStationTileEntity>> ARCHAEOLOGY_STATION = TILE_ENTITY_TYPES.register(
			"archaeology_station",
			() -> TileEntityType.Builder.create(ArchaeologyStationTileEntity::new, BlockInit.ARCHAEOLOGY_STATION.get()).build(null));
	
	public static final RegistryObject<TileEntityType<DisplayStandTileEntity>> DISPLAY_STAND = TILE_ENTITY_TYPES.register(
			"display_stand",
			() -> TileEntityType.Builder.create(DisplayStandTileEntity::new, BlockInit.DISPLAY_STAND.get()).build(null));
	
	public static final RegistryObject<TileEntityType<AlloyFurnaceTileEntity>> ALLOY_FURNACE = TILE_ENTITY_TYPES.register(
			"alloy_furnace",
			() -> TileEntityType.Builder.create(AlloyFurnaceTileEntity::new, BlockInit.ALLOY_FURNACE.get()).build(null));
	
	public static final RegistryObject<TileEntityType<IndustrialGrinderTileEntity>> INDUSTRIAL_GRINDER = TILE_ENTITY_TYPES.register(
			"industrial_grinder",
			() -> TileEntityType.Builder.create(IndustrialGrinderTileEntity::new, BlockInit.INDUSTRIAL_GRINDER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<FloatationBinTileEntity>> FLOATATION_BIN = TILE_ENTITY_TYPES.register(
			"floatation_bin",
			() -> TileEntityType.Builder.create(FloatationBinTileEntity::new, BlockInit.FLOATATION_BIN.get()).build(null));
	
	public static final RegistryObject<TileEntityType<DistillingBarrelTileEntity>> DISTILLING_BARREL = TILE_ENTITY_TYPES.register(
			"distilling_barrel",
			() -> TileEntityType.Builder.create(DistillingBarrelTileEntity::new, BlockInit.DISTILLING_BARREL.get()).build(null));
	
	public static final RegistryObject<TileEntityType<BoilerTileEntity>> BOILER = TILE_ENTITY_TYPES.register(
			"boiler",
			() -> TileEntityType.Builder.create(BoilerTileEntity::new, BlockInit.BOILER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<DistillerTileEntity>> DISTILLER = TILE_ENTITY_TYPES.register(
			"distiller",
			() -> TileEntityType.Builder.create(DistillerTileEntity::new, BlockInit.DISTILLER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<PolymerizationReactorTileEntity>> POLYMERIZATION_REACTOR = TILE_ENTITY_TYPES.register(
			"polymerization_reactor",
			() -> TileEntityType.Builder.create(PolymerizationReactorTileEntity::new, BlockInit.POLYMERIZATION_REACTOR.get()).build(null));
	
	public static final RegistryObject<TileEntityType<IncensePlateTileEntity>> INCENSE_PLATE = TILE_ENTITY_TYPES.register(
			"incense_plate",
			() -> TileEntityType.Builder.create(IncensePlateTileEntity::new, BlockInit.INCENSE_PLATE.get()).build(null));
}
