package com.example.naejcorp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.AutoText;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

public class LibrosActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> librosArray;
    private StorageReference mStorageRef;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros);

        listView=findViewById(R.id.listviewLibros);
        librosArray=new ArrayList<>();

        //inicializacion del objeto en firebase storage
        mStorageRef=FirebaseStorage.getInstance().getReference();

        //traigo la referencia el bucket donde tengo guardados mis libros en pdf de FIREbase
        StorageReference ref=mStorageRef.child("libros");

        //codigo para traer todos los titulos de los libros

        ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                // Handle successful uploads on complete
                // ...

                //Log.e("libros", "Entrando a recuperar libros");
                for (StorageReference item : listResult.getItems()) {
                    librosArray.add(item.getName() + "");
                    //Log.e("Libro: --->>>",item.getPath()+"----"+item.getName());
                }

                //configuro el adaptador de la lista
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, librosArray);

                //muestro el adaptador en la vista
                listView.setAdapter(adapter);

                //se activa al clikear
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        final String titulo_libro_selecionado=listView.getItemAtPosition(position).toString();
                        //Abrir nueva activity
                        Intent i=new Intent(getApplicationContext(),Activity_visor_p_d_f.class);
                        i.putExtra("TITULO LIBRO", titulo_libro_selecionado);
                        startActivity(i);

                    }
                });

                /* listView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         int a= (int) listView.getSelectedItem();



                     }
                 }); */

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                AlertDialog.Builder builder1=new AlertDialog.Builder(LibrosActivity.this);
                builder1.setMessage("Ha ocurrido un error al cargar los libros. Revisa tu conexion a Internet");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        /* ListView listView2;
         FirebaseStorage firebaseStorage;
         StorageReference storageReference;

         listView2=findViewById(R.id.listviewLibros); */

        /* public void descargaArchivo(Context context, String fileName, String fileExtension, String destinationDirectory,String url){

             DownloadManager downloadManager= (DownloadManager).getSystemService(Context.DOWNLOAD_SERVICE);
             Uri uri=Uri.parse(url);
         } */

        /*storageRef=FirebaseStorage.getInstance().getReference();
        StorageReference ref2=storageRef.child("libros");

        ref2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {


            }
        });*/


        /*File localFile = null;
        try {
            localFile = File.createTempFile("libros", "pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStorageRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
            }
        });*/






        /*.addOnFailureListener((OnFailureListener) new AlertDialog.Builder(LibrosActivity.this)
                                .setMessage("Ha ocurrido un error al cargar los libros. Revisa tu conexion a internet")
                                 .setCancelable(true).setPositiveButton("Ok",null).show());*/





        /*ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                //Log.e("libros", "Entrando a recuperar libros");
                for (StorageReference item : listResult.getItems()) {
                    libros.add(item.getName() + "");
                    //Log.e("Libro: --->>>",item.getPath()+"----"+item.getName());
                }

                //configuro el adaptador de la lista
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, libros);

                //muestro el adaptador en la vista
                listView.setAdapter(adapter);
            }
        }).addOnFailureListener(
               AlertDialog.Builder builder_=new AlertDialog.Builder(LibrosActivity.this);
               builder_.setMessage("Ha ocurrido un error al cargar los libros. Revisa tu conexion a internet");
               builder_.setCancelable(true);
               builder_.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id){
                        dialog.cancel();
                    }
                });
                AlertDialog alert11 = builder_.create();
                alert11.show();
                );*/


        //libros.add("Libro 1");
        //libros.add("Libro 2");
        //libros.add("Libro 3");
        //libros.add("Libro 4");
        //libros.add("Libro 5");

        //ArrayAdapter <String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,libros);

        //listView.setAdapter(adapter);
    }
}
