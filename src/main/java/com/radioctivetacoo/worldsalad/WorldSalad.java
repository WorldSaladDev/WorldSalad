package com.radioctivetacoo.worldsalad;

import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.radioctivetacoo.worldsalad.init.BiomeInit;
import com.radioctivetacoo.worldsalad.init.BlockInit;
import com.radioctivetacoo.worldsalad.init.CarverInit;
import com.radioctivetacoo.worldsalad.init.ContainerInit;
import com.radioctivetacoo.worldsalad.init.DimensionInit;
import com.radioctivetacoo.worldsalad.init.EntityInit;
import com.radioctivetacoo.worldsalad.init.FeatureInit;
import com.radioctivetacoo.worldsalad.init.FluidInit;
import com.radioctivetacoo.worldsalad.init.ItemInit;
import com.radioctivetacoo.worldsalad.init.RecipeSerializerInit;
import com.radioctivetacoo.worldsalad.init.SoundInit;
import com.radioctivetacoo.worldsalad.init.TileEntityInit;
import com.radioctivetacoo.worldsalad.objects.blocks.GhostGrapeBushBlock;
import com.radioctivetacoo.worldsalad.objects.blocks.HotcoffeeCropBlock;
import com.radioctivetacoo.worldsalad.objects.items.ModSpawnEggItem;
import com.radioctivetacoo.worldsalad.world.gen.ModOreGen;
import com.radioctivetacoo.worldsalad.world.gen.StructureGen;

@Mod("worldsalad")
@Mod.EventBusSubscriber(modid = WorldSalad.MOD_ID, bus = Bus.MOD)
public class WorldSalad {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "worldsalad";
	public static WorldSalad instance;
	public static final ResourceLocation HYPHAE_DIM_TYPE = new ResourceLocation(MOD_ID, "hyphae");
	
	public WorldSalad() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);

		SoundInit.SOUNDS.register(modEventBus);
		ItemInit.ITEMS.register(modEventBus);
		RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		FluidInit.FLUIDS.register(modEventBus);
		CarverInit.CARVERS.register(modEventBus);
		TileEntityInit.TILE_ENTITY_TYPES.register(modEventBus);
		ContainerInit.CONTAINER_TYPES.register(modEventBus);
		EntityInit.ENTITY_TYPES.register(modEventBus);
		BiomeInit.BIOMES.register(modEventBus);
		DimensionInit.MOD_DIMENSIONS.register(modEventBus);
		FeatureInit.FEATURES.register(modEventBus);

		instance = this;
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		BlockInit.BLOCKS.getEntries().stream().filter(
				block -> !(block.get() instanceof GhostGrapeBushBlock) && !(block.get() instanceof FlowingFluidBlock) && !(block.get() instanceof HotcoffeeCropBlock) && !(block.get() instanceof FlowerPotBlock))
				.map(RegistryObject::get).forEach(block -> {
					final Item.Properties properties = new Item.Properties().group(WorldSaladBlocksItemGroup.instance);
					final BlockItem blockItem = new BlockItem(block, properties);
					blockItem.setRegistryName(block.getRegistryName());
					registry.register(blockItem);
				});
	}

	@SubscribeEvent
	public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
		BiomeInit.registerBiomes();
	}

	@SubscribeEvent
	public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
		EntityInit.registerEntityWorldSpawn();
		EntityInit.registerPlacementType();
		ModSpawnEggItem.initSpawnEggs();
	}

	private void setup(final FMLCommonSetupEvent event) {
		ComposterBlock.registerCompostable(0.3f, BlockInit.WILLOW_LEAVES.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.WILLOW_SAPLING.get());
		ComposterBlock.registerCompostable(0.65f, ItemInit.GREEN_APPLE.get());
		ComposterBlock.registerCompostable(0.65f, ItemInit.GHOST_GRAPES.get());
		ComposterBlock.registerCompostable(0.65f, ItemInit.HOTCOFFEE_BEANS.get());
		ComposterBlock.registerCompostable(0.65f, ItemInit.CHOCOLATE_HOTCOFFEE_BEANS.get());
		ComposterBlock.registerCompostable(0.3f, ItemInit.HOTCOFFE_SEED.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.AMANITA.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.DEATHCAP.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.DESERT_SHAGGY_MANE.get());
		ComposterBlock.registerCompostable(0.2f, BlockInit.TOADSTOOL.get());
		ComposterBlock.registerCompostable(1.0f, BlockInit.MOREL.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.LUMGRASS.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.TALL_LUMGRASS.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.FUNGRASS.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.TALL_FUNGRASS.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.MOLDGRASS.get());
		ComposterBlock.registerCompostable(0.3f, BlockInit.MOLD_GROWTH.get());
		ComposterBlock.registerCompostable(0.1f, BlockInit.DRY_REED.get());
		ComposterBlock.registerCompostable(0.1f, BlockInit.MOLD_BLOCK.get());
		ComposterBlock.registerCompostable(0.1f, BlockInit.MILDEW_BLOCK.get());
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {

	}

	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		ModOreGen.generateOre();
		StructureGen.generateStructures();
	}

	public static class WorldSaladItemsItemGroup extends ItemGroup {
		public static final WorldSaladItemsItemGroup instance = new WorldSaladItemsItemGroup(ItemGroup.GROUPS.length,
				"worldsaladitems");

		private WorldSaladItemsItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.WORLD_SALAD.get());
		}
	}
	
	public static class WorldSaladBlocksItemGroup extends ItemGroup {
		public static final WorldSaladBlocksItemGroup instance = new WorldSaladBlocksItemGroup(ItemGroup.GROUPS.length,
				"worldsaladblocks");

		private WorldSaladBlocksItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockInit.HYCELIUM.get());
		}
	}
	
	public static class WorldSaladToolsItemGroup extends ItemGroup {
		public static final WorldSaladToolsItemGroup instance = new WorldSaladToolsItemGroup(ItemGroup.GROUPS.length,
				"worldsaladtools");

		private WorldSaladToolsItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.ESSENCE_CRYSTALIZER.get());
		}
	}
}
