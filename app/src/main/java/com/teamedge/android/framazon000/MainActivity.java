package com.teamedge.android.framazon000;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    int numberOfItems = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        CheckBox shippingCheckBox = (CheckBox) findViewById(R.id.shipping);
        boolean hasShipping = shippingCheckBox.isChecked();
        CheckBox yearCheckBox = (CheckBox) findViewById(R.id.year);
        boolean hasYear = yearCheckBox.isChecked();
        CheckBox pantsCheckBox = (CheckBox) findViewById(R.id.pants);
        boolean hasPants = pantsCheckBox.isChecked();
        EditText nameEditText = (EditText) findViewById(R.id.name);

        displayQuantity(numberOfItems);
        int price = calculatePrice(hasShipping, hasYear, hasPants);
        displayPrice(price);



        String orderMessage = "Name: " + nameEditText.getText().toString() + "\n" +
                "Shipping in two days? " + hasShipping + "\n" +
                "One year Guarantee? " + hasYear + "\n" +
                "Want pants? " + hasPants + "\n" +
                "Number of shirts: " + numberOfItems + "\n" +
                "Total: $" + price;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Framazon Order");
        intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("$" + number);

    }

    public void increase(View view) {

        numberOfItems++;
        displayQuantity(numberOfItems);
        displayPrice(numberOfItems * 5);

    }

    public void decrease(View view) {

        if(numberOfItems <= 1) {
            return ;
        }
        numberOfItems--;
        displayQuantity(numberOfItems);
        displayPrice(numberOfItems * 5);

    }

    private int calculatePrice(boolean addShipping, boolean addYear, boolean addPants) {
        int price = numberOfItems * 5;
        if(addShipping == true) {
            price += 5;
        }
        if(addYear == true){
            price += 5;
        }
        if(addPants == true){
            price += 10;
        }
        return price;
    }

}
