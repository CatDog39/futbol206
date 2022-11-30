package com.example.vehiculo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getSupportActionBar().hide();
        }

        public void Vehiculo (View view){
            Intent intvehiculo = new Intent(this, VehiculoActivity.class);
            startActivity(intvehiculo);
        }

        public void Factura (View view){
            Intent intfactura = new Intent(this, FacturaActivity.class);
            startActivity(intfactura);
        }
    }