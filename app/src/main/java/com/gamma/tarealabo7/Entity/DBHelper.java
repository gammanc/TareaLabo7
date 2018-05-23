package com.gamma.tarealabo7.Entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.gamma.tarealabo7.Beans.Nota;

import java.util.ArrayList;

/**
 * Created by UCA on 17/05/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "bd_notas";
    public static String TABLE_GRADES = "Notas";
    public static String ROW_ID = "carnet";
    public static String ROW_SUBJECT = "materia";
    public static String ROW_PROFESSOR = "catedratico";
    public static String ROW_GRADE = "nota";

    public static String CREATE_TB_USERS = "CREATE TABLE "+TABLE_GRADES+"("+
            ROW_ID+" TEXT, "+
            ROW_SUBJECT +" TEXT, "+
            ROW_PROFESSOR +" TEXT, "
            + ROW_GRADE +" TEXT)";

    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        db=this.getWritableDatabase();
    }

    public static DBHelper getInstance(Context c){
        if (myDB == null) myDB = new DBHelper(c.getApplicationContext());
        return myDB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ROW_ID);
    }

    public boolean add(Nota p){
        ContentValues values = new ContentValues();
        values.put(ROW_ID, p.getCarnet());
        values.put(ROW_SUBJECT, p.getMateria());
        values.put(ROW_PROFESSOR, p.getCatedratico());
        values.put(ROW_GRADE, p.getNota());

        db.insert(TABLE_GRADES, null, values);
        //Toast.makeText(context, "Insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public Nota findGrade(String carnet){
        Nota nota = null;

        String[] parametros ={carnet};
        String[] campos ={ROW_SUBJECT, ROW_PROFESSOR, ROW_GRADE};

        try {
            Cursor cursor = db.query(TABLE_GRADES, campos, ROW_ID+"=?",parametros,
                    null, null, null);
            cursor.moveToFirst();
                nota = new Nota(carnet, cursor.getString(0),
                        cursor.getString(1), cursor.getDouble(2));

        }catch (Exception e){
            nota = null;
        }
        return nota;
    }

    public ArrayList<Nota> findGrades(){
        ArrayList<Nota> notas = new ArrayList<>();

        String[] campos ={ROW_ID,ROW_SUBJECT, ROW_PROFESSOR, ROW_GRADE};

        try {
            Cursor cursor = db.query(TABLE_GRADES, campos, null,null,
                    null, null, null);
            while (cursor.moveToNext()){
                notas.add(new Nota(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3)));
            }
            cursor.close();

        }catch (Exception e){
            notas = null;
        }
        return notas;
    }

    public boolean editGrade(String carnet, double nota){
        String[] parametros = {carnet};
        String[] campos = {ROW_GRADE};
        ContentValues values = new ContentValues();
        values.put(ROW_GRADE, nota);
        db.update(TABLE_GRADES, values, ROW_ID+"=?", parametros);
        Toast.makeText(context, "Actualizado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }


    public boolean deleteAll(){
        db.delete(TABLE_GRADES, null,null);
        Toast.makeText(context, "Eliminado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }
}
