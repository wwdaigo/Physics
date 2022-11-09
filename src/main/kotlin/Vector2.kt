data class Vector2(
	val x: Float,
	val y: Float
) {
	operator fun plus(other: Vector2): Vector2 {
		return Vector2(x + other.x, y + other.y)
	}

	operator fun times(scalar: Int): Vector2 {
		return Vector2(x * scalar, y * scalar)
	}

	operator fun div(scalar: Float): Vector2 {
		return Vector2(x /scalar, y / scalar)
	}
}