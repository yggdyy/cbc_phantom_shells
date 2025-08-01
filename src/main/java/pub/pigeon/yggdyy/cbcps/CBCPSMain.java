package pub.pigeon.yggdyy.cbcps;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import rbasamoyai.createbigcannons.ModGroup;

@Mod(CBCPSMain.MODID)
public class CBCPSMain
{
    public static final String MODID = "cbc_phantom_shells";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    public CBCPSMain(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        CBCPSBlocks.init();
        CBCPSItems.init();
        CBCPSEntityTypes.init();
        REGISTRATE.registerEventListeners(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        context.registerConfig(ModConfig.Type.COMMON, CBCPSConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == ModGroup.MAIN_TAB_KEY) {
            event.accept(CBCPSBlocks.PHANTOM_HE_SHELL.get());
            event.accept(CBCPSBlocks.PHANTOM_SONIC_SHELL.get());
            event.accept(CBCPSBlocks.PHANTOM_TELEPORT_SHELL.get());
            event.accept(CBCPSItems.AIR_FUZE.get());
            event.accept(CBCPSItems.LIQUID_FUZE.get());
            event.accept(CBCPSItems.FLUID_FUZE.get());
            event.accept(CBCPSItems.SOLID_FUZE.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
