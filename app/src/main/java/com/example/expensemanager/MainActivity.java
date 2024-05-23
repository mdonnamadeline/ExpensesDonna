package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtItem, txtAmount;
    Button btnNew, btnSave, btnExpenses, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtItem = findViewById(R.id.txtItem);
        txtAmount = findViewById(R.id.txtAmount);
        btnNew = findViewById(R.id.btnNew);
        btnSave = findViewById(R.id.btnSave);
        btnExpenses = findViewById(R.id.btnExpenses);
        btnReset = findViewById(R.id.btnReset);

        btnNew.setOnClickListener(v -> {
            txtItem.setText("");
            txtAmount.setText("");
        });

        btnSave.setOnClickListener(v -> {
            String item = txtItem.getText().toString();
            String amount = txtAmount.getText().toString();

            if (item.isEmpty() || amount.isEmpty()) {
                toastMessage("Please fill out all fields");
                return;
            }
            DBhandler dbHandler = new DBhandler(this);

            Expense newExpense = new Expense(item, amount);
            dbHandler.addExpense(newExpense);

            toastMessage("Expense saved successfully");
        });

        btnExpenses.setOnClickListener(v -> {
            startActivity(ExpenseList.class);
        });

        btnReset.setOnClickListener(v -> {
            DBhandler dbHandler = new DBhandler(this);
            dbHandler.resetExpense();
            toastMessage("Expenses reset successfully");
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> cls){
        startActivity(new Intent(this, cls));
    }
}