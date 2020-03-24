package com.haanhgs.app.keyboardsend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etPhone)
    EditText etPhone;

    private void startIntent(Intent intent){
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    private void dialPhone(){
        if (!TextUtils.isEmpty(etPhone.getText())){
            String phoneNumber = "tel:" + etPhone.getText().toString();
            Uri uri = Uri.parse(phoneNumber);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startIntent(intent);
        }
    }

    //edittext should add ime option to send
    private void handleEditText(){
        etPhone.setOnEditorActionListener((v, actionId, event) -> {
            boolean result =  false;
            if (actionId == EditorInfo.IME_ACTION_SEND){
                dialPhone();
                result = true;
            }
            return result;
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        handleEditText();
    }
}
