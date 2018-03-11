package seclass.qc.edu.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TipCalculatorActivity extends AppCompatActivity {

    EditText checkAmountValue, fifteenPercentTipValue, twentyPercentTipValue, twentyFivePercentTipValue;
    EditText partySizeValue, fifteenPercentTotalTipValue, twentyPercentTotalTipValue, twentyFivePercentTotalTipValue;
    Button buttonCompute;

    double checkAmount, partySize, fifteenPercentTip, twentyPercentTip, twentyFivePercentTip, afterSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        //get all views
        checkAmountValue = findViewById(R.id.checkAmountValue);
        partySizeValue = findViewById(R.id.partySizeValue);
        buttonCompute = findViewById(R.id.buttonCompute);
        //tip values
        fifteenPercentTipValue = findViewById(R.id.fifteenPercentTipValue);
        twentyPercentTipValue = findViewById(R.id.twentyPercentTipValue);
        twentyFivePercentTipValue = findViewById(R.id.twentyFivePercentTipValue);
        //total values
        fifteenPercentTotalTipValue = findViewById(R.id.fifteenPercentTotalValue);
        twentyPercentTotalTipValue = findViewById(R.id.twentyPercentTotalValue);
        twentyFivePercentTotalTipValue = findViewById(R.id.twentyFivePercenTotalValue);

        buttonCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = checkAmountValue.getText().toString();
                String party = partySizeValue.getText().toString();

                //display toast
                if(amount.length() == 0 || party.length() == 0) {
                    displayToast();
                }else{
                    checkAmount = Integer.parseInt(amount);
                    partySize = Integer.parseInt(party);

                    if(partySize == 0)
                        afterSplit = checkAmount;
                    else
                        afterSplit = (checkAmount / partySize);
                }

                fifteenPercentTipValue.setText(String.valueOf((int) (Math.ceil(afterSplit * 0.15))));
                fifteenPercentTip = Integer.parseInt(fifteenPercentTipValue.getText().toString());

                twentyPercentTipValue.setText(String.valueOf((int) (Math.ceil(afterSplit * 0.20))));
                twentyPercentTip = Integer.parseInt(twentyPercentTipValue.getText().toString());

                twentyFivePercentTipValue.setText(String.valueOf((int) (Math.ceil(afterSplit * 0.25))));
                twentyFivePercentTip = Integer.parseInt(twentyFivePercentTipValue.getText().toString());

                fifteenPercentTotalTipValue.setText(String.valueOf(afterSplit + fifteenPercentTip));
                twentyPercentTotalTipValue.setText(String.valueOf(afterSplit + twentyPercentTip));
                twentyFivePercentTotalTipValue.setText(String.valueOf(afterSplit + twentyFivePercentTip));
            }
        });

        checkAmountValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAmountValue.setText("");
            }
        });

        partySizeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partySizeValue.setText("");
            }
        });
    }

    public void displayToast(){

        Toast.makeText(this, "Incorrect Values!", Toast.LENGTH_LONG).show();

    }
}
