// 2D box shape. Physics engines usually have a couple different classes of shapes
// such as circles, spheres (3D), cylinders, capsules, polygons, polyhedrons (3D)...

data class BoxShape(
	val width: Float,
	val height: Float,
	val mass: Float,
	val momentOfInertia: Float = 0f
)
