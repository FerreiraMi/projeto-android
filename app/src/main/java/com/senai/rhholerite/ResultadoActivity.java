package com.senai.rhholerite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {


    private TextView dVT, dVR, dVA, dINSS, dIRRF, bruto, liquido, pS;
    private Spinner planoSaude;
    private Button btvoltar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        dVT = findViewById(R.id.descVT);
        dVR = findViewById(R.id.descVR);
        dVA = findViewById(R.id.descVA);
        dIRRF=findViewById(R.id.descIRRF);
        dINSS=findViewById(R.id.descINSS);
        bruto = findViewById(R.id.saBrut);
        liquido = findViewById(R.id.saLiq);

        pS= findViewById(R.id.descPlano);
        btvoltar=findViewById(R.id.bt_voltar);





         Bundle resultado = getIntent().getExtras();

        pS.setText(resultado.getString("planoSaude"));
        dVT.setText(resultado.getString("valeTransporte"));
        dVA.setText(resultado.getString("valeAlimentação"));
        dVR.setText(resultado.getString("valeRefeição"));
        dINSS.setText(resultado.getString("inss"));
        dIRRF.setText(resultado.getString("irrf"));
        liquido.setText(resultado.getString("salarioLiquido"));
        bruto.setText(resultado.getString("salarioBruto"));


        btvoltar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        });

    }
}