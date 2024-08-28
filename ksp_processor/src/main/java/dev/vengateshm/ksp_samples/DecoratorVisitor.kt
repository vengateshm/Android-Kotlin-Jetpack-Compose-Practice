package dev.vengateshm.ksp_samples

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

data class Functions(val functionName: String, var returnType: KSTypeReference? = null)

class DecoratorVisitor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : KSVisitorVoid() {

    private val functions = mutableListOf<Functions>()

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        super.visitClassDeclaration(classDeclaration, data)
        classDeclaration.getDeclaredFunctions().forEach { it.accept(this, Unit) }
        val packageName = classDeclaration.packageName.asString()
        val className = "DecorateWith$classDeclaration"
        val fileSpec = FileSpec.builder(packageName, className).apply {
            addType(
                TypeSpec.classBuilder(className)
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addParameter(getConstructorParameter(classDeclaration)).build(),
                    ).addProperty(
                        PropertySpec.builder(
                            "$classDeclaration".lowercase(),
                            getTypeName(classDeclaration),
                        ).initializer("$classDeclaration".lowercase()).build(),
                    ).addSuperinterface(getTypeName(classDeclaration))
                    .addModifiers(KModifier.OPEN, KModifier.PUBLIC)
                    .addFunctions(getAllFunctions(classDeclaration)).build(),
            )
        }.build()
        fileSpec.writeTo(codeGenerator, true)
    }

    private fun getAllFunctions(classDeclaration: KSClassDeclaration): Iterable<FunSpec> {
        val listOfFunctions = mutableListOf<FunSpec>()
        functions.forEach {
            val returnType = it.returnType!!.toTypeName()
            val paramName = classDeclaration.toString().lowercase()
            listOfFunctions.add(
                FunSpec.builder(it.functionName)
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(returnType)
                    .addStatement("return $paramName.${it.functionName} ()")
                    .build(),
            )
        }
        return listOfFunctions
    }

    private fun getConstructorParameter(classDeclaration: KSClassDeclaration): ParameterSpec {
        return ParameterSpec("$classDeclaration".lowercase(), getTypeName(classDeclaration))
    }

    private fun getTypeName(classDeclaration: KSClassDeclaration): TypeName {
        return ClassName(classDeclaration.packageName.asString(), "$classDeclaration")
    }

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
        functions.add(Functions(function.toString()))
        function.returnType!!.accept(this, Unit)
    }

    override fun visitTypeReference(typeReference: KSTypeReference, data: Unit) {
        functions.firstOrNull {
            it.functionName == typeReference.parent.toString()
        }?.returnType = typeReference
    }
}