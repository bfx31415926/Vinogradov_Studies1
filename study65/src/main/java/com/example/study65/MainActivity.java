package com.example.study65;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.srudy65.R;

public class MainActivity extends Activity {

    final int DIALOG = 1;

    int btn;
    LinearLayout view;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    TextView tvCount;
    ArrayList<TextView> textViews;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textViews = new ArrayList<TextView>(10);
    }

    public void onclick(View v) {
        btn = v.getId();
        showDialog(DIALOG);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Custom dialog");
        // создаем view из dialog.xml
        view = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dialog, null);
        // устанавливаем ее, как содержимое тела диалога
        adb.setView(view);
        // находим TexView для отображения кол-ва
        tvCount = (TextView) view.findViewById(R.id.tvCount);
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if (id == DIALOG) {
            // Находим TextView для отображения времени и показываем текущее
            // время
            TextView tvTime = (TextView) dialog.getWindow().findViewById(
                    R.id.tvTime);
            tvTime.setText(sdf.format(new Date(System.currentTimeMillis())));
            // если была нажата кнопка Добавить
            if (btn == R.id.btnAdd) {
                // создаем новое TextView, добавляем в диалог, указываем текст
                TextView tv = new TextView(this);
                view.addView(tv, new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));
                tv.setText("TextView " + (textViews.size() + 1));
                // добавляем новое TextView в коллекцию
                textViews.add(tv);
                // иначе
            } else {
                // если коллекция созданных TextView непуста
                if (textViews.size() > 0) {
                    // находим в коллекции последний TextView
                    TextView tv = textViews.get(textViews.size() - 1);
                    // удаляем из диалога
                    view.removeView(tv);
                    // удаляем из коллекции
                    textViews.remove(tv);
                }
            }
            // обновляем счетчик
            tvCount.setText("Кол-во TextView = " + textViews.size());
        }
    }
}