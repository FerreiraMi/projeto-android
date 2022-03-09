package com.senai.rhholerite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class TelaInicialActivity extends AppCompatActivity {
    private Button btEntrar;
    private EditText nomeFunc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        btEntrar = findViewById(R.id.bt_entrar);
        nomeFunc = findViewById(R.id.func);

        btEntrar.setOnClickListener(view -> {
            if (nomeFunc.getText().toString().isEmpty()) {
                nomeFunc.setError(getString(R.string.valida_nome));
                Toast.makeText(getBaseContext(), R.string.valida_nome, Toast.LENGTH_SHORT).show();
                Snackbar.make(btEntrar, R.string.valida_nome, Snackbar.LENGTH_SHORT).show();
            } else{


                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}