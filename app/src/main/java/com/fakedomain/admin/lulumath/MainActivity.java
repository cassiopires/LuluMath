package com.fakedomain.admin.lulumath;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgSoma;
    private ImageView imgSubtracao;
    private ImageView imgMultiplicacao;
    private ImageView imgDivisao;
    private TextView textoSoma;
    private TextView textoSubtracao;
    private TextView textoMultiplicacao;
    private TextView textoDivisao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface fonteSegoe = Typeface.createFromAsset(getAssets(), "fonts/segoeprb.ttf");

        imgSoma = (ImageView) findViewById(R.id.imageViewSoma);
        imgSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Soma.class));
            }
        });

        textoSoma = (TextView) findViewById(R.id.textViewSoma);
        textoSoma.setTypeface(fonteSegoe);

        textoSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Soma.class));
            }
        });

        imgSubtracao = (ImageView) findViewById(R.id.imageViewSubtracao);
        imgSubtracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Subtracao.class));
            }
        });
                
        textoSubtracao = (TextView) findViewById(R.id.textViewSubtracao);
        textoSubtracao.setTypeface(fonteSegoe);

        textoSubtracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Subtracao.class));
            }
        });

        imgMultiplicacao = (ImageView) findViewById(R.id.imageViewMultiplicacao);
        imgMultiplicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Multiplicacao.class));
            }
        });

        textoMultiplicacao = (TextView) findViewById(R.id.textViewMultiplicacao);
        textoMultiplicacao.setTypeface(fonteSegoe);

        textoMultiplicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Multiplicacao.class));
            }
        });

        imgDivisao = (ImageView) findViewById(R.id.imageViewDivisao);
        imgDivisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Divisao.class));
            }
        });

        textoDivisao = (TextView) findViewById(R.id.textViewDivisao);
        textoDivisao.setTypeface(fonteSegoe);

        textoDivisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Divisao.class));
            }
        });

    }
}