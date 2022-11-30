package com.example.paseo_jueves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText jetcodigo,jetnombre,jetciudad,jetcantidad;
    CheckBox jcbactivo;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String codigo,nombre,ciudad,cantidad,activo,codigo_id;
    byte sw;

    //Ocultar la barra de titulos por defecto y asociar objetos Java con Xml



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ocultar la barra de titulos por defecto y asociar objetos Java con Xml

        getSupportActionBar().hide();
        jetcodigo=findViewById(R.id.etcodigo);
        jetnombre=findViewById(R.id.etnombre);
        jetciudad=findViewById(R.id.etciudad);
        jetcantidad=findViewById(R.id.etcantidad);
        jcbactivo=findViewById(R.id.cbactivo);
        sw=0;


    }
    public void Adicionar(View view){
        codigo=jetcodigo.getText().toString();
        nombre=jetnombre.getText().toString();
        ciudad=jetciudad.getText().toString();
        cantidad=jetcantidad.getText().toString();
        if (codigo.isEmpty() || nombre.isEmpty() || ciudad.isEmpty() || cantidad.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetcodigo.requestFocus();
        }
        else{
            // Create a new user with a first and last name
            Map<String, Object> user = new HashMap<>();
            user.put("codigo", codigo);
            user.put("nombre", nombre);
            user.put("ciudad", ciudad);
            user.put("cantidad", cantidad);
            user.put("activo", "si");
            // Add a new document with a generated ID
            db.collection("empresajueves")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            Limpiar_campos();
                            Toast.makeText(MainActivity.this, "Documento guardado", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Error guardando documento", Toast.LENGTH_SHORT).show();
                            // Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
    }

    private void Limpiar_campos(){
        jetcodigo.setText("");
        jetcantidad.setText("");
        jetciudad.setText("");
        jetnombre.setText("");
        jetcodigo.requestFocus();
        sw=0;
    }

   //  Código método Consultar

    public void Consultar(View view){
        codigo=jetcodigo.getText().toString();
        if (codigo.isEmpty()){
            Toast.makeText(this, "Codigo es requerido", Toast.LENGTH_SHORT).show();
            jetcodigo.requestFocus();
        }
        else{
            db.collection("empresajueves")
                    .whereEqualTo("codigo", codigo)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    // Log.d(TAG, document.getId() + " => " + document.getData());

                                    sw=1;
                                    codigo_id=document.getId();
                                    jetnombre.setText(document.getString("nombre"));
                                    jetciudad.setText(document.getString("ciudad"));
                                    jetcantidad.setText(document.getString("cantidad"));
                                    if (document.getString("activo").equals("si"))
                                        jcbactivo.setChecked(true);
                                    else
                                        jcbactivo.setChecked(false);
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Documento no hallado", Toast.LENGTH_SHORT).show();
                                //Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
    }
  //  Código Java modificar

    public void Modificar(View view){
        if (sw == 0){
            Toast.makeText(this, "Debe primero consultar", Toast.LENGTH_SHORT).show();
            jetcodigo.requestFocus();
        }
        else{
            codigo=jetcodigo.getText().toString();
            nombre=jetnombre.getText().toString();
            ciudad=jetciudad.getText().toString();
            cantidad=jetcantidad.getText().toString();
            if (codigo.isEmpty() || nombre.isEmpty() || ciudad.isEmpty() || cantidad.isEmpty()){
                Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
                jetcodigo.requestFocus();
            }
            else{
                // Update a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("codigo", codigo);
                user.put("nombre", nombre);
                user.put("ciudad", ciudad);
                user.put("cantidad", cantidad);
                user.put("activo", "si");
                // Update a new document with a generated ID
                db.collection("empresajueves").document(codigo_id)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this,"Estudiante actualizado correctmente...",Toast.LENGTH_SHORT).show();
                                Limpiar_campos();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this,"Error actualizando estudiante...",Toast.LENGTH_SHORT).show();
                            }});
            }
        }
    }

    //  Código Java Eliminar

    public void Eliminar (View view){
        if (sw == 0){
            Toast.makeText(this, "Debe primero consultar", Toast.LENGTH_SHORT).show();
            jetcodigo.requestFocus();
        }
        else{
            codigo=jetcodigo.getText().toString();
            nombre=jetnombre.getText().toString();
            ciudad=jetciudad.getText().toString();
            cantidad=jetcantidad.getText().toString();
            if (codigo.isEmpty() || nombre.isEmpty() || ciudad.isEmpty() || cantidad.isEmpty()){
                Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
                jetcodigo.requestFocus();
            }
            else{
                // Update a new document with a generated ID
                db.collection("empresajueves").document(codigo_id)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this,"Estudiante actualizado correctmente...",Toast.LENGTH_SHORT).show();
                                Limpiar_campos();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this,"Error actualizando estudiante...",Toast.LENGTH_SHORT).show();
                            }});
            }
        }
    }

    public void Anular (View view){
        if (sw == 0){
            Toast.makeText(this, "Debe primero consultar", Toast.LENGTH_SHORT).show();
            jetcodigo.requestFocus();
        }
        else{
            codigo=jetcodigo.getText().toString();
            nombre=jetnombre.getText().toString();
            ciudad=jetciudad.getText().toString();
            cantidad=jetcantidad.getText().toString();
            if (codigo.isEmpty() || nombre.isEmpty() || ciudad.isEmpty() || cantidad.isEmpty()){
                Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
                jetcodigo.requestFocus();
            }
            else{
                // Update a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("codigo", codigo);
                user.put("nombre", nombre);
                user.put("ciudad", ciudad);
                user.put("cantidad", cantidad);
                user.put("activo", "no");
                // Update a new document with a generated ID
                db.collection("empresajueves").document(codigo_id)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this,"Estudiante actualizado correctmente...",Toast.LENGTH_SHORT).show();
                                Limpiar_campos();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this,"Error actualizando estudiante...",Toast.LENGTH_SHORT).show();
                            }});
            }
        }
    }
    public void Cancelar (View view){
            Limpiar_campos();
    }


}

