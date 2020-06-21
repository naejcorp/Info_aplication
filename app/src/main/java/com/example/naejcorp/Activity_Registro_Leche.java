package com.example.naejcorp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naejcorp.modelo.RegistroLecheDiario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_Registro_Leche extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView litrosDiarios;
    private TextView fecha_leche;
    private TextView editText_litrosDiarios_Decimal;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Button guardar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__registro__leche);

        calendarView=findViewById(R.id.calendarView);
        litrosDiarios=findViewById(R.id.text_ingreso_litros_diarios);
        fecha_leche=findViewById(R.id.text_ingreso_fecha);
        editText_litrosDiarios_Decimal=findViewById(R.id.editText_ingreso_NumeroDecima_leche);
        guardar=findViewById(R.id.button_agregar_litros);

        auth = FirebaseAuth.getInstance();

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("registro_leche");

        //myRef.setValue("Hello, World!");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                final String fechaSeleccionada=dayOfMonth+"-"+(month+1)+"-"+year;
                myRef.child(auth.getCurrentUser().getUid()).child(fechaSeleccionada).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        RegistroLecheDiario registroLecheDiarioTemporal=dataSnapshot.getValue(RegistroLecheDiario.class);
                        if(registroLecheDiarioTemporal!=null){
                            litrosDiarios.setText(registroLecheDiarioTemporal.getLecheProducida()+"");
                            fecha_leche.setText(registroLecheDiarioTemporal.getFechaDia());

                        }else{

                            fecha_leche.setText(fechaSeleccionada);
                            litrosDiarios.setText(0+"");
                            //litrosDiarios.setText(editText_litrosDiarios_Decimal.getText());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                fecha_leche.setText(fechaSeleccionada);
                litrosDiarios.setText(editText_litrosDiarios_Decimal.getText());

            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarLecheDiaria(fecha_leche.getText().toString(),Double.parseDouble(editText_litrosDiarios_Decimal.getText().toString()));
            }
        });

    }

    public void guardarLecheDiaria(String fecha,Double litros){

        RegistroLecheDiario registroLecheDiario=new RegistroLecheDiario(fecha,litros);
        if (auth.getCurrentUser()!=null){
            myRef.child(auth.getCurrentUser().getUid()).child(fecha).setValue(registroLecheDiario).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(Activity_Registro_Leche.this,"Se ha guardado correctamente",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Activity_Registro_Leche.this,"No se ha guardado correctamente",Toast.LENGTH_SHORT).show();
                }
            });
        }else{

        }


    }

}