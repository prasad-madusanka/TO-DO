package mobileapplicationdevelopment.lynx.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView todoListView;
    FloatingActionButton fabAdd;
    DatabaseConnection db;
    RecycleViewAdapter recycleViewAdapter;
    List<GettersSetters> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            init();
            readTodoS();
            Toast.makeText(getApplicationContext(), "Click and Hold to Delete", Toast.LENGTH_LONG).show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateNewToDo.class));
            }
        });

        todoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                try {

                    db.delete(view.getId());
                    list.remove(position);
                    ((BaseAdapter) recycleViewAdapter).notifyDataSetChanged();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                return false;
            }
        });


    }

    private void init() {

        db = new DatabaseConnection(getApplicationContext());

        fabAdd = (FloatingActionButton) findViewById(R.id.fabAddTodo);
        todoListView = (ListView) findViewById(R.id.lvTodos);
    }

    protected void readTodoS() throws Exception {

        list = new ArrayList<>();

        Cursor data = db.read();
        data.moveToFirst();

        if (data != null) {
            do {
                list.add(new GettersSetters(data.getInt(0), data.getString(1), data.getString(2)));
            } while (data.moveToNext());

            recycleViewAdapter = new RecycleViewAdapter(this, list);
            todoListView.setAdapter(recycleViewAdapter);
        }
    }

}
