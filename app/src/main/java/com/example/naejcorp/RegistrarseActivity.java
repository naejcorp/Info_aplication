package com.example.naejcorp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarseActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private EditText correo_registrarse;
    private  EditText contrasena_registrarse;
    private EditText contrasenaConfirmacion_registrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        mAuth = FirebaseAuth.getInstance();

        correo_registrarse=findViewById(R.id.id_correo_Registrarse_Activity);
        contrasena_registrarse=findViewById(R.id.id_contrasena_Registrarse_Activity);
        contrasenaConfirmacion_registrase=findViewById(R.id.id_contrasenaConfirmacion_Activity);

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

    }

    public void registrarUsuario(View view){

        if(contrasena_registrarse.getText().toString().trim().equals(contrasenaConfirmacion_registrase.getText().toString().trim())){

            mAuth.createUserWithEmailAndPassword(correo_registrarse.getText().toString().trim(), contrasena_registrarse.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "createUserWithEmail:success");
                                Toast.makeText(getApplicationContext(), "Usuario creado.",
                                        Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                                Intent i=new Intent(getApplicationContext(),Main2_IniciadoSesionORegistradoActivity.class);
                                startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Autentificación fallida.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });

        }else{
            Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }

    }

}
