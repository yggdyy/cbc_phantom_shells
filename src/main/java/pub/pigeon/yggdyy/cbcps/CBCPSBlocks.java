package pub.pigeon.yggdyy.cbcps;

import com.tterrag.registrate.util.entry.BlockEntry;
import pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_he_shell.PhantomHEShellBlock;
import pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_he_shell.PhantomHEShellItem;
import pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_sonic_shell.PhantomSonicShellBlock;
import pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_sonic_shell.PhantomSonicShellItem;
import pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_teleport_shell.PhantomTeleportShellBlock;
import pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_teleport_shell.PhantomTeleportShellItem;
import rbasamoyai.createbigcannons.CBCTags;
import rbasamoyai.createbigcannons.datagen.assets.CBCBuilderTransformers;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static pub.pigeon.yggdyy.cbcps.CBCPSMain.REGISTRATE;
public class CBCPSBlocks {
    public static final BlockEntry<PhantomHEShellBlock> PHANTOM_HE_SHELL = REGISTRATE
            .block("phantom_he_shell", PhantomHEShellBlock::new)
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/phantom_he_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Phantom High Explosive Shell")
            .item(PhantomHEShellItem::new)
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<PhantomSonicShellBlock> PHANTOM_SONIC_SHELL = REGISTRATE
            .block("phantom_sonic_shell", PhantomSonicShellBlock::new)
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/phantom_sonic_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Phantom Sonic Boom Shell")
            .item(PhantomSonicShellItem::new)
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<PhantomTeleportShellBlock> PHANTOM_TELEPORT_SHELL = REGISTRATE
            .block("phantom_teleport_shell", PhantomTeleportShellBlock::new)
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/phantom_teleport_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Phantom Teleport Shell")
            .item(PhantomTeleportShellItem::new)
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    /*
    	public static final BlockEntry<SAPShellBlock> SAP_SHELL = REGISTRATE
			.block("sap_shell", SAPShellBlock::new)
			.transform(shell(MapColor.COLOR_RED))
			.transform(axeOrPickaxe())
			.transform(CBCBuilderTransformers.projectile("projectile/sap_shell"))
			.transform(CBCBuilderTransformers.safeNbt())
			.loot(CBCBuilderTransformers.shellLoot())
			.lang("SAP Shell")
			.item(SAPShellBlockItem::new)
			.tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
			.build()
			.register();
     */
    public static void init() {}
}
