import java.sql._

class Inventario {
  private val URL = "jdbc:sqlserver://localhost:1433;database=Inventario;encrypt=true;trustServerCertificate=true"
  private val USER = "sa"
  private val PSWRD = "123456"
  private var connection: Connection = _

  def conectar(): Unit = {
//    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    connection = DriverManager.getConnection(URL, USER, PSWRD)
  }

  def desconectar(): Unit = {
    if (connection != null) {
      connection.close()
    }
  }

  def agregarProducto(producto: Producto): Unit = {
    conectar()
    try {
      val statement = connection.prepareStatement("INSERT INTO producto (nombre, cantidad) VALUES (?, ?)")
      statement.setString(1, producto.nombre)
      statement.setInt(2, producto.cantidad)
      statement.executeUpdate()
    } finally {
      desconectar()
    }
  }

  def mostrarProductos(): Unit = {
    conectar()
    try {
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM producto")
      while (resultSet.next()) {
        val id=resultSet.getInt("id")
        val nombre = resultSet.getString("nombre")
        val cantidad = resultSet.getInt("cantidad")
        println(s"Id: $id,Nombre: $nombre, Cantidad: $cantidad")
      }
    } finally {
      desconectar()
    }
  }
}
