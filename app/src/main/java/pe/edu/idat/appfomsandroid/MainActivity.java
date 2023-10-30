package pe.edu.idat.appfomsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pe.edu.idat.appfomsandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    private ActivityMainBinding binding;// crea una variable que será utilizada
    // para acceder a los elementos (como botones, texto, imágenes, etc.)

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Almacena info del estado anterior
        super.onCreate(savedInstanceState);  //Inicializacion y configuracion

        //Se crea una variable, y ahora sabe cómo acceder a todas las vistas y elementos en tu diseño
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // vincula el diseño a la pantalla de la actividad.
        setContentView(binding.getRoot());

        //configuran escuchadores de clic
        binding.btnirlistado.setOnClickListener(this);
        binding.btnirregistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Si se hace clic en el botón "Ir al listado",
        // se inicia la actividad ListaActivity.
        if(v.getId() == R.id.btnirlistado){
            startActivity(new Intent(MainActivity.this,
                    ListaActivity.class));
        //...
        }else if(v.getId() == R.id.btnirregistro){
            startActivity(new Intent(MainActivity.this,
                    RegistroActivity.class));
        }
    }
}