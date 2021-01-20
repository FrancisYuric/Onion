package ciruy.b.heimerdinger.annotation

@Target(AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ResponseFormat(val value: String = "json") {
    companion object {
        const val JSON = "json"
        const val XML = "xml"
    }
}