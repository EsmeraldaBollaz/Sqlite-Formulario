package com.example.usuario.bdformulario;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Act1 extends AppCompatActivity {

    private Button btninsertar,btnSelect,btnDelete,btnUpdate,actividad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act1_main);
        btninsertar=findViewById(R.id.insert);
        btnSelect=findViewById(R.id.select);
        btnDelete=findViewById(R.id.delete);
        btnUpdate=findViewById(R.id.update);
        actividad2=findViewById(R.id.actividad2);

        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper helper = new DataBaseHelper(Act1.this,"Alumnos",null,1);
                SQLiteDatabase db= helper.getWritableDatabase();

                if(db!=null){
                    db.execSQL("INSERT INTO ALUMNOS values(1,'Esmeralda','Bollaz')");

                    Toast.makeText(Act1.this,"Registro Guardado",Toast.LENGTH_SHORT).show();

                    db.close();

                }

            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String registros="";
                DataBaseHelper helper = new DataBaseHelper(Act1.this,"Alumnos",null,1);
                SQLiteDatabase db= helper.getWritableDatabase();
                if(db!=null){
                    Cursor cursor = db.rawQuery("SELECT * FROM Alumnos",null);
                    if(cursor.moveToFirst()){
                        do{
                            registros += cursor.getInt(cursor.getColumnIndex("no_control"));
                            registros += cursor.getString(cursor.getColumnIndex("nombre"));
                            registros+="\n";
                        }while (cursor.moveToNext());
                    }
                    if(registros.equals("")){
                        Toast.makeText(Act1.this,"No hay registros",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(Act1.this,registros,Toast.LENGTH_SHORT).show();

                    db.close();

                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper helper = new DataBaseHelper(Act1.this,"Alumnos",null,1);
                SQLiteDatabase db= helper.getWritableDatabase();

                if(db!=null){
                    db.execSQL("DELETE FROM  ALUMNOS WHERE nombre='Esmeralda'");

                    Toast.makeText(Act1.this,"Registro eliminado",Toast.LENGTH_SHORT).show();

                    db.close();

                }
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper helper = new DataBaseHelper(Act1.this,"Alumnos",null,1);
                SQLiteDatabase db= helper.getWritableDatabase();

                if(db!=null){
                    db.execSQL("UPDATE ALUMNOS  set nombre='Bollaz1'WHERE nombre='Bollaz'");

                    Toast.makeText(Act1.this,"Registro actualizado",Toast.LENGTH_SHORT).show();

                    db.close();

                }
            }
        });

        actividad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Act1.this,Act2.class);
                startActivityForResult(intent,0);
            }
        });

    }
}

