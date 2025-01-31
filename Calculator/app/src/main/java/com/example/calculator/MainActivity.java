package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    Button equalsButton;
    Button clearButton;
    TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        displayText = findViewById(R.id.calculationResult);
        equalsButton = findViewById(R.id.equalsButton);
        clearButton = findViewById(R.id.clearButton);
    }

    public void setClearButton(View view)
    {
        displayText.setText("");
    }

    public void addResultToString(View view)
    {
        if (displayText.getText().toString().equals("MATH ERROR") || displayText.getText().toString().equals("LOGIC ERROR") || displayText.getText().toString().equals("INFINITY")) {
            setClearButton(view);
            addResultToString(view);

            return;
        }

        Button buttonClicked = (Button)view;
        displayText.setText(displayText.getText().toString().concat(buttonClicked.getText().toString()));
    }

    public void calculateResult(View view) {
        Double result;
        DecimalFormat df = new DecimalFormat("#.##########");
        String mathmaticalExpression = displayText.getText().toString();

        try {
            ExpressionBuilder expressionToCalculate = new ExpressionBuilder(mathmaticalExpression);
            Expression expression = expressionToCalculate.build();

            result = expression.evaluate();
            displayText.setText(df.format(result));
        }
        catch (IllegalArgumentException exception) {
            displayText.setText("MATH ERROR");
        }
        catch (ArithmeticException exception) {
            displayText.setText("INFINITY");
        }
        catch (UnsupportedOperationException exception) {
            displayText.setText("LOGIC ERROR");
        }
        catch (Exception e) {
            Log.e("Calculation error", "Can't calulate", e);
        }
    }
}