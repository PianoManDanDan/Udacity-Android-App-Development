/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 */


 package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.name;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 1;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Name
        EditText userInput = (EditText) findViewById(R.id.Name_field);
        String name = userInput.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        // Whipped Cream?
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        // Chocolate?
        CheckBox ChocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckbox.isChecked();

        //Calculate Price
        int price = calculatePrice(hasChocolate, hasWhippedCream);
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

//        //Open email browser intent
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order Summary for " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, message);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

        displayMessage(message);

    }

    /**
     * Calculates the price of the order.
     *
     * @return price of coffee
     */
    private int calculatePrice(boolean hasChocolate, boolean hasWhippedCream) {
        int price = 5;
        if (hasChocolate) {
            price += 2;
        }
        if (hasWhippedCream) {
            price += 1;
        }
        return quantity * price;
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        //Display data on app screen
        String result = "Name: " + name;
        result = result + "\nAdd whipped cream? " + addWhippedCream;
        result = result + "\nAdd chocolate? " + addChocolate;
        result = result + "\nQuantity: " + quantity;
        result = result + "\nTotal: £" + price;
        result = result + "\nThank You!";
        return result;
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}