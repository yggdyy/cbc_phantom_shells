package pub.pigeon.yggdyy.cbcps;
import com.tterrag.registrate.util.entry.ItemEntry;
import pub.pigeon.yggdyy.cbcps.munitions.fuzes.AirFuzeItem;
import pub.pigeon.yggdyy.cbcps.munitions.fuzes.FluidFuzeItem;
import pub.pigeon.yggdyy.cbcps.munitions.fuzes.LiquidFuzeItem;
import pub.pigeon.yggdyy.cbcps.munitions.fuzes.SolidFuzeItem;
import rbasamoyai.createbigcannons.CBCTags;

import static pub.pigeon.yggdyy.cbcps.CBCPSMain.REGISTRATE;
public class CBCPSItems {
    public static final ItemEntry<AirFuzeItem> AIR_FUZE = REGISTRATE
            .item("air_fuze", AirFuzeItem::new)
            .tag(CBCTags.CBCItemTags.FUZES)
            .register();
    public static final ItemEntry<LiquidFuzeItem> LIQUID_FUZE = REGISTRATE
            .item("liquid_fuze", LiquidFuzeItem::new)
            .tag(CBCTags.CBCItemTags.FUZES)
            .register();
    public static final ItemEntry<SolidFuzeItem> SOLID_FUZE = REGISTRATE
            .item("solid_fuze", SolidFuzeItem::new)
            .tag(CBCTags.CBCItemTags.FUZES)
            .register();
    public static final ItemEntry<FluidFuzeItem> FLUID_FUZE = REGISTRATE
            .item("fluid_fuze", FluidFuzeItem::new)
            .tag(CBCTags.CBCItemTags.FUZES)
            .register();
    public static void init() {}
}
