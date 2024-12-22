package com.example.prakta_47;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class MainActivity extends AppCompatActivity {
    private NotificationEvent notificationEvent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout notificationLayout = findViewById(R.id.notificationLayout);
        LinearLayout buttonSection = findViewById(R.id.buttonSection);

        updateNotification(notificationLayout);

        setupButtons(buttonSection);
    }

    private void setupButtons(LinearLayout buttonSection) {
        addButton(buttonSection, "Загрузка завершена!", Color.GREEN);
        addButton(buttonSection, "Низкий заряд батареи!", Color.YELLOW);
        addButton(buttonSection, "Ошибка сети!", Color.RED);
        addButton(buttonSection, "Новое сообщение!", Color.BLUE);
        addButton(buttonSection, "Синхронизация...", Color.LTGRAY);
    }

    private void addButton(LinearLayout buttonSection, String text, int color) {
        Button button = new Button(this);
        button.setText(text);
        button.setOnClickListener(view -> {
            notificationEvent = new NotificationEvent(color, text);
            updateNotification(findViewById(R.id.notificationLayout));
        });
        buttonSection.addView(button);
    }

    private void updateNotification(LinearLayout notificationLayout) {
        notificationLayout.removeAllViews();
        if (notificationEvent != null) {
            AppCompatImageView icon = new AppCompatImageView(this);
            icon.setImageResource(R.drawable.ic_notification); // Укажите свой ресурс иконки
            icon.setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);

            TextView textView = new TextView(this);
            textView.setText(notificationEvent.text);
            textView.setTextColor(Color.WHITE);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            notificationLayout.setBackgroundColor(notificationEvent.color);
            notificationLayout.setOrientation(LinearLayout.HORIZONTAL);
            notificationLayout.addView(icon);
            notificationLayout.addView(textView);
            notificationLayout.setVisibility(View.VISIBLE);
        } else {
            notificationLayout.setVisibility(View.GONE);
        }
    }

    private static class NotificationEvent {
        int color;
        String text;

        NotificationEvent(int color, String text) {
            this.color = color;
            this.text = text;
        }
    }
}