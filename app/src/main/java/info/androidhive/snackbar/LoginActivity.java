package info.androidhive.snackbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button submit;
    EditText emailEdt,passwordEdt;
    String email,password;
    private ProgressDialog pDialog;
    private static final String LOGIN_URL = "http://dik-pl.com/dikpl/login.php";
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        emailEdt = (EditText) findViewById(R.id.email);
        passwordEdt = (EditText)findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                //startActivity(intent);
                email = emailEdt.getText().toString();
                password = passwordEdt.getText().toString();

                new AttemptLogin().execute();
                //Toast.makeText(LoginActivity.this, ""+email+""+password, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class AttemptLogin extends AsyncTask<String, String, String> {

        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Attempting for login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            int success;
            try
            {
                List<NameValuePair> paramss = new ArrayList<NameValuePair>();
                paramss.add(new BasicNameValuePair("email", email));
                paramss.add(new BasicNameValuePair("password", password));
                Log.d("request!", "starting");
                JSONObject json = jsonParser.makeHttpRequest( LOGIN_URL, "POST", paramss);

                success = json.getInt(TAG_SUCCESS);
                if (success == 1)
                {
                    Log.d("Successfully Login int!", json.toString());
                    Intent ii = new Intent(LoginActivity.this,MainActivity.class);
                    ii.putExtra("mobile",email);
                    finish();
                    startActivity(ii);

                    return json.getString(TAG_MESSAGE);
                }

                else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } return null;
        }

        protected void onPostExecute(String message) {
            pDialog.dismiss();
            if (message != null)
            {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show(); } }
    }
}
