package dev.vengateshm.ksp_samples

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import dev.vengateshm.ksp_samples.annotations.Decorator

class DecoratorProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = Decorator::class.qualifiedName?.let {
            resolver.getSymbolsWithAnnotation(it)
        }
        symbols?.let { symbol ->
            symbol
                .filter { it is KSClassDeclaration && it.validate() }
                .forEach {
                    it.accept(DecoratorVisitor(codeGenerator, logger), Unit)
                }
        }
        return symbols?.filter { !it.validate() }?.toList() ?: emptyList()
    }
}