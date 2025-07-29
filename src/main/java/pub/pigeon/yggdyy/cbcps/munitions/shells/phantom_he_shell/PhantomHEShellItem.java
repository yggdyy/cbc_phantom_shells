package pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_he_shell;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import pub.pigeon.yggdyy.cbcps.CBCPSEntityTypes;
import rbasamoyai.createbigcannons.base.CBCTooltip;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.FuzedProjectileBlockItem;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonCommonShellProperties;

import java.util.List;

public class PhantomHEShellItem extends FuzedProjectileBlockItem {
    public PhantomHEShellItem(Block block, Properties properties) {
        super(block, properties);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        boolean desc = Screen.hasShiftDown();
        if (!desc) {
            tooltip.add(Component.translatable("tooltip.cbc_phantom_shells.phantom_he_shell.0"));
            return;
        }
        BigCannonCommonShellProperties properties = CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(CBCPSEntityTypes.PHANTOM_HE_SHELL.get());
    }
}
