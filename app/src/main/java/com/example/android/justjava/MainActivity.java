/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
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

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }





    /**
     * This method displays given text on the screen
     */
    public void submitOrder(View view) {

        EditText nameField=(EditText)(findViewById(R.id.name_field));
        String name=nameField.getText().toString();
        //Log.v("MainActivity", "Name: " + name);



        CheckBox whippedCreamCheckBox=(CheckBox)findViewById(R.id.whippedCream_checkbox);
        boolean hasWhippedCream=whippedCreamCheckBox.isChecked();

CheckBox chocolateCheckBox=(CheckBox)findViewById(R.id.chocolate_checkbox);
boolean hasChocolate=chocolateCheckBox.isChecked();

        int price=calculatePrice(hasWhippedCream, hasChocolate);

        String priceMessage= createOrderSummary(price,hasWhippedCream, hasChocolate, name);
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_SUBJECT,"Just Java order for: " + name);
        intent.putExtra(intent.EXTRA_TEXT,priceMessage);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }



      //  displayMessage(priceMessage);
        /**String priceMessage="Total: $" + price;
         priceMessage=priceMessage + "\nThank you!";
         displayMessage(priceMessage);
         **/








    }

    /**
     * This method calculates the price
     * private void CalculatePrice(int quantity, int pricePerCup){
     int price = quantity*pricePerCup;
     }
     */




    public void increment(View view) {
if(quantity==100){
    Toast.makeText(this,"You cannot have more than 100 coffees.", Toast.LENGTH_SHORT).show();

    return;
    //show an error message as a toast


}

        quantity=quantity+1;
        displayQuantity(quantity);
    }



    public void decrement(View view) {
if(quantity==1){
    Toast.makeText(this,"You cannot have less than 1 coffee.", Toast.LENGTH_SHORT).show();
    return;
}
        quantity=quantity-1;
        displayQuantity(quantity);
    }

    /**
     *
     * @param price of teh order, addWhippedCream is whether or not the customer wants whipped cream
     * @return text summary
     */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String addName){
        String priceMessage="Name: " + addName;
priceMessage+="\nAdd Whipped Cream? "+ addWhippedCream;
priceMessage+="\nAdd Chocolate? " + addChocolate;
        priceMessage=priceMessage + "\nQuantity: " + quantity;
        priceMessage=priceMessage + "\nTotal: $" + price;
        priceMessage=priceMessage + "\nThank you!";
        return priceMessage;
    }



    /**
     *@param: addWhippedCream is whether or not a person wants whipped cream
     * @param: addChocolate is whether or not a person wants chocolate
     * @return price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){

        int basePrice=5;

        if(addWhippedCream) {
            basePrice = basePrice+1;
        }


        if(addChocolate){
            basePrice=basePrice+2;
        }

        return  quantity * basePrice;
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given price on the screen.
     */
   /** private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
**/

  // private void displayMessage(String message){
      //  TextView orderSummaryTextView=(TextView)findViewById(R.id.order_summary_text_view);
      //  orderSummaryTextView.setText(message);
   // }




}