package com.senai.rhholerite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private EditText sBr, nDepen;
    private RadioButton vTs, vAs, vRs, vTn, vAn, vRn;
    private RadioButton standart, basico, supe, master;
    private Button btcalcular;
    private TextView  dPlano, dVT, dVR, dVA, dINSS, dIRRF, bruto, liquido;
    private Spinner planoSaude;


    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sBr = findViewById(R.id.sB);
        nDepen = findViewById(R.id.nDep);
        vTs = findViewById(R.id.bt_sim0);
        vAs = findViewById(R.id.bt_sim1);
        vRs = findViewById(R.id.bt_sim2);
        planoSaude = findViewById(R.id.spinnerPsaude);
        btcalcular=findViewById(R.id.bt_calcular);







        Spinner spinerPlanoS = (Spinner) findViewById(R.id.spinnerPsaude);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerPlanoS.setAdapter(adapter);



        btcalcular.setOnClickListener(view -> {
            if (sBr.getText().toString().isEmpty()) {
                sBr.setError(getString(R.string.valida_sB));
                Toast.makeText(getBaseContext(), R.string.valida_sB, Toast.LENGTH_SHORT).show();
            } else if (nDepen.getText().toString().isEmpty()) {
                nDepen.setError(getString(R.string.valida_nDep));
                Snackbar.make(btcalcular, R.string.valida_nDep, Snackbar.LENGTH_SHORT).show();


            } else {


                double salarioBruto = Double.parseDouble(sBr.getText().toString());
                double nuDep = Double.parseDouble(nDepen.getText().toString());
                double vt, va, vr, cIrrf, cInss, salarioliquido;


                String plano = planoSaude.getSelectedItem().toString();
                double tPlano = 0;
                switch (plano) {
                    case "Standard":
                        if (salarioBruto <= 3000.00) {
                            tPlano = 60;
                        } else {
                            tPlano = 80;
                        }

                        break;
                    case "Básico":
                        if (salarioBruto <= 3000.00) {
                            tPlano = 80;
                        } else {
                            tPlano = 110;
                        }

                        break;
                    case "Super":
                        if (salarioBruto <= 3000.00) {
                            tPlano = 95;
                        } else {
                            tPlano = 135;
                        }

                        break;
                    case "Master":
                        if (salarioBruto <= 3000.00) {
                            tPlano = 130;
                        } else {
                            tPlano = 180;
                        }

                        break;
                    default:

                        break;

                }


                if (vTs.isChecked()) {
                    vt = (0.06 * salarioBruto);

                } else {
                    vt = 0;

                }
                if (vAs.isChecked()) {

                    if (salarioBruto <= 3000) {
                        va = 15.00;

                    } else if (salarioBruto <= 5000) {
                        va = 25.00;

                    } else {
                        va = 35.00;

                    }
                } else {
                    va = 0;


                }

                if (vRs.isChecked()) {
                    if (salarioBruto <= 3000) {
                        vr = 2.60 * 22;

                    } else if (salarioBruto <= 5000) {
                        vr = 3.65 * 22;

                    } else {
                        vr = 6.50 * 22;

                    }


                } else {
                    vr = 0;


                }
                if (salarioBruto <= 1212) {
                    cInss = 0.075 * salarioBruto;

                } else if (salarioBruto <= 2427.35) {
                    cInss = 0.09 * salarioBruto - 18.18;

                } else if (salarioBruto <= 3641.03) {
                    cInss = 0.12 * salarioBruto - 91.00;

                } else if (salarioBruto <= 7087.22) {
                    cInss = 0.14 * salarioBruto - 163.82;

                } else {
                    cInss = 0828.39;


                }


                cIrrf = salarioBruto - cInss - (189.59 * nuDep);
                if (cIrrf >= 4664.68) {
                    cIrrf = cIrrf * 0.275 - 869.36;

                } else if (cIrrf >= 3751.05) {
                    cIrrf = cIrrf * 0.225 - 636.13;

                } else if (cIrrf >= 2826.66) {
                    cIrrf = cIrrf * 0.225 - 354.00;


                } else if (cIrrf >= 1903.99) {
                    cIrrf = cIrrf * 0.225 - 354.00;


                } else {
                    cIrrf = 0;


                }
                salarioliquido = salarioBruto - vt - va - vr - cIrrf - cInss - tPlano;


                Intent intent = new Intent(this, ResultadoActivity.class);
                intent.putExtra("planoSaude", String.format("R$ %.2f", tPlano));
                intent.putExtra("valeTransporte", String.format("R$ %.2f", vt));
                intent.putExtra("valeAlimentação", String.format("R$ %.2f", va));
                intent.putExtra("valeRefeição", String.format("R$ %.2f", vr));
                intent.putExtra("inss", String.format("R$ %.2f", cInss));
                intent.putExtra("irrf", String.format("R$ %.2f", cIrrf));
                intent.putExtra("salarioLiquido", String.format("R$ %.2f", salarioliquido));
                intent.putExtra("salarioBruto", String.format("R$ %.2f", salarioBruto));


                startActivity(intent);
                finish();
            }
    });

}
}
















