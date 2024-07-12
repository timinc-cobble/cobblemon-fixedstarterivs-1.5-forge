package us.timinc.mc.cobblemon.fixedstarterivs

import com.cobblemon.mod.common.api.events.CobblemonEvents
import net.minecraftforge.event.server.ServerStartedEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import us.timinc.mc.cobblemon.fixedstarterivs.config.Config
import us.timinc.mc.cobblemon.fixedstarterivs.config.ConfigBuilder

@Mod(FixedStarterIvs.MOD_ID)
object FixedStarterIvs {
    const val MOD_ID = "fixedstarterivs"
    private val config: Config = ConfigBuilder.load(Config::class.java, MOD_ID)

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    object Registration {
        @SubscribeEvent
        fun onInit(e: ServerStartedEvent) {
            CobblemonEvents.STARTER_CHOSEN.subscribe { event ->
                event.pokemon.ivs.forEach { (stat, _) ->
                    event.pokemon.ivs[stat] = config.fixedIvValue
                }
            }
        }
    }
}