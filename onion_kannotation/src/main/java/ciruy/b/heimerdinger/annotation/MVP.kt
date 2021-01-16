package ciruy.b.heimerdinger.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(value = AnnotationRetention.SOURCE)
annotation class MVP(
        val model:KClass<*> = Nothing::class,
        val view:KClass<*> = Nothing::class,
        val presenter:KClass<*> = Nothing::class
)
