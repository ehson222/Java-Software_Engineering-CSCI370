package seclass.qc.edu.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TipCalculatorActivity extends AppCompatActivity {

    EditText checkAmountValue, fifteenPercentTipValue, twentyPercentTipValue, twentyFivePercentTipValue;
    EditText partySizeValue, fifteenPercentTotalTipValue, twentyPercentTotalTipValue, twentyFivePercentTotalTipValue;
    Button buttonCompute;

    double checkAmount, partySize, fifteenPercentTip, twentyPercentTip, twentyFivePercentTip, afterSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
    }
}
