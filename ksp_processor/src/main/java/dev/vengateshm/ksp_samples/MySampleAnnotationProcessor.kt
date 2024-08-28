package dev.vengateshm.ksp_samples

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import dev.vengateshm.ksp_samples.annotations.MySampleAnnotation

class MySampleAnnotationProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation(MySampleAnnotation::class.qualifiedName!!)
            .forEach { symbol ->
                val annotation =
                    symbol.annotations.find { it.shortName.asString() == "MySampleAnnotation" }
                val description = annotation?.arguments?.first()?.value as String
                environment.logger.info("xcvabc Found annotation: $annotation, $description")
                println("xcvabc Found annotation: $annotation, $description")
            }
        return emptyList()
    }
}
