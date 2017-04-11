package com.mikecoding.navigationbartesting;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UnitListItem extends LinearLayout{

    private TextView left;
    private TextView right;
    private Context context;

    private ItemValue itemValue;
    private double inputUnit;
    private Double toM;
    final private int amountOfTextViews = 2;

    public UnitListItem(Context context, ItemValue itemValue) {
        super(context);
        this.context = context;
        this.itemValue = itemValue;
        init(context);
    }

    public void init(Context context) {

        setOrientation(LinearLayout.HORIZONTAL);
        setClickable(true);
        setFocusable(true);
        setPadding(15, 15, 15, 15);
        for (int j = 0; j < amountOfTextViews; j++) {
            TextView myTextView = new TextView(context);
            myTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            myTextView.setTextColor(Color.parseColor("#000000"));
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            myTextView.setLayoutParams(textParams);
            addView(myTextView);
            if (j == 0) {
                myTextView.setText(itemValue.getUnit());
                textParams.weight = 1;
                left = myTextView;
            } else {
                textParams.gravity = Gravity.RIGHT;
                myTextView.setText(itemValue.getConvertValue().toString());
                right = myTextView;
            }
            LinearLayout.LayoutParams LinearParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            setLayoutParams(LinearParams);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getActionMasked();
        if(action == MotionEvent.ACTION_UP) {
            showDialog(context);
            ((MainActivity) context).clearColour();
            setBackgroundColor(Color.parseColor("#999999"));
        }
        return super.onTouchEvent(event);
    }

    public double showDialog(final Context context){

        //Start building the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_input_unit);
        builder.setMessage(left.getText().toString());
        //text input
        final EditText input = new EditText(context);
        input.setText(right.getText().toString());

        // Specify the type of input expected, here it is numbers and type decimal numbers;
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);
        builder.setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                while(true){
                    try{
                        String m_Text = input.getText().toString();
                        inputUnit = Double.parseDouble(m_Text);
                        Log.d("TAG", "UNIT: " + inputUnit);
                        right.setText(String.valueOf(inputUnit));

                        toM = inputUnit/itemValue.getConvertValue();
                        ((MainActivity) context).startConverting(toM);//TODO make an almost equal clear colour
                        break;
                    }catch(Exception e){
                        Log.d("TAG", "EXCEPTION: " + e);
                        Toast.makeText(context, "Input needs to be a number", Toast.LENGTH_SHORT).show();
                        input.setText(right.getText().toString());
                    }
                }

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        return inputUnit;
    }
    public void convert(Double mto){
        //Log.d("TAG", "IN CONVERT: " + mto.toString());
        Double Result = mto * itemValue.getConvertValue();
        right.setText(Result.toString());
    }

    public void clearBackgroundColour(){
            setBackgroundColor(0);
    }

}