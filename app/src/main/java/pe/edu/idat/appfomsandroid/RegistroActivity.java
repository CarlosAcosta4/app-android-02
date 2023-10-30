package pe.edu.idat.appfomsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import pe.edu.idat.appfomsandroid.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity
 implements AdapterView.OnItemSelectedListener,//se utiliza para manejar eventos de selección en un AdapterView (como un Spinner)
        View.OnClickListener {

    private ActivityRegistroBinding binding;
    private String estadocivil ="";
    private List<String> preferencias = new ArrayList<>();
    private List<String> personas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //se crea un adaptador (AdapterArray) para un Spinner.
        ArrayAdapter<CharSequence> adapterSpinner =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.estado_civil,
                        android.R.layout.simple_spinner_item
                );

        //escuchadores
        binding.spestadocivil.setAdapter(adapterSpinner);//se utilizarán las opciones definidas en el adaptador para mostrar las selecciones en el Spinner
        binding.spestadocivil.setOnItemSelectedListener(this);//Cuando se selecciona un elemento en el Spinner, se ejecutará el método onItemSelected
        binding.cbdeporte.setOnClickListener(this);
        binding.cbarte.setOnClickListener(this);
        binding.cbotros.setOnClickListener(this);
        binding.btnregistrar.setOnClickListener(this);
        binding.btnlistapersona.setOnClickListener(this);

    }

    private String obtenerGenero(){
        String genero = "";
        if(binding.rggenero.getCheckedRadioButtonId() == R.id.rbmasculino){
            genero = binding.rbmasculino.getText().toString();
        }else{
            genero = binding.rbfemenino.getText().toString();
        }
        return genero;
    }

    private void agregarQuitarPreferencia(View view, String preferencia){
        boolean checked = ((CheckBox)view).isChecked();
        if(checked)
            preferencias.add(preferencia);
        else
            preferencias.remove(preferencia);

    }


    private Boolean validarNombre(){
        boolean respuesta = true;
        if(binding.etnombre.getText().toString().trim().isEmpty()){
            binding.etnombre.setFocusableInTouchMode(true);
            binding.etnombre.requestFocus();
            respuesta = false;
        }
        return respuesta;
    }

    private Boolean validarApellido() {
        boolean respuesta = true;
        if (binding.etapellido.getText().toString().trim().isEmpty()) {
            binding.etapellido.setFocusableInTouchMode(true);
            binding.etapellido.requestFocus();
            respuesta = false;
        }
        return respuesta;
    }

    private Boolean validarGenero(){
        boolean respuesta = true;
        if(binding.rggenero.getCheckedRadioButtonId() == -1){
            respuesta = false;
        }
        return respuesta;
    }

    private Boolean validarPreferencias(){
        boolean respuesta = false;
        if(binding.cbdeporte.isChecked() || binding.cbarte.isChecked()
            || binding.cbotros.isChecked()){
            respuesta = true;
        }
        return respuesta;
    }

    private Boolean validarEstadoCivil(){
        boolean respuesta = true;
        if(estadocivil.equals("")){
            respuesta = false;
        }
        return  respuesta;
    }

    private Boolean validarFormulario(){
        boolean respuesta = false;
        String mensaje = "";
        if(!validarNombre()){
            mensaje ="Ingrese su nombre ";
        }else if(!validarApellido()){
            mensaje ="Ingrese su  apellido";
        }else if(!validarGenero()){
            mensaje = "Seleccione su género.";
        }else if(!validarPreferencias()){
            mensaje ="Seleccione almenos una preferencia.";
        }else if(!validarEstadoCivil()){
            mensaje ="Seleccione un estado civil";
        }else
            respuesta = true;
        if(!respuesta)
            Snackbar.make(binding.getRoot(), mensaje, Toast.LENGTH_LONG + 2000).show();
        return respuesta;
    }

    private void registrarPersona(){
        if(validarFormulario()){
            StringBuilder infoPersona = new StringBuilder();
            infoPersona.append(binding.etnombre.getText().toString()+ "-");
            infoPersona.append(binding.etapellido.getText().toString()+ "-");
            infoPersona.append(obtenerGenero()+"-");
            infoPersona.append(preferencias.toString()+"-");
            infoPersona.append(estadocivil+"-");
            infoPersona.append(binding.swnotificacion.isChecked());
            personas.add(infoPersona.toString());


            Snackbar.make(binding.getRoot(), "Persona Registrada", Toast.LENGTH_LONG).show();
            setearControles();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
            estadocivil = "";
        }else
            estadocivil = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cbdeporte){
            agregarQuitarPreferencia(v, "Deporte");
        }else if(v.getId() == R.id.cbarte){
            agregarQuitarPreferencia(v, "Arte y creatividad");
        }else if(v.getId() == R.id.cbotros){
            agregarQuitarPreferencia(v, "Otras preferencias");
        }else if(v.getId() == R.id.btnregistrar) {
            registrarPersona();
        }else if (v.getId() == R.id.btnlistapersona){
            Intent intentLista = new Intent(getApplicationContext(),
                    ListaActivity.class);
            intentLista.putExtra("listapersonas",
                    (ArrayList<String>)personas);
            startActivity(intentLista);
        }
    }

    private void setearControles(){

        binding.etnombre.setText("");
        binding.etapellido.setText("");
        binding.rggenero.clearCheck();
        binding.cbarte.setChecked(false);
        binding.cbotros.setChecked(false);
        binding.cbdeporte.setChecked(false);
        binding.spestadocivil.setSelection(0);
        binding.swnotificacion.setChecked(false);
        preferencias.clear();
        binding.etnombre.setFocusableInTouchMode(true);
        binding.etnombre.requestFocus();
    }

}