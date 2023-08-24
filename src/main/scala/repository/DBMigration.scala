package repository

import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.database.DatabaseFactory
import liquibase.resource.ClassLoaderResourceAccessor
import repository.DBInit.{ password, url, username }

import java.sql.DriverManager

object DBMigration {
  def migrate(): Unit = {
    val con = DriverManager.getConnection(url, username, password)
    val database = DatabaseFactory.getInstance.findCorrectDatabaseImplementation(new JdbcConnection(con))
    val liquibase = new Liquibase("db-changelog.xml", new ClassLoaderResourceAccessor, database)

    liquibase.update("")
    con.commit()
    con.close()
    println("Run liquibase successfully")
  }
}
