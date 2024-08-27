package dev.vengateshm.ksp_samples

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import dev.vengateshm.ksp_samples.annotations.GsonSerialize

class GsonSerializeProcessor(private val codeGenerator: CodeGenerator, logger: KSPLogger) :
    SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(GsonSerialize::class.qualifiedName!!)

        for (symbol in symbols) {
            if (symbol !is KSClassDeclaration) {
                continue
            }

            val className = symbol.simpleName.asString()
            val packageName = symbol.packageName.asString()

            val generatedCode = buildString {
                appendLine("package $packageName")
                appendLine()
                appendLine("import com.google.gson.Gson")
                appendLine()
                appendLine("fun $className.toJson(): String {")
                appendLine("    val gson = Gson()")
                appendLine("    return gson.toJson(this)")
                appendLine("}")
                appendLine()
                appendLine("fun String.to$className(): $className {")
                appendLine("    val gson = Gson()")
                appendLine("    return gson.fromJson(this, $className::class.java)")
                appendLine("}")
            }

            // Create a source file with the generated code
            val fileName = "${className}GsonExtensions.kt"
            codeGenerator.createNewFile(
                dependencies = Dependencies(aggregating = false),
                packageName = packageName,
                fileName = fileName
            ).bufferedWriter().use { writer ->
                writer.write(generatedCode)
            }
        }

        return emptyList()
    }

    override fun finish() {
        // Clean-up or final tasks, if needed.
    }
}