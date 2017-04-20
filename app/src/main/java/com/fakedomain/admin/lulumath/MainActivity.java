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
    private TextView textoSoma;
    private TextView textoSubtracao;
    private TextView textoMultiplicacao;
    private TextView textoDivisao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSoma = (ImageView) findViewById(R.id.imageViewSoma);

        imgSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Soma.class);
                startActivity(intent);
            }
        });

        textoSoma = (TextView) findViewById(R.id.textViewSoma);
        Typeface mtypeFace = Typeface.createFromAsset(getAssets(), "fonts/segoeprb.ttf");
        textoSoma.setTypeface(mtypeFace);

        textoSubtracao = (TextView) findViewById(R.id.textViewSubtracao);
        textoSubtracao.setTypeface(mtypeFace);

        textoMultiplicacao = (TextView) findViewById(R.id.textViewMultiplicacao);
        textoMultiplicacao.setTypeface(mtypeFace);

        textoDivisao = (TextView) findViewById(R.id.textViewDivisao);
        textoDivisao.setTypeface(mtypeFace);

    }
}
