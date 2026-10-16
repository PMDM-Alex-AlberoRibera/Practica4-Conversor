package dam.alex.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //TODO: Actividad 3.1 - Declarar el TAG para los logs del ciclo de vida
    private static final String TAG = "Conversor";

    //TODO:Actividad 1.1 Declarar el Spinner a nivel de clase para poder usarlo en otros métodos
    private Spinner selector;
    //TODO:Actividad 4.1 Al poner la vista horizontal la informacion de los textView se va aunque tambien mi EditText desaparece al parecer porque me falta un dato crucial
    //TODO:Actividad 4.1 y porque se recrea la Activity por el opnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //TODO: Actividad 3.1 - Log para onCreate
        Log.d(TAG, "onCreate");

        //TODO:Actividad 1.1 Inicializar el selector
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

    //TODO: Actividad 3.1 - Métodos del ciclo de vida creados para mayor organizacion al momemto de colocar los logs
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "La aplicacion Inicia");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Se puso en Resumen");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Esta pausado");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Esta en estado de Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Se destruyo la app");
    }

    private void setUI() {
        EditText etPulgada = findViewById(R.id.et_Pulgada);
        EditText etResultado = findViewById(R.id.et_Resultado);
        Button buttonConvertir = findViewById(R.id.btnConvertir);
        TextView tvError=findViewById(R.id.tvError);

        buttonConvertir.setOnClickListener(v -> {

            try {
                etResultado.setText("");

                String textoPulgada = etPulgada.getText().toString();
                //// TODO: Actividad 3.2 - Creacion de un log cuando se  pulsa el botón convertir
                Log.d(TAG,"Convertir pulsado");
                //TODO:Actividad 2.1 Valido el valor introducido en este caso si esta vacio
                if(textoPulgada.matches(".*[a-zA-Z].*")){
                    throw new Exception("No puedes introducir letras");
                }
                if (textoPulgada.isEmpty() || textoPulgada.equals(" ")) {
                    throw new Exception("El valor no puede estar vacio");
                }

                etResultado.setText(convertir(textoPulgada));
                tvError.setVisibility(View.GONE);

            } catch (Exception e) {
                Log.e("LogsCOnversor", e.getMessage());
                //TODO:Actividad 2.2 Si falla el codigo el mensaje se guardará en el tvError y se mostrará por pantalla
                tvError.setText(e.getMessage());
                tvError.setVisibility(View.VISIBLE);
                etResultado.setText("");
            }
        });
    }

    //TODO:Actividad 1.1 Modificamos la clase convertir usando la posición del selector
    private String convertir(String pulgadaText) throws Exception {

        double valor = Double.parseDouble(pulgadaText);
        //TODO:Actividad 2.1 Aqui comprueba si el numero es igual o mayor a 1
        if (valor<1){
            throw new Exception("Solo numeros iguales o mayores a 1");
        }
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
        //TODO:Actividad 1.2 Con la línea format le indicamos que solo queremos 2 decimales(por el .2f)
        String format = String.format("%.2f", resultado);
        return format;
    }
}