import scala.io.StdIn.readLine

object Main extends App {
  val inventario = new Inventario()

  println("Inventario actual:")
  inventario.mostrarProductos()

  var continuar = true
  while (continuar) {
    println("Ingrese el nombre del producto:")
    val nombre = readLine()
    println("Ingrese la cantidad del producto:")
    val cantidad = readLine().toInt

    val producto = Producto(nombre, cantidad)
    inventario.agregarProducto(producto)

    println(s"Producto agregado: $producto")

    println("¿Desea agregar otro producto? (s/n)")
    continuar = readLine().toLowerCase == "s"
  }

  println("Inventario actualizado:")
  inventario.mostrarProductos()
}

