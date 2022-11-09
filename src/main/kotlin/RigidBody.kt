data class RigidBody(
	val position: Vector2,
	val linearVelocity: Vector2,
	val angle: Float,
	val angularVelocity: Float,
	val force: Vector2,
	val torque: Float,
	val shape: BoxShape
)
