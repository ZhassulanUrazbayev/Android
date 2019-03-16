package project.tole.converter;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;


public  class MainActivity extends AppCompatActivity {
    EditText et1;
    TextView tx1,titleToTranslate,titleFromTranslate;
    ImageButton aus;
    String copyText = "";

    ArrayList<String> latin = new ArrayList<>(Arrays.asList(new String[]{"Ю","ю","Я","я","Ә","ә","A","а","Б", "б","В", "в","Д","д","Ғ", "ғ","Г","г","Е", "е","Э","э", "Ж" ,"ж","З", "з","И", "и","Й","й","К", "к"
            , "Қ","қ","Л", "л","М", "м","Ң", "ң","Н", "н","Ө", "ө","О", "о","П", "п","Р", "р","Ш","ш","Ц", "ц","С","с","Т","т","У","у","Ү", "ү","Ұ", "ұ","Ф", "ф","Ч", "ч","Х", "х","Ы" ,"ы","І" ,"і"}));

    ArrayList<String> kiril = new ArrayList<>(Arrays.asList(new String[]{"Iý","ıý", "Ia","ıa","Á","á","A","a","B","b","V", "v","D","d", "Ǵ","ǵ","G", "g","E", "e","E", "e","J", "j","Z", "z","I","ı","I","ı","K", "k"
            ,"Q","q","L", "l","M","m", "Ń","ń","N", "n","Ó","ó","O","o", "P", "p","R","r","Sh","sh","Ts","ts","S","s" ,"T","t","Ý", "ý","Ú","ú","U", "u","F","f","Ch","ch", "H","h", "Y","y","I", "i" }));
    int n=0,m=0;
    boolean bool = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.editTextForConvert);
        tx1=findViewById(R.id.resultConvertText);
        aus=findViewById(R.id.replaceConvertButton);

        titleToTranslate = findViewById(R.id.textTitleToTranslate);
        titleFromTranslate = findViewById(R.id.textTitleFromTranslate);
        if(bool){
            tx1.setText(toTranslate(et1.getText().toString(),true));

        }
        else{
            tx1.setText(toTranslate(et1.getText().toString(),false));
        }

        final ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener( new ClipboardManager.OnPrimaryClipChangedListener() {
            public void onPrimaryClipChanged() {
                copyText = clipboard.getText().toString();
            }
        });




        aus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                n++;
                String[] titles={"Кирилица","Латынша "};
                String val = et1.getText().toString();
                et1.setText(tx1.getText().toString());
                tx1.setText(val);
                bool=false;
                m=0;
                if(n>1){
                    n=0;
                    m=1;
                    bool = true;
                }
                if(bool){
                    tx1.setText(toTranslate(et1.getText().toString(),true));

                }
                if(!bool){
                    tx1.setText(toTranslate(et1.getText().toString(),false));
                }


                titleToTranslate.setText(titles[n]);
                titleFromTranslate.setText(titles[m]);
            }
        });
        et1.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(bool){
                    tx1.setText(toTranslate(et1.getText().toString(),true));

                }
                else{
                    tx1.setText(toTranslate(et1.getText().toString(),false));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
    }

    String toTranslate(String word,boolean bool){
        if(bool){
            for(int i =0;i<latin.size();i++){
                if(word.contains(latin.get(i)+""))
                    word = word.replaceAll(latin.get(i)+"",kiril.get(i));
            }
        }else {
            for(int i =0;i<latin.size();i++) {
                if (word.contains(kiril.get(i) + ""))
                    word = word.replaceAll(kiril.get(i) + "", latin.get(i));
            }
        }



        return word;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.send:
                if(copyText.length()>0){
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,copyText);
                    sendIntent.setType("text/plain");

                    Intent chooser = Intent.createChooser(sendIntent,"Share with friends:");

                    if(sendIntent.resolveActivity(getPackageManager())!=null){
                        startActivity(chooser);
                    }
                    break;
                }
                else{
                    Toast.makeText(this, "Nothing to send", Toast.LENGTH_SHORT).show();
                }

        }

        return super.onOptionsItemSelected(item);
    }
}
