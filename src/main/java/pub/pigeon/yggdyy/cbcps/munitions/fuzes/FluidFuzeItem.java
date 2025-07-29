package pub.pigeon.yggdyy.cbcps.munitions.fuzes;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import rbasamoyai.createbigcannons.munitions.AbstractCannonProjectile;

import java.util.List;

public class FluidFuzeItem extends AbstractPathScanFuzeItem{
    public FluidFuzeItem(Properties properties) {
        super(properties);
    }
    @Override
    public boolean detonateCheck(Level level, Vec3 p, ItemStack stack, AbstractCannonProjectile projectile) {
        BlockPos bp = new BlockPos((int) p.x, (int) p.y, (int) p.z);
        return level.getBlockState(bp).isAir() || !level.getFluidState(bp).isEmpty();
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tips, TooltipFlag flag) {
        super.appendHoverText(stack, level, tips, flag);
        tips.add(Component.translatable("tooltip.cbc_phantom_shells.fluid_fuze.0"));
    }
}
