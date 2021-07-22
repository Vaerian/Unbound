package com.cumulusmc.unbound.block;

import com.cumulusmc.unbound.init.UnboundBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.Random;
import java.util.stream.StreamSupport;

public class SoulGlass extends AbstractGlassBlock {
    public SoulGlass() {
        super(FabricBlockSettings.of(Material.GLASS, MapColor.BROWN).nonOpaque().dynamicBounds().allowsSpawning(UnboundBlocks::never).solidBlock(UnboundBlocks::never).suffocates(UnboundBlocks::never).blockVision(UnboundBlocks::never));
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this) || world.getBlockState(entity.getBlockPos().up()).isOf(this)) {
            entity.slowMovement(state, new Vec3d(0.8999999761581421D, 1.5D, 0.8999999761581421D));

            if (world.isClient) {
                Random random = world.getRandom();
                boolean bl = entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ();
                if (bl && random.nextFloat() > 0.8) {
                    world.addParticle(ParticleTypes.SOUL, entity.getX(), pos.getY(), entity.getZ(), MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F, 0.05000000074505806D, MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F);
                }
            }

        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context == ShapeContext.absent()) {
            return VoxelShapes.fullCube();
        }

        if (context instanceof EntityShapeContext) {
            EntityShapeContext entityShapeContext = (EntityShapeContext) context;
            Optional<Entity> optional = entityShapeContext.getEntity();

            if (optional.isPresent()) {
                Entity entity = optional.get();

                boolean wearingSoulSpeed = StreamSupport.stream(entity.getArmorItems().spliterator(), false).anyMatch((stack) -> EnchantmentHelper.getLevel(Enchantments.SOUL_SPEED, stack) > 0);

                if (!wearingSoulSpeed) {
                    return VoxelShapes.fullCube();
                }

            }
        }

        return VoxelShapes.empty();
    }
}
