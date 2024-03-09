plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Lazy
tasks.register("FirstTask") {
    doLast {
        println("This is my first task")
    }
}

// Immediate execution even if other task executed
tasks.create("factorial") {
    var fact = 1
    val n = 5
    var num = n
    while (num > 0) {
        fact *= num
        num--
    }
    println("Factorial of $n is $fact")
}

open class ApplesTask : DefaultTask() {
    fun printAppleCount(count: Int?) {
        println("Apples count $count")
    }
}

tasks.register<ApplesTask>("apples") {
    val count = project.property("appleCount") as String
    doLast {
        printAppleCount(count.toIntOrNull())
    }
}

tasks.register("breadAndButter") {
    description = "A bread and butter task"
    group = "Sample group"
    val slices = project.property("slices") as String
    val scoops = project.property("scoops") as String
    doLast {
        println("$slices slices of bread and $scoops scoops of butter tastes yummy!")
    }
}

tasks.register("printProjectProps") {
    doLast {
        println(project.properties["org.gradle.jvmargs"])
        println(project.properties["android.useAndroidX"])
        println(project.properties["android.enableJetifier"])
        println(project.properties["kotlin.code.style"])
        println(project.properties["android.defaults.buildfeatures.buildconfig"])
        println(project.properties["android.nonTransitiveRClass"])
        println(project.properties["android.nonFinalResIds"])
    }
}

abstract class Oranges : DefaultTask() {
    init {
        description = "An orange task"
        group = "fruits"
    }

    @get:Input
    @get:Optional
    abstract val count: Property<Int>

    @TaskAction
    fun run() {
        if (count.get() < 0)
            throw StopExecutionException("count cannot be negative!")
//            throw StopActionException("count cannot be negative!")
        println("Oranges : ${count.orNull ?: 5}")
    }
}

tasks.register("citrus") {
    doLast {
        println("Citrus")
    }
}

tasks.register<Oranges>("oranges") {
    dependsOn(tasks.findByPath("citrus"))
    count.set(-1)
}

// Register multiple tasks
class ApplesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("applesPlugin") {
            println("Hello form ApplesPlugin!")
        }
    }
}

apply<ApplesPlugin>()