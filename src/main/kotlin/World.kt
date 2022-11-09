import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class World {
	private val dt = 1

	//val bodies = MutableStateFlow(Elements())
	private val _bodies = ArrayList<RigidBody>()
	val bodies = flow {
		while (currentCoroutineContext().isActive) {
			nextSequence()
			emit(_bodies)
			delay(dt * 1000L)
		}
	}

	fun addBox(x: Float, y: Float, width: Float, height: Float, angle: Float = 0f, mass: Float = 1f) {
		val inertia = calculateBoxInertia(mass, width, height)
		val shape = BoxShape(width, height, mass, inertia)
		val rigidBody = RigidBody(
			position = Vector2(x, y),
			linearVelocity = Vector2(0f, 0f),
			angle = angle,
			angularVelocity = 0f,
			force = Vector2(0f, 0f),
			torque = 0f,
			shape = shape
		)

		_bodies.add(rigidBody)
	}

	private fun calculateBoxInertia(mass: Float, width: Float, height: Float): Float {
		return mass * (width * width + height * height) / 12f
	}

	private fun nextSequence() {
		_bodies.forEachIndexed { idx, body ->
			val force = computeForce()
			val torque = computeTorque(body.shape.width, body.shape.height, force)
			val linearAcceleration = body.force / body.shape.mass
			val linearVelocity = (body.linearVelocity + linearAcceleration) * dt
			val position = (body.position + linearVelocity) * dt
			val angularAcceleration = body.torque / body.shape.momentOfInertia
			val angularVelocity = (body.angularVelocity + angularAcceleration) * dt
			val angle = angularVelocity * dt

			val updatedBody = body.copy(
				position = position,
				linearVelocity = linearVelocity,
				angle = angle,
				angularVelocity = angularVelocity,
				force = force,
				torque = torque
			)
			_bodies[idx] = updatedBody
		}
	}

	private fun computeForce(): Vector2 {
		// ad hoc
		return Vector2(0f, 100f)
	}

	private fun computeTorque(width: Float, height: Float, force: Vector2): Float {
		val rx = width / 2f
		val ry = height / 2f
		return rx * force.y - ry * force.x
	}
}
