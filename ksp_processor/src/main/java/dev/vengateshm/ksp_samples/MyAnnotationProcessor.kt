package dev.vengateshm.ksp_samples

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import dev.vengateshm.ksp_samples.annotations.MyAnnotation

class MyAnnotationProcessor(
    environment: SymbolProcessorEnvironment
) : SymbolProcessor {

    private val logger = environment.logger
    private val codeGenerator = environment.codeGenerator

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(MyAnnotation::class.qualifiedName!!)
        symbols.filterIsInstance<KSClassDeclaration>().forEach {
            val className = it.simpleName.asString()
            val packageName = it.packageName.asString()
            val file = codeGenerator.createNewFile(
                dependencies = Dependencies(false),
                packageName = packageName,
                fileName = "${className}Generated"
            )
            file.bufferedWriter().use { writer ->
                writer.write("package $packageName \n \n")
                writer.write("class ${className}Generated {\n")
                writer.write("      fun printName() = println(\"This is the generated class for $className\")\n")
                writer.write("}\n")
            }
            logger.info("Generated file for $className")
        }
        return emptyList()
    }
}
