package com.fakedomain.admin.lulumath;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Operacao extends AppCompatActivity {

    private int fase = 0;
    private int limite = 10;
    private int chance = 0;
    private String operacao;
    private TextView tituloFase;
    private TextView titulo;
    private EditText resultado;
    private ImageView acertoErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacao);

        inicializar();

        EditText resultado = (EditText) findViewById(R.id.editTextResultado);
        resultado.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    TextView valor1TV = (TextView) findViewById(R.id.textViewValor1);
                    int valor1 = Integer.parseInt(valor1TV.getText().toString());
                    TextView valor2TV = (TextView) findViewById(R.id.textViewValor2);
                    int valor2 = Integer.parseInt(valor2TV.getText().toString());
                    TextView resultadoTV = (TextView) findViewById(R.id.editTextResultado);
                    ImageView acertoErro = (ImageView) findViewById(R.id.imageViewAcertoErro);

                    if (resultadoTV.getText().toString().length() == 0) {
                        mostrarMensagemSimples("Nenhum resultado informado.");
                    } else {
                        int resultado = Integer.parseInt(resultadoTV.getText().toString());

                        if (executarOperacao(valor1, valor2) == resultado) {
                            mostrarMensagemSimples("Você acertou e passou para a fase " + String.valueOf(fase + 1) + "! Parabéns!");
                            acertoErro.getLayoutParams().height *= 1.10;
                            acertoErro.getLayoutParams().width *= 1.10;
                            avancarFase();
                        } else {
                            if (chance < 2) {
                                chance++;
                                mostrarMensagemSimples("Tente novamente: a resposta certa era " + String.valueOf(executarOperacao(valor1, valor2)) + ".");
                                sortearNumeros();
                            } else {
                                mostrarMensagemSimples("Que pena, você errou. A resposta certa era " + String.valueOf(executarOperacao(valor1, valor2)) + ". Voltando à fase 1.");
                                manipularRecorde();
                                android.view.ViewGroup.LayoutParams layoutParams = acertoErro.getLayoutParams();
                                layoutParams.width = 132;
                                layoutParams.height = 158;
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

    private void inicializar() {
        Bundle extras = getIntent().getExtras();
        operacao = extras.getString("operacao");

        TextView titulo = (TextView) findViewById(R.id.textViewTitulo);
        TextView sinalOperacao = (TextView) findViewById(R.id.textViewSinal);

        switch (operacao) {
            case "soma":
                titulo.setText(titulo.getText() + "Soma");
                sinalOperacao.setText("+");
                break;
            case "subtracao":
                titulo.setText(titulo.getText() + "Subtração");
                sinalOperacao.setText("-");
                break;
            case "multiplicacao":
                titulo.setText(titulo.getText() + "Multiplicação");
                sinalOperacao.setText("x");
                break;
            case "divisao":
                titulo.setText(titulo.getText() + "Divisão");
                sinalOperacao.setText("÷");
                break;
        }

        avancarFase();
    }

    private void avancarFase() {
        fase++;
        if ((fase != 1) && ((fase % 2) == 0)) {
          switch (operacao) {
              case "soma":
              case "subtracao":
                  limite *= 5;
                  break;
              case "multiplicacao":
              case "divisao":
                  limite *= 2;
                  break;
          }
        }

        sortearNumeros();
    }

    private int executarOperacao(int valor1, int valor2) {
        int resultadoOperacao = 0;

        switch (operacao) {
            case "soma":
                resultadoOperacao = valor1 + valor2;
                break;
            case "subtracao":
                resultadoOperacao = valor1 - valor2;
                break;
            case "multiplicacao":
                resultadoOperacao = valor1 * valor2;
                break;
            case "divisao":
                resultadoOperacao = valor1 / valor2;
                break;
        }

        return resultadoOperacao;
    }

    private void sortearNumeros() {
        Random gerador = new Random();

        int valor1 = 0;
        int valor2 = 0;

        switch (operacao) {
            case "soma":
            case "multiplicacao":
                valor1 = gerador.nextInt(limite);
                valor2 = gerador.nextInt(limite);
                break;
            case "subtracao":
                do {
                    valor1 = gerador.nextInt(limite);
                    valor2 = gerador.nextInt(limite);
                } while (valor1 < valor2);
                break;
            case "divisao":
                do {
                    valor1 = gerador.nextInt(limite);
                    valor2 = gerador.nextInt(limite) + 1;
                } while ((valor1 % valor2) != 0);
                break;
        }

        TextView txtValor1 = (TextView) findViewById(R.id.textViewValor1);
        TextView txtValor2 = (TextView) findViewById(R.id.textViewValor2);

        txtValor1.setText(String.valueOf(valor1));
        txtValor2.setText(String.valueOf(valor2));

        TextView resultado = (TextView) findViewById(R.id.editTextResultado);
        resultado.setText("");

        TextView tituloFase = (TextView) findViewById(R.id.textViewTituloFase);
        tituloFase.setText("Fase " + String.valueOf(fase) + " - Erros: " + String.valueOf(chance));
    }

    private void mostrarMensagemSimples(String mensagem) {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
    }

    private void mostrarMensagem(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Operacao.this);
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

    private void manipularRecorde() {
        SharedPreferences preferencias = getSharedPreferences("LuluMath", MODE_PRIVATE);
        int recorde = preferencias.getInt(operacao, 0);

        if (fase > recorde) {
            mostrarMensagem("Parabéns! Você chegou à fase " + String.valueOf(fase) + " e registrou um novo recorde para esta operação!");
            SharedPreferences.Editor editor = getSharedPreferences("LuluMath", MODE_PRIVATE).edit();
            editor.putInt(operacao, fase);
            Boolean resultado = editor.commit();
        }
    }

    private void reiniciar() {
        fase = 0;
        limite = 10;
        chance = 0;
        avancarFase();
    }
}