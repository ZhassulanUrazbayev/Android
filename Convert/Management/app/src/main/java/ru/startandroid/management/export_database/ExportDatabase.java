package ru.startandroid.management.export_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;

import au.com.bytecode.opencsv.CSVWriter;
import ru.startandroid.management.DatabaseHelper2;

public class ExportDatabase {
    Context context;
    public ExportDatabase(Context context) {
        this.context=context;
    }


    public void exportDataBaseIntoCSV(){


        DatabaseHelper2 db = new DatabaseHelper2(context);
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "Inventories.csv");

        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase sql_db = db.getReadableDatabase();
            Cursor curCSV = sql_db.rawQuery("select serial_numb as 'Инвентарный номер',fullname as 'Наименование'," +
                    "obj_name as 'Объект'," +
                    " uchet_date as 'Дата принятие к учету',amor_gr as 'Срок полезного использование'," +
                    "cab_number as 'Кабинет',korp_name as 'Корпус'," +
                    "stat_name as 'Статус',cond_name as 'Состояние' from inventories\n" +
                    "inner join objects on inventories.obj_id = objects.obj_id\n" +
                    "inner join cabinet on inventories.room_id = cabinet.cab_id\n" +
                    "inner join korpus on cabinet.incorp = korpus.korp_id\n" +
                    "inner join Status on inventories.stat_id = Status.stat_id\n" +
                    "inner join Condition on inventories.con_id = Condition.cond_id;",null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while(curCSV.moveToNext()) {
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2),
                        curCSV.getString(3),curCSV.getString(4), curCSV.getString(5),
                        curCSV.getString(6),curCSV.getString(7), curCSV.getString(8)
                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }
    public void exportByEmployee(){


        DatabaseHelper2 db = new DatabaseHelper2(context);
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        Log.d("FUN",Environment.getExternalStorageDirectory().getAbsolutePath());
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "InventoryByEmployee.csv");

        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase sql_db = db.getReadableDatabase();
            Cursor curCSV = sql_db.rawQuery("select firstname as 'Имя',lastname as 'Фамилия',serial_numb as 'Инвентарный номер',fullname as 'Наименование',\n" +
                    "obj_name as 'Объект',uchet_date as 'Дата принятие к учету',\n" +
                    "amor_gr as 'Срок полезного использование',cab_number as 'Кабинет',korp_name as 'Корпус',\n" +
                    "stat_name as 'Статус',cond_name as 'Состояние' from inventories\n" +
                    "inner join objects on inventories.obj_id = objects.obj_id\n" +
                    "inner join cabinet on inventories.room_id = cabinet.cab_id\n" +
                    "inner join korpus on cabinet.incorp = korpus.korp_id\n" +
                    "inner join Status on inventories.stat_id = Status.stat_id\n" +
                    "inner join Condition on inventories.con_id = Condition.cond_id\n" +
                    "inner join employees on employees.emp_id = cabinet.emp_id;",null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while(curCSV.moveToNext()) {
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2),
                        curCSV.getString(3),curCSV.getString(4), curCSV.getString(5),
                        curCSV.getString(6),curCSV.getString(7), curCSV.getString(8),
                        curCSV.getString(9), curCSV.getString(10)

                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }
    public void exportByCategory(){


        DatabaseHelper2 db = new DatabaseHelper2(context);
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        Log.d("FUN",Environment.getExternalStorageDirectory().getAbsolutePath());
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "InventoriesByObject.csv");

        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase sql_db = db.getReadableDatabase();
            Cursor curCSV = sql_db.rawQuery("select obj_name as 'Объект',serial_numb as 'Инвентарный номер',fullname as 'Наименование',\n" +
                    "uchet_date as 'Дата принятие к учету',\n" +
                    "amor_gr as 'Срок полезного использование',cab_number as 'Кабинет',korp_name as 'Корпус',\n" +
                    "stat_name as 'Статус',cond_name as 'Состояние' from inventories\n" +
                    "inner join objects on inventories.obj_id = objects.obj_id\n" +
                    "inner join cabinet on inventories.room_id = cabinet.cab_id\n" +
                    "inner join korpus on cabinet.incorp = korpus.korp_id\n" +
                    "inner join Status on inventories.stat_id = Status.stat_id\n" +
                    "inner join Condition on inventories.con_id = Condition.cond_id\n" +
                    "inner join employees on employees.emp_id = cabinet.emp_id;",null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while(curCSV.moveToNext()) {
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2),
                        curCSV.getString(3),curCSV.getString(4), curCSV.getString(5),
                        curCSV.getString(6),curCSV.getString(7), curCSV.getString(8)

                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }
    public void exportByCabinet(){


        DatabaseHelper2 db = new DatabaseHelper2(context);
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        Log.d("FUN",Environment.getExternalStorageDirectory().getAbsolutePath());
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "InventoriesByCabinet.csv");

        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase sql_db = db.getReadableDatabase();
            Cursor curCSV = sql_db.rawQuery("select cab_number as 'Кабинет',korp_name as 'Корпус',serial_numb as 'Инвентарный номер',fullname as 'Наименование',\n" +
                    "obj_name as 'Объект',uchet_date as 'Дата принятие к учету',\n" +
                    "amor_gr as 'Срок полезного использование',\n" +
                    "stat_name as 'Статус',cond_name as 'Состояние' from inventories\n" +
                    "inner join objects on inventories.obj_id = objects.obj_id\n" +
                    "inner join cabinet on inventories.room_id = cabinet.cab_id\n" +
                    "inner join korpus on cabinet.incorp = korpus.korp_id\n" +
                    "inner join Status on inventories.stat_id = Status.stat_id\n" +
                    "inner join Condition on inventories.con_id = Condition.cond_id\n" +
                    "inner join employees on employees.emp_id = cabinet.emp_id;",null);
            csvWrite.writeNext(curCSV.getColumnNames());

            while(curCSV.moveToNext()) {
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2),
                        curCSV.getString(3),curCSV.getString(4), curCSV.getString(5),
                        curCSV.getString(6),curCSV.getString(7), curCSV.getString(8)

                };
                csvWrite.writeNext(arrStr);
            }

            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
    }

}
