package mobileapplicationdevelopment.lynx.todo;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvTodos;
    private FloatingActionButton fabAddTodo;
    private DatabaseConnection dc;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            init();
            dc =  new DatabaseConnection(getApplicationContext());
            feedListView();
        }catch (Exception ex){

        }

        fabAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    boolean insert  = dc.addData("88", "AjA");
                    feedListView();

                    if (insert) {
                        toast("Complete");

                    } else {
                        toast("Wrong");
                    }

                }catch (Exception ex){

                }
            }
        });


    }

    private void init(){
        fabAddTodo = (FloatingActionButton)findViewById(R.id.fabAddTodo);
        lvTodos = (ListView) findViewById(R.id.lvTodos);
    }


    private void toast(String ms){
        Toast.makeText(getApplicationContext(), ms, Toast.LENGTH_LONG).show();
    }

    private void feedListView()throws Exception{
        Cursor cur  = dc.readData();
        cur.moveToFirst();
        ArrayList<String> aList = new ArrayList<String>();
        if (cur != null) {
            do {
                String description = cur.getString(1);

                aList.add(description);
            } while (cur.moveToNext());
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aList);
        lvTodos.setAdapter(arrayAdapter);

    }
}
