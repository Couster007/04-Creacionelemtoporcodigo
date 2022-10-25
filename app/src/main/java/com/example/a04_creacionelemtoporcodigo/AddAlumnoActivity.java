package com.example.a04_creacionelemtoporcodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a04_creacionelemtoporcodigo.databinding.ActivityAddAlumnoBinding;
import com.example.a04_creacionelemtoporcodigo.modelos.Alumno;

public class AddAlumnoActivity extends AppCompatActivity {
    //Activa el bingin para el activity
    private ActivityAddAlumnoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Construye el binding
        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());
        //Asocia el binding a la activity
        setContentView(binding.getRoot());


        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alumno alumno = createAlumno();
                if (alumno != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ALUMNO", alumno);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();

                }else{
                    Toast.makeText(AddAlumnoActivity.this, "Faltan Datos", Toast.LENGTH_SHORT).show();
                }
            }

            private Alumno createAlumno() {
                if(binding.txtNombreAddAlumno.getText().toString().isEmpty() || binding.txtApellidoAddAlumno.getText().toString().isEmpty())
                    return null;
                if(binding.spCiclosAddAlumno.getSelectedItemPosition() == 0)
                    return null;
                if(!binding.rbGrupoAAddAlumno.isChecked() && !binding.rbgGrupoBAddAlumno.isChecked() && !binding.rbGrupoCAddAlumno.isChecked())
                    return null;

                String ciclos = (String) binding.spCiclosAddAlumno.getSelectedItem();
                RadioButton rb = findViewById(binding.rgGrupoAddAlumno.getCheckedRadioButtonId());
                char grupo = rb.getText().charAt(0);

                return new Alumno(binding.txtNombreAddAlumno.getText().toString(), binding.txtApellidoAddAlumno.getText().toString(), ciclos, grupo);
            }
        });

    }
}