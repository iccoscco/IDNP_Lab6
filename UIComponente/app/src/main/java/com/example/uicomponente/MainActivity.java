package com.example.uicomponente;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BuildingViewModel viewModel;
    private BuildingView buildingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildingView = new BuildingView(this);

        // Configurar ViewModel
        viewModel = new ViewModelProvider(this).get(BuildingViewModel.class);
        viewModel.getAmbientes().observe(this, ambientes -> {
            // Actualización de View
        });

        setContentView(buildingView);  // Establecer el View personalizado
    }

    // Clase personalizada para dibujar la edificación
    public class BuildingView extends View {
        private Paint paint;
        private Rect salon1, salon2, salon3, salon4;
        private String[] labels = {"Salón 1", "Salón 2", "Salón 3", "Salón 4"};
        private Rect infoRect; // Rectángulo para la información
        private String infoText; // Texto para mostrar la información

        public BuildingView(Context context) {
            super(context);
            init();
        }

        private void init() {
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(40);

            // Definir los rectángulos de los salones
            salon1 = new Rect(100, 100, 400, 300);
            salon2 = new Rect(450, 100, 750, 300);
            salon3 = new Rect(100, 350, 400, 550);
            salon4 = new Rect(450, 350, 750, 550);
            infoRect = new Rect(50, 20, 900, 80); // Rectángulo para la información
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Dibujo de los salones
            paint.setColor(Color.GRAY);
            canvas.drawRect(salon1, paint);
            canvas.drawRect(salon2, paint);
            canvas.drawRect(salon3, paint);
            canvas.drawRect(salon4, paint);

            // Dibujo de las etiquetas
            paint.setColor(Color.BLACK);
            canvas.drawText(labels[0], salon1.centerX() - 40, salon1.centerY(), paint);
            canvas.drawText(labels[1], salon2.centerX() - 40, salon2.centerY(), paint);
            canvas.drawText(labels[2], salon3.centerX() - 40, salon3.centerY(), paint);
            canvas.drawText(labels[3], salon4.centerX() - 40, salon4.centerY(), paint);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float x = event.getX();
                float y = event.getY();

                if (salon1.contains((int) x, (int) y)) {
                    mostrarInformacion("Salón 1");
                } else if (salon2.contains((int) x, (int) y)) {
                    mostrarInformacion("Salón 2");
                } else if (salon3.contains((int) x, (int) y)) {
                    mostrarInformacion("Salón 3");
                } else if (salon4.contains((int) x, (int) y)) {
                    mostrarInformacion("Salón 4");
                }
                return true;
            }
            return false;
        }

        private void mostrarInformacion(String ambiente) {
            RoomInfoFragment fragment = RoomInfoFragment.newInstance(ambiente);
            fragment.show(getSupportFragmentManager(), "room_info");
        }
    }

    // ViewModel para gestionar los datos de los ambientes
    public static class BuildingViewModel extends ViewModel {
        private MutableLiveData<List<Rect>> ambientesLiveData;

        public BuildingViewModel() {
            ambientesLiveData = new MutableLiveData<>();
            cargarDatosDesdeArchivo();
        }

        private void cargarDatosDesdeArchivo() {
            // Por lo pronto con un List
            List<Rect> ambientes = new ArrayList<>();
            ambientes.add(new Rect(100, 100, 400, 300));  // Salón 1
            ambientes.add(new Rect(450, 100, 750, 300));  // Salón 2
            ambientes.add(new Rect(100, 350, 400, 550));  // Patio 1
            ambientes.add(new Rect(450, 350, 750, 550));  // Patio 2
            ambientesLiveData.setValue(ambientes);
        }

        public MutableLiveData<List<Rect>> getAmbientes() {
            return ambientesLiveData;
        }
    }
}
