package com.fakedomain.admin.lulumath;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        SharedPreferences preferencias = getSharedPreferences("LuluMath", MODE_PRIVATE);
        int recordeSoma = preferencias.getInt("soma", 0);
        int recordeSubtracao = preferencias.getInt("subtracao", 0);
        int recordeMultiplicacao = preferencias.getInt("multiplicacao", 0);
        int recordeDivisao = preferencias.getInt("divisao", 0);

        TextView textoSobre = (TextView) findViewById(R.id.textViewTextoSobre);
        textoSobre.setText("O aplicativo LuluMath foi escrito para ajudar uma estudante do 4° ano do Ensino " +
                "Fundamental a ter mais facilidade nos exercícios escolares de matemática: como outros estudantes " +
                "podem ter a mesma necessidade, o aplicativo está sendo compartilhado gratuitamente.\n\nO objetivo do " +
                "jogo é simples: fazer com que o troféu, que aumenta de tamanho a cada acerto, cubra toda a tela.\n\n" +
                "Regras:\n\n1. Somente números inteiros são sorteados;\n2. O resultado nunca será negativo;\n3. O " +
                "resultado nunca terá casas decimais;\n4. Em cada sessão de jogo, o usuário terá 3 chances de acertar " +
                "o resultado;\n5. Os exercícios podem e devem ser executados com o auxílio de papel e caneta, mas " +
                "nunca de uma calculadora;\n6. Além de sozinho, é possível jogar entre várias pessoas, cada um " +
                "resolvendo um problema, e ver quem não consegue resolver na sua vez;\n7. Todas as vezes em que o " +
                "recorde da operação no dispositivo for superado, uma mensagem será mostrada.\n\nSugestões e problemas " +
                "podem ser encaminhados ao e-mail lulumath.app@gmail.com.\n\nBom jogo!\n\n" +
                "***** Recordes do dispositivo ******\n" +
                "Soma: fase " + String.valueOf(recordeSoma) + "\n" +
                "Subração: fase " + String.valueOf(recordeSubtracao) + "\n" +
                "Multiplicação: fase " + String.valueOf(recordeMultiplicacao) + "\n" +
                "Divisão: fase " + String.valueOf(recordeDivisao));
    }
}
