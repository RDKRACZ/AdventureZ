package net.adventurez.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.adventurez.entity.DragonEntity;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "onClientCommand", at = @At(value = "TAIL"))
    public void onClientCommandMixin(ClientCommandC2SPacket packet, CallbackInfo info) {
        if (this.player.getVehicle() instanceof DragonEntity && ((DragonEntity) this.player.getVehicle()).hasChest()) {
            ((DragonEntity) this.player.getVehicle()).openInventory(this.player);
        }
    }

}