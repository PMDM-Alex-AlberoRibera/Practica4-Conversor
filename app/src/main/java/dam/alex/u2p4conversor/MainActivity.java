package dam.alex.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUI();
    }
    private void setUI(){
        EditText etPulgada=findViewById(R.id.et_Pulgada);
        EditText etResultado=findViewById(R.id.et_Resultado);
        Button buttonConvertir=findViewById(R.id.btnConvertir);
        buttonConvertir.setOnClickListener(v ->{
                try{
                etResultado.setText(convertir(etPulgada.getText().toString()));
        } catch (Exception e) {
            Log.e("LogsCOnversor",e.getMessage());
                }
        });
    }
    private String convertir(String pulgadaText){
        double pulgadaValue=Double.parseDouble(pulgadaText)*2.54;
        return String.valueOf(pulgadaValue);
    }
}