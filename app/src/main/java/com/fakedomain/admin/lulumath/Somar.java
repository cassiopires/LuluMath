package com.fakedomain.admin.lulumath;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Somar extends AppCompatActivity {

    private int fase = 0;
    private int limite = 10;
    private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somar);
        avancarFase();

        Button btnOkSomar = (Button) findViewById(R.id.btnOkSomar);
        btnOkSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView valor1TV = (TextView) findViewById(R.id.txtValor1Somar);
                int valor1 = Integer.parseInt(valor1TV.getText().toString());
                TextView valor2TV = (TextView) findViewById(R.id.txtValor2Somar);
                int valor2 = Integer.parseInt(valor2TV.getText().toString());
                TextView resultadoTV = (TextView) findViewById(R.id.edtResultado);
                int resultado = Integer.parseInt(resultadoTV.getText().toString());

                if ((valor1 + valor2) == resultado) {
                    mostrarMensagem("Você acertou! Parabéns!");
                    avancarFase();
                } else {
                    mostrarMensagem("Você errou. Tente novamente.");
                    reiniciar();
                }
            }
        });
    }

    private void avancarFase() {
        fase++;
        if ((fase != 1) && ((fase % 2) == 0)) {
            limite *= 10;
        }
        TextView titulo = (TextView) findViewById(R.id.txtTituloSomar);
        titulo.setText("Exercícios de soma - Fase #" + String.valueOf(fase));
        sortearNumeros();
    }

    private void sortearNumeros() {
        Random gerador = new Random();
        int valor1 = gerador.nextInt(limite);
        int valor2 = gerador.nextInt(limite);

        TextView txtValor1 = (TextView) findViewById(R.id.txtValor1Somar);
        TextView txtValor2 = (TextView) findViewById(R.id.txtValor2Somar);

        txtValor1.setText(String.valueOf(valor1));
        txtValor2.setText(String.valueOf(valor2));

        TextView resultado = (TextView) findViewById(R.id.edtResultado);
        resultado.setText("");
    }

    private void mostrarMensagem(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensagem).setTitle("Atenção!");

        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface d, int arg1) {
                d.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void reiniciar() {
        fase = 0;
        limite = 10;
        avancarFase();
    }
}