import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
	val world = World()
	world.addBox(10f, 10f, 100f, 50f, angle = 30f, mass = 10f)

	world.bodies.collect {
		println(it)
	}
}