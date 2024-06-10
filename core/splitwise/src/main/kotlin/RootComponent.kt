import dagger.Component
import di.modules.PsqlModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PsqlModule::class
    ]
)
interface RootComponent {
    val serviceFactory: ServiceFactory
}