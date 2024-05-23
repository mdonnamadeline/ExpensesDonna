package com.example.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList extends AppCompatActivity {

    private ListView listExpense;

    private TextView txtTotal;
    private List<Expense> expenses;
    private DBhandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenselist);

        listExpense = findViewById(R.id.listExpense);

        dbHandler = new DBhandler(this);
        expenses = dbHandler.getAllExpenses();

        List<String> expenseStrings = new ArrayList<>();
        for (Expense expense : expenses) {
            expenseStrings.add(expense.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseStrings);
        listExpense.setAdapter(adapter);

        listExpense.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expense selectedExpense = expenses.get(position);

                Intent intent = new Intent(ExpenseList.this, MainActivity.class);
                // Assuming Expense has a method getId() that returns a unique identifier for each Expense
                intent.putExtra("ExpenseId", selectedExpense.getId());
                startActivity(intent);
            }
        });

        txtTotal = findViewById(R.id.txtTotal);
        double total = 0;
        for (Expense expense : expenses) {
            total += Double.parseDouble(expense.getAmount());
        }
        txtTotal.setText("Php " + total);

    }
}