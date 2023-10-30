package pe.edu.idat.appfomsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pe.edu.idat.appfomsandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnirlistado.setOnClickListener(this);
        binding.btnirregistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnirlistado){
            startActivity(new Intent(MainActivity.this,
                    ListaActivity.class));
        }else if(v.getId() == R.id.btnirregistro){
            startActivity(new Intent(MainActivity.this,
                    RegistroActivity.class));
        }
    }
}