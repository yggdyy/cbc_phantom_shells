package pub.pigeon.yggdyy.cbcps;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = CBCPSMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CBCPSConfig
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.DoubleValue PHANTOM_SONIC_SHELL_DAMAGE_RADIUS = BUILDER
            .comment("When detonated, a phantom sonic boom shell will hurt all the living entities within a ball centered with the shell with the radius of the value.")
            .defineInRange("phantom_sonic_shell_damage_range", 8.0, 0.5, 64.0);
    public static final ForgeConfigSpec.DoubleValue PHANTOM_SONIC_SHELL_BASE_DAMAGE = BUILDER
            .comment("Define the base damage value of a phantom sonic boom shell. The damage will decrease with the increase of the distance to the shell.")
            .defineInRange("phantom_sonic_shell_base_damage", 20.0, 0.0, 114514.0);
    public static final ForgeConfigSpec.DoubleValue PHANTOM_SONIC_SHELL_BASE_KNOCK_BACK = BUILDER
            .comment("Define the base knock back value of a phantom sonic boom shell. The knock back value will decrease with the increase of the distance to the shell.")
            .defineInRange("phantom_sonic_shell_base_knock_back", 2.5, 0.0, 114514.0);
    public static final ForgeConfigSpec.DoubleValue PHANTOM_TELEPORT_SHELL_SCAN_RADIUS = BUILDER
            .comment("When fired, a phantom teleport shell will mark some living entities within the distance of this value.")
            .defineInRange("phantom_teleport_shell_scan_radius", 4.0, 0.5, 64.0);
    public static final ForgeConfigSpec.IntValue PHANTOM_TELEPORT_SHELL_MAX_DIVER_AMOUNT = BUILDER
            .comment("Define how many living entities a phantom teleport shell can mark.")
            .defineInRange("phantom_teleport_shell_max_diver_amount", 4, 1, 114514);
    static final ForgeConfigSpec SPEC = BUILDER.build();
    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
    }
}
