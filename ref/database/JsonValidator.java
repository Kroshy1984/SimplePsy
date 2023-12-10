//package ru.sfedu.simplepsy.database;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.JsonSchema;
//import com.fasterxml.jackson.databind.node.JsonSchemaFactory;
//
//public class JsonValidator {
//
//    public static void main(String[] args)
//    {
//        String url = "jdbc:your_database_url";
//        String username = "your_username";
//        String password = "your_password";
//        Connection conn;
//        PreparedStatement stat;
//
//        try
//        {
//            conn = DriverManager.getConnection(url, username, password);
//            //Получаем схему
//            stat = conn.prepareStatement("SELECT schema FROM schemas WHERE name = ?");
//            stat.setString(1, "your_schema_name");
//            ResultSet rs = stat.executeQuery();
//
//            if (rs.next())
//            {
//                String schema = rs.getString("schema");
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode schemaNode = mapper.readTree(schema);
//                JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
//                JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);
//
//                //Получаем данные на проверку
//                PreparedStatement dataStat = conn.prepareStatement("SELECT data FROM data WHERE id = ?");
//                dataStat.setInt(1, 1);
//                ResultSet dataRs = dataStat.executeQuery();
//
//
//                //Проверка
//                if (dataRs.next())
//                {
//                    String data = dataRs.getString("data");
//                    JsonNode dataNode = mapper.readTree(data);
//                    jsonSchema.validate(dataNode);
//                    System.out.println("JSON is valid");
//                }
//                else
//                {
//                    System.out.println("Data not found");
//                }
//            }
//
//            else
//            {
//                System.out.println("Schema not found");
//            }
//        }
//        catch (SQLException e)
//        {
//            System.out.println("SQL error: " + e.getMessage());
//        }
//        catch (Exception e)
//        {
//            System.out.println("Invalid JSON: " + e.getMessage());
//        }
//    }
//}
