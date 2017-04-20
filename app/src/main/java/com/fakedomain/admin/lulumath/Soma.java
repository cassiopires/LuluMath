package com.fakedomain.admin.lulumath;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Soma extends AppCompatActivity {

    private int fase = 0;
    private int limite = 10;
    private TextView tituloFase;
    private EditText resultado;
    private ImageView acertoErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soma);
        avancarFase();

        EditText resultado = (EditText) findViewById(R.id.editTextResultadoSoma);
        resultado.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    TextView valor1TV = (TextView) findViewById(R.id.textViewValor1Somar);
                    int valor1 = Integer.parseInt(valor1TV.getText().toString());
                    TextView valor2TV = (TextView) findViewById(R.id.textViewValor2Somar);
                    int valor2 = Integer.parseInt(valor2TV.getText().toString());
                    TextView resultadoTV = (TextView) findViewById(R.id.editTextResultadoSoma);
                    ImageView acertoErro = (ImageView) findViewById(R.id.imageViewPosNeg);

                    if (resultadoTV.getText().toString().length() == 0) {
                        mostrarMensagemSimples("Nenhum resultado informado.");
                    } else {
                        int resultado = Integer.parseInt(resultadoTV.getText().toString());

                        if ((valor1 + valor2) == resultado) {
                            mostrarMensagemSimples("Você acertou! Parabéns!");
                            acertoErro.setBackgroundResource(R.drawable.emoji_nerd);
                            avancarFase();
                        } else {
                            mostrarMensagemSimples("Você errou. Voltando à fase #1.");
                            acertoErro.setBackgroundResource(R.drawable.emoji_chorando);
                            reiniciar();
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void avancarFase() {
        fase++;
        if ((fase != 1) && ((fase % 2) == 0)) {
            limite *= 5;
        }
        TextView tituloFase = (TextView) findViewById(R.id.textViewTituloFase);
        tituloFase.setText("Fase " + String.valueOf(fase));
        sortearNumeros();
    }

    private void sortearNumeros() {
        Random gerador = new Random();
        int valor1 = gerador.nextInt(limite);
        int valor2 = gerador.nextInt(limite);

        TextView txtValor1 = (TextView) findViewById(R.id.textViewValor1Somar);
        TextView txtValor2 = (TextView) findViewById(R.id.textViewValor2Somar);

        txtValor1.setText(String.valueOf(valor1));
        txtValor2.setText(String.valueOf(valor2));

        TextView resultado = (TextView) findViewById(R.id.editTextResultadoSoma);
        resultado.setText("");
    }

    private void mostrarMensagem(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
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

    private void mostrarMensagemSimples(String mensagem) {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
    }

    private void reiniciar() {
        fase = 0;
        limite = 10;
        avancarFase();
    }
}