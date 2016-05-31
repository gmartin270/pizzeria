package org.guille.pizzeria.util;

import java.util.Properties;

import org.guille.pizzeria.model.Cliente;
import org.guille.pizzeria.model.Combo;
import org.guille.pizzeria.model.DetallePedido;
import org.guille.pizzeria.model.GenericObject;
import org.guille.pizzeria.model.Pedido;
import org.guille.pizzeria.model.Producto;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;



public class SqlSchemaGenerator {

	public static void main(String[] args) {
		Configuration config = new Configuration();
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/PIZZERIA"); 
		properties.put("hibernate.connection.username", "root");
		properties.put("hibernate.connection.password", "patoxkgm");
		properties.put("hibernate.connection.driver_class", "org.gjt.mm.mysql.Driver");
		properties.put("hibernate.show_sql", "true");
		config.setProperties(properties);
		
		config.addAnnotatedClass(GenericObject.class);
		config.addAnnotatedClass(Cliente.class);
		config.addAnnotatedClass(Producto.class);
		config.addAnnotatedClass(Combo.class);
		config.addAnnotatedClass(Pedido.class);
		config.addAnnotatedClass(DetallePedido.class);
		
		SchemaExport schemaExport = new SchemaExport(config);
		schemaExport.setDelimiter(";");

		schemaExport.setOutputFile("src/main/resources/Schema.sql");
		schemaExport.setFormat(true);
		schemaExport.execute(true, false, false, false);
	}
}
