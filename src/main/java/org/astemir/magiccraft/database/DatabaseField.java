package org.astemir.magiccraft.database;

public class DatabaseField {

    private DatabaseFieldType type = DatabaseFieldType.INT;
    private String fieldName;

    private int length = -1;

    private boolean notNull = false;
    private boolean primaryKey = false;
    private boolean autoIncrement = false;

    public DatabaseField(DatabaseFieldType type,String fieldName){
        this.type = type;
        this.fieldName = fieldName;
    }

    public static DatabaseField init(DatabaseFieldType type,String fieldName){
        return new DatabaseField(type,fieldName);
    }

    public DatabaseField length(int length){
        this.length = length;
        return this;
    }

    public DatabaseField notNull(){
        notNull = true;
        return this;
    }

    public DatabaseField primaryKey(){
        this.primaryKey = true;
        return this;
    }

    public DatabaseField autoIncrement(){
        this.autoIncrement = true;
        return this;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(fieldName+" ");
        builder.append(type.toString()+" ");
        if (primaryKey){
            builder.append("PRIMARY KEY"+" ");
        }
        if (autoIncrement){
            builder.append("AUTOINCREMENT"+" ");
        }
        if (notNull){
            builder.append("NOT NULL"+" ");
        }
        return builder.toString();
    }
}
