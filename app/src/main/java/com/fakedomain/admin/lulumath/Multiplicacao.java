package com.fakedomain.admin.lulumath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Multiplicacao extends AppCompatActivity {

    private int fase = 0;
    private int limite = 10;
    private int chance = 1;
    private TextView tituloFase;
    private EditText resultado;
    private ImageView acertoErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplicacao);
        avancarFase();

        EditText resultado = (EditText) findViewById(R.id.editTextResultadoMultiplicacao);
        resultado.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    TextView valor1TV = (TextView) findViewById(R.id.textViewValor1Multiplicacao);
                    int valor1 = Integer.parseInt(valor1TV.getText().toString());
                    TextView valor2TV = (TextView) findViewById(R.id.textViewValor2Multiplicacao);
                    int valor2 = Integer.parseInt(valor2TV.getText().toString());
                    TextView resultadoTV = (TextView) findViewById(R.id.editTextResultadoMultiplicacao);
                    ImageView acertoErro = (ImageView) findViewById(R.id.imageViewAcertoErro);

                    if (resultadoTV.getText().toString().length() == 0) {
                        mostrarMensagemSimples("Nenhum resultado informado.");
                    } else {
                        int resultado = Integer.parseInt(resultadoTV.getText().toString());

                        if ((valor1 * valor2) == resultado) {
                            mostrarMensagemSimples("Você acertou e passou para a fase " + String.valueOf(fase + 1) + "! Parabéns!");
                            acertoErro.getLayoutParams().height *= 1.10;
                            acertoErro.getLayoutParams().width *= 1.10;

                            avancarFase();
                        } else {
                            if (chance < 3) {
                                chance++;
                                mostrarMensagemSimples("Tente novamente: a resposta certa era " + String.valueOf(valor1 * valor2) + ".");
                                resultadoTV.setText("");
                                sortearNumeros();
                            } else {
                                mostrarMensagemSimples("Que pena, você errou. A resposta certa era " + String.valueOf(valor1 * valor2) + ". Voltando à fase 1.");
                                android.view.ViewGroup.LayoutParams layoutParams = acertoErro.getLayoutParams();
                                layoutParams.width = 100;
                                layoutParams.height = 120;
                                acertoErro.setLayoutParams(layoutParams);

                                reiniciar();
                            }
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
            limite *= 2;
        }
        TextView tituloFase = (TextView) findViewById(R.id.textViewTituloFase);
        tituloFase.setText("Fase " + String.valueOf(fase));
        sortearNumeros();
    }

    private void sortearNumeros() {
        Random gerador = new Random();
        int valor1 = gerador.nextInt(limite);
        int valor2 = gerador.nextInt(limite);

        TextView txtValor1 = (TextView) findViewById(R.id.textViewValor1Multiplicacao);
        TextView txtValor2 = (TextView) findViewById(R.id.textViewValor2Multiplicacao);

        txtValor1.setText(String.valueOf(valor1));
        txtValor2.setText(String.valueOf(valor2));

        TextView resultado = (TextView) findViewById(R.id.editTextResultadoMultiplicacao);
        resultado.setText("");
    }

    private void mostrarMensagemSimples(String mensagem) {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
    }

    private void reiniciar() {
        fase = 0;
        limite = 10;
        avancarFase();
    }
}