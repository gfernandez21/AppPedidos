package net.gabrielf.apppedidos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;


import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarPass extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener{

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    @Email(message = "Email incorrecto")
    EditText reciep;
    EditText msg;
    String rec, subject, textMessage;
    Validator validator;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    public void onValidationSucceeded() {
        //Toast.makeText(this, "Dato ingresado correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors)
    {
        for (ValidationError error : errors)
        {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }
            else
            {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_pass);

        context = this;

        Button login = (Button) findViewById(R.id.btn_recuperar);
        reciep = (EditText) findViewById(R.id.tv_recupass);
        //sub = (EditText) findViewById(R.id.et_sub);
        msg = (EditText) findViewById(R.id.et_text);

        login.setOnClickListener(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @Override
    public void onClick(View v) {

        EditText d = (EditText)findViewById(R.id.tv_recupass);
        String email = d.getText().toString();
        String emailuser = helper.searchEmail(email);

        if (email.equals("") || email.equals(null)){

            validator.validate();

        }
        else if (!email.matches(emailuser)){

            Toast.makeText(this, "El email ingresado no se encuentra registrado", Toast.LENGTH_SHORT).show();

        }

        else{
            //String mail=null;
            String passForgot = helper.forgotPass(email);
            String sub ="Reenvio de contraseña";
            rec = reciep.getText().toString();
            subject = sub;
            textMessage = passForgot;

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("pedidossinlimite@gmail.com", "pedidossinlimite123");
                }
            });

            pdialog = ProgressDialog.show(context, "", "Enviando correo...", true);

            RetreiveFeedTask task = new RetreiveFeedTask();
            task.execute();

        }

    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            reciep.setText("");
            //msg.setText("");
            //sub.setText("");
            Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_LONG).show();
        }
    }
}
