package dam.alex.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // 1. Declarar el Spinner a nivel de clase para poder usarlo en otros métodos
    private Spinner selector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializar el selector
        selector = findViewById(R.id.selector);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.opciones_conversion, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selector.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUI();
    }

    private void setUI() {
        EditText etPulgada = findViewById(R.id.et_Pulgada);
        EditText etResultado = findViewById(R.id.et_Resultado);
        Button buttonConvertir = findViewById(R.id.btnConvertir);

        buttonConvertir.setOnClickListener(v -> {
            try {
                String textoPulgada = etPulgada.getText().toString();

                // 2. Obtener el texto de la opción seleccionada en el Spinner
                String opcionSeleccionada = selector.getSelectedItem().toString();

                // Pasar ambos valores al método convertir
                etResultado.setText(convertir(textoPulgada, opcionSeleccionada));

            } catch (Exception e) {
                Log.e("LogsCOnversor", e.getMessage());
            }
        });
    }

    //TODO:Actividad 1.1 Modificamos la clase convertir usando la posición del selector
    private String convertir(String pulgadaText, String tipoConversion) {
        if (pulgadaText.isEmpty()) {
            return "";
        }

        double valor = Double.parseDouble(pulgadaText);
        //TODO:Actividad 1.2 Como ya estaba en double el resultado solo tenia que ponerle un 0 extra
        double resultado = 0.00;

        //TODO:Actividad 1.1 Con este comando puedo recoger la posicion de la opcion elegida actualmente (0 para el primero, 1 para el segundo...)
        int posicion = selector.getSelectedItemPosition();

        if (posicion == 0) {
            //TODO:Actividad 1.1(Pulgadas a Centímetros)
            resultado = valor * 2.54;
        } else if (posicion == 1) {
            //TODO:Actividad 1.1(Centímetros a Pulgadas)
            resultado = valor / 2.54;
        }

        return String.valueOf(resultado);
    }
}