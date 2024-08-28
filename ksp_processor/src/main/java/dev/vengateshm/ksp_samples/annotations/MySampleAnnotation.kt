package dev.vengateshm.ksp_samples.annotations

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MySampleAnnotation(val desc: String)
