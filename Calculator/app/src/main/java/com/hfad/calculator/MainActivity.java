package com.hfad.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

        ArrayList<String> OutputList = new ArrayList<String>();

        Stack<String> OperationStack = new Stack<String>();

        HashMap<String,Integer> price = new HashMap<String,Integer>();


        void putting(){

            price.put("(",0);
		    price.put("+",1);
		    price.put("-",1);
		    price.put("*",2);
		    price.put("/",2);

        }

        String result = "asdfasdf";

        int count = 0;

        boolean FirstClick;
        boolean negative;
        boolean OpenBracket;

        TextView mTextView = (TextView)findViewById(R.id.textView);

        TextView mEditText = (TextView) findViewById(R.id.editText);

        private Button Clear;
        private Button Brackets;
        private Button Symbol;
        private Button Divide;

        private Button seven;
        private Button eight;
        private Button nine;
        private Button Multiply;

        private Button four;
        private Button five;
        private Button six;
        private Button Minus;

        private Button one;
        private Button two;
        private Button three;
        private Button Plus;

        private Button zero;
        private Button dot;
        private Button equal;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Clear = (Button) findViewById(R.id.Clear);
            Clear.setOnClickListener(this);

            Brackets = (Button) findViewById(R.id.Brackets);
            Brackets.setOnClickListener(this);

            Symbol = (Button) findViewById(R.id.Symbol);
            Symbol.setOnClickListener(this);

            Divide = (Button) findViewById(R.id.Devide);
            Divide.setOnClickListener(this);

            seven = (Button) findViewById(R.id.seven);
            seven.setOnClickListener(this);

            eight = (Button) findViewById(R.id.eight);
            eight.setOnClickListener(this);

            nine = (Button) findViewById(R.id.nine);
            nine.setOnClickListener(this);

            Multiply = (Button) findViewById(R.id.Multiply);
            Multiply.setOnClickListener(this);

            four = (Button) findViewById(R.id.four);
            four.setOnClickListener(this);

            five = (Button) findViewById(R.id.five);
            five.setOnClickListener(this);

            six = (Button) findViewById(R.id.six);
            six.setOnClickListener(this);

            Minus = (Button) findViewById(R.id.Minus);
            Minus.setOnClickListener(this);

            one = (Button) findViewById(R.id.one);
            one.setOnClickListener(this);

            two = (Button) findViewById(R.id.two);
            two.setOnClickListener(this);

            three = (Button) findViewById(R.id.three);
            three.setOnClickListener(this);

            Plus = (Button) findViewById(R.id.Plus);
            Plus.setOnClickListener(this);

            zero = (Button) findViewById(R.id.zero);
            zero.setOnClickListener(this);

            equal = (Button) findViewById(R.id.equal);
            equal.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                //Method for clearing all the information
                case R.id.Clear:
                    result="";
                    FirstClick=false;
                    negative=false;
                    OpenBracket=false;
                    mEditText.setText(result);
                    mTextView.setText(result);
                //To open and close the Brackets
                case R.id.Brackets:
                    if(OpenBracket){
                        OperationStack.push("(");
                        calculation(OutputList,true);
                        result+="(";
                        mTextView.setText(result);
                    }else {

                        OpenBracket=true;
                        mTextView.setText(result);
                    }
                //Sign the Symbol Minus or resign
                case R.id.Symbol:
                    if(negative){
                        negative=false;
                        result = result.substring(0,result.length()-2);
                        mTextView.setText(result);
                    }else {
                        negative=true;
                        result+="-";
                        mTextView.setText(result);
                    }
                //Divide operation
                case R.id.Devide:
                    if(FirstClick){
                        FirstClick = false;
                        result+="/";
                        if(price.get("/").equals(price.get(OperationStack.peek()))){

                            OutputList.add(OperationStack.pop());

                            OperationStack.push("/");

                        }else{

                            OperationStack.push("/");

                        }
                        mTextView.setText(result);
                    }else {

                        mEditText.setText(calculation(OutputList,false));
                        mTextView.setText(result);
                    }

                //Sign number 7
                case R.id.seven:
                    OutputList.add("7");
                    result+="7";
                    mEditText.setText(result);
                    mTextView.setText(calculation(OutputList,true));
                //Sign number 8
                case R.id.eight:
                    OutputList.add("8");
                    result+="8";
                    mEditText.setText(result);
                    mTextView.setText(calculation(OutputList,true));
                    mTextView.setText(result);
                //Sign number 9
                case R.id.nine:
                    OutputList.add("9");
                    result+="9";
                    mEditText.setText(result);
                    mTextView.setText(calculation(OutputList,true));
                    mTextView.setText(result);
                //Multiplication operand
                case R.id.Multiply:
                    if(FirstClick){
                        mTextView.setText(result);
                    }else {
                        mTextView.setText(result);
                    }

                //Sign number 4
                case R.id.four:
                    OutputList.add("4");
                    FirstClick = true;
                    result+="4";
                    mEditText.setText(result);
                    mTextView.setText(calculation(OutputList,true));
                //Sign number 5
                case R.id.five:
                    OutputList.add("5");
                    FirstClick = true;
                    result+="5";
                    mEditText.setText(result);
                    mTextView.setText(calculation(OutputList,true));
                //Sign number 6
                case R.id.six:
                    OutputList.add("6");
                    FirstClick = true;
                    result+="6";
                    mEditText.setText(result);
                    mTextView.setText(calculation(OutputList,true));
                //Minus operand
                case R.id.Minus:
                    if(FirstClick){
                        mTextView.setText(result);
                    }else {
                        mTextView.setText(result);
                    }

                //Sign number 1
                case R.id.one:
                    OutputList.add("1");
                    FirstClick = true;
                    result+="1";
                    mEditText.setText(result);
                    mTextView.setText(calculation(OutputList,true));
                //Sign number 2
                case R.id.two:
                    OutputList.add("2");
                    FirstClick = true;
                    result+="2";
                    mEditText.setText(result);
                    mTextView.setText(calculation(OutputList,true));
                //Sign number 3
                case R.id.three:
                    OutputList.add("3");
                    FirstClick = true;
                    result+="3";
                    mEditText.setText(result);
                    mTextView.setText(calculation(OutputList,true));
                //Plus operand
                case R.id.Plus:
                    if(FirstClick){
                        FirstClick = false;
                        result+="+";
                        if(price.get("+") < price.get(OperationStack.peek())){

                            OutputList.add(OperationStack.pop());

                            OperationStack.push("+");

                        }else{

                            OperationStack.push("+");

                        }
                        OperationStack.push("+");
                        calculation(OutputList,true);
                        mTextView.setText(result);
                    }else {
                        calculation(OutputList,false);
                        mTextView.setText(result);
                    }

                //Sign number 0
                case R.id.zero:
                    OutputList.add("0");
                    result+="0";
                    mTextView.setText(result);
                //Giving the result of calculation
                case R.id.equal:
                    if(FirstClick){
                        result="";
                        mEditText.setText(result);
                        mTextView.setText(calculation(OutputList,true));
                    }else {
                        mTextView.setText(result);
                    }


            }
    }
    static String calculation(ArrayList<String> arr,boolean flag){
        return "0";
    }
}


