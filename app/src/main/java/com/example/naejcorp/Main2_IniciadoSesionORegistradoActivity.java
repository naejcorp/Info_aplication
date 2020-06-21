package com.example.naejcorp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Main2_IniciadoSesionORegistradoActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseAuth ref;

    private Button login;
    private Button registrar;
    private Button cerrarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        login = findViewById(R.id.id_btn_iniciar_sesion);
        registrar = findViewById(R.id.id_btn_registrar);
        cerrarSesion = findViewById(R.id.id_cerrarSesion_btn);

        //login.setVisibility(View.VISIBLE);
        //registrar.setVisibility(View.VISIBLE);
        //cerrarSesion.setVisibility(View.GONE);

        //if (auth.getCurrentUser() != null) {
        //if (auth.getCurrentUser() != null) {
            //auth.signOut();
            login.setVisibility(View.GONE);
            registrar.setVisibility(View.GONE);
            cerrarSesion.setVisibility(View.VISIBLE);
        //}

    }

    public void irIniciar(View view) {
        Intent i = new Intent(this, IniciarSesionActivity.class);
        startActivity(i);
    }

    public void irRegistrarse(View view) {
        Intent i = new Intent(this, RegistrarseActivity.class);
        startActivity(i);
    }

    public void cerrarSesion(View view) {
        auth.signOut();
        if (auth.getCurrentUser() == null) {
            login.setVisibility(View.VISIBLE);
            registrar.setVisibility(View.VISIBLE);
            cerrarSesion.setVisibility(View.GONE);
            //btncerrarSesion.setVisibility(View.GONE);
        }
    }


    public void irLibros(View view) {

        if (auth.getCurrentUser() != null) {
            Intent i = new Intent(this, LibrosActivity.class);
            startActivity(i);
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2_IniciadoSesionORegistradoActivity.this);
            builder1.setMessage("Debes registrarte o iniciar sesion para acceder a material grafico");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Ok",
                    null);
            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
    }

    public void irRegistroLeche(View view) {

        if (auth.getCurrentUser() != null) {
            Intent i = new Intent(this, Activity_Registro_Leche.class);
            startActivity(i);
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2_IniciadoSesionORegistradoActivity.this);
            builder1.setMessage("Debes registrarte o iniciar sesion para acceder a registrar la produccion de leche diaria de tu finca.");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Ok",
                    null);
            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
    }

    public void irNoticias(View view) {
        if (auth.getCurrentUser() != null) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hermanodeleche.com/topic/54825-natasha-ecuatoriana/"));
            startActivity(i);
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2_IniciadoSesionORegistradoActivity.this);
            builder1.setMessage("Debes registrarte o iniciar sesion para acceder a material grafico");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Ok",
                    null);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    public void irConsejos(View view) {
        if (auth.getCurrentUser() != null) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hermanodeleche.com/topic/75554-mirella/"));
            startActivity(i);
        }else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2_IniciadoSesionORegistradoActivity.this);
            builder1.setMessage("Debes registrarte o iniciar sesion para acceder a material grafico");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Ok",
                    null);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }

    public void irRazas(View view){
        if (auth.getCurrentUser() != null) {
            Intent i=new Intent( Intent.ACTION_VIEW, Uri.parse("https://hermanodeleche.com/topic/15148-maria-jose-ecuatoriana-en-el-6/page/2/"));
            startActivity(i);
        }else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2_IniciadoSesionORegistradoActivity.this);
            builder1.setMessage("Debes registrarte o iniciar sesion para acceder a material grafico");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Ok",
                    null);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    public void irTipos(View view){
        if (auth.getCurrentUser() != null) {
            Intent i=new Intent( Intent.ACTION_VIEW, Uri.parse("https://hermanodeleche.com/topic/4755-noelia-cuarto-107-de-local-chico-7/page/5/"));
            startActivity(i);
        }else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2_IniciadoSesionORegistradoActivity.this);
            builder1.setMessage("Debes registrarte o iniciar sesion para acceder a material grafico");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Ok",
                    null);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}
