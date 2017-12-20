package cn.yview.uitest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ProgressBar progressBar;
    public Handler handler;
    public int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.progressbar).setOnClickListener(MainActivity.this);
        findViewById(R.id.alertdialog).setOnClickListener(MainActivity.this);
        findViewById(R.id.progressdialog).setOnClickListener(MainActivity.this);
        progressBar =  findViewById(R.id.Progress);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what)
                {
                    case 1:
                        progress = progressBar.getProgress();
                        progress +=1;
                        progressBar.setProgress(progress);
                        if (progress == 100)
                        {
                            progress = 0;
                            progressBar.setVisibility(View.GONE);
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.progressbar:
                Toast.makeText(this, "progressbar", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while(i < 200)
                        {
                            i ++;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }

                    }
                }).start();
                break;
            case R.id.alertdialog:
                startDisplayDialog();
                Toast.makeText(this, "alertdialog", Toast.LENGTH_SHORT).show();
                break;
            case R.id.progressdialog:
                startDiaplayProgressDialog();
                Toast.makeText(this, "progressdialog", Toast.LENGTH_SHORT).show();
                    break;
        }
    }

    public void startDisplayDialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("This is a Dialog");
        dialog.setMessage("Something important");
        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }

    public  void startDiaplayProgressDialog()
    {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("This is ProgressDialog");
        progressDialog.setMessage("Loading.....");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

}
