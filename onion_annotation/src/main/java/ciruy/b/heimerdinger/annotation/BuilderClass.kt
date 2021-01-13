package ciruy.b.heimerdinger.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(value = AnnotationRetention.SOURCE)
annotation class BuilderClass(val classArray:Array<KClass<*>>)