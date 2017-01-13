package com.quique.weightmeter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Usuario on 03/01/2017.
 */

public class BtnListener implements View.OnClickListener {
    private Context contexto;

    public Context getContexto() { return this.contexto; }
    public void setContexto(Context valor) { this.contexto = valor; }
    public BtnListener(Context valor) { setContexto(valor); }

    @Override
    public void onClick(View v) {
        Log.d(getClass().getCanonicalName(), "Detectado click en botón");
        Activity actividad = (Activity)contexto;

        Log.d(getClass().getCanonicalName(), "Recuperamos los valores introducidos");
        String cajaAltura = ((EditText)actividad.findViewById(R.id.cajaAltura)).getText().toString();
        String cajaPeso = ((EditText)actividad.findViewById(R.id.cajaPeso)).getText().toString();

        if(cajaAltura != "" && cajaPeso != "") {
            Log.d(getClass().getCanonicalName(), "Valor recuperado de Altura: " + cajaAltura + " cm.");
            Log.d(getClass().getCanonicalName(), "Valor recuperado de Peso: " + cajaPeso + " kg.");

            ViewGroup vistaRespuesta = (ViewGroup) actividad.findViewById(R.id.linealRespuesta);
            View vistaInflada;
            if (vistaRespuesta.getChildCount() > 0) {
                Log.d(getClass().getCanonicalName(), "El layout de respuesta ya está creado");
                vistaInflada = vistaRespuesta.getChildAt(0);
            } else {
                Log.d(getClass().getCanonicalName(), "Procedemos a crear el Layout de respuesta");
                LayoutInflater respuestaLayout = actividad.getLayoutInflater();
                vistaInflada = respuestaLayout.inflate(R.layout.respuesta, vistaRespuesta);
            }
            float valorIMC = IMCalc.calcularIMC(Float.parseFloat(cajaAltura), Float.parseFloat(cajaPeso));
            ((TextView) vistaInflada.findViewById(R.id.labelIndice)).setText(String.valueOf(valorIMC));
            ((TextView) vistaInflada.findViewById(R.id.labelDescripcion)).setText(IMCalc.describirIMC(valorIMC, contexto));
        }
    }
}
