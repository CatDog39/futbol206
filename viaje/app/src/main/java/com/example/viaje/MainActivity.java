package com.example.viaje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    EditText ValorP,ValorT,CodigoViaje,NumP;
    RadioButton Cartagena,SanAndres,LaGuajira;
   String valorp,valort,codigoviaje, nump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ValorP=findViewById(R.id.ValorP);
        ValorT=findViewById(R.id.ValorT);
        CodigoViaje=findViewById(R.id.CodigoViaje);
        NumP=findViewById(R.id.numP);
        Cartagena=findViewById(R.id.Cartagena);
        SanAndres=findViewById(R.id.SanAndres);
        LaGuajira=findViewById(R.id.LaGuajira);
    }

    public void Guardar(View view){
        valort=ValorT.getText().toString();
        valorp=ValorP.getText().toString();
        codigoviaje=CodigoViaje.getText().toString();
        nump=NumP.getText().toString();
        Cartagena.isChecked();
        SanAndres.isChecked();
        LaGuajira.isChecked();
        if(valort.isEmpty()||valorp.isEmpty()||codigoviaje.isEmpty())



    }

}