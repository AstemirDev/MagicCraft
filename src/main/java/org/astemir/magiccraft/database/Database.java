package org.astemir.magiccraft.database;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.utils.Pair;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.sqlite.JDBC;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Database {

    private Connection connection;
    private DatabaseField[] fields;
    private String name;

    public Database(String databaseName) {
        this.name = databaseName;
    }

    public void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            String path = MagicCraft.getPlugin(MagicCraft.class).getDataFolder().getAbsolutePath();
            File dir = new File(path);
            if (!dir.exists()){
                dir.mkdirs();
            }
            connection = DriverManager.getConnection("jdbc:sqlite:"+path+"/"+name+".db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(boolean createIfNotExists,DatabaseField... fields){
        try {
            this.fields = fields;
            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE ";
            if (createIfNotExists){
                sql+="IF NOT EXISTS ";
            }
            sql+=name+ "(";

            for (int i = 0;i<fields.length;i++){
                DatabaseField field = fields[i];
                sql+=field.toString();

                if (i != fields.length-1){
                    sql+=",";
                }
            }
            sql+=")";

            statement.execute(sql);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public ResultSet getValues(@Nullable Pair<String,Object> where, String... keys){
        String sql = "SELECT ";
        for (int i = 0;i<keys.length;i++){
            sql+=keys[i];
            if (i != keys.length -1){
                sql+=",";
            }
        }
        sql+=" FROM "+name;
        if (where != null) {
            sql += " WHERE " + where.key + "='" + where.value + "'";
        }
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ResultSet getAllValues(){
        return getAllValues(null);
    }

    public ResultSet getAllValues(@Nullable Pair<String,Object> where){
        String sql = "SELECT * FROM "+name;
        if (where != null) {
            sql += " WHERE " + where.key + "='" + where.value + "'";
        }
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public void updateValues(Pair<String,Object> where,Pair<String,Object>... pairs){
        String sql = "UPDATE "+name+" SET ";
        for (int i = 0;i<pairs.length;i++){
            sql+=pairs[i].key+"='"+pairs[i].value+"'";
            if (i != pairs.length -1){
                sql+=",";
            }
        }
        sql+=" WHERE "+where.key+"='"+where.value+"'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertValues(Pair<String,Object>... pairs){
        String sql = "INSERT INTO "+name;
        String keys = "(";
        String values = "(";
        for (int i = 0;i<pairs.length;i++){
            keys+=pairs[i].key;
            values+="'"+pairs[i].value+"'";
            if (i != pairs.length -1){
                keys+=",";
                values+=",";
            }
        }
        values+=")";
        keys+=")";
        sql+=keys+" VALUES"+values;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close() throws SQLException {
        connection.close();
    }

}
