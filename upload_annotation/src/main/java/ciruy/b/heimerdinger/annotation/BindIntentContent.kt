package ciruy.b.heimerdinger.annotation

import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FIELD)
@Retention(value = AnnotationRetention.SOURCE)
annotation class BindIntentContent {

}