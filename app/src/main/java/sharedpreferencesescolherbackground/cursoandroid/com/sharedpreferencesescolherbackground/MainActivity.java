package sharedpreferencesescolherbackground.cursoandroid.com.sharedpreferencesescolherbackground;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    //SALVAR INFORMAÇÕES (PREFERENCIAS DO USUARIO, POR EXEMPLO)

    private Button botaoEscolher;
    private RadioGroup cores;
    private RadioButton corEscolhida;
    private RelativeLayout layout;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cores = (RadioGroup) findViewById(R.id.radiogroupId);
        botaoEscolher = (Button) findViewById(R.id.botaoEscolherId);
        layout = (RelativeLayout) findViewById(R.id.layoutId);

        botaoEscolher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recebe o ID do button selecionado
                int idCorEscolhida = cores.getCheckedRadioButtonId();

                //Verifica se algum button foi selecionado. Caso sim, o valor da variavel vai ser maior que 0
                if(idCorEscolhida > 0){
                    corEscolhida = (RadioButton) findViewById(idCorEscolhida);

                    //Salvar informações
                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String corSelecionada = corEscolhida.getText().toString();
                    editor.putString("corEscolhida", corEscolhida.getText().toString());
                    editor.commit();

                    setBackground(corSelecionada);
                }
            }
        });

        //Recuperar as informações
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(sharedPreferences.contains("corEscolhida")){
            String corRecuperada = sharedPreferences.getString("corEscolhida", "");
            setBackground(corRecuperada);
        }
    }

    private void setBackground(String cor){
        if(cor.equals("Vermelho")){
            layout.setBackgroundColor(Color.parseColor("#FFCC0000"));
        } else if(cor.equals("Amarelo")){
            layout.setBackgroundColor(Color.parseColor("#ffee00"));
        } else if(cor.equals("Azul")){
            layout.setBackgroundColor(Color.parseColor("#FF0099CC"));
        }
    }
}
