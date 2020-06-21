package com.example.naejcorp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Activity_visor_p_d_f extends AppCompatActivity {

    public final static long ONE_MEGABYTE=1024*1024*20;

    private String NombreLibro;
    private  PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_p_d_f);

        NombreLibro=getIntent().getStringExtra("TITULO LIBRO");

        pdfView=findViewById(R.id.pdfView);

        FirebaseStorage mFirebaseStorage=FirebaseStorage.getInstance();
        StorageReference mStorageReference=mFirebaseStorage.getReference().child("libros");

        mStorageReference.child(NombreLibro).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                pdfView.fromBytes(bytes).load();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Activity_visor_p_d_f.this, "dowload no cargo correctamente",Toast.LENGTH_LONG).show();
            }
        });

    }
}
